package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity{
    private static final String TAG = "Welcome Page";
    Button signupBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: Starting Welcome Page");
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

        Button investingButton = (Button) findViewById(R.id.menuButton);

        investingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MenuPage.class);
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

