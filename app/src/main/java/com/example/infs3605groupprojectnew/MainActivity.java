package com.example.infs3605groupprojectnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private TextView mDate, mUserDisplay;

    private Calendar mCalendar;

    private SimpleDateFormat mDateFormat;
    private String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button investingButton = (Button) findViewById(R.id.menuButton);
        mDate = (TextView) findViewById(R.id.date_display);



        investingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuPage.class);
                startActivity(intent);
            }
        });
        //display current date and time
        mCalendar = Calendar.getInstance();
        mDateFormat = new SimpleDateFormat("EEEE, MMMM-dd");
        date = mDateFormat.format(mCalendar.getTime());
        mDate.setText(date);
    }
}