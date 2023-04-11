package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.infs3605groupprojectnew.InfoPages.BiodiversityPage;
import com.example.infs3605groupprojectnew.InfoPages.HistoryOfUNSWLandPage;
import com.example.infs3605groupprojectnew.InfoPages.LinkToResourcesPage;
import com.example.infs3605groupprojectnew.InfoPages.WellBeingOnCampusPage;


public class InfoFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_info, container, false);
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        // History of UNSW land button
        Button historyLandBtn = rootView.findViewById(R.id.HistoryLandBtn);
        historyLandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HistoryOfUNSWLandPage.class);
                startActivity(intent);
            }
        });

        // Biodiversity button
        Button biodiversityBtn = rootView.findViewById(R.id.BiodiversityBtn);
        biodiversityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BiodiversityPage.class);
                startActivity(intent);
            }
        });

        // Wellbeing on Campus button
        Button wellbeingOnCampusBtn = rootView.findViewById(R.id.WellBeingBtn);
        wellbeingOnCampusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WellBeingOnCampusPage.class);
                startActivity(intent);
            }
        });
        // Link to FAQ Page
        Button FrquentAskButton = rootView.findViewById(R.id.FAQBtn);
        FrquentAskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), FAQ.class);
                startActivity(intent1);
            }
        });

        // Link to Contact Us Page
        Button FAQBtn = rootView.findViewById(R.id.ContactUsBtn);
        FAQBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactUs.class);
                startActivity(intent);
            }
        });

        // Link to Resources button
        Button linkToResourcesBtn = rootView.findViewById(R.id.LinkResourceBtn);
        linkToResourcesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LinkToResourcesPage.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

