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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowStudentActivity extends AppCompatActivity {

    private static final String TAG = "ShowStudentActivity";
    public static final int SHOW_STUDENT_ACTIVITY = 2;
    private static final int CALL_PHONE_PERMISSION = 1;
    private static final int SMS_SEND_PERMISSION = 2;
    private final int[] viewsID = {R.id.studentNameEditText, R.id.fatherNameEditText, R.id.regNoEditText, R.id.batchEditText,
            R.id.departmentEditText, R.id.semesterEditText, R.id.roomEditText, R.id.hostelEditText, R.id.cgpaEditText, R.id.ageEditText,
            R.id.genderEditText, R.id.bloodGroupEditText, R.id.contactNoEditView, R.id.emailEditText, R.id.addressEditText};

    private ImageView studentImageView;
    private ArrayList<TextView> textViews = new ArrayList<>();
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
            setTitle(student.getStudentData("studentName"));
            setViewsData();
        } else {
            Toast.makeText(ShowStudentActivity.this, "Invalid Student Index", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initViews() {
        textViews.clear();
        studentImageView = findViewById(R.id.studentImageView);
        for (int aViewsID : viewsID) {
            textViews.add((TextView) findViewById(aViewsID));
        }

        for (int i = 0; i < viewsID.length; i++) {
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView view = (TextView) v;
                    Toast.makeText(ShowStudentActivity.this, view.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setViewsData() {
        studentImageView.setImageResource(R.drawable.pieas_logo);
        for(int i = 0; i < viewsID.length; i++){
            textViews.get(i).setText(student.getStudentData(Student.STUDENT_KEYS[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_showstudent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.callBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + student.getStudentData("phoneNo")));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
                }
                break;
            case R.id.msgBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_SENDTO);
                    callIntent.setData(Uri.fromParts("sms", student.getStudentData("phoneNo"), null));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
                }
                break;
            case R.id.edit:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            default:
                Toast.makeText(this, "Invalid Menu Item Selected", Toast.LENGTH_SHORT).show();
                return false;
        }
        return true;
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
