package com.durvasa.anganwadi_TDC;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.durvasa.anganwadi_TDC.Adapter.Adapter;
import com.durvasa.anganwadi_TDC.Pojo.Model;

import java.util.ArrayList;

public class AdminDashboard extends AppCompatActivity {

    ArrayList<Model>arrayList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        recyclerView = findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new Model(R.drawable.totalchild,"Total Students"));
        arrayList.add(new Model(R.drawable.totalss,"Total Staff"));
        arrayList.add(new Model(R.drawable.children,"Add Students"));
        arrayList.add(new Model(R.drawable.employees,"Add Staff"));
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);

        Adapter adapter = new Adapter(arrayList,this);
        recyclerView.setAdapter(adapter);
    }



}