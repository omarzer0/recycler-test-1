package com.example.android.recylertest1;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name , number ;
    private int imgResource;
    private int[] images = {R.drawable.img1,R.drawable.img2,R.drawable.img3};
    private static int index = 0;

    public Contact(String name, String number, int imgResource) {
        this.name = name;
        this.number = number;
        this.imgResource = imgResource;
    }

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        imgResource = images[getIndex()];
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

    private int getIndex(){
        if(index < 3){
            return index++;
        }else{
            return index=0;
        }
    }
    public void getItelClickedPosition(){

    }
}
