package com.birget.xdrjve;

public class Search_7_model {
        String name;
        String phone;
        String uid;
    public Search_7_model(){

    }

    public Search_7_model(String name,String phone,String uid) {
        this.name = name;
        this.phone = phone;
        this.uid = uid;
    }

        public String getName() {
        return name;
    }

        public void setName(String name) {
        this.name = name;
    }

        public String getPhone() {
        return phone;
    }

        public void setPhone(String phone) {
        this.phone = phone;
    }

        public String getUid() {
        return uid;
    }
}
