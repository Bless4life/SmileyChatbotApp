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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    //Variables
    Button register;
    TextView txt_welcome, txt, txt_login;
    ImageView logo;
    TextInputEditText etRegEmail, etRegPassword, etRegName;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);

        register = (Button) findViewById(R.id.btn_register);
        txt_login = (TextView) findViewById(R.id.text_login);
        logo = (ImageView) findViewById(R.id.logo_image);
        txt_welcome = (TextView) findViewById(R.id.txt_welcome);
        txt = (TextView) findViewById(R.id.text);

        etRegEmail = (TextInputEditText) findViewById(R.id.etRegEmail);
        etRegPassword = (TextInputEditText) findViewById(R.id.etRegPass);
        etRegName = (TextInputEditText) findViewById(R.id.etRegFullName);

        //Calls the register page using intent
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity();
            }
        });

        register.setOnClickListener(view -> {
            createUser();
        });

        mAuth = FirebaseAuth.getInstance();


    }

    private void createUser(){
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();
        String fullName = etRegName.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";//email pattern

        //password pattern
        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        /*
        ^  #start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
        (?=\\S+$)          # no whitespace allowed in the entire string
        .{4,}             # anything, at least six places though
        $

         */
        //FullName
        if (TextUtils.isEmpty(fullName)) {
            etRegName.setError("Please enter your full Name");
            etRegName.requestFocus();

            //email
        }else if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email cannot be empty");
            etRegEmail.requestFocus();

            //if the email does not match the email pattern, then its invalid.
        }else if(!email.matches(emailPattern)){
            etRegEmail.setError("invalid email");
            etRegEmail.requestFocus();
        }
        //password
        else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password cannot be empty");
            etRegPassword.requestFocus();

        }else if(!password.matches(passPattern)){
            etRegPassword.setError("Your password is too weak");
            etRegPassword.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegisterActivity.this, "You are now registered!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    //takes user to login
    public void LoginActivity(){
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        startActivity(LoginIntent);
    }


}