package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LinkToResources extends AppCompatActivity {
    ImageView Nura_Gili,IndigiGrow,La_Perouse,Gujaga,resource_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_to_resources_page);
        Nura_Gili = findViewById(R.id.img_nura);
        IndigiGrow = findViewById(R.id.img_indigi);
        La_Perouse = findViewById(R.id.img_laperouse);
        Gujaga = findViewById(R.id.img_gujaga);
        resource_img = findViewById(R.id.img_details_resources);

        Nura_Gili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nura_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indigenous.unsw.edu.au/nura-gili-centre-indigenous-programs"));
                startActivity(nura_source);
            }

        });
        IndigiGrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent indigi_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://indigigrow.com.au"));
                startActivity(indigi_source);
            }

        });
        La_Perouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent La_Perouse_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.laperouse.org.au"));
                startActivity(La_Perouse_source);
            }

        });
        Gujaga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Gujaga_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.gujaga.org.au"));
                startActivity(Gujaga_source);
            }

        });
        resource_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  resource_img_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indigenous.unsw.edu.au/current-students"));
                startActivity(resource_img_source);
            }

        });
    }
}
