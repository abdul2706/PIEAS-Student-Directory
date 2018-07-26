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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Student> studentArrayList;
    private MainActivity mainActivity;

    public CustomAdapter(MainActivity mainActivity, ArrayList<Student> studentsList) {
        this.mainActivity = mainActivity;
        studentArrayList = studentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "position -> " + position);
        holder.imageView.setImageResource(R.drawable.android_logo);
        holder.nameTextView.setText(studentArrayList.get(position).getStudentName());
        holder.departTextView.setText(studentArrayList.get(position).getDepartment());
        holder.regNoTextView.setText(studentArrayList.get(position).getRegNo());

        final int index = position;
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.getContext(), "index -> " + index, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.getContext(), ShowStudentActivity.class);
                intent.putExtra("index", index);
                mainActivity.startActivityForResult(intent, ShowStudentActivity.SHOW_STUDENT_ACTIVITY);
            }
        });

        holder.relativeLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(MainActivity.getContext(), "index -> " + index, Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView nameTextView;
        TextView departTextView;
        TextView regNoTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.itemRelativeLayout);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            departTextView = itemView.findViewById(R.id.departmentTextView);
            regNoTextView = itemView.findViewById(R.id.regTextView);
        }
    }
}
