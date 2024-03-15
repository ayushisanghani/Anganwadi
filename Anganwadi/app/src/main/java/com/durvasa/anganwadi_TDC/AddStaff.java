package com.durvasa.anganwadi_TDC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.durvasa.anganwadi_TDC.Pojo.StaffData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStaff extends AppCompatActivity {

    TextInputEditText edtName,edtAge,edtQualitfication,edtEmail,edtPhone,edtCity;
    Button btnStaff;
    FirebaseDatabase fDatabase;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        edtName = findViewById(R.id.edtSName);
        edtAge = findViewById(R.id.edtSAge);
        edtQualitfication = findViewById(R.id.edtQua);
        edtEmail = findViewById(R.id.edtSEmail);
        edtPhone = findViewById(R.id.edtPhone2);
        edtCity = findViewById(R.id.edtCity2);


        fDatabase = FirebaseDatabase.getInstance();

        btnStaff = findViewById(R.id.btnStaff);
        btnStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StaffData staffData = new StaffData();
                staffData.setName(edtName.getText().toString());
                staffData.setAge(edtAge.getText().toString());
                staffData.setEmail(edtEmail.getText().toString());
                staffData.setQualification(edtQualitfication.getText().toString());
                staffData.setPhoneNo(Integer.parseInt(edtPhone.getText().toString()));
                staffData.setCity(edtCity.getText().toString());

                if (key == null) {
                    DatabaseReference ref = fDatabase.getReference("Staffs").push();
                    ref.setValue(staffData);
                } else {
                    DatabaseReference ref = fDatabase.getReference("Staffs").child(key);
                    ref.setValue(staffData);
                }
                Toast.makeText(AddStaff.this, "Staff Added Successfully!", Toast.LENGTH_SHORT).show();

            }
        });

    }
}