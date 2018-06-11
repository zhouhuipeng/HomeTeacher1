package com.example.asus.hometeacher;

import cn.bmob.v3.BmobObject;

/**
 * Created by Asus on 2018/6/10.
 */

public class HT_student_teacher extends BmobObject {
    private String U_id,U_name;

    public String getU_id() {
        return U_id;
    }

    public String getU_name() {
        return U_name;
    }

    public void setU_id(String u_id) {
        U_id = u_id;
    }

    public void setU_name(String u_name) {
        U_name = u_name;
    }
}
