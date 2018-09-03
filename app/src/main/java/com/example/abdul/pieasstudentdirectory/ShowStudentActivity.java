package com.example.abdul.pieasstudentdirectory;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowStudentActivity extends AppCompatActivity {

    private static final String TAG = "ShowStudentActivity";
    public static final int SHOW_STUDENT_ACTIVITY = 2;
    private static final int CALL_PHONE_PERMISSION = 1;
    private static final int SMS_SEND_PERMISSION = 2;
    public static final int REQUEST_IMAGE_SELECT = 3;
    private final int[] viewsID = {R.id.studentNameEditText, R.id.fatherNameEditText, R.id.regNoEditText, R.id.batchEditText,
            R.id.departmentEditText, R.id.semesterEditText, R.id.roomEditText, R.id.hostelEditText, R.id.cgpaEditText, R.id.ageEditText,
            R.id.genderEditText, R.id.bloodGroupEditText, R.id.contactNoEditView, R.id.emailEditText, R.id.addressEditText};

    private ImageView studentImageView;
    private ArrayList<TextView> textViews = new ArrayList<>();
    private Student student;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showstudent);

        mainActivity = MainActivity.getContext();
        initViews();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
//        Toast.makeText(ShowStudentActivity.this, "index -> " + index, Toast.LENGTH_SHORT).show();
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

        studentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowStudentActivity.this, LargeUserImage.class);
                intent.putExtra("studentName", student.getStudentData("studentName"));
                intent.putExtra("studentImageURL", student.getPhoto().getPhotoURL());
                startActivity(intent);
            }
        });
        for (int i = 0; i < viewsID.length; i++) {
            final int index = i;
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView view = (TextView) v;
                    Toast.makeText(ShowStudentActivity.this, view.getText().toString(), Toast.LENGTH_SHORT).show();
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    final EditText editText = new EditText(ShowStudentActivity.this);
                    editText.setLayoutParams(layoutParams);
                    new AlertDialog.Builder(ShowStudentActivity.this)
                            .setView(editText)
                            .setIcon(android.R.drawable.stat_sys_warning)
                            .setTitle("Old Value: " + view.getText().toString())
                            .setMessage("Enter New Value: ")
                            .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String newValue = editText.getText().toString();
                                    Toast.makeText(ShowStudentActivity.this, "new value: " + newValue, Toast.LENGTH_SHORT).show();
                                    mainActivity.updateStudent(student, Student.STUDENT_KEYS[index], newValue);
                                    student.setStudentData(Student.STUDENT_KEYS[index], newValue);
                                    setViewsData();
                                    mainActivity.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
            });
        }
    }

    public void setViewsData() {
//        studentImageView.setImageResource(R.drawable.man_icon);
        try {
            Photo photoItem = student.getPhoto();
            Picasso.get().load(photoItem.getPhotoURL() + "_m.jpg")
                    .error(R.drawable.man_icon)
                    .placeholder(R.drawable.man_icon)
                    .into(studentImageView);
        } catch (Exception e) {
            studentImageView.setImageResource(R.drawable.man_icon);
        }
        for (int i = 0; i < viewsID.length; i++) {
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
        switch (item.getItemId()) {
            case R.id.selectImage:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, REQUEST_IMAGE_SELECT);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_IMAGE_SELECT);
                }
                break;
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
        } else if (requestCode == SMS_SEND_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Permission Granted : " + permissions[0], Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_IMAGE_SELECT && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Permission Granted : " + permissions[0], Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                studentImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
