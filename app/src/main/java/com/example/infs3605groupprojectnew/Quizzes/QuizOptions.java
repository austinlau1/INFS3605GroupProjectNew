package com.example.infs3605groupprojectnew.Quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.infs3605groupprojectnew.MenuPage;
import com.example.infs3605groupprojectnew.R;

public class QuizOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

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

        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizOptions.this, MenuPage.class);
                intent.putExtra("ToNavigation", "HomeFragment");
                //intent.putExtra("ProfileInfo", username);
                startActivity(intent);

            }
        });
    }
}