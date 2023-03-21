package com.example.infs3605groupprojectnew;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class IndividualPlantFragment extends Fragment {

    RecyclerView mRecyclerView;
    private PlantAdapter adapter;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    String name;

    //
    public IndividualPlantFragment(){

    }
    public IndividualPlantFragment(String name){
        this.name = name;}

    public static IndividualPlantFragment newInstance(String param1, String param2) {
        IndividualPlantFragment fragment = new IndividualPlantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.individual_plant_page, container, false);

//        ImageView imageholder=view.findViewById(R.id.imagegholder);
        TextView nameholder=view.findViewById(R.id.plantName1);
//        TextView courseholder=view.findViewById(R.id.courseholder);
//        TextView emailholder=view.findViewById(R.id.emailholder);

        nameholder.setText(name);
//        courseholder.setText(course);
//        emailholder.setText(email);
//        Glide.with(getContext()).load(purl).into(imageholder);


        return  view;
    }
    public void onBackPressed()
    {
        AppCompatActivity activity=(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragmenthome, new PlantFragment()).addToBackStack(null).commit();

    }

}
