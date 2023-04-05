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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    EditText Inuser_name, Inuser_password, Inuser_email, Inuser_first_name, Inuser_last_name;
    TextView btnAlready;
    private FirebaseAuth mAuth;
    private ProgressDialog mLoadingProgres;
    private DatabaseReference mDatabase;

    private static final String TAG = "Registration Page";

    UserDAO dao = new UserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: Starting Launch");
        setTitle("Register User");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);

        Inuser_name = findViewById(R.id.username);
        Inuser_password = findViewById(R.id.password);
        Inuser_email = findViewById(R.id.email);
        Inuser_first_name = findViewById(R.id.first_name);
        Inuser_last_name = findViewById(R.id.last_name);
        mLoadingProgres = new ProgressDialog(RegisterActivity.this);
        mAuth = FirebaseAuth.getInstance();


        btnAlready = findViewById(R.id.have_acc_button);
        btnAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        // hide and show password for users
        ImageView imageViewShowHidePwd = findViewById(R.id.imageView_show_hide_pwd1);
        imageViewShowHidePwd.setImageResource(R.drawable.img_hidepw);
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //check whether is visible in the beginning
                if (Inuser_password.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {
                    Inuser_password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    //change icon
                    imageViewShowHidePwd.setImageResource(R.drawable.img_hidepw);
                } else {
                    Inuser_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.img_showpw);
                }
            }
        });

        // Adding user into the firebase authentication and realtime database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("User");
        final DatabaseReference[] ref = new DatabaseReference[1];
        final FirebaseUser[] mCurrentUser = new FirebaseUser[1];

        Button regoSave = findViewById(R.id.register_button);

        regoSave.setOnClickListener((v-> {

            if (!validateUsername() | !validateEmail() | !validateFirstName() | !validateLastName() | !validatePassword()) {
                return;
            }

            String username = Inuser_name.getText().toString();
            String password = Inuser_password.getText().toString();
            String email = Inuser_email.getText().toString();
            String firstname = Inuser_first_name.getText().toString();
            String lastname = Inuser_last_name.getText().toString();

            mLoadingProgres.setTitle("Registration");
            mLoadingProgres.setMessage("Checking your login details!");
            mLoadingProgres.setCanceledOnTouchOutside(false);
            mLoadingProgres.show();

                /*String username = "testings";
                String password = "testings";
                String email = "testings@gmail.com";
                String firstname = "testings";
                String lastname = "testings";*/

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Toasty.info(getApplicationContext(), "creation of account was: " + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if (task.isSuccessful()) {
                                mCurrentUser[0] = task.getResult().getUser();
                                ref[0] = mDatabase.child(mCurrentUser[0].getUid());
                                ref[0].child("email").setValue(email);
                                ref[0].child("password").setValue(password);
                                ref[0].child("username").setValue(username);
                                ref[0].child("firstname").setValue(firstname);
                                ref[0].child("lastname").setValue(lastname);
                                Toast.makeText(RegisterActivity.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                mLoadingProgres.dismiss();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed to register! Please try again!", Toast.LENGTH_LONG).show();
                                mLoadingProgres.dismiss();
                            }
                        }
                    });
        }));
    }

