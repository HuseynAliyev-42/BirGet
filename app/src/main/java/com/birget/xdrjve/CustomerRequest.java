package com.birget.xdrjve;

public class CustomerRequest {
        String name;
        String phone;
        String uid;
    public CustomerRequest() {

    }
    public CustomerRequest(String name,String phone,String uid) {
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
