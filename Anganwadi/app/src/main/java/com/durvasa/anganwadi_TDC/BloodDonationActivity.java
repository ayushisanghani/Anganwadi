package com.durvasa.anganwadi_TDC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.durvasa.anganwadi_TDC.Pojo.BloodUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BloodDonationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, phone, city;
    Button register;
    Spinner spin;
    String[] grp = {"A+", "B+", "A-", "B-", "AB", "AB+", "O" , "O+"};
    FirebaseDatabase database;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donation);

        name = findViewById(R.id.edtName);
        phone = findViewById(R.id.edtPhone);
        city = findViewById(R.id.edtCity);
        register = findViewById(R.id.btnRegister);
        spin = findViewById(R.id.bloodgrpspinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, grp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.common_google_signin_btn_text_dark_default));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Blood Donation");


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || phone.getText().toString().length() < 10) {
                    name.setError("Please Enter Name");
                    phone.setError("Enter 10 digit Phone Number");
                } else {
                    database = FirebaseDatabase.getInstance();
                    BloodUser bloodUser = new BloodUser();
                    bloodUser.setName(name.getText().toString());
                    bloodUser.setPhone(phone.getText().toString());
                    bloodUser.setCity(city.getText().toString());
                    String grp = spin.getSelectedItem().toString();
                    bloodUser.setGrp(grp);

                    if (key == null) {
                        DatabaseReference ref = database.getReference("BloodUser").push();
                        ref.setValue(bloodUser);
                    } else {
                        DatabaseReference ref = database.getReference("BloodUser").child(key);
                        ref.setValue(bloodUser);
                    }
                    Toast.makeText(BloodDonationActivity.this, "Your Blood Data Registered With us!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(BloodDonationActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, grp[i], Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}