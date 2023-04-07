package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Biodiversity extends AppCompatActivity {
    ImageView bio_img,bio_benefits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biodiversity_page);
        bio_img = findViewById(R.id.img_details_bio);
        bio_benefits = findViewById(R.id.img_bio_benefit);
        bio_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bct.nsw.gov.au/what-biodiversity-and-why-it-important"));
                startActivity(news_source);
            }

        });
        bio_benefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent benefit_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://earth.org/benefits-of-biodiversity/"));
                startActivity(benefit_source);
            }
        });
    }
}

