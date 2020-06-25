package com.example.firebasefetchdatalistview;

public class MyList {

    String id,name,city,gender;

    public MyList(String id, String name, String city, String gender) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.gender = gender;
    }

    public MyList(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
