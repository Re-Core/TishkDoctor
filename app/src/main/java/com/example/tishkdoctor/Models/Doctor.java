package com.example.tishkdoctor.Models;

public class Doctor {

    private String email,password,name,phone,doctorImg;

    public Doctor() {
    }



    public Doctor(String email, String password, String name, String phone, String doctorImg) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.doctorImg = doctorImg;
    }

    public Doctor(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
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
    public String getDoctorImg() {
        return doctorImg;
    }

    public void setDoctorImg(String doctorImg) {
        this.doctorImg = doctorImg;
    }
}
