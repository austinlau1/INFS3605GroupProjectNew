package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ViewMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapView;
    private Marker[] plantMarkers = new Marker[25];
    private Marker[] shadeMarkers = new Marker[9];
    private final int REQUEST_CODE = 10001;
    private FusedLocationProviderClient fusedLocationProviderClient;
    Location currentLocation;
    private LocationCallback locationCallback;
    CheckBox toggleShadeMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        setTitle("Map");

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

        displayPlantMarkers();

        toggleShadeMarker = findViewById(R.id.toggleShadeMarker);
        toggleShadeMarker.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    displayShadeMarkers();
                } else {
                    hideShadeMarkers();
                }
            }
        });


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

    private void displayShadeMarkers() {
        BitmapDescriptor blueMarker = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);

        LatLng shade1 = new LatLng(-33.916135, 151.226598);
        shadeMarkers[0] = mapView.addMarker(new MarkerOptions().position(shade1).title("Shade 1").icon(blueMarker));

        LatLng shade2 = new LatLng(-33.916053, 151.226476);
        shadeMarkers[1] = mapView.addMarker(new MarkerOptions().position(shade2).title("Shade 2").icon(blueMarker));

        LatLng stairs = new LatLng(-33.918099, 151.232028);
        shadeMarkers[2] = mapView.addMarker(new MarkerOptions().position(stairs).title("Stairs").icon(blueMarker));

        LatLng shade4 = new LatLng(-33.916613, 151.234654);
        shadeMarkers[3] = mapView.addMarker(new MarkerOptions().position(shade4).title("Shade 4").icon(blueMarker));

        LatLng shade5 = new LatLng(-33.916573, 151.234597);
        shadeMarkers[4] = mapView.addMarker(new MarkerOptions().position(shade5).title("Shade 5").icon(blueMarker));

        LatLng shade6 = new LatLng(-33.916927, 151.235211);
        shadeMarkers[5] = mapView.addMarker(new MarkerOptions().position(shade6).title("Shade 6").icon(blueMarker));

        LatLng shade7 = new LatLng(-33.916965, 151.235415);
        shadeMarkers[6] = mapView.addMarker(new MarkerOptions().position(shade7).title("Shade 7").icon(blueMarker));

        LatLng shade8 = new LatLng(-33.917973, 151.234417);
        shadeMarkers[7] = mapView.addMarker(new MarkerOptions().position(shade8).title("Shade 8").icon(blueMarker));

        LatLng shade9 = new LatLng(-33.917924, 151.234101);
        shadeMarkers[8] = mapView.addMarker(new MarkerOptions().position(shade9).title("Shade 9").icon(blueMarker));
    }

    private void hideShadeMarkers() {
        for (Marker marker : shadeMarkers) {
            marker.remove();
        }
    }

    private void displayPlantMarkers() {
        // Add a marker for each plant
        LatLng stranglingFig = new LatLng(-33.917263, 151.226437);
        plantMarkers[0] = mapView.addMarker(new MarkerOptions().position(stranglingFig).title("Strangling Fig"));

        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        plantMarkers[1] = mapView.addMarker(new MarkerOptions().position(gymeaLily).title("Gymea Lily"));

        LatLng paperbark = new LatLng(-33.916172, 151.226620);
        plantMarkers[2] = mapView.addMarker(new MarkerOptions().position(paperbark).title("Paperbark"));

        LatLng crimsonBottlebrush = new LatLng(-33.915816, 151.226514);
        plantMarkers[3] = mapView.addMarker(new MarkerOptions().position(crimsonBottlebrush).title("Crimson Bottlebrush"));

        LatLng banksia = new LatLng(-33.915667, 151.226618);
        plantMarkers[4] = mapView.addMarker(new MarkerOptions().position(banksia).title("Banksia"));

        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        plantMarkers[5] = mapView.addMarker(new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle"));

        LatLng nativeMint = new LatLng(-33.915863, 151.226838);
        plantMarkers[6] = mapView.addMarker(new MarkerOptions().position(nativeMint).title("Native Mint"));

        LatLng tuckeroo = new LatLng(-33.916110, 151.228196);
        plantMarkers[7] = mapView.addMarker(new MarkerOptions().position(tuckeroo).title("Tuckeroo"));

        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        plantMarkers[8] = mapView.addMarker(new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly-leaved Tea Tree"));

        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        plantMarkers[9] = mapView.addMarker(new MarkerOptions().position(waterVine).title("Water Vine"));

        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        plantMarkers[10] = mapView.addMarker(new MarkerOptions().position(rockLily).title("Rock Lily"));

        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        plantMarkers[11] = mapView.addMarker(new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig"));

        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        plantMarkers[12] = mapView.addMarker(new MarkerOptions().position(burrawang).title("Burrawang"));

        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        plantMarkers[13] = mapView.addMarker(new MarkerOptions().position(plumPine).title("Plum Pine/Brown Pine"));

        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);
        plantMarkers[14] = mapView.addMarker(new MarkerOptions().position(tussockGrass).title("Tussock Grass"));

        //LatLng cabbageTreePalm = new LatLng(-33.916774, 151.234849);
        //markers[15] = mapView.addMarker(new MarkerOptions().position(cabbageTreePalm).title("Cabbage Tree Palm"));

        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        plantMarkers[16] = mapView.addMarker(new MarkerOptions().position(bolwarra).title("Bolwarra"));

        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        plantMarkers[17] = mapView.addMarker(new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily/Blueberry Lily"));

        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);
        plantMarkers[18] = mapView.addMarker(new MarkerOptions().position(oldManBanksia).title("Saw Banksia/Old Man Banksia"));

        LatLng matrush = new LatLng(-33.917789, 151.232173);
        plantMarkers[19] = mapView.addMarker(new MarkerOptions().position(matrush).title("Spiny-headed Mat-rush"));

        LatLng ribery = new LatLng(-33.917964, 151.232071);
        plantMarkers[20] = mapView.addMarker(new MarkerOptions().position(ribery).title("Riberry"));

        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        plantMarkers[21] = mapView.addMarker(new MarkerOptions().position(grassTree).title("Grass Tree"));

        LatLng nativeGinger = new LatLng(-33.917056, 151.230081);
        plantMarkers[22] = mapView.addMarker(new MarkerOptions().position(nativeGinger).title("Native Ginger"));

        LatLng flameTree = new LatLng(-33.917351, 151.230178);
        plantMarkers[23] = mapView.addMarker(new MarkerOptions().position(flameTree).title("Illawarra Flame Tree"));

        LatLng portJacksonFig = new LatLng(-33.917499, 151.227739);
        plantMarkers[24] = mapView.addMarker(new MarkerOptions().position(portJacksonFig).title("Port Jackson Fig"));
    }

}