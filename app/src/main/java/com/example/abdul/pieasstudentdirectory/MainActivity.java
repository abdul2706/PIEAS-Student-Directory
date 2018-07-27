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

        context = this;
        try {
            sqLiteDatabase = openOrCreateDatabase("StudentData", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS studentTable (id INTEGER PRIMARY KEY, studentName VARCHAR, fatherName VARCHAR, roomNo VARCHAR, hostel VARCHAR, address VARCHAR, bloodGroup VARCHAR, phoneNo VARCHAR, semester VARCHAR, batch VARCHAR, department VARCHAR, email VARCHAR, regNo VARCHAR, gender VARCHAR)");
            loadStudentsToDatabase();
            getStudentFromDatabase();
        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
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
        sqLiteDatabase.execSQL("INSERT INTO studentTable (studentName, fatherName, roomNo, hostel, address, bloodGroup, phoneNo, semester, batch, department, email, regNo, gender) VALUES (" + Student.parseStudentToString(std) + ")");
    }

    public void deleteStudent(int index) {
        sqLiteDatabase.execSQL("DELETE FROM studentTable WHERE regNo = '" + studentArrayList.get(index).getRegNo() + "'");
    }

    public void updateStudent(String value) {
        sqLiteDatabase.execSQL("UPDATE studentTable SET studentName = 'ARK' WHERE studentName = 'ABDUL REHMAN KHAN'");
    }

    public Cursor selectStudent(String query) {
//        return sqLiteDatabase.rawQuery("SELECT * FROM studentTable WHERE " + "studentName = 'Abdul Rehman Khan' AND fatherName = 'Tanveer Ahmed Khan' AND roomNo = '207' AND hostel = 'E' AND address = 'khaqan St#1, Arif Colony, Gill Road, GRW' AND bloodGroup = 'A+' AND phoneNo = '03311205526' AND semester = '3' AND batch = '17-21' AND department = 'DCIS' AND email = 'abdulrehmankhan27061998@gmail.com' AND regNo = '03310032017' AND gender = 'M'", null);
        Log.i(TAG, "selectStudent : " + "SELECT * FROM studentTable WHERE " + query);
        return sqLiteDatabase.rawQuery("SELECT * FROM studentTable WHERE " + query, null);
//        return sqLiteDatabase.rawQuery("SELECT * FROM studentTable WHERE studentName LIKE UPPER('%Abdul%')", null);
    }

    public void getStudentFromDatabase() {
        StringBuilder studentAsString;
        studentArrayList.clear();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM studentTable", null);
        cursor.moveToFirst();
        Log.i(TAG, "getCount : " + cursor.getCount());
        if (cursor.getCount() != 0) {
            do {
                studentAsString = new StringBuilder();
                for (int i = 1; i < cursor.getColumnNames().length; i++) {
                    studentAsString.append(cursor.getString(i)).append(";");
                }
                addStudent(Student.parseStringToStudent(studentAsString.toString()));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void notifyDataSetChanged() {
        customAdapter.notifyDataSetChanged();
    }

    public void setCustomAdapter(CustomAdapter customAdapter) {
        this.customAdapter = customAdapter;
        recyclerView.setAdapter(this.customAdapter);
    }

    public void loadStudentsToDatabase(){
//        insertStudent(new Student("Abdul Rehman Khan", "Tanveer Ahmed Khan", "207", "E", "khaqan St#1, Arif Colony, Gill Road, GRW", "A+", "03311205526", "3", "17-21", "dcis", "abdulrehmankhan27061998@gmail.com", "03310032017", "M"));
        insertStudent(new Student("AAMIR ABBAS",                "",                     "207",  "E", "parachinar",      "",     "03349333694",  "3", "17-21", "dcis", "bscs1733@pieas.edu.pk", "03310012017", "M"));
        insertStudent(new Student("ABDUL RAFAY",                "",                     "107",  "B", "faisalabad",      "",     "03123804728",  "3", "17-21", "dcis", "bscs1723@pieas.edu.pk", "03310022017", "M"));
        insertStudent(new Student("Abdul Rehman Khan",          "Tanveer Ahmed Khan",   "207",  "E", "gujranwala",      "A+",   "03311205526",  "3", "17-21", "dcis", "bscs1725@pieas.edu.pk", "03310032017", "M"));
        insertStudent(new Student("ABDULLAH AHMAD",             "",                     "210",  "E", "lahore",          "",     "03318465317",  "3", "17-21", "dcis", "bscs1704@pieas.edu.pk", "03310042017", "M"));
        insertStudent(new Student("AHMAD ASHIQ",                "",                     "202",  "E", "gujranwala",      "",     "03370735034",  "3", "17-21", "dcis", "bscs1727@pieas.edu.pk", "03310052017", "M"));
        insertStudent(new Student("AHMED RAMEEZ",               "",                     "214",  "E", "islamabad",       "",     "03318844841",  "3", "17-21", "dcis", "bscs1707@pieas.edu.pk", "03310062017", "M"));
        insertStudent(new Student("AHMER HASSAN",               "",                     "117",  "B", "haripur",         "",     "03361514671",  "3", "17-21", "dcis", "bscs1711@pieas.edu.pk", "03310072017", "M"));
        insertStudent(new Student("ALEENA AJAZ",                "",                     "-",    "G", "islamabad",       "",     "",             "3", "17-21", "dcis", "bscs1728@pieas.edu.pk", "03310082017", "F"));
        insertStudent(new Student("AMEER HAMZA",                "",                     "214",  "E", "lahore",          "",     "03367278119",  "3", "17-21", "dcis", "bscs1713@pieas.edu.pk", "03310092017", "M"));
        insertStudent(new Student("AYESHA SALAR",               "",                     "-",    "G", "islamabad",       "",     "",             "3", "17-21", "dcis", "bscs1729@pieas.edu.pk", "03310102017", "F"));
        insertStudent(new Student("BIBI AYISHA",                "",                     "-",    "G", "islamabad",       "",     "",             "3", "17-21", "dcis", "bscs1701@pieas.edu.pk", "03310112017", "F"));
        insertStudent(new Student("HAMZA IRSHAD",               "",                     "202",  "E", "lahore",          "",     "03067098760",  "3", "17-21", "dcis", "bscs1726@pieas.edu.pk", "03310122017", "M"));
        insertStudent(new Student("HASSAN ASKARY",              "",                     "-",    "E", "islamabad",       "",     "03342013999",  "3", "17-21", "dcis", "bscs1717@pieas.edu.pk", "03310132017", "M"));
        insertStudent(new Student("HASSAN RAZA KHAN",           "",                     "210",  "E", "lahore",          "",     "",             "3", "17-21", "dcis", "bscs1777@pieas.edu.pk", "03310142017", "M"));
        insertStudent(new Student("HASSAN SATTAR",              "",                     "207",  "E", "shinkiari",       "",     "03439555320",  "3", "17-21", "dcis", "bscs1724@pieas.edu.pk", "03310152017", "M"));
        insertStudent(new Student("KHAYYAM AKHTAR",             "",                     "-",    "E", "islamabad",       "",     "03128666939",  "3", "17-21", "dcis", "bscs1709@pieas.edu.pk", "03310162017", "M"));
        insertStudent(new Student("MUHAMMAD AAQIB",             "",                     "107",  "B", "sargodha",        "",     "03377255904",  "3", "17-21", "dcis", "bscs1734@pieas.edu.pk", "03310172017", "M"));
        insertStudent(new Student("MUHAMMAD FARRUKH IRFAN",     "",                     "117",  "B", "faisalabad",      "",     "03041258003",  "3", "17-21", "dcis", "bscs1706@pieas.edu.pk", "03310182017", "M"));
        insertStudent(new Student("MUHAMMAD HASSAN KHAN",       "",                     "115",  "B", "lahore",          "",     "03334706761",  "3", "17-21", "dcis", "bscs1722@pieas.edu.pk", "03310192017", "M"));
        insertStudent(new Student("MUHAMMAD INAAM ELAHI",       "",                     "313",  "E", "faisalabad",      "",     "03340754787",  "3", "17-21", "dcis", "bscs1716@pieas.edu.pk", "03310202017", "M"));
        insertStudent(new Student("MUHAMMAD OBAIDULLAH",        "",                     "207",  "E", "daska",           "",     "03349841289",  "3", "17-21", "dcis", "bscs1708@pieas.edu.pk", "03310212017", "M"));
        insertStudent(new Student("MUHAMMAD UMAR",              "",                     "313",  "E", "taxila",          "",     "",             "3", "17-21", "dcis", "bscs1739@pieas.edu.pk", "03310222017", "M"));
        insertStudent(new Student("MUHAMMAD UMAR FAROOQ",       "",                     "201",  "E", "faisalabad",      "",     "03074113833",  "3", "17-21", "dcis", "bscs1715@pieas.edu.pk", "03310232017", "M"));
        insertStudent(new Student("MUHAMMAD ZEESHAN TAHIR",     "",                     "319",  "E", "DJ khan",         "",     "03103303732",  "3", "17-21", "dcis", "bscs1720@pieas.edu.pk", "03310242017", "M"));
        insertStudent(new Student("MUHAMMAD ZUNAIR",            "",                     "115",  "B", "faisalabad",      "",     "03006040195",  "3", "17-21", "dcis", "bscs1721@pieas.edu.pk", "03310252017", "M"));
        insertStudent(new Student("MUSFIRAH EHSAN",             "",                     "-",    "G", "rawalpindi",      "",     "",             "3", "17-21", "dcis", "bscs1710@pieas.edu.pk", "03310262017", "F"));
        insertStudent(new Student("NIMRA RIAZ",                 "",                     "-",    "G", "rawalpindi",      "",     "",             "3", "17-21", "dcis", "bscs1714@pieas.edu.pk", "03310272017", "F"));
        insertStudent(new Student("SALMA RASHID",               "",                     "-",    "G", "islamabad",       "",     "",             "3", "17-21", "dcis", "bscs1718@pieas.edu.pk", "03310282017", "F"));
        insertStudent(new Student("TANVEER HUSSAIN",            "",                     "119",  "B", "rahim yar khan",  "",     "03063291827",  "3", "17-21", "dcis", "bscs1723@pieas.edu.pk", "03310292017", "M"));
        insertStudent(new Student("UMAR SHAHZAD",               "",                     "209",  "E", "rahim yar khan",  "",     "03087282420",  "3", "17-21", "dcis", "bscs1705@pieas.edu.pk", "03310302017", "M"));
        insertStudent(new Student("WALEED AKHTAR",              "",                     "315",  "E", "lahore",          "",     "03355786231",  "3", "17-21", "dcis", "bscs1731@pieas.edu.pk", "03310312017", "M"));
        insertStudent(new Student("ZOHA ASSAD",                 "",                     "-",    "G", "islamabad",       "",     "",             "3", "17-21", "dcis", "bscs1712@pieas.edu.pk", "03310322017", "F"));
    }

}
