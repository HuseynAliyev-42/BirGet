package com.birget.xdrjve;

public class Search_6_model {
    String price;
    String fromplace;
    String toplace;
    String time;
    String name;
    String uid;
    String predicttime;
    String profil;
    String placeto;
    String placedan;
    String day;
    String month;
    public Search_6_model(){

    }

    public Search_6_model(String price,String fromplace,String toplace,String time,String name,String uid,String predicttime,String profil,String placeto,String placedan,String day,String month) {
        this.price = price;
        this.fromplace = fromplace;
        this.toplace = toplace;
        this.time = time;
        this.name = name;
        this.uid = uid;
        this.predicttime = predicttime;
        this.profil = profil;
        this.placeto = placeto;
        this.placedan = placedan;
        this.day = day;
        this.month = month;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPlacedan() {
        return placedan;
    }

    public void setPlacedan(String placedan) {
        this.placedan = placedan;
    }

    public String getPlaceto() {
        return placeto;
    }

    public void setPlaceto(String placeto) {
        this.placeto = placeto;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFromplace() {
        return fromplace;
    }

    public void setFromplace(String fromplace) {
        this.fromplace = fromplace;
    }

    public String getToplace() {
        return toplace;
    }

    public void setToplace(String toplace) {
        this.toplace = toplace;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPredicttime() {
        return predicttime;
    }

    public void setPredicttime(String predicttime) {
        this.predicttime = predicttime;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }
}
