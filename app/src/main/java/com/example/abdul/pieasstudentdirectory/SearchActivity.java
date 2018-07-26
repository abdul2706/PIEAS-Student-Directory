package com.example.abdul.pieasstudentdirectory;

import android.content.Intent;
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
import java.util.HashMap;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    public static final int SEARCH_ACTIVITY = 3;
    //    "SELECT * FROM studentTable WHERE " + "studentName = 'Abdul Rehman Khan' AND fatherName = 'Tanveer Ahmed Khan' AND roomNo = '207' AND hostel = 'E' AND address = 'khaqan St#1, Arif Colony, Gill Road, GRW' AND bloodGroup = 'A+' AND phoneNo = '03311205526' AND semester = '3' AND batch = '17-21' AND department = 'DCIS' AND email = 'abdulrehmankhan27061998@gmail.com' AND regNo = '03310032017' AND gender = 'M'";
    private MainActivity mainActivity;
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private ArrayList<String> searchTags = new ArrayList<>();
    private ArrayList<String> databaseColumns = new ArrayList<>();

    private HashMap<String, ArrayList<Integer>> matchedIndex;
    private ArrayList<Student> studentList;
    private ArrayList<Student> searchedStudentList = new ArrayList<>();
    private ArrayList<Integer> finalSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        studentList = (ArrayList<Student>) intent.getExtras().get("studentArrayList");
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
            Toast.makeText(MainActivity.getContext(), "Search is Clicked", Toast.LENGTH_SHORT).show();
            setSearchTags();
            Log.i("SearchActivity", "query -> " + createQuery());
            searchSQLiteDatabase(createQuery());

//            matchedIndex = new HashMap<>();
//            setSearchTags();
//
////            for (int i = 0; i < searchTags.size(); i++) {
////                Log.i("SearchActivity", "actionPerformed : " + i + " -> " + searchTags.get(i));
////            }
//
//            matchedIndex.put(searchTags.get(0), searchByName());
//            matchedIndex.put(searchTags.get(1), searchByRegNo());
//            matchedIndex.put(searchTags.get(2), searchByDepartment());
//            matchedIndex.put(searchTags.get(3), searchByPhoneNo());
//            matchedIndex.put(searchTags.get(4), searchByGender());
////            showMap();
//
////            Log.i("SearchActivity", "actionPerformed : " + "Intersection of searchTags.get(0) and searchTags.get(1) -> ");
//            finalSearch = intersect(matchedIndex.get(searchTags.get(0)), matchedIndex.get(searchTags.get(1)));
////            printList(finalSearch);
//
////            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(2) -> ");
//            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(2)));
////            printList(finalSearch);
//
////            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(3) -> ");
//            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(3)));
////            printList(finalSearch);
//
////            Log.i("SearchActivity", "actionPerformed : " + "Intersection of intersect and searchTags.get(4) -> ");
//            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(4)));
////            printList(finalSearch);
//
//            Toast.makeText(MainActivity.getContext(), finalSearch.size() + " Match Found", Toast.LENGTH_SHORT).show();
//            if(finalSearch.size() != 0){
//                setSearchedStudentList();
//                mainActivity.setCustomAdapter(new CustomAdapter(mainActivity, searchedStudentList));
//                finish();
//            }
        } else if (clickedButton.getText().equals("Cancel")) {
            Toast.makeText(MainActivity.getContext(), "Cancel is Clicked", Toast.LENGTH_SHORT).show();
            Log.i("SearchActivity", "actionPerformed : " + "Cancel Button Clicked");
            setIntent(new Intent());
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
        searchedStudentList.clear();
        cursor.moveToFirst();
        Log.i("SearchActivity", "searchSQLiteDatabase : " + "cursor.getCount() -> " + cursor.getCount());
        if (cursor.getCount() != 0) {
            Toast.makeText(mainActivity, cursor.getCount() + " Match Found", Toast.LENGTH_SHORT).show();
            do {
                studentAsString = new StringBuilder();
                for (int i = 1; i < cursor.getColumnNames().length; i++) {
                    studentAsString.append(cursor.getString(i)).append(";");
                }
                searchedStudentList.add(Student.parseStringToStudent(studentAsString.toString()));
            } while (cursor.moveToNext());
        } else {
            Log.i("SearchActivity", "searchSQLiteDatabase : " + "No Match Found");
            Toast.makeText(mainActivity, "No Match Found", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    public void setSearchTags() {
        searchTags.clear();
        for (int i = 0; i < inputEditTexts.size(); i++) {
            searchTags.add(inputEditTexts.get(i).getText().toString());
        }
    }

//    public <T> void printList(ArrayList<T> list) {
//        if (list != null) {
//            Log.i("SearchActivity", "printList : " + "Start");
//            Log.i("SearchActivity", "printList : " + "size() -> " + list.size());
//            for (T t : list) {
//                Log.i("SearchActivity", "printList : " + "t -> " + t);
//            }
//            Log.i("SearchActivity", "printList : " + "End");
//        }
//    }
//
//    public void showMap() {
//        Set<String> keys = matchedIndex.keySet();
//        for (String k : keys) {
//            Log.i("SearchActivity", "showMap : " + "key -> " + k);
//            if (matchedIndex.get(k) != null) {
//                for (int i : matchedIndex.get(k)) {
//                    Log.i("SearchActivity", "showMap : " + "value -> " + i);
//                }
//            }
//        }
//    }

}
