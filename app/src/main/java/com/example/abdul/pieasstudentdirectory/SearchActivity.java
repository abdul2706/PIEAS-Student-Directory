package com.example.abdul.pieasstudentdirectory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    public static final int SEARCH_ACTIVITY = 3;
    private MainActivity mainActivity;
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private ArrayList<String> searchTags = new ArrayList<>();
    private ArrayList<String> databaseColumns = new ArrayList<>();
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mainActivity = MainActivity.getContext();
        studentList = mainActivity.getStudentArrayList();
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

//        studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester, batch, department, email, regNo, gender
        databaseColumns.add("studentName");
        databaseColumns.add("fatherName");
        databaseColumns.add("roomNo");
        databaseColumns.add("hostel");
        databaseColumns.add("address");
        databaseColumns.add("bloodGroup");
        databaseColumns.add("phoneNo");
        databaseColumns.add("semester");
        databaseColumns.add("batch");
        databaseColumns.add("department");
        databaseColumns.add("email");
        databaseColumns.add("regNo");
        databaseColumns.add("gender");
    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Search")) {
            setSearchTags();
            searchSQLiteDatabase(createQuery());
        } else if (clickedButton.getText().equals("Cancel")) {
            finish();
        }
    }

    public String createQuery() {
        StringBuilder queryBuilder = new StringBuilder();
        String queryPart;
        boolean firstAND = false;
        for (int i = 0; i < searchTags.size(); i++) {
            if(!searchTags.get(i).equals("")) {
                if (i > 0 && firstAND) {
                    queryBuilder.append(" AND ");
                }
                queryPart = databaseColumns.get(i) + " LIKE UPPER('%" + searchTags.get(i) + "%')";
                queryBuilder.append(queryPart);
                firstAND = true;
            }
        }
        return queryBuilder.toString();
    }

    public void searchSQLiteDatabase(String query) {
        Cursor cursor = mainActivity.selectStudent(query);
        StringBuilder studentAsString;
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            studentList.clear();
            Toast.makeText(this, cursor.getCount() + " Match Found", Toast.LENGTH_SHORT).show();
            do {
                studentAsString = new StringBuilder();
                for (int i = 1; i < cursor.getColumnNames().length; i++) {
                    studentAsString.append(cursor.getString(i)).append(";");
                }
                studentList.add(Student.parseStringToStudent(studentAsString.toString()));
            } while (cursor.moveToNext());
            mainActivity.notifyDataSetChanged();
            finish();
        } else {
            Log.i(TAG, "searchSQLiteDatabase : " + "No Match Found");
            Toast.makeText(this, "No Match Found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public void setSearchTags() {
        searchTags.clear();
        for (int i = 0; i < inputEditTexts.size(); i++) {
            searchTags.add(inputEditTexts.get(i).getText().toString());
        }
    }

}
