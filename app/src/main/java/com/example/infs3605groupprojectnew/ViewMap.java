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
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mapView = googleMap;
        // Set the map's properties
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);


        // Set Boundaries for the map
        LatLngBounds UNSW_BOUNDS = new LatLngBounds(
                new LatLng(-33.921597, 151.223430), // South-west corner
                new LatLng(-33.916677, 151.238434) // North-east corner
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

        int padding = 50;

        // Set the boundary on the map
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(UNSW_BOUNDS, 0));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17f));

    }

    private void createMarkers() {
        // Add a marker for each plant
        LatLng stranglingFig = new LatLng(-33.917263, 151.226437);
        markers[0] = mapView.addMarker(new MarkerOptions().position(stranglingFig).title("Strangling Fig"));

        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        markers[1] = mapView.addMarker(new MarkerOptions().position(gymeaLily).title("Gymea Lily"));

        LatLng paperbark = new LatLng(-33.916172, 151.226620);
        markers[2] = mapView.addMarker(new MarkerOptions().position(paperbark).title("Paperbark"));

        LatLng crimsonBottlebrush = new LatLng(-33.915816, 151.226514);
        markers[3] = mapView.addMarker(new MarkerOptions().position(crimsonBottlebrush).title("Crimson Bottlebrush"));

        LatLng banksia = new LatLng(-33.915667, 151.226618);
        markers[4] = mapView.addMarker(new MarkerOptions().position(banksia).title("Banksia"));

        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        markers[5] = mapView.addMarker(new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle"));

        LatLng nativeMint = new LatLng(-33.915863, 151.226838);
        markers[6] = mapView.addMarker(new MarkerOptions().position(nativeMint).title("Native Mint"));

        LatLng tuckeroo = new LatLng(-33.916110, 151.228196);
        markers[7] = mapView.addMarker(new MarkerOptions().position(tuckeroo).title("Tuckeroo"));

        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        markers[8] = mapView.addMarker(new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly-leaved Tea Tree"));

        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        markers[9] = mapView.addMarker(new MarkerOptions().position(waterVine).title("Water Vine"));

        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        markers[10] = mapView.addMarker(new MarkerOptions().position(rockLily).title("Rock Lily"));

        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        markers[11] = mapView.addMarker(new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig"));

        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        markers[12] = mapView.addMarker(new MarkerOptions().position(burrawang).title("Burrawang"));

        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        markers[13] = mapView.addMarker(new MarkerOptions().position(plumPine).title("Plum Pine/Brown Pine"));

        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);
        markers[14] = mapView.addMarker(new MarkerOptions().position(tussockGrass).title("Tussock Grass"));

        //LatLng cabbageTreePalm = new LatLng(-33.916774, 151.234849);
        //markers[15] = mapView.addMarker(new MarkerOptions().position(cabbageTreePalm).title("Cabbage Tree Palm"));

        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        markers[16] = mapView.addMarker(new MarkerOptions().position(bolwarra).title("Bolwarra"));

        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        markers[17] = mapView.addMarker(new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily/Blueberry Lily"));

        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);
        markers[18] = mapView.addMarker(new MarkerOptions().position(oldManBanksia).title("Saw Banksia/Old Man Banksia"));

        LatLng matrush = new LatLng(-33.917789, 151.232173);
        markers[19] = mapView.addMarker(new MarkerOptions().position(matrush).title("Spiny-headed Mat-rush"));

        LatLng ribery = new LatLng(-33.917964, 151.232071);
        markers[20] = mapView.addMarker(new MarkerOptions().position(ribery).title("Riberry"));

        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        markers[21] = mapView.addMarker(new MarkerOptions().position(grassTree).title("Grass Tree"));

        LatLng nativeGinger = new LatLng(-33.917056, 151.230081);
        markers[22] = mapView.addMarker(new MarkerOptions().position(nativeGinger).title("Native Ginger"));

        LatLng flameTree = new LatLng(-33.917351, 151.230178);
        markers[23] = mapView.addMarker(new MarkerOptions().position(flameTree).title("Illawarra Flame Tree"));

        LatLng portJacksonFig = new LatLng(-33.917499, 151.227739);
        markers[24] = mapView.addMarker(new MarkerOptions().position(portJacksonFig).title("Port Jackson Fig"));
    }

}