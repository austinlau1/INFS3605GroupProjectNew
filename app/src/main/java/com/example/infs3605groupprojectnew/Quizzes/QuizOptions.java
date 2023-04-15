package com.example.infs3605groupprojectnew.Quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.infs3605groupprojectnew.InfoPages.BiodiversityPage;
import com.example.infs3605groupprojectnew.MenuPage;
import com.example.infs3605groupprojectnew.R;

public class QuizOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);
        setTitle("Quiz");

        Button easyBtn = (Button) findViewById(R.id.easyBtn);

        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizOptions.this, EasyStart.class);
                startActivity(intent);
            }
        });

        Button hardBtn = (Button) findViewById(R.id.hardBtn);

        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizOptions.this, HardStart.class);
                startActivity(intent);
            }
        });

        Button locationBtn = (Button) findViewById(R.id.locationBtn);

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizOptions.this, LocationStart.class);
                startActivity(intent);
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
                Intent intent = new Intent(QuizOptions.this, MenuPage.class);
                intent.putExtra("ToNavigation", "HomeFragment");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}