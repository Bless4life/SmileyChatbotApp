package com.example.smileychatbot;

import static android.os.Build.VERSION.SDK_INT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    //Variables
    Button Login, register;
    TextView login_txt;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);

        logo = (ImageView) findViewById(R.id.logo_image);
        login_txt = (TextView) findViewById(R.id.txt_login);
        register = (Button) findViewById(R.id.button_register);
        Login = (Button) findViewById(R.id.btn_login);

        //Calls the register page using intent
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity();
            }

        });

    }

    //takes user to Register
    public void RegisterActivity(){
        Intent RegisterIntent = new Intent(this, RegisterActivity.class);
        startActivity(RegisterIntent);
    }

}