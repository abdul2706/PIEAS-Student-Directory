//package com.example.abdul.pieasdirectory.removed;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//enum DownloadStatus {IDLE, PROCESSING, NOT_INSTALLED, FAILED_OR_EMPTY, OK}
//
//class GetFlickrPhotos extends AsyncTask<String, Void, String> {
//
//    private static final String TAG = "GetFlickrPhotos";
//    private ArrayList<Photo> photoArrayList;
//    private DownloadStatus downloadStatus;
//    private final OnPhotosAvailable callback;
//
//    interface OnPhotosAvailable {
//        void onPhotosAvailable(ArrayList<Photo> photos, DownloadStatus status);
//    }
//
//    public GetFlickrPhotos(OnPhotosAvailable callback) {
//        downloadStatus = DownloadStatus.IDLE;
//        this.callback = callback;
//        this.photoArrayList = new ArrayList<>();
//    }
//
//    public void runOnSameThread(String url) {
//        if (callback != null) {
//            callback.onPhotosAvailable(createPhotos(doInBackground(url)), downloadStatus);
//        }
//    }
//
//    @Override
//    protected void onPostExecute(String jsonData) {
//        Log.d(TAG, "onPostExecute: starts");
//        if (callback != null) {
//            callback.onPhotosAvailable(createPhotos(jsonData), downloadStatus);
//        }
//        Log.d(TAG, "onPostExecute: end");
//    }
//
//    @Override
//    protected String doInBackground(String... strings) {
//        Log.d(TAG, "doInBackground: start");
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//
//        if (strings == null) {
//            downloadStatus = DownloadStatus.NOT_INSTALLED;
//            Log.d(TAG, "doInBackground: end");
//            return null;
//        }
//
//        try {
//            downloadStatus = DownloadStatus.PROCESSING;
//            URL url = new URL(strings[0]);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            connection.connect();
//            int responseCode = connection.getResponseCode();
//            Log.i(TAG, "doInBackground: responseCode -> " + responseCode);
//
//            StringBuilder result = new StringBuilder();
//            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//                result.append(line).append('\n');
//            }
//
//            downloadStatus = DownloadStatus.OK;
////            Log.d(TAG, "doInBackground: result -> " + result);
//            Log.d(TAG, "doInBackground: end");
//            return result.toString();
//        } catch (Exception e) {
//            Log.e(TAG, "doInBackground: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//
//            if (reader != null) {
//                try {
//                    reader.close();
//                } catch (IOException e) {
//                    Log.e(TAG, "doInBackground: Error Closing Stream: " + e.getMessage());
//                }
//            }
//        }
//
//        downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
//        Log.d(TAG, "doInBackground: end");
//        return null;
//    }
//
//    private ArrayList<Photo> createPhotos(String data) {
//        Log.d(TAG, "createPhotos: starts");
//        if (downloadStatus == DownloadStatus.OK) {
//            try {
//                JSONObject jsonObject = new JSONObject(data);
//                JSONArray itemsArray = jsonObject.getJSONObject("photos").getJSONArray("photo");
//                for (int i = 0; i < itemsArray.length(); i++) {
//                    JSONObject jsonPhoto = itemsArray.getJSONObject(i);
//
//                    String title = jsonPhoto.getString("title");
//                    String farm = jsonPhoto.getString("farm");
//                    String server = jsonPhoto.getString("server");
//                    String id = jsonPhoto.getString("id");
//                    String secret = jsonPhoto.getString("secret");
//                    String photoURL = "http://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";
//                    Photo photoObject = new Photo(title, photoURL);
//                    Log.d(TAG, "createPhotos: photoObject -> " + photoObject);
//                    photoArrayList.add(photoObject);
//                }
//            } catch (JSONException e) {
//                Log.e(TAG, "onDownloadComplete: error -> " + e.getMessage());
//                e.printStackTrace();
//                downloadStatus = DownloadStatus.FAILED_OR_EMPTY;
//            }
//        }
//
//        Log.d(TAG, "createPhotos: ends");
//        return photoArrayList;
//    }
//
//}
