package com.example.infs3605groupprojectnew.InfoPages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infs3605groupprojectnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BiodiversityPage extends AppCompatActivity {
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

        // Pull data from firebase
        DatabaseReference myRef = null;
        myRef = FirebaseDatabase.getInstance().getReference("Info Page/Biodiversity");
        TextView content_detail_1 = findViewById(R.id.content_detail_1);
        TextView content_detail_2 = findViewById(R.id.content_detail_2);
        TextView content_detail_3 = findViewById(R.id.content_detail_3);
        TextView content_detail_4 = findViewById(R.id.content_detail_4);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Biodiversity biodiversity = snapshot.getValue(Biodiversity.class);
                content_detail_1.setText(biodiversity.getDesc());
                content_detail_2.setText(biodiversity.getImportance());
                content_detail_3.setText(biodiversity.getBenefits());
                content_detail_4.setText(biodiversity.getValues());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

