package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WellBeingOnCampus extends AppCompatActivity {

    Button navigateToWellBSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellbeing_on_campus_page);
        Button navigateToWellBSource = findViewById(R.id.toWellBSourceBtn);

        navigateToWellBSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wellbeing.unsw.edu.au/healthy-places"));
                startActivity(google_search);
            }
        });
    }
    }



