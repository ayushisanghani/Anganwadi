package com.durvasa.anganwadi_TDC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.durvasa.anganwadi_TDC.Pojo.StudentData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudent extends AppCompatActivity {

    TextInputEditText edtName, edtAge,edtFather,edtMother, edtCity,edtPhone;
    Button btnAddStudent;
    FirebaseDatabase mDatabase;
    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        edtName = findViewById(R.id.edtCName);
        edtAge = findViewById(R.id.edtAge);
        edtFather = findViewById(R.id.edtFathername);
        edtMother = findViewById(R.id.edtMothername);
        edtCity = findViewById(R.id.edtCity1);
        edtPhone = findViewById(R.id.edtCPhone1);
        btnAddStudent = findViewById(R.id.btnRegister);
        mDatabase = FirebaseDatabase.getInstance();
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentData studentData = new StudentData();
                studentData.setName(edtName.getText().toString());
                studentData.setAge(edtAge.getText().toString());
                studentData.setFatherName(edtFather.getText().toString());
                studentData.setMotherName(edtMother.getText().toString());
                studentData.setCity(edtCity.getText().toString());
                studentData.setPhone(edtPhone.getText().toString());

                if (key == null) {
                    DatabaseReference ref = mDatabase.getReference("Students").push();
                    ref.setValue(studentData);
                } else {
                    DatabaseReference ref = mDatabase.getReference("Students").child(key);
                    ref.setValue(studentData);
                }
                Toast.makeText(AddStudent.this, "Students Added Successfully!", Toast.LENGTH_SHORT).show();


            }
        });


    }
}