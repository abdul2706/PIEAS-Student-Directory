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
        setCustomAdapter(new CustomAdapter(this, studentArrayList));
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
            case R.id.showAll:
                Log.i("MainActivity", "onOptionsItemSelected" + "case showAll");
                setCustomAdapter(new CustomAdapter(this, studentArrayList));
                break;
            case R.id.search:
                Log.i("MainActivity", "onOptionsItemSelected" + "case search");
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("studentArrayList", studentArrayList);
                startActivityForResult(intent, SearchActivity.SEARCH_ACTIVITY);
                break;
            case R.id.add:
                Log.i("MainActivity", "onOptionsItemSelected" + "case add");
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivityForResult(intent, RegistrationActivity.REGISTRATION_ACTIVITY);
                break;
//            case R.id.remove:
//                Log.i("MainActivity", "onOptionsItemSelected" + "case remove");
//                customAdapter.notifyDataSetChanged();
//                studentArrayList.remove(0);
//                break;
            case R.id.about:
                Log.i("MainActivity", "onOptionsItemSelected" + "case about");
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("About")
                        .setMessage("This app is for PIEAS University")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("", null)
                        .show();
                break;
            default:
                Log.i("MainActivity", "onOptionsItemSelected" + "Returning False (default)");
                return false;
        }
        Log.i("MainActivity", "Returning True");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MainActivity", "onActivityResult" + "requestCode -> " + requestCode);
        Log.i("MainActivity", "onActivityResult" + "resultCode -> " + resultCode);
        Toast.makeText(this, "requestCode -> " + requestCode + "; resultCode -> " + resultCode, Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case RegistrationActivity.REGISTRATION_ACTIVITY:
                break;
            case ShowStudentActivity.SHOW_STUDENT_ACTIVITY:
                break;
            case SearchActivity.SEARCH_ACTIVITY:
                break;
            default:
        }
    }

    public void addStudent(Student student) {
        this.studentArrayList.add(student);
    }

    public void notifyDataSetChanged() {
        customAdapter.notifyDataSetChanged();
    }

    public void setCustomAdapter(CustomAdapter customAdapter) {
        this.customAdapter = customAdapter;
        recyclerView.setAdapter(this.customAdapter);
    }

}
