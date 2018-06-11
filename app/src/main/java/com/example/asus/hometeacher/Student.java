package com.example.asus.hometeacher;

/**
 * Created by Asus on 2018/5/29.
 */

public class Student  {
    private int imageID;
    private String studentname;
    private String studenttelephone;
    private String studenthelpstudygrade;
    private String studenthelpstudyarea;
    private String studenthelpstudyitem;
    public Student(int imageID,String studentname,String studenttelephone,String studenthelpstudygrade,String studenthelpstudyitem,String studenthelpstudyarea){
        this.imageID=imageID;
        this.studentname=studentname;
        this.studenthelpstudyitem=studenthelpstudyitem;
        this.studenttelephone=studenttelephone;
        this.studenthelpstudygrade=studenthelpstudygrade;
        this.studenthelpstudyarea=studenthelpstudyarea;
    }

    public int getImageID() {
        return imageID;
    }

    public String getStudentname() {
        return studentname;
    }

    public String getStudenttelephone() {
        return studenttelephone;
    }

    public String getStudenthelpstudygrade() {
        return studenthelpstudygrade;
    }

    public String getStudenthelpstudyarea() {
        return studenthelpstudyarea;
    }

    public String getStudenthelpstudyitem() {
        return studenthelpstudyitem;
    }
}
