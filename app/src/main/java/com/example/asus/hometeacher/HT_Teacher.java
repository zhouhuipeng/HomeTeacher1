package com.example.asus.hometeacher;

import cn.bmob.v3.BmobObject;

/**
 * Created by Asus on 2018/6/5.
 */

public class HT_Teacher extends BmobObject {
    private String T_id,T_pass,T_phone_number,T_education_bg,T_professor_grade,T_item,T_area,T_power,T_name;

    public String getT_area() {
        return T_area;
    }

    public String getT_education_bg() {
        return T_education_bg;
    }

    public String getT_id() {
        return T_id;
    }

    public String getT_item() {
        return T_item;
    }

    public String getT_pass() {
        return T_pass;
    }

    public String getT_phone_number() {
        return T_phone_number;
    }

    public String getT_power() {
        return T_power;
    }

    public String getT_professor_grade() {
        return T_professor_grade;
    }

    public String getT_name() {
        return T_name;
    }


    public void setT_area(String t_area) {
        T_area = t_area;
    }

    public void setT_education_bg(String t_education_bg) {
        T_education_bg = t_education_bg;
    }

    public void setT_id(String t_id) {
        T_id = t_id;
    }

    public void setT_item(String t_item) {
        T_item = t_item;
    }

    public void setT_pass(String t_pass) {
        T_pass = t_pass;
    }

    public void setT_phone_number(String t_phone_number) {
        T_phone_number = t_phone_number;
    }

    public void setT_power(String t_power) {
        T_power = t_power;
    }

    public void setT_professor_grade(String t_professor_grade) {
        T_professor_grade = t_professor_grade;
    }

    public void setT_name(String t_name) {
        T_name = t_name;
    }
}
