package com.example.infs3605groupprojectnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrivacyPolicy extends AppCompatActivity {
    Button mContactDirect, mhomeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        setTitle("Privacy Policy");
        mContactDirect = findViewById(R.id.DirectContact);

        mContactDirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct_contact = new Intent(PrivacyPolicy.this, ContactUs.class);
                startActivity(direct_contact);
            }
        });

        mhomeBack = findViewById(R.id.backButton);

        mhomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent direct_home = new Intent(PrivacyPolicy.this, UserDetails.class);
                startActivity(direct_home);
            }
        });
    }
}