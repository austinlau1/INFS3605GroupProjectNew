package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.infs3605groupprojectnew.Quizzes.QuizOptions;
//import com.example.infs3605groupprojectnew.Quizzes.Quizzes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeFragment extends Fragment {

    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Banner
        FrameLayout bannerLayout = rootView.findViewById(R.id.banner_layout);
        TextView bannerText = rootView.findViewById(R.id.banner_text);
        ImageButton bannerClose = rootView.findViewById(R.id.banner_close);

        bannerLayout.setVisibility(View.VISIBLE);
        bannerText.setText("This is the acknowledgement of country.");
        bannerClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerLayout.setVisibility(View.GONE);
            }
        });

        // Edit profile button
        Button editProfileBtn = rootView.findViewById(R.id.editProfileBtn);
        editProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserDetails.class);
                startActivity(intent);
            }
        });

        // Display first name
        TextView firstNameTextView = rootView.findViewById(R.id.name);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*if (user != null) {
            String firstName = user.getDisplayName();
            Log.d("TAG", "User first name: " + firstName);
            firstNameTextView.setText(firstName);
        }*/

        // Display user email
        if (user != null) {
            String email = user.getEmail();
            Log.d("TAG", "User email: " + email);
            firstNameTextView.setText(email);
        }

        // Button to explore plants
        Button toPlantsBtn = (Button) rootView.findViewById(R.id.toPlantsBtn);

        toPlantsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });

        //Button to Quiz
        Button toQuizzesBtn = (Button) rootView.findViewById(R.id.toQuizzesBtn);

        toQuizzesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizOptions.class);
                startActivity(intent);
            }
        });

        // Button to log out
        Button logoutButton = (Button) rootView.findViewById(R.id.logout_but);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log the user out
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}