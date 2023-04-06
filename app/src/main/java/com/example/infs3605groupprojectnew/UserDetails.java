package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infs3605groupprojectnew.Quizzes.HardStart;
import com.example.infs3605groupprojectnew.Quizzes.QuizOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDetails extends AppCompatActivity {
    /*private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String userId;*/

    List <User> users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);
        /*Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        User users = (User) bundle.getSerializable("key");*/

        Button termsncond = findViewById(R.id.TCBtn);
        Button privacy = findViewById(R.id.PPBtn);
        Button modifyDetailsBtn = findViewById(R.id.modifyDetailsBtn);

        // Direct users to modify user details page
        modifyDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this,ModifyDetails.class);
                startActivity(intent);
            }
        });

        //direct to terms and conditions and privacy policy page
        termsncond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this,TermsAndConditions.class);
                startActivity(intent);
            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this,PrivacyPolicy.class);
                startActivity(intent);
            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = null;
        if (user != null) {
            myRef = FirebaseDatabase.getInstance().getReference("User/" + user.getUid());
        }

        TextView userName = findViewById(R.id.userUsername);
        TextView hiFirstName = findViewById(R.id.hiUserName);
        TextView userEmail = findViewById(R.id.userEmail);
        TextView userFName = findViewById(R.id.userFirstName);
        TextView userLName = findViewById(R.id.userLastName);


        users = new ArrayList<>();

        //if (users != null) {

        if (myRef != null) {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile != null) {
                        hiFirstName.setText(userProfile.getFirstname());
                        userEmail.setText(userProfile.getEmail());
                        userName.setText(userProfile.getUsername());
                        userFName.setText(userProfile.getFirstname());
                        userLName.setText(userProfile.getLastname());
                    }
    //                userName.setText(userProfile.getUsername());
                    System.out.println("Username: " + userName);
                    System.out.println("Email: " + userEmail);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

            /*myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // Loop through the children nodes
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        // Get the values of the user's fields
                    *//*String name = userSnapshot.child("username").getValue(String.class);
                    String email = userSnapshot.child("email").getValue(String.class);*//*
                        //int age = userSnapshot.child("age").getValue(Integer.class);
                        User userProfile = dataSnapshot.getValue(User.class);
                        userEmail.setText(userProfile.getEmail());
                        userName.setText(userProfile.getLastname());

                        // Do something with the user's details, e.g. print them to the console
                        System.out.println("Username: " + userName);
                        System.out.println("Email: " + userEmail);


                        // System.out.println("Age: " + age);
                    }
                }*/

                /*@Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    // Log.w(TAG, "Failed to read value.", error.toException());
                }
            });*/
        }




        // User userRecord = FirebaseAuth.getInstance().getUser(uid);
        // System.out.println("Successfully fetched user data: " + userRecord.getUid());

        /*Button TCBtn = (Button) findViewById(R.id.hardBtn);

        TCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails.this, TermsAndConditions.class);
                startActivity(intent);
            }
        });

        Button PPBtn = (Button) findViewById(R.id.hardBtn);

        PPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserDetails.this, PrivacyPolicy.class);
                startActivity(intent);
            }
        });*/

/*        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        User users = (User) bundle.getSerializable("key");*//*

        TextView userEmail = findViewById(R.id.userEmail);
        TextView firstNameTextView = findViewById(R.id.userFirstName);
        TextView lastNameTextView = findViewById(R.id.userLastName);
        TextView userNameTextView = findViewById(R.id.userUserName);

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();

        *//*if (user != null) {
            String email = user.getEmail();
            userEmail.setText(email);

        }*//*

        mDatabase.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String Email = userProfile.email;
                    String firstName = userProfile.firstname;
                    String lastName = userProfile.lastname;
                    String userName = userProfile.username;

                    userEmail.setText(userProfile.getEmail());
                    firstNameTextView.setText(userProfile.getFirstname());
                    lastNameTextView.setText(userProfile.getLastname());
                    userNameTextView.setText(userProfile.getUsername());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserDetails.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });


        *//*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        if (user != null)


            String email = user.getEmail();
            Log.d("TAG", "User email: " + email);
            firstNameTextView.setText(email);

            String firstName = user.get

        }*//*


        }*/
    //}
}

