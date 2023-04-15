package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity{
    private static final String TAG = "Welcome Page";
    Button signupBtn, loginBtn;
    public boolean popup = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: Starting Welcome Page");
        setTitle("First Page Display");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

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


        Button toMenuButton = (Button) findViewById(R.id.toMenuButton);

        toMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MenuPage.class);
                startActivity(intent);
            }
        });

        Button toMapButton = (Button) findViewById(R.id.toMapButton);

        toMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ViewMap.class);
                startActivity(intent);
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

