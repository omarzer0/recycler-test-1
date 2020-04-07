package com.example.android.recylertest1;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name, number;
    private int imgResource;

    public Contact(String name, String number, int imgResource) {
        this.name = name;
        this.number = number;
        this.imgResource = imgResource;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        imgResource = R.drawable.img1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
