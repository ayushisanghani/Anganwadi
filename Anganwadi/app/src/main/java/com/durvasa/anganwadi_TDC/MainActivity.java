package com.durvasa.anganwadi_TDC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.durvasa.anganwadi_TDC.Adapter.DataAdapter;
import com.durvasa.anganwadi_TDC.Pojo.DataClass;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickInterface {

    RecyclerView recyclerView;
    ArrayList<DataClass> data;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        actionBar = getSupportActionBar();
//        actionBar.setTitle("Anganwadi");
//        if (actionBar != null) {
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            actionBar.setCustomView(LayoutInflater.from(this).inflate(R.layout.action_bar_profil, null));
//
//            // Get the ImageView from the custom ActionBar layout
//            ImageView profileIcon = actionBar.getCustomView().findViewById(R.id.profileIcon);
//
//            // Set click listener for the profile icon
//            profileIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // Open ProfileActivity
//                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
//                }
//            });
//        }
        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //Insert Data Method Call
        prepareData();


        DataAdapter adapter = new DataAdapter(MainActivity.this, data,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public void prepareData() {
        data = new ArrayList<>();
        data.add(new DataClass("Centers", R.drawable.center1));
        data.add(new DataClass("Nutrition", R.drawable.nplan));
        data.add(new DataClass("Reports", R.drawable.chart));
        data.add(new DataClass("News", R.drawable.news));
        data.add(new DataClass("Child Care", R.drawable.care));
        data.add(new DataClass("Blood Donation",R.drawable.bloodtest));


    }


    @Override
    public void onItemClick(int position) {

            switch (position){
                case 0:
                    Intent location = new Intent(MainActivity.this,MapsActivity.class);
                    startActivity(location);
                    break;
                case 1:
                    Intent nutrition = new Intent(MainActivity.this,NutritionActivity.class);
                    startActivity(nutrition);
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this,ReportActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this,NewsActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this,ChildCareActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(MainActivity.this,BloodDonationActivity.class));
                    break;


            }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.icon_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_profile_icon) {
            // Open ProfileActivity
            startActivity(new Intent(this, ProfileActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}