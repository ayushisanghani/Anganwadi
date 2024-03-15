package com.durvasa.anganwadi_TDC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Demo extends AppCompatActivity {

    Button signup,signup1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        signup=findViewById(R.id.btnSignup);
        signup1=findViewById(R.id.btnSignup1);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent reset = new Intent(Demo.this,LoginActivity.class);
                startActivity(reset);
            }
        });
        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Demo.this,AdminLogin.class));
            }
        });
    }
}