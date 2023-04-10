package com.example.infs3605groupprojectnew;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuPage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    InfoFragment infoFragment = new InfoFragment();
    CameraFragment cameraFragment = new CameraFragment();
    MapFragment mapFragment = new MapFragment();
    PlantFragment plantFragment = new PlantFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //bottomNavigationView.setSelectedItemId(R.id.home);
        pushFragment(new InfoFragment());

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        Intent intent = getIntent();
        String message = intent.getStringExtra("ToNavigation");
        if(!intent.hasExtra("ToNavigation")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        } else {
            if (message.equals("CameraFragment")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, cameraFragment).commit();
            } else if (message.equals("MapFragment")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
            } else if (message.equals("PlantFragment")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, plantFragment).commit();
            } else if (message.equals("InfoFragment")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, infoFragment).commit();
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
            }
        }



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, infoFragment).commit();
                        return true;
                    case R.id.camera:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, cameraFragment).commit();
                        return true;
                    case R.id.map:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
                        return true;
                    case R.id.plants:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, plantFragment).commit();
                        return true;
                }

                return false;
            }
        });
    }

    private void pushFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        transaction.replace(R.id.container, fragment).addToBackStack("Info Fragment").commit();
        //transaction.commit();
    }

}