package com.example.infs3605groupprojectnew.InfoPages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infs3605groupprojectnew.FAQ;
import com.example.infs3605groupprojectnew.InfoPages.Biodiversity;
import com.example.infs3605groupprojectnew.InfoPages.HistoryOfUNSWLand;
import com.example.infs3605groupprojectnew.MenuPage;
import com.example.infs3605groupprojectnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryOfUNSWLandPage extends AppCompatActivity {
    ImageView unsw_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_of_unsw_land_page);

        unsw_history = findViewById(R.id.img_history);

        unsw_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent history_source = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indigenous.unsw.edu.au/strategy/culture-and-country/history"));
                startActivity(history_source);
            }
        });

        // Pull data from firebase
        DatabaseReference myRef = null;
        myRef = FirebaseDatabase.getInstance().getReference("Info Page/HistoryUNSWLand");
        TextView history_detail_1 = findViewById(R.id.history_detail_1);


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HistoryOfUNSWLand historyOfUNSWLand = snapshot.getValue(HistoryOfUNSWLand.class);
                history_detail_1.setText(historyOfUNSWLand.getHistory());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Handle the back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(HistoryOfUNSWLandPage.this, MenuPage.class);
                intent.putExtra("ToNavigation", "InfoFragment");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
