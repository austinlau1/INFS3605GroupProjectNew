package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class WelcomeActivity extends AppCompatActivity{
    private static final String TAG = "Opening Page";
    Button signupBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: Starting Opening Page");
        setTitle("First Page Display");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        //Initializing widgets
        signupBtn = findViewById(R.id.signup_button);
        loginBtn = findViewById(R.id.login_butt);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUp();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });
    }

    public void goToSignUp() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

