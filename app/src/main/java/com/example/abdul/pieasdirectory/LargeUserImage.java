package com.example.abdul.pieasdirectory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class LargeUserImage extends AppCompatActivity {

    private static final String TAG = "LargeUserImage";
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_user_image);

        imageView = findViewById(R.id.largeUserImage);

        Intent intent = getIntent();
        String studentName = intent.getExtras().getString("studentName");
        setTitle(studentName);

        String studentPhotoFileString = intent.getExtras().getString("studentPhotoFileString");
        try {
            Picasso.get().load(Uri.fromFile(new File(studentPhotoFileString)))
                    .error(R.drawable.man_icon)
                    .placeholder(R.drawable.man_icon)
                    .into(imageView);
        } catch (Exception e) {
            imageView.setImageResource(R.drawable.man_icon);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
