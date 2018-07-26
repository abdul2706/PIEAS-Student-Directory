package com.example.abdul.pieasstudentdirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowStudentActivity extends AppCompatActivity {

    private static final String TAG = "ShowStudentActivity";
    public static final int SHOW_STUDENT_ACTIVITY = 2;

    private ImageView studentImageView;
    private TextView studentNameTextView, fatherNameTextView, roomAndHostelTextView, permanentAddressTextView, bloodGroupTextView,
            contactTextView, semesterTextView, batchTextView, departmentTextView, emailTextView, regNoTextView, genderTextView;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);

        MainActivity mainActivity = MainActivity.getContext();
        initViews();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        Toast.makeText(ShowStudentActivity.this, "index -> " + index, Toast.LENGTH_SHORT).show();
        if (index != -1) {
            student = mainActivity.getStudent(index);
            setViews();
        } else {
            Toast.makeText(ShowStudentActivity.this, "Invalid Student Index", Toast.LENGTH_SHORT).show();
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

        addClickHandlers(studentNameTextView);
        addClickHandlers(fatherNameTextView);
        addClickHandlers(roomAndHostelTextView);
        addClickHandlers(permanentAddressTextView);
        addClickHandlers(bloodGroupTextView);
        addClickHandlers(contactTextView);
        addClickHandlers(semesterTextView);
        addClickHandlers(batchTextView);
        addClickHandlers(departmentTextView);
        addClickHandlers(emailTextView);
        addClickHandlers(regNoTextView);
        addClickHandlers(genderTextView);

//        studentNameTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        fatherNameTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        roomAndHostelTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        permanentAddressTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        bloodGroupTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        contactTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        semesterTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        batchTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        departmentTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        emailTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        regNoTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        genderTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mainActivity, ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void addClickHandlers(TextView textView) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView view = (TextView) v;
                Toast.makeText(ShowStudentActivity.this, view.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

//        textView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Toast.makeText(ShowStudentActivity.this, "onLongClick Start", Toast.LENGTH_SHORT).show();
//                Log.i(TAG, "onLongClick: Start");
//                final TextView view = (TextView) v;
//                final EditText editText = new EditText(ShowStudentActivity.this);
//                AlertDialog.Builder builder = new AlertDialog.Builder(ShowStudentActivity.this);
//                builder.setTitle("Enter New Value");
//                builder.setMessage("Old Value:" + view.getText());
//                builder.setView(editText);
//                builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.i(TAG, "onClick: which -> " + which);
//                        view.setText(editText.getText());
//                    }
//                });
//                builder.setNegativeButton("Cancel", null);
//                builder.show();
//                Toast.makeText(ShowStudentActivity.this, "onLongClick End", Toast.LENGTH_SHORT).show();
//                Log.i(TAG, "onLongClick: End");
//                return true;
//            }
//        });
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
