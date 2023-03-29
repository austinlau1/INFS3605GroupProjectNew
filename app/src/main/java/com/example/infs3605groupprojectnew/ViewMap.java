package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

public class ViewMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapView;
    private Marker[] markers = new Marker[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_map_view);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        /*mapView = (MapView) findViewById(R.id.newMapView);
        mapView.onCreate(savedInstanceState);*/
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
                new LatLng(-33.918861, 151.228165), // South-west corner
                new LatLng(-33.913424, 151.237360) // North-east corner
        );

        // Add a marker for UNSW Library
        LatLng unswLibrary = new LatLng(-33.916789, 151.228217);
        markers[0] = mapView.addMarker(new MarkerOptions().position(unswLibrary).title("UNSW Library"));

        // Add a marker for UNSW Main Walkway
        LatLng unswMainWalkway = new LatLng(-33.917433, 151.228489);
        markers[1] = mapView.addMarker(new MarkerOptions().position(unswMainWalkway).title("UNSW Main Walkway"));

        // Add a marker for UNSW Roundhouse
        LatLng unswRoundhouse = new LatLng(-33.916191, 151.227012);
        markers[2] = mapView.addMarker(new MarkerOptions().position(unswRoundhouse).title("UNSW Roundhouse"));

        // Add a marker for UNSW Scientia Building
        LatLng unswScientia = new LatLng(-33.917605, 151.231381);
        markers[3] = mapView.addMarker(new MarkerOptions().position(unswScientia).title("UNSW Scientia Building"));

        // Add a marker for UNSW Kensington Colleges
        LatLng unswKensington = new LatLng(-33.917903, 151.230505);
        markers[4] = mapView.addMarker(new MarkerOptions().position(unswKensington).title("UNSW Kensington Colleges"));


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

}