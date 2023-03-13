package com.example.infs3605groupprojectnew;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity{

    private EditText emailEditText;
    private Button resetPW;
    // private ProgressBar mProgressBar;
    private static final String TAG = "Forgot Password Page";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword_page);

        emailEditText = findViewById(R.id.email_reset_ps);
        resetPW = findViewById(R.id.reset_button);
        mAuth = FirebaseAuth.getInstance();
        //mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // mAuth = FirebaseAuth.getInstance();

        resetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Referenced from ChatGPT
                String email = emailEditText.getText().toString().trim();
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "Password reset email sent.");
                                }
                            }

                        });

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(ForgotPassword.this, "Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }

    /*// From ChatGPT
    private void sendPasswordResetEmail() {
        String email = emailEditText.getText().toString().trim();
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Password reset email sent.");
                        }
                    }
                });*/

    }

    /*private void resetPassword() {

        String email = emailEditText.getText().toString().trim();

        if(email.isEmpty()){
            emailEditText.setError("Email is required!");
            emailEditText.requestFocus();
            return;
        }
//        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            emailEditText.setError("Please provide valid email");
//            emailEditText.requestFocus();
//            return;

        mProgressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPassword.this, "Try again! Something wrong happened!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }*/

    





