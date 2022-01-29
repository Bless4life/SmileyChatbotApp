package com.example.smileychatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    //Variables
    Button register;
    TextView txt_welcome, txt, txt_login;
    ImageView logo;

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

        //Calls the register page using intent
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity();
            }
        });
    }

    //takes user to login
    public void LoginActivity(){
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        startActivity(LoginIntent);
    }

}