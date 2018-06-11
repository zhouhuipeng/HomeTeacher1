package com.example.asus.hometeacher;
/**
 * Created by 说散就散 on 2018/6/10.
 */

public class Myteacher {
    private int ImageID;
    private String teacher_name;
    Myteacher(int ImageID,String teacher_name){
        this.ImageID=ImageID;
        this.teacher_name=teacher_name;
    }

    public int getImageID() {
        return ImageID;
    }

    public String getTeacher_name() {
        return teacher_name;
    }
}
