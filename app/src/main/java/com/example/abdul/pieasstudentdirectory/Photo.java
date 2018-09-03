package com.example.abdul.pieasstudentdirectory;

import java.io.Serializable;

public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String owner;
    private String id;
    private String secret;
    private String server;
    private String farm;
    private String photoURL;

    public Photo() {
        this("", "", "", "", "", "", "");
    }

    public Photo(String title, String owner, String id, String secret, String server, String farm, String photoURL) {
        this.title = title;
        this.owner = owner;
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.photoURL = photoURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getFarm() {
        return farm;
    }

    public void setFarm(String farm) {
        this.farm = farm;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "title='" + title + '\'' +
                ", owner='" + owner + '\'' +
                ", id='" + id + '\'' +
                ", secret='" + secret + '\'' +
                ", server='" + server + '\'' +
                ", farm='" + farm + '\'' +
                '}' + '\n';
    }

}
