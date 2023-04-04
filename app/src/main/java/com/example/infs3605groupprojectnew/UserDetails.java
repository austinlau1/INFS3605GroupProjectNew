package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserDetails extends AppCompatActivity {
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);


        // User userRecord = FirebaseAuth.getInstance().getUser(uid);
        // System.out.println("Successfully fetched user data: " + userRecord.getUid());

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
    }
}

