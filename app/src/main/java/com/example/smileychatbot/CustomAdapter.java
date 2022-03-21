package com.example.smileychatbot;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ViewHolder> {
    ShowActivity showActivity;
    List<Model> modelList;
    Context context;

    public CustomAdapter(ShowActivity showActivity, List<Model> modelList) {
        this.showActivity = showActivity;
        this.modelList = modelList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //inflate layout
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.model_layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder((itemView));

        //handle item clicks here
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //this will be called when user click item

                //show data in toast on clicking
                String title = modelList.get(position).getTitle();
                String desc = modelList.get(position).getDescription();
                Toast.makeText(showActivity, title+"\n"+desc, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                //this will be called when user long click item

                //creating AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(showActivity);
                //options to display in dialog
                String[] options = {"Update", "Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                       if (which == 0){
                           //update is clicked
                           //get data
                           String id = modelList.get(position).getId();
                           String title = modelList.get(position).getTitle();
                           String description = modelList.get(position).getDescription();

                           //intent to start activity
                           Intent intent = new Intent(showActivity, DiaryActivity.class);
                           //put data in intent
                           intent.putExtra("pId", id);
                           intent.putExtra("pTitle", title);
                           intent.putExtra("pDescription", description);

                           //start activity
                           showActivity.startActivity(intent);
                       }
                       if(which == 1){
                           //delete is clicked
                           showActivity.deleteData(position);
                       }
                    }
                }).create().show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //bind views / set data
        viewHolder.mTitleTv.setText(modelList.get(i).getTitle());
        viewHolder.mDescriptionTv.setText(modelList.get(i).getDescription());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
