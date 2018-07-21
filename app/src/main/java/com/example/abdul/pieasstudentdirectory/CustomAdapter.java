package com.example.abdul.pieasstudentdirectory;

import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private View view;
    private ArrayList<Student> studentArrayList;
    private MainActivity mainActivity;

    public CustomAdapter(MainActivity mainActivity, ArrayList<Student> studentsList){
        this.mainActivity = mainActivity;
        studentArrayList = studentsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i("onBindViewHolder", "position -> " + position);
        holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
        holder.nameTextView.setText(studentArrayList.get(position).getName());
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
