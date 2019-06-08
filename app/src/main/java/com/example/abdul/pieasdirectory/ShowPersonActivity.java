package com.example.abdul.pieasdirectory;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowPersonActivity extends AppCompatActivity {

    private static final String TAG = "ShowPersonActivity";
    public static final int SHOW_PERSON_ACTIVITY = 2;
    private static final int CALL_PHONE_PERMISSION = 1;
    private static final int SMS_SEND_PERMISSION = 2;
    private final int[] viewsID = {R.id.personNameEditText, R.id.designationEditText, R.id.postEditText, R.id.phoneNoEditText, R.id.officeLocationEditText, R.id.extensionEditText, R.id.phoneNoEditText, R.id.cellNoEditView, R.id.emailEditText};

    private ImageView personImageView;
    private ArrayList<TextView> textViews = new ArrayList<>();
    private Person person;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showperson2);

        mainActivity = MainActivity.getContext();
        initViews();

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        if (index != -1) {
            person = mainActivity.getPerson(index);
            setTitle(person.getPersonData("personName"));
            setViewsData();
        } else {
            Toast.makeText(ShowPersonActivity.this, "Invalid Person", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void initViews() {
        textViews.clear();
        personImageView = findViewById(R.id.personImageView);
        for (int aViewsID : viewsID) {
            textViews.add((TextView) findViewById(aViewsID));
        }

        personImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowPersonActivity.this, LargeUserImage.class);
                intent.putExtra("personName", person.getPersonData("personName"));
                intent.putExtra("personPhotoFileString", person.getPersonData("department"));
                startActivity(intent);
            }
        });

        for (int i = 0; i < viewsID.length; i++) {
            final int index = i;
            Log.d(TAG, "initViews: textViews -> " + textViews.get(i));
            textViews.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView view = (TextView) v;
                    Toast.makeText(getApplicationContext(), view.getText().toString(), Toast.LENGTH_SHORT).show();
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    final EditText editText = new EditText(ShowPersonActivity.this);
                    editText.setLayoutParams(layoutParams);
                    new AlertDialog.Builder(ShowPersonActivity.this)
                            .setView(editText)
                            .setTitle("Old Value: " + view.getText().toString())
                            .setMessage("Enter New Value: ")
                            .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String newValue = editText.getText().toString();
                                    Toast.makeText(getApplicationContext(), "New Value: " + newValue, Toast.LENGTH_SHORT).show();
                                    DatabaseHandler.updatePerson(getApplicationContext(), person, Person.PERSON_KEYS[index], newValue);
                                    person.setPersonData(Person.PERSON_KEYS[index], newValue);
                                    setViewsData();
                                    mainActivity.notifyDataSetChanged();
//                                    mainActivity.loadNewPersonsData();
                                    dialog.dismiss();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .show();
                }
            });
        }
    }

    public void setViewsData() {
        personImageView.setImageResource(R.drawable.pieas_logo);
        for (int i = 0; i < viewsID.length; i++) {
            textViews.get(i).setText(person.getPersonData(Person.PERSON_KEYS[i]));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_showperson, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.callBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + person.getPersonData("phoneNo")));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
                }
                break;
            case R.id.msgBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_SENDTO);
                    callIntent.setData(Uri.fromParts("sms", person.getPersonData("phoneNo"), null));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
                }
                break;
            default:
                Toast.makeText(this, "Invalid Menu Item Selected", Toast.LENGTH_SHORT).show();
                return false;
        }
        return true;
    }

    public void actionPerformed(View view) {
        switch (view.getId()) {
            case R.id.callBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + person.getPersonData("phoneNo")));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, CALL_PHONE_PERMISSION);
                }
                break;
            case R.id.msgBtn:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
                    Intent callIntent = new Intent(Intent.ACTION_SENDTO);
                    callIntent.setData(Uri.fromParts("sms", person.getPersonData("phoneNo"), null));
                    startActivity(callIntent);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, SMS_SEND_PERMISSION);
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CALL_PHONE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Call Permission Granted", Toast.LENGTH_SHORT).show();
        } else if (requestCode == SMS_SEND_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            Log.i(TAG, "onRequestPermissionsResult: Permission Granted -> " + permissions[0]);
            Toast.makeText(this, "Message Permission Granted", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
//            Uri selectedImageUri = data.getData();
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//                personImageView.setImageBitmap(bitmap);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