//method to validate user's input
    private Boolean validateUsername(){
        String val = Inuser_name.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()){
            Inuser_name.setError("Field cannot be empty");
            return false;

        }
        else if(!val.matches(noWhiteSpace)){
            Inuser_name.setError("White Spaces are not allowed");
            return false;

        }
        // pass the error when the first time the user entered
        else{
            Inuser_name.setError(null);
//        user_name.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = Inuser_password.getText().toString();
        String valPass = "^" +
                " (.*[A-Z].*)" + //must have atleast one uppercase character
//                        "(.*[@,#,$,%].*$)" + // at least 1 special character special character among @#$%"
                "(.*[0-9].*)" ;// Password must have atleast one number

        if (val.isEmpty()) {
            Inuser_password.setError("Field cannot be empty");
            return false;

        } else if (!val.matches(valPass) && val.length() < 8) {
            Inuser_password.setError("Password is too weak");
            return false;
        }
        // pass the error when the first time the user entered
        else {
            Inuser_password.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = Inuser_email.getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            Inuser_email.setError("Field cannot be empty");
            return false;

        }
        else if(!val.matches(emailPattern)){
            Inuser_email.setError("Invalid email address");
            return false;

        }
        // pass the error when the first time the user entered
        else{
            Inuser_email.setError(null);
            return true;
        }


    }
    private Boolean validateFirstName(){
        String val = Inuser_first_name.getText().toString();
        if (val.isEmpty()){
            Inuser_first_name.setError("Field cannot be empty");
            return false;}
        // pass the error when the first time the user entered
        else{
            Inuser_first_name.setError(null);
            return true;
        }
    }
    private Boolean validateLastName(){
        String val = Inuser_last_name.getText().toString();
        if (val.isEmpty()){
            Inuser_last_name.setError("Field cannot be empty");
            return false;}
        // pass the error when the first time the user entered
        else{
            Inuser_last_name.setError(null);
            return true;
        }
    }
}


        //Button rego_save = findViewById(R.id.register_button);
        /*rego_save.setOnClickListener(v-> {

            if(!validateUsername() |  !validateEmail() | !validateFirstName() | !validateLastName() | !validatePassword()) {
                return;
            }

            String username = Inuser_name.getText().toString();
            String password =  Inuser_password.getText().toString();
            String email  =    Inuser_email.getText().toString();
            String firstname = Inuser_first_name.getText().toString();
            String lastname  = Inuser_last_name.getText().toString();

            mLoadingProgres.setTitle("Registration");
            mLoadingProgres.setMessage("Checking your login details!");
            mLoadingProgres.setCanceledOnTouchOutside(false);
            mLoadingProgres.show();

            //Send email and password to firebase for authentication
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    // if the user has been registered successfully -> create a user object to store them in realtime database
                    if(task.isSuccessful()){
                        User user = new User(username, password, email, firstname, lastname);

                        //send user info to real time database + get ID -> turns out the id for the registered user

                        FirebaseDatabase.getInstance().getReference("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid());
                        dao.add(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"User has been registered successfully",Toast.LENGTH_LONG).show();

                                    mLoadingProgres.dismiss();
                                }else{
                                    Toast.makeText(RegisterActivity.this,"Failed to register! Please try again!",Toast.LENGTH_LONG).show();
                                    mLoadingProgres.dismiss();
                                }
                            }
                        });
                    }
                }
            });
        });
    }



    //method to validate user's input
    private Boolean validateUsername(){
        String val = Inuser_name.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()){
            Inuser_name.setError("Field cannot be empty");
            return false;

        }
        else if(!val.matches(noWhiteSpace)){
            Inuser_name.setError("White Spaces are not allowed");
            return false;

        }
        // pass the error when the first time the user entered
        else{
            Inuser_name.setError(null);
//        user_name.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword() {
        String val = Inuser_password.getText().toString();
        String valPass = "^" +
                " (.*[A-Z].*)" + //must have atleast one uppercase character
//                        "(.*[@,#,$,%].*$)" + // at least 1 special character special character among @#$%"
                "(.*[0-9].*)" ;// Password must have atleast one number

        if (val.isEmpty()) {
            Inuser_password.setError("Field cannot be empty");
            return false;

        } else if (!val.matches(valPass) && val.length() < 8) {
            Inuser_password.setError("Password is too weak");
            return false;
        }
        // pass the error when the first time the user entered
        else {
            Inuser_password.setError(null);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = Inuser_email.getText().toString();
        String emailPattern = "[a-zA-z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()){
            Inuser_email.setError("Field cannot be empty");
            return false;

        }
        else if(!val.matches(emailPattern)){
            Inuser_email.setError("Invalid email address");
            return false;

        }
        // pass the error when the first time the user entered
        else{
            Inuser_email.setError(null);
            return true;
        }


    }
    private Boolean validateFirstName(){
        String val = Inuser_first_name.getText().toString();
        if (val.isEmpty()){
            Inuser_first_name.setError("Field cannot be empty");
            return false;}
        // pass the error when the first time the user entered
        else{
            Inuser_first_name.setError(null);
            return true;
        }
    }
    private Boolean validateLastName(){
        String val = Inuser_last_name.getText().toString();
        if (val.isEmpty()){
            Inuser_last_name.setError("Field cannot be empty");
            return false;}
        // pass the error when the first time the user entered
        else{
            Inuser_last_name.setError(null);
            return true;
        }
    }
*/
/*    }
}*/
// pass the user object and the test result is returned back which we can listen to callback success and fail

//        dao.add(user).addOnSuccessListener(suc->{
//                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();
//                }).addOnFailureListener(er->{
//                Toast.makeText(this, "" +er.getMessage(),Toast.LENGTH_SHORT).show();
//                });

