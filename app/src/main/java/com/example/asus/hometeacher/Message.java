package com.example.asus.hometeacher;

/**
 * Created by Asus on 2018/6/8.
 */

public class Message {
    private String message,name;
    private int ImageID;
    public Message(String name,String message,int ImageID){
        this.name=name;
        this.message=message;
        this.ImageID=ImageID;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public int getImageID() {
        return ImageID;
    }
}
