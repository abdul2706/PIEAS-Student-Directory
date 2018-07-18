package com.example.abdul.pieasstudentdirectory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ShowStudentActivity extends AppCompatActivity {

    public void goBack(View view){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);
        Toast.makeText(MainActivity.getContext(), "ShowStudentActivity Started", Toast.LENGTH_SHORT).show();
        Log.i("ShowStudentActivity", "onCreate");
    }
}
