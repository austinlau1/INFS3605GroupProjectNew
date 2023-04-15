package com.example.infs3605groupprojectnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermsAndConditions extends AppCompatActivity {

    Button mhomeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        setTitle("Terms and Conditions");
        mhomeBack = findViewById(R.id.button2);

        mhomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct_home = new Intent(TermsAndConditions.this, UserDetails.class);
                startActivity(direct_home);
            }
        });

    }
}