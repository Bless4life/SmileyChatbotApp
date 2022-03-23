package com.example.smileychatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapDirectionActivity extends AppCompatActivity {

    //Initialize variable
    EditText etPlace, etDestination;
    Button btnTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_direction);

        //Assign variable
        etPlace = (EditText) findViewById(R.id.et_place);
        etDestination = (EditText) findViewById(R.id.et_destination);
        btnTrack = (Button) findViewById(R.id.btn_track);

        btnTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sPlace = etPlace.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //check condition
                if(sPlace.equals("") && sDestination.equals("")){
                    //when both value blank
                    Toast.makeText(getApplicationContext()
                    , "Enter both location", Toast.LENGTH_SHORT).show();
                }else{
                    //when both value fill
                    //display route
                    DisplayRoute(sPlace, sDestination);
                }
            }
        });
    }

    private void DisplayRoute(String sPlace, String sDestination) {
        //if the device does not have a map installed then redirect it to play store
        try{
            //when google map is installed
            //Initialize uri
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + sPlace + "/"
                + sDestination);

            //Initialize intent with action view
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);

            //set package
            mapIntent.setPackage("com.google.android.apps.maps");

            //set flag
            mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //start activity
            startActivity(mapIntent);

        }catch(ActivityNotFoundException e){
            //when google map is not installed
            //Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");

            //Initialize intent
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            //set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //start activity
            startActivity(intent);
        }
    }
}