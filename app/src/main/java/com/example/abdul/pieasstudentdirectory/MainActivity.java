package com.example.abdul.pieasstudentdirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static MainActivity context;

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        sharedPreferences = getSharedPreferences("com.example.abdul.pieasstudentdirectory", Context.MODE_PRIVATE);

        try {
            studentArrayList = (ArrayList<Student>) ObjectSerializer.deserialize(sharedPreferences.getString("studentArrayList", ObjectSerializer.serialize(new ArrayList<Student>())));
            Log.i("MainActivity", "size -> " + studentArrayList.size());
        } catch (Exception e) {
            Log.i("MainActivity", e.getMessage());
            e.printStackTrace();
        }

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
                Log.i("MainActivity", "onOptionsItemSelected : " + "case showAll");
                setCustomAdapter(new CustomAdapter(this, studentArrayList));
                break;
            case R.id.search:
                Log.i("MainActivity", "onOptionsItemSelected : " + "case search");
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("studentArrayList", studentArrayList);
                startActivityForResult(intent, SearchActivity.SEARCH_ACTIVITY);
                break;
            case R.id.add:
                Log.i("MainActivity", "onOptionsItemSelected : " + "case add");
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivityForResult(intent, RegistrationActivity.REGISTRATION_ACTIVITY);
                break;
            case R.id.about:
                Log.i("MainActivity", "onOptionsItemSelected : " + "case about");
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("About")
                        .setMessage("This app is for PIEAS University")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("", null)
                        .show();
                break;
            default:
                Log.i("MainActivity", "onOptionsItemSelected : " + "Returning False (default)");
                return false;
        }
        Log.i("MainActivity", "onOptionsItemSelected : " + "Returning True");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MainActivity", "onActivityResult : " + "requestCode -> " + requestCode);
        Log.i("MainActivity", "onActivityResult : " + "resultCode -> " + resultCode);
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

    public Student getStudent(int index) {
        return studentArrayList.get(index);
    }

    public void addStudent(Student student) {
        this.studentArrayList.add(student);
        updateSharedPreferences();
    }

    public void updateSharedPreferences() {
        try {
            sharedPreferences.edit().putString("studentArrayList", ObjectSerializer.serialize(studentArrayList)).apply();
        } catch (Exception e) {
            Log.i("MainActivity", "updateSharedPreferences : " + "e.getMessage() -> " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void notifyDataSetChanged() {
        customAdapter.notifyDataSetChanged();
    }

    public void setCustomAdapter(CustomAdapter customAdapter) {
        this.customAdapter = customAdapter;
        recyclerView.setAdapter(this.customAdapter);
    }

}
