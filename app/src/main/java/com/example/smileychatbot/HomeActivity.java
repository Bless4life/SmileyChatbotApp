package com.example.smileychatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ImageButton bot, walk, music, game, diary, phone, profile, exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bot = (ImageButton) findViewById(R.id.imgBtn_bot);
        walk = (ImageButton) findViewById(R.id.imgBtn_walk);
        music = (ImageButton) findViewById(R.id.imgBtn_music);
        game = (ImageButton) findViewById(R.id.imgBtn_game);
        diary = (ImageButton) findViewById(R.id.imgBtn_diary);
        phone = (ImageButton) findViewById(R.id.imgBtn_phone);
        profile = (ImageButton) findViewById(R.id.imgBtn_profile);
        exit = (ImageButton) findViewById(R.id.imgBtn_exit);



        //takes the user to google playStore for recommended games
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this, "Welcome to google play store! You must sign in to check out the games selected just for YOU", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(
                        "https://play.google.com/store/apps/category/GAME_ACTION"));
                intent.setPackage("com.android.vending");
                startActivity(intent);
            }
        });

        //signs the user out of the app
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(HomeActivity.this, "You are now logged out", Toast.LENGTH_SHORT).show();
                MainActivity();
            }
        });
    }


    //takes user back to the front page
    public void MainActivity(){
        Intent MainIntent = new Intent(this, MainActivity.class);
        MainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(MainIntent);
        finish();
    }
}