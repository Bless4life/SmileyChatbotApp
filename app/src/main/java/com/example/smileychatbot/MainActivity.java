package com.example.smileychatbot;

/**
 * Blessing Adeniji B00117254
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Variables
    Button register;
    TextView text_login, txt_botName, txt_slogan, txt2;
    ImageView logo;

    //Add animations
    Animation topAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);

        logo = (ImageView) findViewById(R.id.logo_image);
        txt_botName = (TextView) findViewById(R.id.text_botName);
        txt_slogan = (TextView) findViewById(R.id.slogan);
        txt2 = (TextView) findViewById(R.id.text2);

        register = (Button) findViewById(R.id.btn_register);
        text_login = (TextView) findViewById(R.id.login_text);

        //Calls the register page using intent
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity();
            }
        });

        //Calls the login page using intent
        text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity();
            }
        });

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        //set animations on the page
        logo.setAnimation(topAnim);
        txt_botName.setAnimation(bottomAnim);
        txt_slogan.setAnimation(bottomAnim);
        txt2.setAnimation(bottomAnim);
        register.setAnimation(bottomAnim);
        text_login.setAnimation(bottomAnim);

    }

    //takes user to login
    public void LoginActivity(){
        Intent LoginIntent = new Intent(this, LoginActivity.class);
        startActivity(LoginIntent);
    }

    //takes user to login
    public void RegisterActivity(){
        Intent RegisterIntent = new Intent(this, RegisterActivity.class);
        startActivity(RegisterIntent);
    }

}