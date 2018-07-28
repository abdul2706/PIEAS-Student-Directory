package com.example.abdul.pieasstudentdirectory;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class SearchActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    public static final int SEARCH_ACTIVITY = 3;
    private final int[] viewsID = {R.id.studentNameEditText, R.id.fatherNameEditText, R.id.regNoEditText, R.id.batchEditText,
            R.id.departmentEditText, R.id.semesterEditText, R.id.roomEditText, R.id.hostelEditText, R.id.cgpaEditText, R.id.ageEditText,
            R.id.genderEditText, R.id.bloodGroupEditText, R.id.contactNoEditView, R.id.emailEditText, R.id.addressEditText};

    private MainActivity mainActivity;
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private ArrayList<String> searchTags = new ArrayList<>();
    private ArrayList<String> databaseColumns = new ArrayList<>();
    private ArrayList<Student> studentList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle(R.string.search_student);

        mainActivity = MainActivity.getContext();
        studentList = mainActivity.getStudentArrayList();
        initViews();
    }

    public void initViews() {
        inputEditTexts.clear();
        for (int aViewsID : viewsID) {
            inputEditTexts.add((EditText) findViewById(aViewsID));
        }

        Collections.addAll(databaseColumns, Student.STUDENT_KEYS);
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
        String[] values = new String[Student.STUDENT_KEYS.length];
        Cursor cursor = mainActivity.selectStudent(query);
        cursor.moveToFirst();
        if (cursor.getCount() != 0) {
            studentList.clear();
            Toast.makeText(this, cursor.getCount() + " Match Found", Toast.LENGTH_SHORT).show();
            do {
                for (int i = 1; i < cursor.getColumnNames().length; i++) {
                    values[i - 1] = cursor.getString(i);
                }
                studentList.add(new Student(values));
            } while (cursor.moveToNext());
            mainActivity.notifyDataSetChanged();
            finish();
        } else {
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
