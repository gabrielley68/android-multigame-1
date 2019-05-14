package com.example.flori.android_multi_game.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Player extends RealmObject {

    @PrimaryKey
    private String name;
    private String firstName;
    private int age;
    private String localisation;
    private String picture;

    public Player(){}

    public Player(String name, String firstName, int age, String localisation, String picture){
        setName(name);
        setFirstName(firstName);
        setAge(age);
        setLocalisation(localisation);
        setPicture(picture);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
