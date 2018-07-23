package com.example.abdul.pieasstudentdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowStudentActivity extends AppCompatActivity {

    public static final int SHOW_STUDENT_ACTIVITY = 2;

    private MainActivity mainActivity;
    private ImageView studentImageView;
    private TextView studentNameTextView, fatherNameTextView, roomAndHostelTextView, permanentAddressTextView, bloodGroupTextView,
            contactTextView, semesterTextView, batchTextView, departmentTextView, emailTextView, regNoTextView, genderTextView;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);

        Toast.makeText(MainActivity.getContext(), "ShowStudentActivity Started", Toast.LENGTH_SHORT).show();
        Log.i("ShowStudentActivity", "onCreate");
        mainActivity = MainActivity.getContext();
        initViews();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        Toast.makeText(MainActivity.getContext(), "index -> " + index, Toast.LENGTH_SHORT).show();
        if (index != -1) {
            student = mainActivity.getStudent(index);
            setViews();
        } else {
            Toast.makeText(MainActivity.getContext(), "Invalid Student Index", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        studentImageView = findViewById(R.id.studentImageView);
        studentNameTextView = findViewById(R.id.studentNameTextView);
        fatherNameTextView = findViewById(R.id.fatherNameTextView);
        roomAndHostelTextView = findViewById(R.id.roomAndHostelTextView);
        permanentAddressTextView = findViewById(R.id.permanentAddressTextView);
        bloodGroupTextView = findViewById(R.id.bloodGroupTextView);
        contactTextView = findViewById(R.id.contactTextView);
        semesterTextView = findViewById(R.id.semesterTextView);
        batchTextView = findViewById(R.id.batchTextView);
        departmentTextView = findViewById(R.id.departmentTextView);
        emailTextView = findViewById(R.id.emailTextView);
        regNoTextView = findViewById(R.id.regNoTextView);
        genderTextView = findViewById(R.id.genderTextView);

        studentNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        fatherNameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        roomAndHostelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        permanentAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        bloodGroupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        contactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        semesterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        batchTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        departmentTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        regNoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        genderTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setViews() {
        studentImageView.setImageResource(R.drawable.android_logo);
        studentNameTextView.setText(student.getStudentName());
        fatherNameTextView.setText(student.getFatherName());
        roomAndHostelTextView.setText(student.getHostel() + "-" + student.getRoomNo());
        permanentAddressTextView.setText(student.getAddress());
        bloodGroupTextView.setText("Blood Group : " + student.getBloodGroup());
        contactTextView.setText(student.getPhoneNo());
        semesterTextView.setText(student.getSemester());
        batchTextView.setText(student.getBatch());
        departmentTextView.setText(student.getDepartment());
        emailTextView.setText(student.getEmail());
        regNoTextView.setText(student.getRegNo());
        genderTextView.setText(student.getGender());
    }

    public void goBack(View view) {
        finish();
    }

}
