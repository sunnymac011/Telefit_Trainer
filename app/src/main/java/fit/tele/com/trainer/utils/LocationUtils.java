package fit.tele.com.trainer.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationUtils {
    public static final int RC = 111;

    @SuppressLint({"MissingPermission", "StaticFieldLeak"})
    public static void getLastLocation(final Context context, final LocationListener locationListener) {
        new AsyncTask<Void, Void, Task<Location>>() {
            @Override
            protected Task<Location> doInBackground(Void... voids) {
                FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                return fusedLocationProviderClient.getLastLocation();
            }

            @Override
            protected void onPostExecute(Task<Location> locationTask) {
                super.onPostExecute(locationTask);
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(locationListener != null && location != null)
                            locationListener.onSuccess(location);
                    }
                });
                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if(locationListener != null)
                            locationListener.onFailure(e);
                    }
                });
            }
        }.execute();
    }

    public interface LocationListener {
        void onSuccess(Location location);

        void onFailure(Exception e);
    }
}