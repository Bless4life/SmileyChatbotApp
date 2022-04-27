package com.example.smileychatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ImageButton bot, walk, music, game, diary, phone, weather, exit;
    Button chat;
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
        weather = (ImageButton) findViewById(R.id.imgBtn_weather);
        exit = (ImageButton) findViewById(R.id.imgBtn_exit);

        chat = (Button) findViewById(R.id.btn_talk);

        //user clicks the "talk to me" to chat with the IBM Watson
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatBotActivity();
            }
        });

        //user can view the chat bot using IBM Watson
        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatBotActivity();
            }
        });
        //user can click to view map and go for a walk
        walk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MapTrackActivity();
            }
        });

        //takes the user to youtube to listen to music
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayMusicActivity();
            }
        });

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

        //user can click to create /save/deletes a diary
        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DiaryActivity();
            }
        });

        //user can click to see the list of help services and can call
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallActivity();
            }
        });

        //user can click to view the weather
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WeatherActivity();
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

    //takes user to the list of helpline services and dial activity
    public void CallActivity(){
        Intent callIntent = new Intent(this, CallActivity.class);
        startActivity(callIntent);
    }

    //takes user to music Activity where user plays song
    public void PlayMusicActivity(){
        Intent musicIntent = new Intent(this, MusicActivity.class);
        startActivity(musicIntent);
    }

    //takes user to diary activity
    public void DiaryActivity(){
        Intent diaryIntent = new Intent(this, DiaryActivity.class);
        startActivity(diaryIntent);
    }

    //takes user to the map activity
    private void MapTrackActivity() {
        Intent MapIntent = new Intent(this, MapDirectionActivity.class);
        startActivity(MapIntent);
    }

    //takes user to the chat bot activity
    private void chatBotActivity() {
        Intent ChatBotIntent = new Intent(this, ChatBotActivity.class);
        startActivity(ChatBotIntent);
    }

    //takes user to the weather activity
    public void WeatherActivity(){
        Intent WeatherIntent = new Intent(this, WeatherActivity.class);
        startActivity(WeatherIntent);
    }
}