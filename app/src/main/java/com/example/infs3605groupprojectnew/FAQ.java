package com.example.infs3605groupprojectnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class FAQ extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_page);
        setTitle("FAQs");
        // Enable the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Handle the back button press
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(FAQ.this, MenuPage.class);
                intent.putExtra("ToNavigation", "InfoFragment");
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
