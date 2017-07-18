package com.example.nirmal.sintacs;

import android.graphics.Bitmap;

/**
 * Created by nirmal on 17/7/17.
 */

public class ImageDataClass  {
    Bitmap myImageBitMap;
    String text;

    ImageDataClass(Bitmap myMap, String name){
        myImageBitMap = myMap;
        text = name;
    }

    public Bitmap getMyImageBitMap() {
        return myImageBitMap;
    }

    public void setMyImageBitMap(Bitmap myImageBitMap) {
        this.myImageBitMap = myImageBitMap;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}

