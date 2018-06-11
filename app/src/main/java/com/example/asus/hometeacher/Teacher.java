package com.example.asus.hometeacher;

/**
 * Created by Asus on 2018/5/29.
 */

public class Teacher {
    private String teachername;
    private int imageID;
    private String teachertelephone;
    private String teacherstudyhistory;
    private String teacherhelpstudyarea;
    private String teacherintroduce;

    public Teacher(int imageID,String teachername,String teachertelephone,String teacherstudyhistory,String teacherhelpstudyarea,String teacherintroduce){
        this.imageID=imageID;
        this.teachername=teachername;
        this.teachertelephone=teachertelephone;
        this.teacherstudyhistory=teacherstudyhistory;
        this.teacherhelpstudyarea=teacherhelpstudyarea;
        this.teacherintroduce=teacherintroduce;

    }

    public String getTeachername() {
        return teachername;
    }

    public int getImageID() {
        return imageID;
    }

    public String getTeachertelephone() {
        return teachertelephone;
    }

    public String getTeacherstudyhistory() {
        return teacherstudyhistory;
    }

    public String getTeacherhelpstudyarea() {
        return teacherhelpstudyarea;
    }

    public String getTeacherintroduce() {
        return teacherintroduce;
    }
}
