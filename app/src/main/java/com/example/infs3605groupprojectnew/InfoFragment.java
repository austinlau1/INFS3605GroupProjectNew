package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


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
                Intent intent = new Intent(getActivity(), HistoryOfUNSWLand.class);
                startActivity(intent);
            }
        });

        // Biodiversity button
        Button biodiversityBtn = rootView.findViewById(R.id.BiodiversityBtn);
        biodiversityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Biodiversity.class);
                startActivity(intent);
            }
        });

        // Wellbeing on Campus button
        Button wellbeingOnCampusBtn = rootView.findViewById(R.id.WellBeingBtn);
        wellbeingOnCampusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WellBeingOnCampus.class);
                startActivity(intent);
            }
        });

        // Link to Resources button
        Button linkToResourcesBtn = rootView.findViewById(R.id.LinkResourceBtn);
        linkToResourcesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LinkToResources.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}

