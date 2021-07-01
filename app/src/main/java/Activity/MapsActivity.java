package Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.annasutha.student_app.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "";
    final String ACCESS_FINE_LOCATION_PERMISSION = "android.permission.ACCESS_FINE_LOCATION";
    int ACCESS_FINE_LOCATION = 0;
    private GoogleMap mMap;
    //private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        askAllPermissions();

        if (mMap == null) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                    .show();
        }
        Location myLocation = mMap.getMyLocation();  //Nullpointer exception.........
        LatLng myLatLng = new LatLng(myLocation.getLatitude(),
                myLocation.getLongitude());

        CameraPosition myPosition = new CameraPosition.Builder()
                .target(myLatLng).zoom(17).bearing(90).tilt(30).build();
        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(myPosition));
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        /*mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location arg0) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(arg0.getLatitude(), arg0.getLongitude())).title("It's Me!"));
            }
        });*/
        //updateLocationUI();
        //getDeviceLocation();

    }
   /* private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                //lastKnownLocation = null;
                askAllPermissions();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }*/
  /* private void getDeviceLocation() {
       *//*
        * Get the best and most recent location of the device, which may be null in rare
        * cases when a location is not available.
        *//*
       try {
           if (locationPermissionGranted) {
               Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
               locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                   @Override
                   public void onComplete(@NonNull Task<Location> task) {
                       if (task.isSuccessful()) {
                           // Set the map's camera position to the current location of the device.
                           lastKnownLocation = task.getResult();
                           if (lastKnownLocation != null) {
                               map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                       new LatLng(lastKnownLocation.getLatitude(),
                                               lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                           }
                       } else {
                           Log.d(TAG, "Current location is null. Using defaults.");
                           Log.e(TAG, "Exception: %s", task.getException());
                           mMap.moveCamera(CameraUpdateFactory
                                   .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                           mMap.getUiSettings().setMyLocationButtonEnabled(false);
                       }
                   }
               });
           }
       } catch (SecurityException e)  {
           Log.e("Exception: %s", e.getMessage(), e);
       }
   }*/

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void askAllPermissions() {
        askForPermission(ACCESS_FINE_LOCATION_PERMISSION, ACCESS_FINE_LOCATION);
    }

    private void askForPermission(String permission, Integer requestCode) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(MapsActivity.this, new String[]{permission}, requestCode);
            }
        } else {
            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }
}