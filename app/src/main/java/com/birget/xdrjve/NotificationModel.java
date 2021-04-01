package com.birget.xdrjve;

public class NotificationModel {
    private String uid;
    private String name;
    private String profil;

    public NotificationModel(String uid,String name,String profil) {
        this.uid = uid;
        this.name = name;
        this.profil = profil;
    }

    public NotificationModel() {
    }

    public String getImageUrl() {
        return profil;
    }

    public void setImageUrl(String profil) {
        this.profil = profil;
    }

    public String getUsername() {
        return name;
    }

    public void setUsername(String name) {
        this.name = name;
    }

    public String getId() {
        return uid;
    }

    public void setId(String uid) {
        this.uid = uid;
    }
}
