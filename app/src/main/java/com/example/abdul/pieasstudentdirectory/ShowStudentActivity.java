package com.example.abdul.pieasstudentdirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;

public class ShowStudentActivity extends AppCompatActivity {

    private static final String TAG = "ShowStudentActivity";
    public static final int SHOW_STUDENT_ACTIVITY = 2;
    private static final int CALL_PHONE_PERMISSION = 1;
    private static final int SMS_SEND_PERMISSION = 2;

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

    public void actionPerformed(View view) {
        if (view.getId() == R.id.callBtn) {
            Log.i(TAG, "actionPerformed: callBtn Clicked");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + student.getPhoneNo()));
                startActivity(callIntent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
            }
        } else if (view.getId() == R.id.msgBtn) {
            Log.i(TAG, "actionPerformed: msgBtn Clicked");

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                Intent callIntent = new Intent(Intent.ACTION_SENDTO);
                callIntent.setData(Uri.fromParts("sms", student.getPhoneNo(), null));
                startActivity(callIntent);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Permission Granted : " + permissions[0], Toast.LENGTH_SHORT).show();
        } else if(requestCode == SMS_SEND_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Permission Granted : " + permissions[0], Toast.LENGTH_SHORT).show();
        }
    }
}
