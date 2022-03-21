package com.example.smileychatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.model.FieldIndex;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    List<Model> modelList = new ArrayList<>();
    RecyclerView mRecyclerView;

    //layout manager for recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton mAddBtn;

    //Firestore instance
    FirebaseFirestore db;

    CustomAdapter adapter;

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        //initialize views
        mRecyclerView = findViewById(R.id.recycler_view);

        mAddBtn = (FloatingActionButton) findViewById(R.id.addBtn);

        db = FirebaseFirestore.getInstance();

        pd = new ProgressDialog(this);

        //set recycler view properties
        mRecyclerView.setHasFixedSize(true);
        layoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //show data in recycleView
        showData();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShowActivity.this, DiaryActivity.class));
                finish();
            }
        });
    }

    private void showData(){
        db.collection("Diary")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        //called when data is retrieved
                        pd.dismiss();

                        //show data
                        for (DocumentSnapshot doc: task.getResult()){
                            Model model = new Model(doc.getString("id"),
                                    doc.getString("title"),
                                    doc.getString("description"));
                            modelList.add(model);
                        }

                        //adapter
                        adapter = new CustomAdapter(ShowActivity.this, modelList);
                        //set adapter to recyclerview
                        mRecyclerView.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //call when there is any error while retrieving
                        pd.dismiss();
                        Toast.makeText(ShowActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}