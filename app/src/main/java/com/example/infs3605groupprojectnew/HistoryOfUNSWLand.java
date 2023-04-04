package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryOfUNSWLand extends AppCompatActivity {
    Button navigateToSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_of_unsw_land_page);

        Button historySourceBtn = findViewById(R.id.toSourceBtn);

        historySourceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent google_search = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.indigenous.unsw.edu.au/strategy/culture-and-country/history"));
                startActivity(google_search);
            }
        });
    }
}
