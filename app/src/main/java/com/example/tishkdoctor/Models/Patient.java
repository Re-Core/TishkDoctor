package com.example.tishkdoctor.Models;

public class Patient {

    private String email,password,name,phone,pateintId;

    public Patient() {
    }

    public Patient(String email, String password, String name, String phone,String pateintId) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.pateintId=pateintId;
    }

    public String getPateintId() {
        return pateintId;
    }

    public void setPateintId(String pateintId) {
        this.pateintId = pateintId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


}
