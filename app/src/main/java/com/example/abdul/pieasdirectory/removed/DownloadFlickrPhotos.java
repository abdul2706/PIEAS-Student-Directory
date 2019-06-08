//package com.example.abdul.pieasdirectory.removed;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.example.abdul.pieasdirectory.removed.Photo;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.Collections;
//
//public class DownloadFlickrPhotos extends AsyncTask<Photo, Void, ArrayList<Photo>> {
//
//    private static final String TAG = "DownloadFlickrPhotos";
//    private ArrayList<File> photoFiles;
//    private File photosFolderPath;
//
//    private final OnPhotosDownload callBack;
//
//    interface OnPhotosDownload {
//        void onPhotosDownload(ArrayList<Photo> photos);
//    }
//
//    public DownloadFlickrPhotos(OnPhotosDownload callBack, File photosFolderPath) {
//        this.callBack = callBack;
//        this.photoFiles = new ArrayList<>();
//        this.photosFolderPath = photosFolderPath;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<Photo> photos) {
//        Log.d(TAG, "onPostExecute: starts");
//        if(callBack != null) {
//            callBack.onPhotosDownload(photos);
//        }
//        Log.d(TAG, "onPostExecute: ends");
//    }
//
//    @Override
//    protected ArrayList<Photo> doInBackground(Photo... photos) {
//        Log.d(TAG, "doInBackground: starts");
//        try {
//            for (Photo photo : photos) {
//                URL imageURL = new URL(photo.getPhotoURL());
//                File imageFilePath = new File(this.photosFolderPath, photo.getTitle() + ".jpg");
//                photo.setPhotoFile(imageFilePath);
//                if (!imageFilePath.exists()) {
//                    boolean resultImageFile = imageFilePath.createNewFile();
//                    Log.d(TAG, "doInBackground: resultImageFile -> " + resultImageFile);
//                    HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
//                    connection.setRequestMethod("GET");
//                    connection.connect();
//                    int responseCode = connection.getResponseCode();
//                    Log.d(TAG, "doInBackground: responseCode -> " + responseCode);
//                    InputStream inputStream = connection.getInputStream();
//                    FileOutputStream fileOutputStream = new FileOutputStream(imageFilePath);
//                    byte[] buffer = new byte[1024 * 100];
//                    long total = 0;
//                    int count;
//                    while ((count = inputStream.read(buffer)) != -1) {
//                        total += count;
//                        fileOutputStream.write(buffer, 0, count);
//                    }
//                    Log.d(TAG, "doInBackground: total -> " + total);
//                } else {
//                    Log.d(TAG, "doInBackground: image file already exists");
//                }
//            }
//            Log.d(TAG, "doInBackground: after for loop");
//        } catch (Exception e) {
//            Log.d(TAG, "doInBackground: " + e.getMessage());
//            e.printStackTrace();
//        }
//
//        ArrayList<Photo> photoArrayList = new ArrayList<>();
//        Collections.addAll(photoArrayList, photos);
//        return photoArrayList;
//    }
//
//}
