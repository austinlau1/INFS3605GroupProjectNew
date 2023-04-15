package com.example.infs3605groupprojectnew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;

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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class ViewMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapView;
    private Marker[] plantMarkers = new Marker[25];
    private Marker[] shadeMarkers = new Marker[9];
    private final int REQUEST_CODE = 10001;
    CheckBox toggleShadeMarker;

    private List<Marker> routeOneMarkers = new ArrayList<>();
    private List<Marker> routeTwoMarkers = new ArrayList<>();
    private List<Marker> routeThreeMarkers = new ArrayList<>();
    private List<Marker> routeFourMarkers = new ArrayList<>();
    private List<Marker> routeFiveMarkers = new ArrayList<>();
    private List<Marker> routeSixMarkers = new ArrayList<>();
    private List<Marker> routeSevenMarkers = new ArrayList<>();
    private List<Marker> wholeRouteMarkers = new ArrayList<>();
    private List<Polyline> routeOnePolylines = new ArrayList<>();
    private List<Polyline> routeTwoPolylines = new ArrayList<>();
    private List<Polyline> routeThreePolylines = new ArrayList<>();
    private List<Polyline> routeFourPolylines = new ArrayList<>();
    private List<Polyline> routeFivePolylines = new ArrayList<>();
    private List<Polyline> routeSixPolylines = new ArrayList<>();
    private List<Polyline> routeSevenPolylines = new ArrayList<>();
    private List<Polyline> routeEightPolylines = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        setTitle("Map");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Handle the back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ViewMap.this, MenuPage.class);
                intent.putExtra("ToNavigation", "MapFragment");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
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




        Spinner routeSpinner = findViewById(R.id.routeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.route_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        routeSpinner.setAdapter(adapter);

        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mapView.clear();
                        displayPlantMarkers();
                        break;
                    case 1:
                        mapView.clear();
                        displayRouteOne();
                        break;
                    case 2:
                        mapView.clear();
                        displayRouteTwo();
                        break;
                    case 3:
                        mapView.clear();
                        displayRouteThree();
                        break;
                    case 4:
                        mapView.clear();
                        displayRouteFour();
                        break;
                    case 5:
                        mapView.clear();
                        displayRouteFive();
                        break;
                    case 6:
                        mapView.clear();
                        displayRouteSix();
                        break;
                    case 7:
                        mapView.clear();
                        displayRouteSeven();
                        break;
                    case 8:
                        mapView.clear();
                        displayWholeRoute();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //displayPlantMarkers();
            }
        });

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

        // Set the boundary on the map
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(UNSW_BOUNDS, 0));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17f));

    }

    private void displayRouteOne() {
        // Add your markers to the map and store them in the markers list
        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        LatLng paperbark = new LatLng(-33.916172, 151.226620);
        LatLng crimsonBottlebrush = new LatLng(-33.915816, 151.226514);
        LatLng banksia = new LatLng(-33.915667, 151.226618);
        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        LatLng nativeMint = new LatLng(-33.915863, 151.226838);

        MarkerOptions marker1 = new MarkerOptions().position(gymeaLily).title("Gymea Lily");
        routeOneMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(paperbark).title("Paperbark");
        routeOneMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(crimsonBottlebrush).title("Crimson Bottlebrush");
        routeOneMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(banksia).title("Banksia");
        routeOneMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle");
        routeOneMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(nativeMint).title("Native Mint");
        routeOneMarkers.add(mapView.addMarker(marker6));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeOneMarkers.size(); i++) {
            if (i == (routeOneMarkers.size() - 1)) {
                LatLng from = routeOneMarkers.get(i).getPosition();
                LatLng to = routeOneMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeOnePolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeOneMarkers.get(i).getPosition();
                LatLng to = routeOneMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeOnePolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteTwo() {
        // Add your markers to the map and store them in the markers list
        LatLng stranglingFig = new LatLng(-33.917263, 151.226437);
        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        LatLng paperbark = new LatLng(-33.916172, 151.226620);
        LatLng crimsonBottlebrush = new LatLng(-33.915816, 151.226514);
        LatLng banksia = new LatLng(-33.915667, 151.226618);
        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        LatLng nativeMint = new LatLng(-33.915863, 151.226838);
        LatLng tuckeroo = new LatLng(-33.916110, 151.228196);
        plantMarkers[7] = mapView.addMarker(new MarkerOptions().position(tuckeroo).title("Tuckeroo"));
        LatLng portJacksonFig = new LatLng(-33.917499, 151.227739);

        MarkerOptions marker1 = new MarkerOptions().position(stranglingFig).title("Strangling Fig");
        routeTwoMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(gymeaLily).title("Gymea Lily");
        routeTwoMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(paperbark).title("Paperbark");
        routeTwoMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(crimsonBottlebrush).title("Crimson Bottlebrush");
        routeTwoMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(banksia).title("Banksia");
        routeTwoMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle");
        routeTwoMarkers.add(mapView.addMarker(marker6));
        MarkerOptions marker7 = new MarkerOptions().position(nativeMint).title("Native Mint");
        routeTwoMarkers.add(mapView.addMarker(marker7));
        MarkerOptions marker8 = new MarkerOptions().position(tuckeroo).title("Tuckeroo");
        routeTwoMarkers.add(mapView.addMarker(marker8));
        MarkerOptions marker9 = new MarkerOptions().position(portJacksonFig).title("Port Jackson Fig");
        routeTwoMarkers.add(mapView.addMarker(marker9));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeTwoMarkers.size(); i++) {
            if (i == (routeTwoMarkers.size() - 1)) {
                LatLng from = routeTwoMarkers.get(i).getPosition();
                LatLng to = routeTwoMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeTwoPolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeTwoMarkers.get(i).getPosition();
                LatLng to = routeTwoMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeTwoPolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteThree() {
        // Add your markers to the map and store them in the markers list
        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        LatLng ribery = new LatLng(-33.917964, 151.232071);
        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        LatLng matrush = new LatLng(-33.917789, 151.232173);

        MarkerOptions marker1 = new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly-leaved Tea Tree");
        routeThreeMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(waterVine).title("Water Vine");
        routeThreeMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(rockLily).title("Rock Lily");
        routeThreeMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig");
        routeThreeMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(ribery).title("Riberry");
        routeThreeMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(grassTree).title("Grass Tree");
        routeThreeMarkers.add(mapView.addMarker(marker6));
        MarkerOptions marker7 = new MarkerOptions().position(matrush).title("Spiny-headed Mat-rush");
        routeThreeMarkers.add(mapView.addMarker(marker7));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeThreeMarkers.size(); i++) {
            if (i == (routeThreeMarkers.size() - 1)) {
                LatLng from = routeThreeMarkers.get(i).getPosition();
                LatLng to = routeThreeMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeThreePolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeThreeMarkers.get(i).getPosition();
                LatLng to = routeThreeMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeThreePolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteFour() {
        // Add your markers to the map and store them in the markers list
        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        LatLng ribery = new LatLng(-33.917964, 151.232071);
        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        LatLng matrush = new LatLng(-33.917789, 151.232173);
        LatLng flameTree = new LatLng(-33.917351, 151.230178);
        LatLng nativeGinger = new LatLng(-33.917056, 151.230081);

        MarkerOptions marker1 = new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly-leaved Tea-tree");
        routeFourMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(waterVine).title("Water Vine");
        routeFourMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(rockLily).title("Rock Lily");
        routeFourMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig");
        routeFourMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(ribery).title("Riberry");
        routeFourMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(grassTree).title("Grass Tree");
        routeFourMarkers.add(mapView.addMarker(marker6));
        MarkerOptions marker7 = new MarkerOptions().position(matrush).title("Mat-rush");
        routeFourMarkers.add(mapView.addMarker(marker7));
        MarkerOptions marker8 = new MarkerOptions().position(flameTree).title("Flame Tree");
        routeFourMarkers.add(mapView.addMarker(marker8));
        MarkerOptions marker9 = new MarkerOptions().position(nativeGinger).title("Native Ginger");
        routeFourMarkers.add(mapView.addMarker(marker9));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeFourMarkers.size(); i++) {
            if (i == (routeFourMarkers.size() - 1)) {
                LatLng from = routeFourMarkers.get(i).getPosition();
                LatLng to = routeFourMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeFourPolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeFourMarkers.get(i).getPosition();
                LatLng to = routeFourMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeFourPolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteFive() {
        // Add your markers to the map and store them in the markers list
        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);

        MarkerOptions marker1 = new MarkerOptions().position(burrawang).title("Burrawang");
        routeFiveMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(plumPine).title("Plum Pine/Brown Pine");
        routeFiveMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(tussockGrass).title("Tussock Grass");
        routeFiveMarkers.add(mapView.addMarker(marker3));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeFiveMarkers.size(); i++) {
            if (i == (routeFiveMarkers.size() - 1)) {
                LatLng from = routeFiveMarkers.get(i).getPosition();
                LatLng to = routeFiveMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeFivePolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeFiveMarkers.get(i).getPosition();
                LatLng to = routeFiveMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeFivePolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteSix() {
        // Add your markers to the map and store them in the markers list
        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);

        MarkerOptions marker1 = new MarkerOptions().position(bolwarra).title("Bolwarra");
        routeSixMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily/Blueberry Lily");
        routeSixMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(oldManBanksia).title("Saw Banksia/Old Man Banksia");
        routeSixMarkers.add(mapView.addMarker(marker3));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeSixMarkers.size(); i++) {
            if (i == (routeSixMarkers.size() - 1)) {
                LatLng from = routeSixMarkers.get(i).getPosition();
                LatLng to = routeSixMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeSixPolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeSixMarkers.get(i).getPosition();
                LatLng to = routeSixMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeSixPolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayRouteSeven() {
        // Add your markers to the map and store them in the markers list
        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);
        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);

        MarkerOptions marker1 = new MarkerOptions().position(burrawang).title("Burrawang");
        routeSevenMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(plumPine).title("Plum Pine/Brown Pine");
        routeSevenMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(tussockGrass).title("Tussock Grass");
        routeSevenMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(bolwarra).title("Bolwarra");
        routeSevenMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily");
        routeSevenMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(oldManBanksia).title("Old Man Banksia");
        routeSevenMarkers.add(mapView.addMarker(marker6));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < routeSevenMarkers.size(); i++) {
            if (i == (routeSevenMarkers.size() - 1)) {
                LatLng from = routeSevenMarkers.get(i).getPosition();
                LatLng to = routeSevenMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeSevenPolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = routeSevenMarkers.get(i).getPosition();
                LatLng to = routeSevenMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeSevenPolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
    }

    private void displayWholeRoute() {
        // Add your markers to the map and store them in the markers list
        LatLng stranglingFig = new LatLng(-33.917263, 151.226437);
        LatLng gymeaLily = new LatLng(-33.916721, 151.226312);
        LatLng paperbark = new LatLng(-33.916172, 151.226620);
        LatLng crimsonBottlebrush = new LatLng(-33.915816, 151.226514);
        LatLng banksia = new LatLng(-33.915667, 151.226618);
        LatLng mountainCedarWattle = new LatLng(-33.915686, 151.226898);
        LatLng nativeMint = new LatLng(-33.915863, 151.226838);
        LatLng tuckeroo = new LatLng(-33.916110, 151.228196);
        LatLng pricklyLeavedTeaTree = new LatLng(-33.917091, 151.232192);
        LatLng waterVine = new LatLng(-33.917207, 151.232165);
        LatLng rockLily = new LatLng(-33.917308, 151.232144);
        LatLng sandpaperFig = new LatLng(-33.917407, 151.232119);
        LatLng burrawang = new LatLng(-33.916716, 151.234349);
        LatLng plumPine = new LatLng(-33.916251, 151.234503);
        LatLng tussockGrass = new LatLng(-33.916503, 151.234676);
        LatLng bolwarra = new LatLng(-33.918027, 151.234931);
        LatLng blueFlaxLily = new LatLng(-33.917972, 151.234534);
        LatLng oldManBanksia = new LatLng(-33.917945, 151.234238);
        LatLng matrush = new LatLng(-33.917789, 151.232173);
        LatLng ribery = new LatLng(-33.917964, 151.232071);
        LatLng grassTree = new LatLng(-33.918203, 151.232105);
        LatLng nativeGinger = new LatLng(-33.917056, 151.230081);
        LatLng flameTree = new LatLng(-33.917351, 151.230178);
        LatLng portJacksonFig = new LatLng(-33.917499, 151.227739);

        MarkerOptions marker1 = new MarkerOptions().position(stranglingFig).title("Strangling Fig");
        wholeRouteMarkers.add(mapView.addMarker(marker1));
        MarkerOptions marker2 = new MarkerOptions().position(gymeaLily).title("Gymea Lily");
        wholeRouteMarkers.add(mapView.addMarker(marker2));
        MarkerOptions marker3 = new MarkerOptions().position(paperbark).title("Paperbark");
        wholeRouteMarkers.add(mapView.addMarker(marker3));
        MarkerOptions marker4 = new MarkerOptions().position(crimsonBottlebrush).title("Crimson Bottlebrush");
        wholeRouteMarkers.add(mapView.addMarker(marker4));
        MarkerOptions marker5 = new MarkerOptions().position(banksia).title("Banksia");
        wholeRouteMarkers.add(mapView.addMarker(marker5));
        MarkerOptions marker6 = new MarkerOptions().position(mountainCedarWattle).title("Mountain Cedar Wattle");
        wholeRouteMarkers.add(mapView.addMarker(marker6));
        MarkerOptions marker7 = new MarkerOptions().position(nativeMint).title("Native Mint");
        wholeRouteMarkers.add(mapView.addMarker(marker7));
        MarkerOptions marker8 = new MarkerOptions().position(tuckeroo).title("Tuckeroo");
        wholeRouteMarkers.add(mapView.addMarker(marker8));
        MarkerOptions marker9 = new MarkerOptions().position(pricklyLeavedTeaTree).title("Prickly Leaved Tea Tree");
        wholeRouteMarkers.add(mapView.addMarker(marker9));
        MarkerOptions marker10 = new MarkerOptions().position(waterVine).title("Water Vine");
        wholeRouteMarkers.add(mapView.addMarker(marker10));
        MarkerOptions marker11 = new MarkerOptions().position(rockLily).title("Rock Lily");
        wholeRouteMarkers.add(mapView.addMarker(marker11));
        MarkerOptions marker12 = new MarkerOptions().position(sandpaperFig).title("Sandpaper Fig");
        wholeRouteMarkers.add(mapView.addMarker(marker12));
        MarkerOptions marker13 = new MarkerOptions().position(burrawang).title("Burrawang");
        wholeRouteMarkers.add(mapView.addMarker(marker13));
        MarkerOptions marker14 = new MarkerOptions().position(plumPine).title("Plum Pine");
        wholeRouteMarkers.add(mapView.addMarker(marker14));
        MarkerOptions marker15 = new MarkerOptions().position(tussockGrass).title("Tussock Grass");
        wholeRouteMarkers.add(mapView.addMarker(marker15));
        MarkerOptions marker16 = new MarkerOptions().position(bolwarra).title("Bolwarra");
        wholeRouteMarkers.add(mapView.addMarker(marker16));
        MarkerOptions marker17 = new MarkerOptions().position(blueFlaxLily).title("Blue Flax Lily");
        wholeRouteMarkers.add(mapView.addMarker(marker17));
        MarkerOptions marker18 = new MarkerOptions().position(oldManBanksia).title("Old Man Banksia");
        wholeRouteMarkers.add(mapView.addMarker(marker18));
        MarkerOptions marker19 = new MarkerOptions().position(matrush).title("Matrush");
        wholeRouteMarkers.add(mapView.addMarker(marker19));
        MarkerOptions marker20 = new MarkerOptions().position(ribery).title("Ribery");
        wholeRouteMarkers.add(mapView.addMarker(marker20));
        MarkerOptions marker21 = new MarkerOptions().position(grassTree).title("Grass Tree");
        wholeRouteMarkers.add(mapView.addMarker(marker21));
        MarkerOptions marker22 = new MarkerOptions().position(nativeGinger).title("Native Ginger");
        wholeRouteMarkers.add(mapView.addMarker(marker22));
        MarkerOptions marker23 = new MarkerOptions().position(flameTree).title("Flame Tree");
        wholeRouteMarkers.add(mapView.addMarker(marker23));
        MarkerOptions marker24 = new MarkerOptions().position(portJacksonFig).title("Port Jackson Fig");
        wholeRouteMarkers.add(mapView.addMarker(marker24));
        PolylineOptions polylineOptions1;

        // Add polylines between adjacent markers and store them in the polylines list
        for (int i = 0; i < wholeRouteMarkers.size(); i++) {
            if (i == (wholeRouteMarkers.size() - 1)) {
                LatLng from = wholeRouteMarkers.get(i).getPosition();
                LatLng to = wholeRouteMarkers.get(0).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeEightPolylines.add(mapView.addPolyline(polylineOptions1));
            } else {
                LatLng from = wholeRouteMarkers.get(i).getPosition();
                LatLng to = wholeRouteMarkers.get(i + 1).getPosition();
                Log.d("ViewMap", Integer.toString(i));
                polylineOptions1 = new PolylineOptions().add(from, to).color(Color.BLUE).width(5);
                routeEightPolylines.add(mapView.addPolyline(polylineOptions1));
            }
        }
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

        // Plant that was removed:
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