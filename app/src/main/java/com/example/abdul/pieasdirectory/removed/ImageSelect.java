//package com.example.abdul.pieasdirectory.removed;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.net.Uri;
//import android.provider.MediaStore;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//
//import com.example.abdul.pieasdirectory.R;
//
//import java.security.Permission;
//import java.security.Permissions;
//
//public class ImageSelect extends AppCompatActivity {
//
//    private static final String TAG = "ImageSelect";
//    public static final int REQUEST_CODE_IMAGE = 100;
//    ImageView imageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_image_select);
//
//        imageView = findViewById(R.id.imageView2);
//
//        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//        }
//    }
//
//    public void getPhoto(View view) {
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(intent, REQUEST_CODE_IMAGE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Uri selectedImageUri = data.getData();
//        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK) {
//            try {
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
//                imageView.setImageBitmap(bitmap);
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            getPhoto(findViewById(R.id.button));
//        }
//    }
//}
