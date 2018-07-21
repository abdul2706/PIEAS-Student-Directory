package com.example.abdul.pieasstudentdirectory;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static MainActivity context;

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        studentArrayList.add(new Student("ark", "03-3-1-001-2017", "BSME", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman", "03-3-1-002-2017", "EE", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman Khan", "03-3-1-003-2017", "cis", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul", "03-3-1-001-2017", "BSME", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman", "03-3-1-002-2017", "EE", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman Khan", "03-3-1-003-2017", "cis", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul", "03-3-1-001-2017", "BSME", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman", "03-3-1-002-2017", "EE", "0331-1205526", "Male"));
        studentArrayList.add(new Student("Abdul Rehman Khan", "03-3-1-003-2017", "cis", "0331-1205526", "Male"));

        recyclerView = findViewById(R.id.studentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter(this, studentArrayList);
        recyclerView.setAdapter(customAdapter);
    }

    public static MainActivity getContext() {
        return context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch (item.getItemId()) {
            case R.id.search:
                Log.i("onOptionsItemSelected", "case search");
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, SearchActivity.SEARCH_ACTIVITY);
                break;
            case R.id.add:
                Log.i("onOptionsItemSelected", "case add");
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivityForResult(intent, RegistrationActivity.REGISTRATION_ACTIVITY);
                break;
            case R.id.remove:
                Log.i("onOptionsItemSelected", "case remove");
                studentArrayList.remove(0);
                customAdapter.notifyDataSetChanged();
//                intent = new Intent(getApplicationContext(), ShowStudentActivity.class);
//                startActivity(intent);
                break;
            case R.id.about:
                Log.i("onOptionsItemSelected", "case about");
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("About")
                        .setMessage("This app is for PIEAS University")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("", null)
                        .show();
                break;
            default:
                Log.i("onOptionsItemSelected", "Returning False (default)");
                return false;
        }
        Log.i("onOptionsItemSelected", "Returning True");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("onActivityResult", "requestCode -> " + requestCode);
        Log.i("onActivityResult", "resultCode -> " + resultCode);
        Toast.makeText(this, "requestCode -> " + requestCode + "; resultCode -> " + resultCode, Toast.LENGTH_SHORT).show();
    }

    public void addStudent(Student student){
        this.studentArrayList.add(student);
    }

    public void notifyDataSetChanged(){
        customAdapter.notifyDataSetChanged();
    }

}
