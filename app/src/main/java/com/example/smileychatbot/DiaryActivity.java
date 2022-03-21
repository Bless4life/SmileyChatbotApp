package com.example.smileychatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class DiaryActivity extends AppCompatActivity {

    //Views
    EditText mTitleEt, mDescriptionEt;
    Button mSaveBtn, mShowBtn;

    //progress dialog
    ProgressDialog pd;

    //Firestore instance
    FirebaseFirestore db;

    String pId, pTitle, pDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //Initialize views with its xml
        mTitleEt = (EditText) findViewById(R.id.titleET);
        mDescriptionEt = (EditText) findViewById(R.id.descET);
        mSaveBtn = (Button) findViewById(R.id.BtnSave);
        mShowBtn = (Button) findViewById(R.id.BtnShow);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            //Update data
            mSaveBtn.setText("Update");
            //get data
            pId = bundle.getString("pId");
            pTitle = bundle.getString("pTitle");
            pDescription = bundle.getString("pDescription");

            //set data
            mTitleEt.setText(pTitle);
            mDescriptionEt.setText(pDescription);
        }
        else{
            mSaveBtn.setText("Save");
        }

        //progress dialog
        pd = new ProgressDialog(this);

        //Firestore
        db = FirebaseFirestore.getInstance();

        //click button to upload the data
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle1 = getIntent().getExtras();
                if(bundle != null){
                    //updating
                    //input data
                    String id = pId;
                    String title = mTitleEt.getText().toString().trim();
                    String desc = mDescriptionEt.getText().toString().trim();
                    //function call to update data
                    updateData(id, title, desc);
                }
                else{
                    //input data
                    String title = mTitleEt.getText().toString().trim();
                    String desc = mDescriptionEt.getText().toString().trim();
                    //send data
                    uploadData(title, desc);
                }
            }
        });

        mShowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiaryActivity.this, ShowActivity.class));
                Toast.makeText(DiaryActivity.this, "To update/delete, please hold the details long", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void updateData(String id, String title, String desc) {
        //set title of progress bar
        pd.setTitle("Updating your diary");
        pd.show();

        db.collection("Diary").document(id)
                .update("title", title,
                        "description", desc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(DiaryActivity.this, "your diary has been updated", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(DiaryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void uploadData(String title, String desc){
        //set title of progress bar
        pd.setTitle("Add your diary");
        pd.show();

        String id = UUID.randomUUID().toString();

        Map<String, Object> doc = new HashMap<>();
        doc.put("id", id);
        doc.put("title", title);
        doc.put("description", desc);

        //add data to Firestore
        db.collection("Diary").document(id).set(doc)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //data added successfully
                        pd.dismiss();
                        Toast.makeText(DiaryActivity.this, "Your diary is now added", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //data failed to add
                        pd.dismiss();
                        //error message
                        Toast.makeText(DiaryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}