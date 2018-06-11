package com.example.asus.hometeacher;

import cn.bmob.v3.BmobObject;

/**
 * Created by Asus on 2018/6/5.
 */

public class HT_Student extends BmobObject {
    private String  S_id,S_pass,S_phone_number,S_grade,S_item,S_area,S_credit,S_name;

    public String getS_area() {
        return S_area;
    }

    public String getS_credit() {
        return S_credit;
    }

    public String getS_grade() {
        return S_grade;
    }

    public String getS_id() {
        return S_id;
    }

    public String getS_item() {
        return S_item;
    }

    public String getS_pass() {
        return S_pass;
    }

    public String getS_phone_number() {
        return S_phone_number;
    }

    public String getS_name() {
        return S_name;
    }

    public void setS_area(String s_area) {
        S_area = s_area;
    }

    public void setS_credit(String s_credit) {
        S_credit = s_credit;
    }

    public void setS_grade(String s_grade) {
        S_grade = s_grade;
    }

    public void setS_id(String s_id) {
        S_id = s_id;
    }

    public void setS_item(String s_item) {
        S_item = s_item;
    }

    public void setS_pass(String s_pass) {
        S_pass = s_pass;
    }

    public void setS_phone_number(String s_phone_number) {
        S_phone_number = s_phone_number;
    }

    public void setS_name(String s_name) {
        S_name = s_name;
    }
}
