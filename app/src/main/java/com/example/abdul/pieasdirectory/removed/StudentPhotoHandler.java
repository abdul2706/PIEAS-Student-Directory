//package com.example.abdul.pieasdirectory.removed;
//
//import android.content.Context;
//import android.net.ConnectivityManager;
//import android.net.Network;
//import android.net.NetworkInfo;
//import android.net.Uri;
//import android.os.Environment;
//import android.util.Log;
//import android.widget.Toast;
//
//import java.io.File;
//import java.util.ArrayList;
//
//enum NetworkStatus {AVAILABLE, NOT_AVAILABLE}
//
//public class StudentPhotoHandler implements GetFlickrPhotos.OnPhotosAvailable, DownloadFlickrPhotos.OnPhotosDownload {
//
//    private static final String TAG = "StudentPhotoHandler";
//    private File mainFolderPath = new File(Environment.getExternalStorageDirectory(), "PIEAS Person Directory");
//    private File photosFolderPath = new File(mainFolderPath, "Photos");
//    private File databaseFolderPath = new File(mainFolderPath, "Database");
//    private NetworkStatus networkStatus;
//    private ArrayList<Photo> photoArrayList;
//    private String baseURL;
//    private Context context;
//
//    private final OnDataAvailable callBack;
//
//    interface OnDataAvailable {
//        void onDataAvailable(ArrayList<Photo> files);
//    }
//
//    StudentPhotoHandler(Context context, OnDataAvailable callBack) {
//        Log.d(TAG, "StudentPhotoHandler: starts");
//        this.context = context;
//        this.callBack = callBack;
//        this.photoArrayList = new ArrayList<>();
//        this.networkStatus = NetworkStatus.NOT_AVAILABLE;
//        this.baseURL = "https://api.flickr.com/services/rest/";
//
//        if (!this.mainFolderPath.exists()) {
//            this.mainFolderPath.mkdir();
//            Toast.makeText(context, "PIEAS Person Directory Folder Created", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "StudentPhotoHandler: PIEAS Person Directory folder created");
//        } else {
//            Log.d(TAG, "StudentPhotoHandler: PIEAS Person Directory folder exists");
//        }
//
//        if (!this.photosFolderPath.exists()) {
//            this.photosFolderPath.mkdir();
//            Toast.makeText(context, "Photos Folder Created", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "StudentPhotoHandler: Photos folder created");
//        } else {
//            Log.d(TAG, "StudentPhotoHandler: Photos folder exists");
//        }
//
//        if (!this.databaseFolderPath.exists()) {
//            this.databaseFolderPath.mkdir();
//            Toast.makeText(context, "Database Folder Created", Toast.LENGTH_SHORT).show();
//            Log.d(TAG, "StudentPhotoHandler: Database folder created");
//        } else {
//            Log.d(TAG, "StudentPhotoHandler: Database folder exists");
//        }
//        Log.d(TAG, "StudentPhotoHandler: ends");
//    }
//
//    private String createURI() {
////    "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=bdecd7c92f562d11aac6087ee05644de&user_id=156365122@N06&format=json&nojsoncallback=1"
//        return Uri.parse(baseURL).buildUpon()
//                .appendQueryParameter("method", "flickr.photos.search")
//                .appendQueryParameter("api_key", "bdecd7c92f562d11aac6087ee05644de")
//                .appendQueryParameter("user_id", "156365122@N06")
//                .appendQueryParameter("lang", "en-us")
//                .appendQueryParameter("format", "json")
//                .appendQueryParameter("nojsoncallback", "1")
//                .build().toString();
//    }
//
//    public void getFlickrPhotos() {
//        Log.d(TAG, "getFlickrPhotos: starts");
//        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivityManager != null) {
//            Network[] networks = connectivityManager.getAllNetworks();
//            for (Network network : networks) {
//                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
//                if (networkInfo.getTypeName().equalsIgnoreCase("WIFI") && networkInfo.isConnected()) {
//                    networkStatus = NetworkStatus.AVAILABLE;
//                    break;
//                }
//            }
//        } else {
//            Log.d(TAG, "getFlickrPhotos: networks array is null");
//        }
//
//        Log.d(TAG, "onOptionsItemSelected: isNetworkAvailable -> " + networkStatus);
//        if (networkStatus == NetworkStatus.AVAILABLE) {
//            GetFlickrPhotos getFlickrPhotos = new GetFlickrPhotos(this);
//            getFlickrPhotos.execute(createURI());
//            Log.d(TAG, "getFlickrPhotos: ends");
//        }
//        Log.d(TAG, "getFlickrPhotos: ends");
//    }
//
//    @Override
//    public void onPhotosDownload(ArrayList<Photo> photos) {
//        Log.d(TAG, "onPhotosDownload: starts");
//        if (callBack != null) {
//            photoArrayList = photos;
//            callBack.onDataAvailable(photoArrayList);
//        }
//        Log.d(TAG, "onPhotosDownload: ends");
//    }
//
//    @Override
//    public void onPhotosAvailable(ArrayList<Photo> photos, DownloadStatus status) {
//        Log.d(TAG, "onPhotosAvailable: starts");
//        Log.d(TAG, "onPhotosAvailable: downloadStatus -> " + status);
//        if (status == DownloadStatus.OK) {
//            photoArrayList = photos;
//            DownloadFlickrPhotos downloadFlickrPhotos = new DownloadFlickrPhotos(this, photosFolderPath);
//            Photo[] photosArray = new Photo[photoArrayList.size()];
//            for (int i = 0; i < photoArrayList.size(); i++) {
//                photosArray[i] = photoArrayList.get(i);
//            }
//            downloadFlickrPhotos.execute(photosArray);
//        }
//        Log.d(TAG, "onPhotosAvailable: ends");
//    }
//
//    public void getPhotosFromMemory() {
//        Log.d(TAG, "getPhotosFromMemory: starts");
//        File[] photosPath = photosFolderPath.listFiles();
//        Log.d(TAG, "getPhotosFromMemory: photosPath -> " + photosPath);
//        if (photosPath != null && photosPath.length != 0) {
//            for (File photoFile : photosPath) {
//                Photo photo = new Photo();
//                String title = photoFile.getName();
//                photo.setTitle(title.substring(0, title.lastIndexOf(".jpg")));
//                photo.setPhotoFile(photoFile.getAbsoluteFile());
//                Log.d(TAG, "getPhotosFromMemory: photo -> " + photo);
//                photoArrayList.add(photo);
//            }
//        } else {
//            Log.d(TAG, "getPhotosFromMemory: No Person Photo Found Download Photos from Menu Option");
//            Toast.makeText(context, "No Person Photo Found.\nDownload Photos from Menu Option.", Toast.LENGTH_SHORT).show();
//        }
//        Log.d(TAG, "getPhotosFromMemory: photoArrayList.size() -> " + photoArrayList.size());
//        callBack.onDataAvailable(photoArrayList);
//        Log.d(TAG, "getPhotosFromMemory: ends");
//    }
//
//}
