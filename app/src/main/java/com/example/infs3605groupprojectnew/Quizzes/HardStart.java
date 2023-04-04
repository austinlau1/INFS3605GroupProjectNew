package com.example.infs3605groupprojectnew.Quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.infs3605groupprojectnew.R;

public class HardStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_start);

        Button startQuiz = (Button) findViewById(R.id.startQuizBtn);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HardStart.this, HardProgress.class);
                //intent.putExtra("ProfileInfo", username);
                startActivity(intent);

            }
        });


        Button changeDifficulty = (Button) findViewById(R.id.difficultyBtn);
        changeDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HardStart.this, QuizOptions.class);
                intent.putExtra("ToNavigation", "QuizFragment");
                //intent.putExtra("ProfileInfo", username);
                startActivity(intent);

            }
        });

    }
}