package com.example.abdul.pieasstudentdirectory;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFlickrJSONData extends AsyncTask<String, Void, Void> implements GetRawData.OnDownloadComplete {

    private static final String TAG = "GetFlickrJSONData";
    private MainActivity mainActivity;
    private ArrayList<Student> studentArrayList;
    private String baseURL;

//    private final OnDataAvailable callBack;
//    private boolean runOnSameThread = false;

//    interface OnDataAvailable {
//        void onDataAvailable(List<Photo> data, DownloadStatus status);
//    }

    GetFlickrJSONData(MainActivity mainActivity, String baseURL, ArrayList<Student> students) {
        this.mainActivity = mainActivity;
        this.baseURL = baseURL;
        this.studentArrayList = students;
    }

//    public void executeOnSameThread(String searchCriteria) {
//        Log.d(TAG, "executeOnSameThread: start");
//        runOnSameThread = true;
//        String destinationURI = createURI(searchCriteria);
//        GetRawData getRawData = new GetRawData(this);
//        getRawData.execute(destinationURI);
//        Log.d(TAG, "executeOnSameThread: end");
//    }

    private String createURI() {
        //https://api.flickr.com/services/rest/?
        //method=flickr.photos.search&
        //api_key=bdecd7c92f562d11aac6087ee05644de&
        //user_id=156365122@N06&
        //format=json&
        //nojsoncallback=1
        return Uri.parse(baseURL).buildUpon()
                .appendQueryParameter("method", "flickr.photos.search")
                .appendQueryParameter("api_key", "bdecd7c92f562d11aac6087ee05644de")
                .appendQueryParameter("user_id", "156365122@N06")
                .appendQueryParameter("lang", "en-us")
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .build().toString();
    }

    @Override
    protected void onPostExecute(Void s) {
        if (mainActivity != null) {
            mainActivity.notifyDataSetChanged();
//            callBack.onDataAvailable(s, DownloadStatus.OK);
        }
    }

    @Override
    protected Void doInBackground(String... strings) {
        String destinationUri = createURI();
        GetRawData getRawData = new GetRawData(this);
        getRawData.runOnSameThread(destinationUri);
        return null;
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray itemsArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject jsonPhoto = itemsArray.getJSONObject(i);

                    String title = jsonPhoto.getString("title");
                    String owner = jsonPhoto.getString("owner");
                    String id = jsonPhoto.getString("id");
                    String secret = jsonPhoto.getString("secret");
                    String server = jsonPhoto.getString("server");
                    String farm = jsonPhoto.getString("farm");
                    String photoURL = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret;// + ".jpg";
                    Photo photoObject = new Photo(title, owner, id, secret, server, farm, photoURL);

                    for (int j = 0; j < studentArrayList.size(); j++) {
                        if (studentArrayList.get(j).getStudentData("regNo").equalsIgnoreCase(photoObject.getTitle())) {
                            studentArrayList.get(j).setPhoto(photoObject);
                        }
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "onDownloadComplete: error -> " + e.getMessage());
                e.printStackTrace();
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }
//        if (runOnSameThread && callBack != null) {
//            callBack.onDataAvailable(photoList, status);
//        }
    }

}


















/*
package com.example.abdul.pieasstudentdirectory;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetFlickrJSONData extends AsyncTask<String, Void, List<Photo>> implements GetRawData.OnDownloadComplete {

    private static final String TAG = "GetFlickrJSONData";
    private List<Photo> photoList = null;
    private String baseURL;
    private String language;
    private boolean matchAll;

    private final OnDataAvailable callBack;
    private boolean runOnSameThread = false;

    interface OnDataAvailable {
        void onDataAvailable(List<Photo> data, DownloadStatus status);
    }

    GetFlickrJSONData(OnDataAvailable callBack, String baseURL, String language, boolean matchAll) {
        this.callBack = callBack;
        this.baseURL = baseURL;
        this.language = language;
        this.matchAll = matchAll;
    }

    public void executeOnSameThread(String searchCriteria) {
        Log.d(TAG, "executeOnSameThread: start");
        runOnSameThread = true;
        String destinationURI = createURI(searchCriteria, language, matchAll);
        GetRawData getRawData = new GetRawData(this);
        getRawData.execute(destinationURI);
        Log.d(TAG, "executeOnSameThread: end");
    }

    private String createURI(String searchCriteria, String language, boolean matchAll) {
        //https://api.flickr.com/services/rest/?
        //method=flickr.photos.search&
        //api_key=bdecd7c92f562d11aac6087ee05644de&
        //user_id=156365122@N06&
        //format=json&
        //nojsoncallback=1
        return Uri.parse(baseURL).buildUpon()
                .appendQueryParameter("method", "flickr.photos.search")
                .appendQueryParameter("api_key", "bdecd7c92f562d11aac6087ee05644de")
                .appendQueryParameter("user_id", "156365122@N06")
                .appendQueryParameter("lang", language)
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .build().toString();
    }

    @Override
    protected void onPostExecute(List<Photo> s) {
        if (callBack != null) {
            callBack.onDataAvailable(s, DownloadStatus.OK);
        }
    }

    @Override
    protected List<Photo> doInBackground(String... strings) {
        String destinationUri = createURI(strings[0], language, matchAll);
        GetRawData getRawData = new GetRawData(this);
        getRawData.runOnSameThread(destinationUri);
        return photoList;
    }

    @Override
    public void onDownloadComplete(String data, DownloadStatus status) {
        if (status == DownloadStatus.OK) {
            photoList = new ArrayList<>();
            try {
//                Log.d(TAG, "onDownloadComplete: data -> " + data);
                JSONObject jsonObject = new JSONObject(data);
                Log.d(TAG, "onDownloadComplete: jsonObject -> " + jsonObject.get("photos"));
                JSONArray itemsArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
                Log.d(TAG, "onDownloadComplete: itemsArray -> " + itemsArray);
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject jsonPhoto = itemsArray.getJSONObject(i);

                    String title = jsonPhoto.getString("title");
                    String owner = jsonPhoto.getString("owner");
                    String id = jsonPhoto.getString("id");
                    String secret = jsonPhoto.getString("secret");
                    String server = jsonPhoto.getString("server");
                    String farm = jsonPhoto.getString("farm");

                    String photoURL = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";

                    Photo photoObject = new Photo(title, owner, id, secret, server, farm, photoURL);
                    photoList.add(photoObject);
                }
            } catch (JSONException e) {
                Log.e(TAG, "onDownloadComplete: error -> " + e.getMessage());
                e.printStackTrace();
                status = DownloadStatus.FAILED_OR_EMPTY;
            }
        }
        if (runOnSameThread && callBack != null) {
            callBack.onDataAvailable(photoList, status);
        }
    }

}
*/