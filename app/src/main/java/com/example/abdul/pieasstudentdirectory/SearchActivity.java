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
import java.util.HashMap;
import java.util.Set;

public class SearchActivity extends AppCompatActivity {

    private String[] labelStrings = {"Name : ", "Registration# : ", "Department : ", "Phone# : ", "Gender : "};
    private ArrayList<EditText> inputEditTexts = new ArrayList<>();
    private Button addButton, cancelButton;
    private ArrayList<String> searchTags = new ArrayList<>();

    private HashMap<String, ArrayList<Integer>> matchedIndex;
    private ArrayList<Student> studentList;
    private ArrayList<Integer> finalSearch;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toast.makeText(MainActivity.getContext(), "SearchActivity Started", Toast.LENGTH_SHORT).show();
        Log.i("SearchActivity", "onCreate");

        inputEditTexts.add((EditText) findViewById(R.id.nameEditText));
        inputEditTexts.add((EditText) findViewById(R.id.regNoEditText));
        inputEditTexts.add((EditText) findViewById(R.id.departmentEditText));
        inputEditTexts.add((EditText) findViewById(R.id.phoneNoEditView));
        inputEditTexts.add((EditText) findViewById(R.id.genderEditText));
        addButton = findViewById(R.id.addButton);
        cancelButton = findViewById(R.id.cancelButton);
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.activity_search);
//    }

