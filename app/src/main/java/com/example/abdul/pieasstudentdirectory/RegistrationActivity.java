package com.example.abdul.pieasstudentdirectory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "RegistrationActivity";
    public static final int REGISTRATION_ACTIVITY = 1;
    private MainActivity mainActivity;
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private ArrayList<String> inputData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mainActivity = MainActivity.getContext();
        initViews();
    }

    public void initViews() {
        inputEditTexts.clear();
        inputEditTexts.add((EditText) findViewById(R.id.studentNameEditText));
        inputEditTexts.add((EditText) findViewById(R.id.fatherNameEditText));
        inputEditTexts.add((EditText) findViewById(R.id.roomEditText));
        inputEditTexts.add((EditText) findViewById(R.id.hostelEditText));
        inputEditTexts.add((EditText) findViewById(R.id.addressEditText));
        inputEditTexts.add((EditText) findViewById(R.id.bloodGroupEditText));
        inputEditTexts.add((EditText) findViewById(R.id.contactNoEditView));
        inputEditTexts.add((EditText) findViewById(R.id.semesterEditText));
        inputEditTexts.add((EditText) findViewById(R.id.batchEditText));
        inputEditTexts.add((EditText) findViewById(R.id.departmentEditText));
        inputEditTexts.add((EditText) findViewById(R.id.emailEditText));
        inputEditTexts.add((EditText) findViewById(R.id.regNoEditText));
        inputEditTexts.add((EditText) findViewById(R.id.genderEditText));
    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Add")) {
            setInputData();
            if (allFieldsFilled()) {
                if (isValidData()) {
                    Student student = new Student(inputData.get(0), inputData.get(1), inputData.get(2), inputData.get(3), inputData.get(4), inputData.get(5), inputData.get(6), inputData.get(7), inputData.get(8), inputData.get(9), inputData.get(10), inputData.get(11), inputData.get(12));
                    mainActivity.insertStudent(student);
                    mainActivity.getStudentFromDatabase();
                    mainActivity.notifyDataSetChanged();
                    Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
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
        inputData.clear();
        for (int i = 0; i < inputEditTexts.size(); i++) {
            inputData.add(inputEditTexts.get(i).getText().toString());
        }
    }

    public boolean allFieldsFilled() {
        for (int i = 0; i < inputData.size(); i++) {
            String input = inputData.get(i);
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
        return Student.isValidHostel(inputData.get(3).toUpperCase()) &&
                Student.isValidPhoneNo(inputData.get(6)) &&
                Student.isValidSemester(inputData.get(7)) &&
                Student.isValidDepartment(inputData.get(9)) &&
                Student.isValidRegNo(inputData.get(11)) &&
                Student.isValidGender(inputData.get(12));
    }

}
