package com.durvasa.anganwadi_TDC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.durvasa.anganwadi_TDC.Pojo.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtCreatePass, edtRePass, edtPhone, edtCity;
    RadioGroup rg;
    RadioButton rMale, rFemale, rOther;
    Button regBtn;
    ProgressBar progressBar;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtCreatePass = findViewById(R.id.edtCreatePassword);
        edtRePass = findViewById(R.id.edtRepeatPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtCity = findViewById(R.id.edtCity);
        rg = findViewById(R.id.rg);
        rMale = findViewById(R.id.male);
        rFemale = findViewById(R.id.female);
        rOther = findViewById(R.id.other);
        regBtn = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressbar);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edtName.getText().toString().equals("")) {
                    edtName.setError("Name is Required");
                } else if (edtEmail.getText().toString().equals("")) {
                    edtEmail.setError("Email is Required");
                } else if (edtCreatePass.getText().toString().equals("")) {
                    edtCreatePass.setError("Please Create your Password");
                } else if (edtRePass.getText().toString().equals("")) {
                    edtRePass.setError("Retype your password");
                } else if (edtPhone.getText().toString().equals("")) {
                    edtPhone.setError("Phone Number is required");
                } else if (edtCity.getText().toString().equals("")) {
                    edtCity.setError("City is required");
                } else {
                    createUser(); // Method Call

                    database = FirebaseDatabase.getInstance();
                    Register register = new Register();
                    register.setName(edtName.getText().toString());
                    register.setEmail(edtEmail.getText().toString());
                    register.setCreatePass(edtCreatePass.getText().toString());
                    register.setRePass(edtRePass.getText().toString());
                    register.setPhone(edtPhone.getText().toString());
                    register.setCity(edtCity.getText().toString());

                    if (key == null) {
                        DatabaseReference ref = database.getReference("Register").push();
                        ref.setValue(register);
                    } else {
                        DatabaseReference ref = database.getReference("Register").child(key);
                        ref.setValue(register);
                    }

                }
            }
        });

    }

    private void createUser() {

        progressBar.setVisibility(View.VISIBLE);

        final String email, password;
        email = edtEmail.getText().toString();
        password = edtCreatePass.getText().toString();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter Email..", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please Enter Password..", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                        if (password.length() < 6) {
                            Toast.makeText(RegisterActivity.this, "Please enter 6 digit password", Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();

                            Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                            progressBar.setVisibility(View.GONE);
                        }


                    }
                });
    }
}