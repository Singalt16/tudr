package com.example.tudr;
import java.util.ArrayList;

public class User {

    public User(int id) {

    }

    public int getRole() {
        return 2;
    }

    public ArrayList<String> getClasses() {
        ArrayList<String> classes =  new ArrayList<>();
        classes.add("CSC350");
        classes.add("MAT205");
        classes.add("PHY201");
        return classes;
    }

    public String getImage() {
        return "";
    }

    public void addClass() {

    }

    public void removeClass() {

    }

    public void setImage() {

    }
}
