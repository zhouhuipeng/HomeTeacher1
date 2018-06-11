package com.example.asus.hometeacher;

/**
 * Created by 说散就散 on 2018/6/10.
 */

public class Mystudent {
    private int ImageID;
    private String student_name;
    Mystudent(int ImageID,String student_name){
        this.ImageID=ImageID;
        this.student_name=student_name;
    }

    public int getImageID() {
        return ImageID;
    }

    public String getStudent_name() {
        return student_name;
    }
}