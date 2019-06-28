package fit.tele.com.telefit.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
    private Context context;
    private CurrentLocation getLocation;

    public MyLocationListener(Context context, CurrentLocation getLocation) {
        this.context = context;
        this.getLocation = getLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        // Retrieving Latitude
        if(location==null)
            return;

        if (getLocation != null && location != null) {
            try {
                getLocation.currentLocation(location);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(context, "GPS Disabled",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(context, "GPS Enabled",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public interface CurrentLocation {
        void currentLocation(Location location);
    }
}
