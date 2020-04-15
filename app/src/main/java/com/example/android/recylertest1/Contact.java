package com.example.android.recylertest1;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "Contact_Name")
    private String name;
    @ColumnInfo(name = "Contact_Number")
    private String number;
    @ColumnInfo(name = "Contact_Image")
    private int imgResource;

//    public Contact(String name, String number, int imgResource) {
//        this.name = name;
//        this.number = number;
//        this.imgResource = imgResource;
//    }

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
