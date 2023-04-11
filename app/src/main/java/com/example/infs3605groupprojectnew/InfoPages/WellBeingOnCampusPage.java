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

public class WellBeingOnCampusPage extends AppCompatActivity {

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

        // Pull data from firebase
        DatabaseReference myRef = null;
        myRef = FirebaseDatabase.getInstance().getReference("Info Page/Wellbeing On Campus");
        TextView wellb_detail_2 = findViewById(R.id.wellb_detail_2);
        TextView wellb_detail_3 = findViewById(R.id.wellb_detail_3);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                WellBeingOnCampus wellbeingOnCampus = snapshot.getValue(WellBeingOnCampus.class);
                wellb_detail_2.setText(wellbeingOnCampus.getGreenSpaces());
                wellb_detail_3.setText(wellbeingOnCampus.getUrbanHeat());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}



