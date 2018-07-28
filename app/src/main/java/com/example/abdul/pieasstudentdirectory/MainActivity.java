package com.example.abdul.pieasstudentdirectory;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static MainActivity context;

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.student_list);
        context = this;

        try {
            sqLiteDatabase = this.openOrCreateDatabase("StudentData", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS studentTable (id INTEGER PRIMARY KEY, studentName VARCHAR, fatherName VARCHAR, regNo VARCHAR, batch VARCHAR, department VARCHAR, semester VARCHAR, roomNo VARCHAR, hostel VARCHAR, cgpa VARCHAR, age VARCHAR, gender VARCHAR, bloodGroup VARCHAR, phoneNo VARCHAR, email VARCHAR, address VARCHAR)");
            loadStudentsToDatabase();
            getStudentFromDatabase();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.studentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setCustomAdapter(new CustomAdapter(this, studentArrayList));
    }

    public static MainActivity getContext() {
        return context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch (item.getItemId()) {
            case R.id.showAll:
                Log.i(TAG, "onOptionsItemSelected : " + "case showAll");
                getStudentFromDatabase();
                notifyDataSetChanged();
                break;
            case R.id.search:
                Log.i(TAG, "onOptionsItemSelected : " + "case search");
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, SearchActivity.SEARCH_ACTIVITY);
                break;
            case R.id.add:
                Log.i(TAG, "onOptionsItemSelected : " + "case add");
                intent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivityForResult(intent, RegistrationActivity.REGISTRATION_ACTIVITY);
                break;
            case R.id.about:
                Log.i(TAG, "onOptionsItemSelected : " + "case about");
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("About")
                        .setMessage("This app is for PIEAS University")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("", null)
                        .show();
                break;
            case R.id.loadStudent:
                Log.i(TAG, "onOptionsItemSelected : " + "case loadStudent");
//                insertStudent(new Student("ARK", "Tanveer Ahmed Khan", "204", "A", "khaqan St#1, Arif Colony, Gill Road, GRW", "A+", "03311205526", "1", "18-22", "dee", "abdulrehmankhan27061998@gmail.com", "03310012018", "M"));
//                insertStudent(new Student("Abdul Rehman Khan", "Tanveer Ahmed Khan", "207", "E", "khaqan St#1, Arif Colony, Gill Road, GRW", "A+", "03311205526", "3", "17-21", "dcis", "abdulrehmankhan27061998@gmail.com", "03310032017", "M"));
                loadStudentsToDatabase();
                Toast.makeText(this, "Students Loaded in Database", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteTable:
                Log.i(TAG, "onOptionsItemSelected : " + "case deleteTable");
                sqLiteDatabase.execSQL("DELETE FROM studentTable");
                studentArrayList.clear();
                notifyDataSetChanged();
                break;
            default:
                Log.i(TAG, "onOptionsItemSelected : " + "Returning False (default)");
                return false;
        }
        Log.i(TAG, "onOptionsItemSelected : " + "Returning True");
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "onActivityResult : " + "requestCode -> " + requestCode);
        Log.i(TAG, "onActivityResult : " + "resultCode -> " + resultCode);
        Toast.makeText(this, "requestCode -> " + requestCode + "; resultCode -> " + resultCode, Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case RegistrationActivity.REGISTRATION_ACTIVITY:
                break;
            case ShowStudentActivity.SHOW_STUDENT_ACTIVITY:
                break;
            case SearchActivity.SEARCH_ACTIVITY:
                break;
            default:
        }
    }

    public ArrayList<Student> getStudentArrayList(){
        return studentArrayList;
    }

    public Student getStudent(int index) {
        return studentArrayList.get(index);
    }

    public void addStudent(Student student) {
        this.studentArrayList.add(student);
    }

    public void insertStudent(Student std) {
        sqLiteDatabase.execSQL("INSERT INTO studentTable (studentName, fatherName, regNo, batch, department, semester, roomNo, hostel, cgpa, age, gender, bloodGroup, phoneNo, email, address) VALUES (" + Student.parseStudentToString(std) + ")");
    }

    public void deleteStudent(int index) {
        sqLiteDatabase.execSQL("DELETE FROM studentTable WHERE regNo = '" + studentArrayList.get(index).getStudentData("regNo") + "'");
    }

    public void updateStudent(String value) {
        sqLiteDatabase.execSQL("UPDATE studentTable SET studentName = 'ARK' WHERE studentName = 'ABDUL REHMAN KHAN'");
    }

    public Cursor selectStudent(String query) {
        Log.i(TAG, "selectStudent : " + "SELECT * FROM studentTable WHERE " + query);
        return sqLiteDatabase.rawQuery("SELECT * FROM studentTable WHERE " + query, null);
    }

    public void getStudentFromDatabase() {
        Log.i(TAG, "getStudentFromDatabase: Start");
        String[] values = new String[Student.STUDENT_KEYS.length];
        studentArrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM studentTable", null);
        cursor.moveToFirst();
        Log.i(TAG, "getCount : " + cursor.getCount());
        if (cursor.getCount() != 0) {
            do {
                for (int i = 1; i < cursor.getColumnNames().length; i++) {
                    values[i - 1] = cursor.getString(i);
                }
                addStudent(new Student(values));
            } while (cursor.moveToNext());
        }
        cursor.close();
        Log.i(TAG, "getStudentFromDatabase: End");
    }

    public void notifyDataSetChanged() {
        customAdapter.notifyDataSetChanged();
    }

    public void setCustomAdapter(CustomAdapter customAdapter) {
        this.customAdapter = customAdapter;
        recyclerView.setAdapter(this.customAdapter);
    }

    public void loadStudentsToDatabase(){
        Log.i(TAG, "loadStudentsToDatabase: Start");
        String[][] bscisStudents = {
//                {"studentName", "fatherName", "regNo", "batch", "department", "semester", "roomNo", "hostel", "cgpa", "age", "gender", "bloodGroup", "phoneNo", "email", "address"},
                {"AAMIR ABBAS",             "",                     "03310012017", "17-21", "dcis", "3", "207", "E", "",        "19", "m", "",      "03349333694", "bscs1733@pieas.edu.pk", "parachinar"},
                {"ABDUL RAFAY",             "",                     "03310022017", "17-21", "dcis", "3", "107", "B", "",        "19", "m", "",      "03123804728", "bscs1723@pieas.edu.pk", "faisalabad"},      //"bscs1723@pieas.edu.pk"
                {"Abdul Rehman Khan",       "Tanveer Ahmed Khan",   "03310032017", "17-21", "dcis", "3", "207", "E", "3.91",    "19", "m", "A+",    "03311205526", "bscs1725@pieas.edu.pk", "gujranwala"},      //"bscs1725@pieas.edu.pk"
                {"ABDULLAH AHMAD",          "",                     "03310042017", "17-21", "dcis", "3", "210", "E", "",        "19", "m", "",      "03318465317", "bscs1704@pieas.edu.pk", "lahore"},          //"bscs1704@pieas.edu.pk"
                {"AHMAD ASHIQ",             "",                     "03310052017", "17-21", "dcis", "3", "202", "E", "",        "19", "m", "",      "03370735034", "bscs1727@pieas.edu.pk", "gujranwala"},      //"bscs1727@pieas.edu.pk"
                {"AHMED RAMEEZ",            "",                     "03310062017", "17-21", "dcis", "3", "214", "E", "",        "19", "m", "",      "03318844841", "bscs1707@pieas.edu.pk", "islamabad"},       //"bscs1707@pieas.edu.pk"
                {"AHMER HASSAN",            "",                     "03310072017", "17-21", "dcis", "3", "117", "B", "",        "19", "m", "",      "03361514671", "bscs1711@pieas.edu.pk", "haripur"},         //"bscs1711@pieas.edu.pk"
                {"ALEENA AJAZ",             "",                     "03310082017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1728@pieas.edu.pk", "islamabad"},       //"bscs1728@pieas.edu.pk"
                {"AMEER HAMZA",             "",                     "03310092017", "17-21", "dcis", "3", "214", "E", "",        "19", "m", "",      "03367278119", "bscs1713@pieas.edu.pk", "lahore"},          //"bscs1713@pieas.edu.pk"
                {"AYESHA SALAR",            "",                     "03310102017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1729@pieas.edu.pk", "islamabad"},       //"bscs1729@pieas.edu.pk"
                {"BIBI AYISHA",             "",                     "03310112017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1701@pieas.edu.pk", "islamabad"},       //"bscs1701@pieas.edu.pk"
                {"HAMZA IRSHAD",            "",                     "03310122017", "17-21", "dcis", "3", "202", "E", "",        "19", "m", "",      "03067098760", "bscs1726@pieas.edu.pk", "lahore"},          //"bscs1726@pieas.edu.pk"
                {"HASSAN ASKARY",           "",                     "03310132017", "17-21", "dcis", "3", "000", "E", "",        "19", "m", "",      "03342013999", "bscs1717@pieas.edu.pk", "islamabad"},       //"bscs1717@pieas.edu.pk"
                {"HASSAN RAZA KHAN",        "",                     "03310142017", "17-21", "dcis", "3", "210", "E", "",        "19", "m", "",      "",            "bscs1777@pieas.edu.pk", "lahore"},          //"bscs1777@pieas.edu.pk"
                {"HASSAN SATTAR",           "",                     "03310152017", "17-21", "dcis", "3", "207", "E", "",        "19", "m", "",      "03439555320", "bscs1724@pieas.edu.pk", "shinkiari"},       //"bscs1724@pieas.edu.pk"
                {"KHAYYAM AKHTAR",          "",                     "03310162017", "17-21", "dcis", "3", "000", "E", "",        "19", "m", "",      "03128666939", "bscs1709@pieas.edu.pk", "islamabad"},       //"bscs1709@pieas.edu.pk"
                {"MUHAMMAD AAQIB",          "",                     "03310172017", "17-21", "dcis", "3", "107", "B", "",        "19", "m", "",      "03377255904", "bscs1734@pieas.edu.pk", "sargodha"},        //"bscs1734@pieas.edu.pk"
                {"MUHAMMAD FARRUKH IRFAN",  "",                     "03310182017", "17-21", "dcis", "3", "117", "B", "",        "19", "m", "",      "03041258003", "bscs1706@pieas.edu.pk", "faisalabad"},      //"bscs1706@pieas.edu.pk"
                {"MUHAMMAD HASSAN KHAN",    "",                     "03310192017", "17-21", "dcis", "3", "115", "B", "",        "19", "m", "",      "03334706761", "bscs1722@pieas.edu.pk", "lahore"},          //"bscs1722@pieas.edu.pk"
                {"MUHAMMAD INAAM ELAHI",    "",                     "03310202017", "17-21", "dcis", "3", "313", "E", "",        "19", "m", "",      "03340754787", "bscs1716@pieas.edu.pk", "faisalabad"},      //"bscs1716@pieas.edu.pk"
                {"MUHAMMAD OBAIDULLAH",     "",                     "03310212017", "17-21", "dcis", "3", "207", "E", "",        "19", "m", "",      "03349841289", "bscs1708@pieas.edu.pk", "daska"},           //"bscs1708@pieas.edu.pk"
                {"MUHAMMAD UMAR",           "",                     "03310222017", "17-21", "dcis", "3", "313", "E", "",        "19", "m", "",      "",            "bscs1739@pieas.edu.pk", "taxila"},          //"bscs1739@pieas.edu.pk"
                {"MUHAMMAD UMAR FAROOQ",    "",                     "03310232017", "17-21", "dcis", "3", "201", "E", "",        "19", "m", "",      "03074113833", "bscs1715@pieas.edu.pk", "faisalabad"},      //"bscs1715@pieas.edu.pk"
                {"MUHAMMAD ZEESHAN TAHIR",  "",                     "03310242017", "17-21", "dcis", "3", "319", "E", "",        "19", "m", "",      "03103303732", "bscs1720@pieas.edu.pk", "DJ khan"},         //"bscs1720@pieas.edu.pk"
                {"MUHAMMAD ZUNAIR",         "",                     "03310252017", "17-21", "dcis", "3", "115", "B", "",        "19", "m", "",      "03006040195", "bscs1721@pieas.edu.pk", "faisalabad"},      //"bscs1721@pieas.edu.pk"
                {"MUSFIRAH EHSAN",          "",                     "03310262017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1710@pieas.edu.pk", "rawalpindi"},      //"bscs1710@pieas.edu.pk"
                {"NIMRA RIAZ",              "",                     "03310272017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1714@pieas.edu.pk", "rawalpindi"},      //"bscs1714@pieas.edu.pk"
                {"SALMA RASHID",            "",                     "03310282017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1718@pieas.edu.pk", "islamabad"},       //"bscs1718@pieas.edu.pk"
                {"TANVEER HUSSAIN",         "",                     "03310292017", "17-21", "dcis", "3", "119", "B", "",        "19", "m", "",      "03063291827", "bscs1723@pieas.edu.pk", "rahim yar khan"},  //"bscs1723@pieas.edu.pk"
                {"UMAR SHAHZAD",            "",                     "03310302017", "17-21", "dcis", "3", "209", "E", "",        "19", "m", "",      "03087282420", "bscs1705@pieas.edu.pk", "rahim yar khan"},  //"bscs1705@pieas.edu.pk"
                {"WALEED AKHTAR",           "",                     "03310312017", "17-21", "dcis", "3", "315", "E", "",        "19", "m", "",      "03355786231", "bscs1731@pieas.edu.pk", "lahore"},          //"bscs1731@pieas.edu.pk"
                {"ZOHA ASSAD",              "",                     "03310322017", "17-21", "dcis", "3", "000", "G", "",        "19", "m", "",      "",            "bscs1712@pieas.edu.pk", "islamabad"},       //"bscs1712@pieas.edu.pk"
        };

        for(String[] std : bscisStudents) {
            insertStudent(new Student(std));
        }
        Log.i(TAG, "loadStudentsToDatabase: End");
    }

}
