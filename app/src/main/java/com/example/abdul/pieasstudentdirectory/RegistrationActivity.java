package com.example.abdul.pieasstudentdirectory;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrationActivity extends AppCompatActivity {

    private MainActivity mainActivity;
    private String[] labelStrings = { "Name : ", "Registration# : ", "Department : ", "Phone# : ", "Gender : " };
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private Button addButton, cancelButton;
    private ArrayList<String> inputData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toast.makeText(MainActivity.getContext(), "RegistrationActivity Started", Toast.LENGTH_SHORT).show();
        Log.i("RegistrationActivity", "onCreate");
        mainActivity = MainActivity.getContext();

        inputEditTexts.add((EditText)findViewById(R.id.nameEditText));
        inputEditTexts.add((EditText)findViewById(R.id.regNoEditText));
        inputEditTexts.add((EditText)findViewById(R.id.departmentEditText));
        inputEditTexts.add((EditText)findViewById(R.id.phoneNoEditView));
        inputEditTexts.add((EditText)findViewById(R.id.genderEditText));
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Add")) {
            Log.i("actionPerformed", "Add Button Clicked");
            setInputData();
            if (allFieldsFilled() && validateInputData()) {
                Toast.makeText(MainActivity.getContext(), "Student Added", Toast.LENGTH_SHORT).show();
                String stringStudent = getInputDataAsString();
                mainActivity.addStudent(Student.parseStringToStudent(stringStudent));
                mainActivity.notifyDataSetChanged();
                finish();
            } else {
                Toast.makeText(MainActivity.getContext(), "All Input Fields Not Provided", Toast.LENGTH_SHORT).show();
            }
        } else if (clickedButton.getText().equals("Cancel")) {
            Log.i("actionPerformed", "Cancel Button Clicked");
            finish();
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

    public void setInputData() {
        inputData.clear();
        for(int i = 0; i < inputEditTexts.size(); i++) {
            inputData.add(inputEditTexts.get(i).getText().toString());
        }
    }

    public String getInputDataAsString() {
        String stringData = "";
        for (int i = 0; i < inputData.size(); i++) {
            stringData += inputData.get(i) + ";";
        }
        return stringData;
    }

    public boolean validateInputData() {
        boolean result = true;
        return result;
    }

}
