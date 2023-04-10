package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ModifyDetails extends AppCompatActivity {
    private static final String TAG = "ModifyDetails";
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_modify_details_page);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        EditText editUsername = findViewById(R.id.editUsername);
        EditText editFirstName = findViewById(R.id.editFirstName);
        EditText editLastName = findViewById(R.id.editSecondName);
        TextView existingUsername = findViewById(R.id.existingUsername);
        TextView existingFirstname = findViewById(R.id.existingFirstname);
        TextView existingLastname = findViewById(R.id.existingLastname);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = databaseRef.child("User/" + user.getUid());

        if (myRef != null) {
            myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile != null) {
                        existingUsername.setText(userProfile.getUsername());
                        existingFirstname.setText(userProfile.getFirstname());
                        existingLastname.setText(userProfile.getLastname());
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        Button ModifyUserDetailsBtn = findViewById(R.id.ModifyUserDetailsBtn);
        ModifyUserDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newUserName = editUsername.getText().toString();
                String newFirstName = editFirstName.getText().toString();
                String newLastName= editLastName.getText().toString();

                Map<String, Object> newData = new HashMap<>();
                newData.put("username", newUserName);
                newData.put("firstname", newFirstName);
                newData.put("lastname", newLastName);

                myRef.updateChildren(newData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(ModifyDetails.this, "User data updated successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ModifyDetails.this, "Failed to update user data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });



            }
        });

    }
    /*public void updateProfile(String newUsername, String newFirstName, String newSecondName) {
        HashMap User = new HashMap();
        User.put("username", newUsername);
        User.put("firstname", newFirstName);
        User.put("secondname", newSecondName);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        databaseReference.child(newUsername).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (task.isSuccessful()){
                    binding.newUserName.s
                }
            }
        });*/



    }



