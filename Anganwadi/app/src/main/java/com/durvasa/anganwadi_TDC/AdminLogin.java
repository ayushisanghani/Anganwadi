package com.durvasa.anganwadi_TDC;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AdminLogin extends AppCompatActivity {

    Button loginbtn;

    EditText edtUser, edtPassword;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        loginbtn = findViewById(R.id.btnLoginAdmin);
        edtUser = findViewById(R.id.edtUsernameAdmin);
        edtPassword = findViewById(R.id.edtPasswordAdmin);



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                LoginAdmin();
            }

        });


    }
    private void LoginAdmin(){

        if (edtUser.getText().toString().equals("user") && edtPassword.getText().toString().equals("1234")) {
            Toast.makeText(AdminLogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AdminLogin.this, AdminDashboard.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(AdminLogin.this, "Login Failed!", Toast.LENGTH_SHORT).show();
        }
    }


}


