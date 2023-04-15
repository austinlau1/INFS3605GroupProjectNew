package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.example.infs3605groupprojectnew.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.util.Calendar;

public class ContactUs extends AppCompatActivity {
    Button submitBtn;

    EditText uploadEnquiry, uploadEnquiryTitle, uploadEnquiryEmail;

   private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_page);
        setTitle("Contact Us");
        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = null;
        if (user != null) {
            myRef = FirebaseDatabase.getInstance().getReference("User/" + user.getUid());
        }



        uploadEnquiryTitle = findViewById(R.id.uploadEnquiry_title);
        uploadEnquiryEmail = findViewById(R.id.uploadEnquiry_email);
        uploadEnquiry = findViewById(R.id.uploadEnquiry_detail);

        submitBtn = findViewById(R.id.submit_enquiry_Btn);



        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               feedbacksent(view);
//                Toast.makeText(ContactUs.this, "Enquiry has been sent successfully", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Handle the back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(ContactUs.this, MenuPage.class);
                intent.putExtra("ToNavigation", "InfoFragment");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void feedbacksent (View view){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        String titleinput = uploadEnquiryTitle.getText().toString();
        String emailinput = uploadEnquiryEmail.getText().toString();
        String descinput = uploadEnquiry.getText().toString();

        User userInstance = new User();
        User.Enquiry enquiryInstance = userInstance.new Enquiry(emailinput,titleinput,descinput);

        //We are changing the child from title to currentDate,
        // because we will be updating title as well and it may affect child value.
        String currentDate = " New User Enquiry " + DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("User Enquiry").child(currentDate).setValue(enquiryInstance).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ContactUs.this, "Enquiry has been sent successfully", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ContactUs.this, "Enquiry has not sent, please try again", Toast.LENGTH_LONG).show();
            }
        });

    }
}
