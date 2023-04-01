package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationResult;
import android.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class ViewMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapView;
    private Marker[] markers = new Marker[25];
    private final int REQUEST_CODE = 10001;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private LocationCallback locationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Initialize the FusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        enableUserLocation();

        /*// Initialize the LocationCallback
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    // Update the user's location on the map
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        };*/
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapView = googleMap;
        // Set the map's properties
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            }
        }

        // Set Boundaries for the map
        LatLngBounds UNSW_BOUNDS = new LatLngBounds(
                new LatLng(-33.918861, 151.228165), // South-west corner
                new LatLng(-33.913424, 151.237360) // North-east corner
        );

        createMarkers();

        // Set the camera position and zoom level to a location inside the boundary
        LatLng UNSW_LOCATION = new LatLng(-33.9172, 151.2305);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                UNSW_LOCATION, 17f));


        mapView.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(ViewMap.this, PlantDetails.class);
                intent.putExtra("plant_name", marker.getTitle());
                Log.e("ViewMap", marker.getTitle());
                startActivity(intent);
            }
        });


        // Set the boundary on the map
        googleMap.setLatLngBoundsForCameraTarget(UNSW_BOUNDS);
        googleMap.setMinZoomPreference(15f);
        //mapView.addMarker(new MarkerOptions().position(UNSW_LOCATION).title("UNSW"));

    }


    /*@Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }*/

    /*private void startLocationUpdates() {
        // Check if location permissions are granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Define the location request settings
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(5000); // update location every 5 seconds
        locationRequest.setFastestInterval(3000); // update location at fastest interval of 3 seconds
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Request location updates
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }*/

    private void createMarkers() {
        // Add a marker for each plant
        LatLng hillsFig = new LatLng(-33.917263, 151.226437);
        markers[0] = mapView.addMarker(new MarkerOptions().position(hillsFig).title("Hills Fig"));

        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        markers[1] = mapView.addMarker(new MarkerOptions().position(gymeaLily).title("Gymea Lily"));

        LatLng broadLeavedPaperback = new LatLng(-33.916172, 151.226620);
        markers[2] = mapView.addMarker(new MarkerOptions().position(broadLeavedPaperback).title("Broad Leaved Paperback"));

        LatLng crimsonBottlebush = new LatLng(-33.915816, 151.226514);
        markers[3] = mapView.addMarker(new MarkerOptions().position(crimsonBottlebush).title("Crimson Bottlebush"));

        LatLng healthBanksia = new LatLng(-33.915667, 151.226618);
        markers[4] = mapView.addMarker(new MarkerOptions().position(healthBanksia).title("Health Banksia"));

        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        markers[5] = mapView.addMarker(new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle"));

        LatLng nativeMint = new LatLng(-33.915863, 151.226838);
        markers[6] = mapView.addMarker(new MarkerOptions().position(nativeMint).title("Native Mint"));

        LatLng tuckeroo = new LatLng(-33.916110, 151.228196);
        markers[7] = mapView.addMarker(new MarkerOptions().position(tuckeroo).title("Tuckeroo"));

        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        markers[8] = mapView.addMarker(new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly Leaved Tea Tree"));

        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        markers[9] = mapView.addMarker(new MarkerOptions().position(waterVine).title("Water Vine"));

        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        markers[10] = mapView.addMarker(new MarkerOptions().position(rockLily).title("Rock Lily"));

        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        markers[11] = mapView.addMarker(new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig"));

        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        markers[12] = mapView.addMarker(new MarkerOptions().position(burrawang).title("Burrawang"));

        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        markers[13] = mapView.addMarker(new MarkerOptions().position(plumPine).title("Plum Pine"));

        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);
        markers[14] = mapView.addMarker(new MarkerOptions().position(tussockGrass).title("Tussock Grass"));

        LatLng cabbageTreePalm = new LatLng(-33.916774, 151.234849);
        markers[15] = mapView.addMarker(new MarkerOptions().position(cabbageTreePalm).title("Cabbage Tree Palm"));

        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        markers[16] = mapView.addMarker(new MarkerOptions().position(bolwarra).title("Bolwarra"));

        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        markers[17] = mapView.addMarker(new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily"));

        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);
        markers[18] = mapView.addMarker(new MarkerOptions().position(oldManBanksia).title("Old man banksia"));

        LatLng matrush = new LatLng(-33.917789, 151.232173);
        markers[19] = mapView.addMarker(new MarkerOptions().position(matrush).title("Matrush"));

        LatLng ribery = new LatLng(-33.917964, 151.232071);
        markers[20] = mapView.addMarker(new MarkerOptions().position(ribery).title("Ribery"));

        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        markers[21] = mapView.addMarker(new MarkerOptions().position(grassTree).title("Grass Tree"));

        LatLng nativeGinger = new LatLng(-33.917056, 151.230081);
        markers[22] = mapView.addMarker(new MarkerOptions().position(nativeGinger).title("Native Ginger"));

        LatLng flameTree = new LatLng(-33.917351, 151.230178);
        markers[23] = mapView.addMarker(new MarkerOptions().position(flameTree).title("Flame Tree"));

        LatLng portJacksonFig = new LatLng(-33.917499, 151.227739);
        markers[24] = mapView.addMarker(new MarkerOptions().position(portJacksonFig).title("Port Jackson Fig"));
    }

    private void enableUserLocation() {
        // Check if location permissions are granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    currentLocation = location;
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(ViewMap.this);
                }
            }
        });
        

        /*// Enable the user's location on the map
        mapView.setMyLocationEnabled(true);

        // Request the user's last known location
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    // Move the camera to the user's location
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                }
            }
        });*/
    }

    /*private void zoomToUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17));
            }
        });

    }*/

    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
            } else {

            }
        }
    }
*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (REQUEST_CODE) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    enableUserLocation();
                }
                break;
        }
    }
}