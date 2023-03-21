package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class PlantFragment extends Fragment implements View.OnClickListener {
    //public static String PLANT_SYMBOL_KEY = "plantSymbol";

    RecyclerView mRecyclerView;
    private PlantAdapter adapter;
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button navigateToList;
    TextView mPlantName;

    String  name;
//    int id;
//
    public PlantFragment(){

    }

    public PlantFragment(String name){
      this.name = name;

    }


    public static PlantFragment newInstance(String param1, String param2) {
        PlantFragment fragment = new PlantFragment();
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.fragment_plant, container, false);
        View rootView = inflater.inflate(R.layout.plant_recyclerview, container, false);
        mRecyclerView = rootView.findViewById(R.id.rvList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Plants Database");
        // Initialize Firebase
        // FirebaseApp.initializeApp(getContext());
        // Get reference to the database
        // DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //adapter = new PlantAdapter(new ArrayList<Plant>(), listener);

//        navigateToList = (Button) rootView.findViewById(R.id.view_plants_but);
//        mPlantName = rootView.findViewById(R.id.plant_fact);
//
//        mPlantName.setText(name);





        navigateToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                intent = new Intent(getActivity(), PlantList.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onClick(View view) {

    }
//    @Override
//    public void onStart() {
//    super.onStart();
//    adapter.startListening();
//}
//
//        @Override
//        public void onStop() {
//        super.onStop();
//        adapter.stopListening();
//    }

    /*PlantAdapter.ClickListener listener = new PlantAdapter.ClickListener() {
        @Override
        public void onPlantClick(View view, String plantSymbol) {
            Intent intent = new Intent(getActivity(), IndividualPlant.class);
            intent.putExtra(PLANT_SYMBOL_KEY, plantSymbol);
            startActivity(intent);
        }
    };*/



        /*mRecyclerView = findViewById(R.id.rvList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);*/

        /*adapter = new PlantAdapter(new ArrayList<Plant>(), listener);*/
        /*mRecyclerView.setAdapter(adapter);*/

        // Add a ValueEventListener to the reference
        /*databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Handle data changes
                // Use getValue() to get the data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors
            }
        });

        return rootView;*/

//    @Override
//    public void onClick(View v) {
//        Intent intent;
//        intent = new Intent(getActivity(), PlantList.class);
//        startActivity(intent);
//    }


//    public void onBackPressed()
//    {
//        AppCompatActivity activity = (AppCompatActivity)getContext();
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.plant_id, new PlantFragment()).addToBackStack(null).commit();
//    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }*/

    /*// Sort plants
    @Override
    public boolean onOptionsItemSelected (MenuItem menuSelected){
        if (menuSelected.getItemId() == R.id.name_sort) {
            adapter.Sort(1);
        }
        else if (menuSelected.getItemId() == R.id.value_sort) {
            adapter.Sort(2);
        }
        return true;

    }*/


}