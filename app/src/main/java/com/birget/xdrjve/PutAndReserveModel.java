package com.birget.xdrjve;

public class PutAndReserveModel {
    private String id;
    private String time;
    private String day;
    private String month;
    private String fromplace;
    private String toplace;
    public PutAndReserveModel(String id,String time,String day,String month,String fromplace,String toplace) {
        this.id = id;
        this.time = time;
        this.day = day;
        this.month = month;
        this.fromplace = fromplace;
        this.toplace = toplace;
    }
    public PutAndReserveModel(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToplace() {
        return toplace;
    }

    public void setToplace(String toplace) {
        this.toplace = toplace;
    }

    public String getFromplace() {
        return fromplace;
    }

    public void setFromplace(String fromplace) {
        this.fromplace = fromplace;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
