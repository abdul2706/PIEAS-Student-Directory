package com.example.abdul.pieasdirectory;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
    private static final int PERMISSIONS_CODE = 123;
    private static MainActivity context;

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ArrayList<Person> personArrayList = new ArrayList<>();
    //    private ArrayList<Photo> photoArrayList = new ArrayList<>();
    private int totalPersonsInDataBase = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        DatabaseHandler.initialize(this);
        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new CustomAdapter(this, personArrayList);
        recyclerView.setAdapter(this.customAdapter);

        String read = Manifest.permission.READ_EXTERNAL_STORAGE;
        String write = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        String network = Manifest.permission.ACCESS_NETWORK_STATE;

        if (checkPermission(read) && checkPermission(write) && checkPermission(network)) {
            DatabaseHandler.loadPersonsToDatabase(this);
            personArrayList = DatabaseHandler.selectPersons(this, "id = id");
            Log.d(TAG, "onResume: personArrayList.size() -> " + personArrayList.size());
            totalPersonsInDataBase = personArrayList.size();
//            setTitleText();

//            PersonPhotoHandler photoHandler = new PersonPhotoHandler(this, this);
//            photoHandler.getPhotosFromMemory();

            notifyDataSetChanged();
//            loadNewPersonsData();
        } else {
            Log.d(TAG, "onCreate: requesting permissions");
            ActivityCompat.requestPermissions(this, new String[]{read, write, network}, PERMISSIONS_CODE);
        }

        Log.d(TAG, "onCreate: ends");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: starts");
//        setPersonPhotos();
        notifyDataSetChanged();
//        loadNewPersonsData();
        Log.d(TAG, "onResume: ends");
    }

//    @Override
//    public void onDataAvailable(ArrayList<Photo> photos) {
//        Log.d(TAG, "onDataAvailable: start");
//        if (photos != null) {
//            this.photoArrayList = photos;
//            setPersonPhotos();
//            notifyDataSetChanged();
//            customAdapter.loadPersonsData(personArrayList);
//        } else {
//            Log.d(TAG, "onDataAvailable: error somewhere");
//        }
//        Log.d(TAG, "onDataAvailable: end");
//    }

//    public void setPersonPhotos() {
//        for (Photo photo : this.photoArrayList) {
//            for (Person person : personArrayList) {
//                if (person.getPersonData("regNo").equalsIgnoreCase(photo.getTitle())) {
//                    person.setPhoto(photo);
//                }
//            }
//        }
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
                personArrayList = DatabaseHandler.selectPersons(this, "id = id");
                Log.d(TAG, "onResume: personArrayList.size() -> " + personArrayList.size());
//                setPersonPhotos();
                notifyDataSetChanged();
//                loadNewPersonsData();
                break;
            case R.id.search:
                Log.i(TAG, "onOptionsItemSelected : " + "case search");
                intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, SearchActivity.SEARCH_ACTIVITY);
                break;
            case R.id.about:
                Log.i(TAG, "onOptionsItemSelected : " + "case about");
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.pieas_logo)
                        .setTitle("About")
                        .setMessage("This app is for PIEAS University\nIt shows Person's Data.")
                        .setPositiveButton("OK", null)
                        .setNegativeButton("", null)
                        .show();
                break;
            case R.id.loadPerson:
                Log.i(TAG, "onOptionsItemSelected : " + "case loadPerson");
                DatabaseHandler.loadPersonsToDatabase(this);
                personArrayList = DatabaseHandler.selectPersons(this, "id = id");
                Log.d(TAG, "onResume: personArrayList.size() -> " + personArrayList.size());
//                notifyDataSetChanged();
//                loadNewPersonsData();
                Toast.makeText(this, "Persons Loaded in Database", Toast.LENGTH_SHORT).show();
                break;
            case R.id.deleteTable:
                Log.i(TAG, "onOptionsItemSelected : " + "case deleteTable");
                DatabaseHandler.deleteTable(this);
                personArrayList.clear();
                notifyDataSetChanged();
//                loadNewPersonsData();
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
        switch (requestCode) {
            case ShowPersonActivity.SHOW_PERSON_ACTIVITY:
                break;
            case SearchActivity.SEARCH_ACTIVITY:
                Toast.makeText(this, "total -> " + totalPersonsInDataBase + "\nshowing -> " + personArrayList.size(), Toast.LENGTH_SHORT).show();
                totalPersonsInDataBase = personArrayList.size();
//                setTitleText();
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onRequestPermissionsResult: permissions granted");
            personArrayList = DatabaseHandler.selectPersons(this, "id = id");
            Log.d(TAG, "onResume: personArrayList.size() -> " + personArrayList.size());
            totalPersonsInDataBase = personArrayList.size();
//            setTitleText();
//            PersonPhotoHandler photoHandler = new PersonPhotoHandler(this, this);
//            photoHandler.getPhotosFromMemory();
            notifyDataSetChanged();
//            loadNewPersonsData();
        }
    }

    public static MainActivity getContext() {
        return context;
    }

    public ArrayList<Person> getPersonArrayList() {
        return this.personArrayList;
    }

    public void setPersonArrayList(ArrayList<Person> people) {
        this.personArrayList = people;
    }

    public Person getPerson(int index) {
        return this.personArrayList.get(index);
    }

    public void addPerson(Person person) {
        this.personArrayList.add(person);
    }

//    public void notifyDataSetChanged() {
//        this.customAdapter.notifyDataSetChanged();
//    }

    public void notifyDataSetChanged() {
//        setTitleText();
        this.customAdapter.loadPersonsData(this.personArrayList);
    }

//    public void setTitleText() {
//        Log.d(TAG, "setTitleText: starts");
//        Resources resources = getResources();
//        setTitle(resources.getString(R.string.person_list, personArrayList.size(), totalPersonsInDataBase));
//        Log.d(TAG, "setTitleText: ends");
//    }

    public boolean checkPermission(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

}
