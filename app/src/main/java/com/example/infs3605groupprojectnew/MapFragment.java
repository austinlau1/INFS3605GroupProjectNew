package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MapFragment extends Fragment {
    private TextView mDate;
    private Calendar mCalendar;

    private SimpleDateFormat mDateFormat;
    private String date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        getActivity().setTitle("Map");

        Button toMapBtn = (Button) rootView.findViewById(R.id.toMapBtn);
        mDate = (TextView) rootView.findViewById(R.id.date_display);
        toMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewMap.class);
                startActivity(intent);
            }
        });
        //display current date and time
        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("EEEE, MMMM-dd");
        date = mDateFormat.format(mCalendar.getTime());
        mDate.setText(date);
        return rootView;
    }
}