package com.example.infs3605groupprojectnew;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ModifyDetails extends AppCompatActivity {
    private static final String TAG = "ModifyDetails";
    private CircleImageView profilePicture;
    Uri imageUri;
    

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


        // Profile Picture
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference profilePicRef = storageRef.child("ProfilePicture/" + user.getUid() + ".jpg");

        /*ActivityResultLauncher<Intent> galleryLauncher;

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            // Get the selected image from the Intent
                            Intent data = result.getData();
                            Uri imageUri = data.getData();
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                                profilePicture.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });*/

        ActivityResultLauncher<String> mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    profilePicture.setImageURI(result);
                    imageUri = result;
                }
            }
        });

        profilePicture = findViewById(R.id.profilePicture);
        Button changePictureBtn = findViewById(R.id.changePictureBtn);
        Button uploadPictureBtn = findViewById(R.id.uploadPictureBtn);

        //
        changePictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryLauncher.launch(intent);*/
                mGetContent.launch("image/*");

            }

        });

        uploadPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();

                /*// Get the selected image from the ImageView
                Bitmap selectedImage = ((BitmapDrawable) profilePicture.getDrawable()).getBitmap();

                // Convert the selected image to a byte array
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                // Upload the byte array to Firebase Storage
                UploadTask uploadTask = profilePicRef.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Handle successful upload
                        // You can get the download URL of the uploaded image using taskSnapshot.getDownloadUrl()
                        profilePicRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                // Display the downloaded image in the ImageView
                                Glide.with(ModifyDetails.this)
                                        .load(uri)
                                        .into(profilePicture);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful upload
                    }
                });*/
            }

            private void uploadImage() {
                StorageReference profilePicRef = storageRef.child("ProfilePicture/" + user.getUid() + ".jpg");

                profilePicRef.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ModifyDetails.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ModifyDetails.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


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


        //go back to previous user details page
        Button GoBackUserPageBtn = findViewById(R.id.BackUserBtn);
        GoBackUserPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyDetails.this, UserDetails.class);
                startActivity(intent);
            }
        });

    }



    /*FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    StorageReference profilePicRef = storageRef.child("ProfilePicture/" + user.getUid() + ".jpg");

    private void uploadImage() {
        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();
            StorageReference ref = storageRef.child("ProfilePicture/"+ user.getUid());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(ModifyDetails.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ModifyDetails.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }*/
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





