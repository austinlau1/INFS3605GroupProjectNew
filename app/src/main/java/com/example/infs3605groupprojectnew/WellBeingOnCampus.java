package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class WellBeingOnCampus extends AppCompatActivity {

    ImageView well_being;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wellbeing_on_campus_page);
        well_being = findViewById(R.id.img_wellb);

        well_being.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wellb_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.wellbeing.unsw.edu.au/healthy-places"));
                startActivity(wellb_source );
            }
        });
    }
    }



