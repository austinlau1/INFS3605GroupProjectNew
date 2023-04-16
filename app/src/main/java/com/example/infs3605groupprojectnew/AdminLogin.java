package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {
    private static final String TAG = "AdminPage";
    private FirebaseAuth mAuth;
    EditText adminEmail, adminPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting Launch");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login_page);

        adminEmail = findViewById(R.id.adminEmailTxt);
        adminPassword = findViewById(R.id.adminPasswordTxt);
        Button adminLoginBtn = findViewById(R.id.adminLoginBtn);

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String inputEmail = adminEmail.getText().toString().trim();
                final String inputPassword = adminPassword.getText().toString().trim();
                loginWithEmailAndPassword(inputEmail, inputPassword);
            }
        });

        /*FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(adminEmail, adminPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // The user has been signed in
                        } else {
                            // Login failed, handle the error
                        }
                    }
                });*/

    }
    private void loginWithEmailAndPassword(String inputEmail, String inputPassword) {

        if (validate()==false) {
            return;
        } else {

            /*mLoadingProgressBar.setTitle("Login");
            mLoadingProgressBar.setMessage("Checking your user details!");
            mLoadingProgressBar.setCanceledOnTouchOutside(false);
            mLoadingProgressBar.show();*/

            //Authentication process
            mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        // mLoadingProgressBar.dismiss();
                        Intent intent = new Intent(AdminLogin.this, MenuPage.class); //maybe to go another activity, admin one
                        Toast.makeText(AdminLogin.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("email",inputEmail);
                        startActivity(intent);

                    } else {

                        Toast.makeText(AdminLogin.this, "User Not Existed! Please Sign Up", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }
    private boolean validate() {
        if (adminEmail.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (adminPassword.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else{

            adminEmail.setError(null);
            adminPassword.setError(null);
            return true;
        }
    }
}
