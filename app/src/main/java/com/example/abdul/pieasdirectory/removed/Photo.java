//package com.example.abdul.pieasdirectory.removed;
//
//import java.io.File;
//import java.io.Serializable;
//
//public class Photo implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    private String title;
//    private String photoURL;
//    private File photoFile;
//
//    public Photo() {
//        this("", "");
//    }
//
//    public Photo(String title, String photoURL) {
//        this.title = title;
//        this.photoURL = photoURL;
//        this.photoFile = new File("");
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getPhotoURL() {
//        return photoURL;
//    }
//
//    public void setPhotoURL(String photoURL) {
//        this.photoURL = photoURL;
//    }
//
//    public File getPhotoFile() {
//        return photoFile;
//    }
//
//    public void setPhotoFile(File photoFile) {
//        this.photoFile = photoFile;
//    }
//
//    @Override
//    public String toString() {
//        return "Photo{" +
//                "title='" + title + '\'' +
//                ", photoURL='" + photoURL + '\'' +
//                ", photoFile=" + photoFile +
//                '}';
//    }
//
//}
