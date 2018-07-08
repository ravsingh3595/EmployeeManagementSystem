package com.androidproject.employeemanagementsystem.model.user;


public class User {
    private String full_name;
    private String email;
    private String password;
    private String picture;
    private String  birthDate;
    private String address;
    private String city;
    private String province;
    private String country;
    private double latitude;
    private double longitude;

    public User() {
    }

    public User(String full_name, String email, String password, String picture, String birthDate, String address, String city, String province, String country, double latitude, double longitude) {
        this.full_name = full_name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.province = province;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getFullname() {
        return full_name;
    }

    public void setFullname(String full_name) {
        this.full_name = full_name;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
