package com.example.smileychatbot;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;


public class LoginActivity extends AppCompatActivity {

    //Variables
    Button Login, register;
    TextView login_txt;
    ImageView logo;
    TextInputEditText etLoginEmail, etLoginPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);

        logo = (ImageView) findViewById(R.id.logo_image);
        login_txt = (TextView) findViewById(R.id.txt_login);
        register = (Button) findViewById(R.id.button_register);
        Login = (Button) findViewById(R.id.btn_login);
        etLoginEmail = (TextInputEditText) findViewById(R.id.etLoginEmail);
        etLoginPassword = (TextInputEditText) findViewById(R.id.etLoginPass);

        //Calls the register page using intent
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity();
            }

        });

        Login.setOnClickListener(view -> {
            loginUser();
        });
        //Initialise Firebase Auth
        mAuth = FirebaseAuth.getInstance();

    }

    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email cannot be empty");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "You are now logged in!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }else{
                        Toast.makeText(LoginActivity.this, "Failed to log in: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    //takes user to Register
    public void RegisterActivity(){
        Intent RegisterIntent = new Intent(this, RegisterActivity.class);
        startActivity(RegisterIntent);
    }


}