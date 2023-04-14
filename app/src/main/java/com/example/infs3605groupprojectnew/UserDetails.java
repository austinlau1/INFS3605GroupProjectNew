package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.infs3605groupprojectnew.Quizzes.HardStart;
import com.example.infs3605groupprojectnew.Quizzes.QuizOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetails extends AppCompatActivity {

    List<User> users;
    private CircleImageView profilePicture;
    private Uri imageUri;
    private static final String TAG = "UserDetail";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);
        setTitle("Profile");

        Button termsncond = findViewById(R.id.TCBtn);
        Button privacy = findViewById(R.id.PPBtn);
        Button modifyDetailsBtn = findViewById(R.id.modifyDetailsBtn);

        // Direct users to modify user details page
        modifyDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this, ModifyDetails.class);
                startActivity(intent);
            }
        });

        //direct to terms and conditions and privacy policy page
        termsncond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this, TermsAndConditions.class);
                startActivity(intent);
            }
        });


        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserDetails.this, PrivacyPolicy.class);
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

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference profilePicRef = storage.getReference().child("ProfilePicture/" + user.getUid() + ".jpeg");

        profilePicture = findViewById(R.id.profilePicture);

        try {
            final File file = File.createTempFile("image", "jpeg");
            profilePicRef.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                    profilePicture.setImageBitmap(bitmap);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        //StorageReference storageRef = FirebaseStorage.getInstance().getReference();


        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageUri = result;

                // Now, upload the selected image to Firebase Storage:
                if (imageUri != null) {
                    StorageReference profilePicRef = storage.getReference().child("ProfilePicture/" + user.getUid() + ".jpeg");

                    profilePicRef.putFile(imageUri)
                            .addOnSuccessListener(taskSnapshot -> {
                                recreate();
                                Log.d(TAG, "Image upload successful");
                                // Do something here, such as display the uploaded image in your app
                            })
                            .addOnFailureListener(exception -> {
                                Log.e(TAG, "Image upload failed: " + exception.getMessage());
                                // Handle the error here
                            });
                }
            }
        });


        Button changePictureBtn = findViewById(R.id.changePictureBtn);
        //Button uploadPictureBtn = findViewById(R.id.uploadPictureBtn);

        //
        changePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGetContent.launch("image/*");

            }

        });

        /*uploadPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();

            }*/

            /*private void uploadImage() {
                StorageReference profilePicRef = storageRef.child("ProfilePicture/" + user.getUid() + ".jpg");

                profilePicRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UserDetails.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UserDetails.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }*/
        }
    }



