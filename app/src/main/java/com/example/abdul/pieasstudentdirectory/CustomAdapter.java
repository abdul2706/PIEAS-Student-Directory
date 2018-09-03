package com.example.abdul.pieasstudentdirectory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private static final String TAG = "CustomAdapter";
    private ArrayList<Student> studentArrayList;
//    private List<Photo> photoList;
    private MainActivity mainActivity;

    CustomAdapter(MainActivity mainActivity, ArrayList<Student> studentsList, List<Photo> photoList) {
        this.mainActivity = mainActivity;
        this.studentArrayList = studentsList;
//        this.photoList = photoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
//        return new ViewHolder(view);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Photo photoItem = studentArrayList.get(position).getPhoto();
            Picasso.get().load(photoItem.getPhotoURL() + "_s.jpg")
                    .error(R.drawable.man_icon)
                    .placeholder(R.drawable.man_icon)
                    .into(holder.imageView);
        } catch (Exception e) {
            holder.imageView.setImageResource(R.drawable.man_icon);
        }
        holder.nameTextView.setText(studentArrayList.get(position).getStudentData("studentName").toUpperCase());
        holder.departTextView.setText(studentArrayList.get(position).getStudentData("department").toUpperCase());
        holder.regNoTextView.setText(studentArrayList.get(position).getStudentData("regNo"));

        final int index = position;
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.getContext(), ShowStudentActivity.class);
                intent.putExtra("index", index);
                mainActivity.startActivityForResult(intent, ShowStudentActivity.SHOW_STUDENT_ACTIVITY);
            }
        });

        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(mainActivity)
                        .setIcon(android.R.drawable.stat_sys_warning)
                        .setTitle("Warning")
                        .setMessage("Do you want to delete selected item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainActivity.deleteStudent(index);
                                mainActivity.getStudentFromDatabase();
                                mainActivity.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

//    public void loadData(List<Photo> newPhotoList) {
//        photoList = newPhotoList;
//        notifyDataSetChanged();
//        Log.d(TAG, "loadData: photoList.size() -> " + photoList.size());
//        mainActivity.notifyDataSetChanged();
//    }

//    public Photo getPhoto(int position) {
//        return ((photoList != null && photoList.size() != 0) ? photoList.get(position) : null);
//    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView nameTextView;
        TextView departTextView;
        TextView regNoTextView;

        ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.itemRelativeLayout);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            departTextView = itemView.findViewById(R.id.departmentTextView);
            regNoTextView = itemView.findViewById(R.id.regTextView);
        }
    }
}
