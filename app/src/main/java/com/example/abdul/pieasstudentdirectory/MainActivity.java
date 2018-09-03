package com.example.abdul.pieasstudentdirectory;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    public static MainActivity context;

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Student> studentArrayList = new ArrayList<>();
    private SQLiteDatabase sqLiteDatabase;
    private int totalStudentsInDataBase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setTitle(R.string.student_list);
        context = this;

        try {
            sqLiteDatabase = this.openOrCreateDatabase("StudentData", Context.MODE_PRIVATE, null);
            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS studentTable (id INTEGER PRIMARY KEY, studentName VARCHAR, fatherName VARCHAR, regNo VARCHAR, batch VARCHAR, department VARCHAR, semester VARCHAR, roomNo VARCHAR, hostel VARCHAR, cgpa VARCHAR, age VARCHAR, gender VARCHAR, bloodGroup VARCHAR, phoneNo VARCHAR, email VARCHAR, address VARCHAR)");
//            loadStudentsToDatabase();
            getStudentFromDatabase();
            setTitleText();
            setTitleText();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        recyclerView = findViewById(R.id.studentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setCustomAdapter(new CustomAdapter(this, studentArrayList, new ArrayList<Photo>()));
    }

    public static MainActivity getContext() {
        return context;
    }

    public ArrayList<Student> getStudentArrayList() {
        return studentArrayList;
    }

    public Student getStudent(int index) {
        return studentArrayList.get(index);
    }

    public void addStudent(Student student) {
        this.studentArrayList.add(student);
    }

    public void notifyDataSetChanged() {
        customAdapter.notifyDataSetChanged();
    }

    public void setCustomAdapter(CustomAdapter customAdapter) {
        this.customAdapter = customAdapter;
        recyclerView.setAdapter(this.customAdapter);
    }

    public void setTitleText() {
        Resources resources = getResources();
        setTitle(resources.getString(R.string.student_list, studentArrayList.size(), totalStudentsInDataBase));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: start");
        super.onResume();
        //"https://api.flickr.com/services/feeds/photos_public.gne"
        //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=bdecd7c92f562d11aac6087ee05644de&user_id=156365122@N06&format=json&nojsoncallback=1
        String flickrUrl = "https://api.flickr.com/services/rest/";
        GetFlickrJSONData getFlickrJSONData = new GetFlickrJSONData(this, flickrUrl, studentArrayList);
        getFlickrJSONData.execute("");
        Log.d(TAG, "onResume: end");
    }

//    @Override
//    public void onDataAvailable(List<Photo> data, DownloadStatus status) {
//        Log.d(TAG, "onDataAvailable: start");
//        if (status == DownloadStatus.OK) {
//            notifyDataSetChanged();
////            customAdapter.loadData(data);
//        } else {
//            Log.d(TAG, "onDataAvailable: Download Failed with status -> " + status);
//        }
//        Log.d(TAG, "onDataAvailable: end");
//    }

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
                Toast.makeText(this, "total -> " + totalStudentsInDataBase + "\nshowing -> " + studentArrayList.size(), Toast.LENGTH_SHORT).show();
                setTitleText();
                break;
            default:
        }
    }

    public void insertStudent(Student std) {
        for (Student prevStd : studentArrayList) {
            if (prevStd.equals(std)) {
                Log.d(TAG, "insertStudent: Student Already Exists");
                Toast.makeText(this, "Student Already Exists", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        sqLiteDatabase.execSQL("INSERT INTO studentTable (studentName, fatherName, regNo, batch, department, semester, roomNo, hostel, cgpa, age, gender, bloodGroup, phoneNo, email, address) VALUES (" + Student.parseStudentToString(std) + ")");
        Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
    }

    public void deleteStudent(int index) {
        sqLiteDatabase.execSQL("DELETE FROM studentTable WHERE regNo = '" + studentArrayList.get(index).getStudentData("regNo") + "'");
    }

    public void updateStudent(Student std, String key, String newValue) {
        Log.d(TAG, "updateStudent: query -> " + "UPDATE studentTable SET " + key + " = '" + newValue + "' WHERE regNo = '" + std.getStudentData("regNo") + "'");
        sqLiteDatabase.execSQL("UPDATE studentTable SET " + key + " = '" + newValue + "' WHERE regNo = '" + std.getStudentData("regNo") + "'");
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
        totalStudentsInDataBase = cursor.getCount();
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

    public void loadStudentsToDatabase() {
        Log.i(TAG, "loadStudentsToDatabase: Start");
        String[][] bscisStudents = {
//                {"studentName", "fatherName", "regNo", "batch", "department", "semester", "roomNo", "hostel", "cgpa", "age", "gender", "bloodGroup", "phoneNo", "email", "address"},
                {"AAMIR ABBAS", "fatherName", "03310012017", "17-21", "dcis", "3", "207", "E", "4.00", "19", "m", "", "03349333694", "bscs1733@pieas.edu.pk", "parachinar"},
                {"ABDUL RAFAY", "fatherName", "03310022017", "17-21", "dcis", "3", "107", "B", "4.00", "19", "m", "", "03123804728", "bscs1723@pieas.edu.pk", "faisalabad"},
                {"Abdul Rehman Khan", "Tanveer Ahmed Khan", "03310032017", "17-21", "dcis", "3", "207", "E", "3.91", "19", "m", "A+", "03311205526", "bscs1725@pieas.edu.pk", "gujranwala"},
                {"ABDULLAH AHMAD", "fatherName", "03310042017", "17-21", "dcis", "3", "210", "E", "4.00", "19", "m", "", "03318465317", "bscs1704@pieas.edu.pk", "lahore"},
                {"AHMAD ASHIQ", "fatherName", "03310052017", "17-21", "dcis", "3", "202", "E", "4.00", "19", "m", "", "03370735034", "bscs1727@pieas.edu.pk", "gujranwala"},
                {"AHMED RAMEEZ", "fatherName", "03310062017", "17-21", "dcis", "3", "214", "E", "4.00", "19", "m", "", "03318844841", "bscs1707@pieas.edu.pk", "islamabad"},
                {"AHMER HASSAN", "fatherName", "03310072017", "17-21", "dcis", "3", "117", "B", "4.00", "19", "m", "", "03361514671", "bscs1711@pieas.edu.pk", "haripur"},
                {"ALEENA AJAZ", "fatherName", "03310082017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1728@pieas.edu.pk", "islamabad"},
                {"AMEER HAMZA", "fatherName", "03310092017", "17-21", "dcis", "3", "214", "E", "4.00", "19", "m", "", "03367278119", "bscs1713@pieas.edu.pk", "lahore"},
                {"AYESHA SALAR", "fatherName", "03310102017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1729@pieas.edu.pk", "islamabad"},
                {"BIBI AYISHA", "fatherName", "03310112017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1701@pieas.edu.pk", "islamabad"},
                {"HAMZA IRSHAD", "fatherName", "03310122017", "17-21", "dcis", "3", "202", "E", "4.00", "19", "m", "", "03067098760", "bscs1726@pieas.edu.pk", "lahore"},
                {"HASSAN ASKARY", "fatherName", "03310132017", "17-21", "dcis", "3", "000", "E", "4.00", "19", "m", "", "03342013999", "bscs1717@pieas.edu.pk", "islamabad"},
                {"HASSAN RAZA KHAN", "fatherName", "03310142017", "17-21", "dcis", "3", "210", "E", "4.00", "19", "m", "", "", "bscs1777@pieas.edu.pk", "lahore"},
                {"HASSAN SATTAR", "fatherName", "03310152017", "17-21", "dcis", "3", "207", "E", "4.00", "19", "m", "", "03439555320", "bscs1724@pieas.edu.pk", "shinkiari"},
                {"KHAYYAM AKHTAR", "fatherName", "03310162017", "17-21", "dcis", "3", "000", "E", "4.00", "19", "m", "", "03128666939", "bscs1709@pieas.edu.pk", "islamabad"},
                {"MUHAMMAD AAQIB", "fatherName", "03310172017", "17-21", "dcis", "3", "107", "B", "4.00", "19", "m", "", "03377255904", "bscs1734@pieas.edu.pk", "sargodha"},
                {"MUHAMMAD FARRUKH IRFAN", "fatherName", "03310182017", "17-21", "dcis", "3", "117", "B", "4.00", "19", "m", "", "03041258003", "bscs1706@pieas.edu.pk", "faisalabad"},
                {"MUHAMMAD HASSAN KHAN", "fatherName", "03310192017", "17-21", "dcis", "3", "115", "B", "4.00", "19", "m", "", "03334706761", "bscs1722@pieas.edu.pk", "lahore"},
                {"MUHAMMAD INAAM ELAHI", "fatherName", "03310202017", "17-21", "dcis", "3", "313", "E", "4.00", "19", "m", "", "03340754787", "bscs1716@pieas.edu.pk", "faisalabad"},
                {"MUHAMMAD OBAIDULLAH", "fatherName", "03310212017", "17-21", "dcis", "3", "207", "E", "4.00", "19", "m", "", "03349841289", "bscs1708@pieas.edu.pk", "daska"},
                {"MUHAMMAD UMAR", "fatherName", "03310222017", "17-21", "dcis", "3", "313", "E", "4.00", "19", "m", "", "", "bscs1739@pieas.edu.pk", "taxila"},
                {"MUHAMMAD UMAR FAROOQ", "fatherName", "03310232017", "17-21", "dcis", "3", "201", "E", "4.00", "19", "m", "", "03074113833", "bscs1715@pieas.edu.pk", "faisalabad"},
                {"MUHAMMAD ZEESHAN TAHIR", "fatherName", "03310242017", "17-21", "dcis", "3", "319", "E", "4.00", "19", "m", "", "03103303732", "bscs1720@pieas.edu.pk", "DJ khan"},
                {"MUHAMMAD ZUNAIR", "fatherName", "03310252017", "17-21", "dcis", "3", "115", "B", "4.00", "19", "m", "", "03006040195", "bscs1721@pieas.edu.pk", "faisalabad"},
                {"MUSFIRAH EHSAN", "fatherName", "03310262017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1710@pieas.edu.pk", "rawalpindi"},
                {"NIMRA RIAZ", "fatherName", "03310272017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1714@pieas.edu.pk", "rawalpindi"},
                {"SALMA RASHID", "fatherName", "03310282017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1718@pieas.edu.pk", "islamabad"},
                {"TANVEER HUSSAIN", "fatherName", "03310292017", "17-21", "dcis", "3", "119", "B", "4.00", "19", "m", "", "03063291827", "bscs1723@pieas.edu.pk", "rahim yar khan"},
                {"UMAR SHAHZAD", "fatherName", "03310302017", "17-21", "dcis", "3", "209", "E", "4.00", "19", "m", "", "03087282420", "bscs1705@pieas.edu.pk", "rahim yar khan"},
                {"WALEED AKHTAR", "fatherName", "03310312017", "17-21", "dcis", "3", "315", "E", "4.00", "19", "m", "", "03355786231", "bscs1731@pieas.edu.pk", "lahore"},
                {"ZOHA ASSAD", "fatherName", "03310322017", "17-21", "dcis", "3", "000", "G", "4.00", "19", "f", "", "", "bscs1712@pieas.edu.pk", "islamabad"}
        };

        for (String[] std : bscisStudents) {
            insertStudent(new Student(std));
        }
        Log.i(TAG, "loadStudentsToDatabase: End");
    }

}
