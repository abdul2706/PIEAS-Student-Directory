package com.example.abdul.pieasstudentdirectory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    public static final int REGISTRATION_ACTIVITY = 1;
    private final int[] viewsID = {R.id.studentNameEditText, R.id.fatherNameEditText, R.id.regNoEditText, R.id.batchEditText,
            R.id.departmentEditText, R.id.semesterEditText, R.id.roomEditText, R.id.hostelEditText, R.id.cgpaEditText, R.id.ageEditText,
            R.id.genderEditText, R.id.bloodGroupEditText, R.id.contactNoEditView, R.id.emailEditText, R.id.addressEditText};

    private MainActivity mainActivity;
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private String[] inputData = new String[Student.STUDENT_KEYS.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(R.string.registration_form);

        mainActivity = MainActivity.getContext();
        initViews();
    }

    public void initViews() {
        inputEditTexts.clear();
        for (int aViewsID : viewsID) {
            inputEditTexts.add((EditText) findViewById(aViewsID));
        }
    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Add")) {
            setInputData();
            if (allFieldsFilled()) {
                if (isValidData()) {
                    Student student = new Student(inputData);
                    mainActivity.insertStudent(student);
                    mainActivity.getStudentFromDatabase();
                    mainActivity.notifyDataSetChanged();
                    finish();
                } else {
                    Toast.makeText(this, "Invalid Data Provided", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "All Input Fields Not Provided", Toast.LENGTH_SHORT).show();
            }
        } else if (clickedButton.getText().equals("Cancel")) {
            finish();
        }
    }

    public void setInputData() {
        for (int i = 0; i < inputData.length; i++) {
            inputData[i] = inputEditTexts.get(i).getText().toString();
        }
    }

    public boolean allFieldsFilled() {
        for (String input : inputData) {
            if (input.equals("")) {
                return false;
            } else {
                int chars = 0;
                for (int j = 0; j < input.length(); j++) {
                    if (input.charAt(j) != ' ') {
                        chars++;
                        break;
                    }
                }
                if (chars == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidData() {
        return Student.isValidHostel(inputData[7].toUpperCase()) &&
                Student.isValidPhoneNo(inputData[12]) &&
                Student.isValidSemester(inputData[5]) &&
                Student.isValidDepartment(inputData[4]) &&
                Student.isValidRegNo(inputData[2]) &&
                Student.isValidGender(inputData[10]) &&
                Student.isValidCGPA(inputData[8]);
    }

}
