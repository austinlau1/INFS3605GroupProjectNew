package com.example.infs3605groupprojectnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private static final String TAG = "LoginPage";
    //    DBHelper DB;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingProgressBar;
    EditText user_email, user_passw;
    Button btnForgetPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting Launch");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        user_email = findViewById(R.id.email_input);
        user_passw = findViewById(R.id.pass_input);
        Button registerbtn = findViewById(R.id.rego_button);
        Button userloginbtn = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        mLoadingProgressBar = new ProgressDialog(LoginActivity.this);

        //user sign up direction
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        //show hide password using eye icon

        ImageView imageViewShowHidePwd = findViewById(R.id.imageView_show_hide_pwd);
        imageViewShowHidePwd.setImageResource(R.drawable.img_hidepw);
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check whether is visible in the beginning
                if(user_passw.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())){
                    user_passw.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    //change icon
                    imageViewShowHidePwd.setImageResource(R.drawable.img_hidepw);
                } else{
                    user_passw.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.img_showpw);
                }
            }
        });

        //user forget password

        btnForgetPW = findViewById(R.id.forgot_button);
        btnForgetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPassword.class));
            }
        });

        userloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String inputEmail = user_email.getText().toString().trim();
                final String inputPassword = user_passw.getText().toString().trim();
                loginWithEmailAndPassword(inputEmail, inputPassword);
            }
        });

        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
        finish();
        return true;
    }

    private void loginWithEmailAndPassword(String inputEmail, String inputPassword) {

        if (validate()==false) {
            return;
        } else {

            mLoadingProgressBar.setTitle("Login");
            mLoadingProgressBar.setMessage("Checking your user details!");
            mLoadingProgressBar.setCanceledOnTouchOutside(false);
            mLoadingProgressBar.show();

            //Authentication process
            mAuth.signInWithEmailAndPassword(inputEmail, inputPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        mLoadingProgressBar.dismiss();
                        Intent intent = new Intent(LoginActivity.this, MenuPage.class);
                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("email",inputEmail);
                        startActivity(intent);

                    } else {

                        Toast.makeText(LoginActivity.this, "User Not Existed! Please Sign Up", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    private boolean validate() {
        if (user_email.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
            return false;
        }

        else if (user_passw.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
            return  false;
        }
        else{

            user_email.setError(null);
            user_passw.setError(null);
            return true;
        }
    }
}