//    public StudentSearch(Window window) {
//        this.setTitle(TITLE);
//        this.setSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 2));
//        this.setLocationRelativeTo(null);
//
//        this.window = window;
//        this.setLayout(LAYOUT_GRIDBAG);
//        this.init();
//
//        // this.setVisible(true);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    public void init() {
//        CONSTRAINTS_GRIDBAG.fill = GridBagConstraints.VERTICAL;
//        CONSTRAINTS_GRIDBAG.gridwidth = 1;
//        CONSTRAINTS_GRIDBAG.gridheight = 1;
//        CONSTRAINTS_GRIDBAG.insets = new Insets(10, 10, 10, 10);
//
//        for (int i = 0; i < labelStrings.length; i++) {
//            labels[i] = new JLabel(labelStrings[i]);
//            CONSTRAINTS_GRIDBAG.gridx = 0;
//            CONSTRAINTS_GRIDBAG.gridy = i + 1;
//            this.add(labels[i], CONSTRAINTS_GRIDBAG);
//
//            textFields[i] = new JTextField();
//            textFields[i].setColumns(25);
//            CONSTRAINTS_GRIDBAG.gridx = 1;
//            CONSTRAINTS_GRIDBAG.gridy = i + 1;
//            this.add(textFields[i], CONSTRAINTS_GRIDBAG);
//        }
//
//        CONSTRAINTS_GRIDBAG.insets = new Insets(10, 10, 10, 10);
//        searchButton = new JButton("SEARCH");
//        searchButton.addActionListener(this);
//        CONSTRAINTS_GRIDBAG.gridx = 0;
//        CONSTRAINTS_GRIDBAG.gridy = labelStrings.length + 1;
//        this.add(searchButton, CONSTRAINTS_GRIDBAG);
//
//        cancelButton = new JButton("CANCEL");
//        cancelButton.addActionListener(this);
//        CONSTRAINTS_GRIDBAG.gridx = 1;
//        CONSTRAINTS_GRIDBAG.gridy = labelStrings.length + 1;
//        this.add(cancelButton, CONSTRAINTS_GRIDBAG);
//
//        header = new JLabel("SEARCH STUDENT");
//        header.setFont(FONT_BOLD);
//        CONSTRAINTS_GRIDBAG.insets = new Insets(10, 10, 10, 10);
//        CONSTRAINTS_GRIDBAG.gridx = 0;
//        CONSTRAINTS_GRIDBAG.gridy = 0;
//        CONSTRAINTS_GRIDBAG.gridwidth = GridBagConstraints.REMAINDER;
//        this.add(header, CONSTRAINTS_GRIDBAG);
//    }

    public void actionPerformed(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getText().equals("Search")) {
            Toast.makeText(MainActivity.getContext(), "Search is Clicked", Toast.LENGTH_SHORT).show();

            matchedIndex = new HashMap<>();
            setSearchTags();

//            for (int i = 0; i < searchTags.size(); i++) {
//                Log.i("actionPerformed", i + " -> " + searchTags.get(i));
//            }

            matchedIndex.put(searchTags.get(0), searchByName());
            matchedIndex.put(searchTags.get(1), searchByRegNo());
            matchedIndex.put(searchTags.get(2), searchByDepartment());
            matchedIndex.put(searchTags.get(3), searchByPhoneNo());
            matchedIndex.put(searchTags.get(4), searchByGender());
            // showMap();

//            Log.i("actionPerformed", "Intersection of " + searchTags.get(0) + " and " + searchTags.get(1) + " -> ");
            finalSearch = intersect(matchedIndex.get(searchTags.get(0)), matchedIndex.get(searchTags.get(1)));
//            printList(finalSearch);

//            Log.i("actionPerformed", "Intersection of intersect and " + searchTags.get(2) + " -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(2)));
//            printList(finalSearch);

//            Log.i("actionPerformed", "Intersection of intersect and " + searchTags.get(3) + " -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(3)));
//            printList(finalSearch);

//            Log.i("actionPerformed", "Intersection of intersect and " + searchTags.get(4) + " -> ");
            finalSearch = intersect(finalSearch, matchedIndex.get(searchTags.get(4)));
//            printList(finalSearch);
        } else if (clickedButton.getText().equals("Cancel")) {
            Toast.makeText(MainActivity.getContext(), "Cancel is Clicked", Toast.LENGTH_SHORT).show();
            Log.i("actionPerformed", "Cancel Button Clicked");
            finish();
        }
    }

    public void setStudentList(ArrayList<Student> stds) {
        this.studentList = stds;
    }

    public void setSearchTags() {
        searchTags.clear();
        for (int i = 0; i < inputEditTexts.size(); i++) {
            searchTags.add(inputEditTexts.get(i).getText().toString());
        }
    }

    public ArrayList<Integer> searchByName() {
        ArrayList<Integer> matchedStudents = new ArrayList<Integer>();
        if (searchTags.get(0).length() != 0) {
            // System.out.println("searchTags[0] -> " + searchTags[0]);
            // System.out.println("---------SearchByName---------");
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getName().toLowerCase().contains(searchTags.get(0).toLowerCase())) {
                    // System.out.println(i + "th student's name contains " + searchTags[0]);
                    matchedStudents.add(i);
                    // System.out.print(i + ";");
                }
            }
            // System.out.println("\n---------------------");
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByRegNo() {
        ArrayList<Integer> matchedStudents = new ArrayList<Integer>();
        if (searchTags.get(1).length() != 0) {
            // System.out.println("searchTags[1] -> " + searchTags[1]);
            // System.out.println("---------SearchByRegNo---------");
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getRegNo().toLowerCase().equals(searchTags.get(1).toLowerCase())) {
                    // System.out.println(i + "th student's regNo is " + searchTags[1]);
                    matchedStudents.add(i);
                    // System.out.print(i + ";");
                }
            }
            // System.out.println("\n---------------------");
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByDepartment() {
        ArrayList<Integer> matchedStudents = new ArrayList<Integer>();
        if (searchTags.get(1).length() != 0) {
            // System.out.println("searchTags[2] -> " + searchTags[2]);
            // System.out.println("---------SearchByDepartment---------");
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getDepartment().toLowerCase().contains(searchTags.get(1).toLowerCase())) {
                    // System.out.println(i + "th student's department contains " + searchTags[2]);
                    matchedStudents.add(i);
                    // System.out.print(i + ";");
                }
            }
            // System.out.println("\n---------------------");
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByPhoneNo() {
        ArrayList<Integer> matchedStudents = new ArrayList<Integer>();
        if (searchTags.get(3).length() != 0) {
            // System.out.println("searchTags[3] -> " + searchTags[3]);
            // System.out.println("---------SearchByPhoneNo---------");
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getPhoneNo().toLowerCase().equals(searchTags.get(3).toLowerCase())) {
                    // System.out.println(i + "th student's phoneNo is " + searchTags[3]);
                    matchedStudents.add(i);
                    // System.out.print(i + ";");
                }
            }
            // System.out.println("\n---------------------");
        }
        return matchedStudents;
    }

    public ArrayList<Integer> searchByGender() {
        ArrayList<Integer> matchedStudents = new ArrayList<Integer>();
        if (searchTags.get(4).length() != 0) {
            // System.out.println("searchTags[4] -> " + searchTags[4]);
            // System.out.println("---------SearchByGender---------");
            for (int i = 0; i < studentList.size(); i++) {
                if (studentList.get(i).getGender().toLowerCase().charAt(0) == searchTags.get(4).toLowerCase().charAt(0)) {
                    // System.out.println(i + "th student's gender contains " + searchTags[4]);
                    matchedStudents.add(i);
                    // System.out.print(i + ";");
                }
            }
            // System.out.println("\n---------------------");
        }
        return matchedStudents;
    }

    public ArrayList<Integer> intersect(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> intersection = new ArrayList<Integer>();

        if (list1.size() != 0 && list2.size() == 0) {
            // System.out.println("list1 is not empty and list2 is empty");
            return list1;
        } else if (list1.size() == 0 && list2.size() != 0) {
            // System.out.println("list2 is not empty and list1 is empty");
            return list2;
        } else {
            // System.out.println("-------------intersect------------");
            for (int i = 0; i < list1.size(); i++) {
                for (int j = 0; j < list2.size(); j++) {
                    if (list1.get(i).equals(list2.get(j))) {
                        intersection.add(list1.get(i));
                        break;
                    }
                }
            }
            // System.out.println("-------------------------");
            return intersection;
        }
    }

    public <T> void printList(ArrayList<T> list) {
        if (list != null) {
            Log.i("printList", "Start");
            for (T t : list) {
                Log.i("printList", "t -> " + t);
            }
            Log.i("printList", "End");
        }
    }

    public void showMap() {
        Set<String> keys = matchedIndex.keySet();
        for (String k : keys) {
            Log.i("showMap", "key -> " + k);
            if (matchedIndex.get(k) != null) {
                for (int i : matchedIndex.get(k)) {
                    Log.i("showMap", "value -> " + i);
                }
            }
        }
    }

}
