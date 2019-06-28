package fit.tele.com.telefit.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Surface;
import android.view.WindowManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class LocationService extends Service {
    private LocationChangeListener gpsLocationListener = new LocationChangeListener();
    private LocationChangeListener networkLocationListener = new LocationChangeListener();
    private SensorListener sensorEventListener = new SensorListener();
    //private LocalBinder localBinder = new LocalBinder();

    private int TWO_MINUTES = 1000 * 60 * 2;
    private float MIN_BEARING_DIFF = 2.0f;
    private Long FASTEST_INTERVAL_IN_MS = 1000L;

    private float bearing = 0f;
    private int axisX = 0;
    private int axisY = 0;
    private Location currentBestLocation;
    private LocationCallback locationCallback;

    private LocationManager locationManager;
    private SensorManager sensorManager;
    private WindowManager windowManager;
    private String CHAT_REFERENCE = "";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        getLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopLocation();
        if (sensorManager != null)
            sensorManager.unregisterListener(sensorEventListener);
    }

    @SuppressLint("StaticFieldLeak")
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location lastKnownGpsLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location lastKnownNetworkLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location bestLastKnownLocation = currentBestLocation;

            if (lastKnownGpsLocation != null && isBetterLocation(lastKnownGpsLocation, bestLastKnownLocation)) {
                bestLastKnownLocation = lastKnownGpsLocation;
            }

            if (lastKnownNetworkLocation != null && isBetterLocation(lastKnownNetworkLocation, bestLastKnownLocation)) {
                bestLastKnownLocation = lastKnownNetworkLocation;
            }

            currentBestLocation = bestLastKnownLocation;

            if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, FASTEST_INTERVAL_IN_MS, 0.0f, gpsLocationListener);
            }

            if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, FASTEST_INTERVAL_IN_MS, 0.0f, networkLocationListener);
            }

            if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                new AsyncTask<Void, Void, Task<Location>>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    protected Task<Location> doInBackground(Void... voids) {
                        FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(LocationService.this);
                        return fusedLocationProviderClient.getLastLocation();
                    }

                    @Override
                    protected void onPostExecute(Task<Location> locationTask) {
                        super.onPostExecute(locationTask);
                        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location == null)
                                    return;
                                if (isBetterLocation(location, currentBestLocation)) {
                                    currentBestLocation = location;
                                    currentBestLocation.setBearing(bearing);
                                }
                            }
                        });
                    }
                }.execute();
            }
            if (bestLastKnownLocation != null)
                bestLastKnownLocation.setBearing(bearing);

            Sensor mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
            sensorManager.registerListener(sensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL * 5);
        }
    }

    private void stopLocation() {
        locationManager.removeUpdates(gpsLocationListener);
        locationManager.removeUpdates(networkLocationListener);
        sensorManager.unregisterListener(sensorEventListener);
    }

    public void setLocationCallback(LocationCallback locationCallback) {
        this.locationCallback = locationCallback;
    }

    private boolean isBetterLocation(Location location, Location currentBestLocation) {
        if (currentBestLocation == null)
            return true;

        long timeDelta = location.getTime() - currentBestLocation.getTime();
        boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
        boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
        boolean isNewer = timeDelta > 0;

        if (isSignificantlyNewer)
            return true;
        else if (isSignificantlyOlder)
            return false;

        int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDelta > 0;
        boolean isMoreAccurate = accuracyDelta < 0;
        boolean isSignificantlyLessAccurate = accuracyDelta > 200;
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        if (isMoreAccurate) {
            return true;
        } else if (isNewer && !isLessAccurate) {
            return true;
        } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
            return true;
        }
        return false;
    }

    private boolean isSameProvider(String provider1, String provider2) {
        if (provider1 == null || provider2 == null) {
            return false;
        } else if (provider1.equals(provider2))
            return true;
        else
            return false;
    }

    private void readDisplayRotation() {
        axisX = SensorManager.AXIS_X;
        axisY = SensorManager.AXIS_Y;
        if (windowManager.getDefaultDisplay().getRotation() == Surface.ROTATION_90) {
            axisX = SensorManager.AXIS_Y;
            axisY = SensorManager.AXIS_MINUS_X;
        } else if (windowManager.getDefaultDisplay().getRotation() == Surface.ROTATION_180) {
            axisY = SensorManager.AXIS_MINUS_Y;
        } else if (windowManager.getDefaultDisplay().getRotation() == Surface.ROTATION_270) {
            axisX = SensorManager.AXIS_MINUS_Y;
            axisY = SensorManager.AXIS_X;
        }
    }

    interface LocationCallback {
        void handleNewLocation(Location location);
    }

    class LocationChangeListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location == null)
                return;
            if (isBetterLocation(location, currentBestLocation)) {
                currentBestLocation = location;
                currentBestLocation.setBearing(bearing);
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
        }

        @Override
        public void onProviderEnabled(String s) {
        }

        @Override
        public void onProviderDisabled(String s) {
        }
    }

    class SensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float[] rotationMatrix = new float[16];
            SensorManager.getRotationMatrixFromVector(rotationMatrix, sensorEvent.values);

            float[] orientationValues = new float[3];
            readDisplayRotation();

            SensorManager.remapCoordinateSystem(rotationMatrix, axisX, axisY, rotationMatrix);
            SensorManager.getOrientation(rotationMatrix, orientationValues);

            boolean abs = Math.abs(bearing - Math.toDegrees(orientationValues[0])) > MIN_BEARING_DIFF;
            if (abs) {
                bearing = (float) Math.toDegrees(orientationValues[0]);
                if(currentBestLocation != null)
                    currentBestLocation.setBearing(bearing);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }

    class LocalBinder extends Binder {
        private LocationService service = new LocationService();
    }

    class CusLocation {
        private double latitude;
        private double longitude;

        public CusLocation(double latitude, double longitude) {
            setLatitude(latitude);
            setLongitude(longitude);
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }
    }
}
