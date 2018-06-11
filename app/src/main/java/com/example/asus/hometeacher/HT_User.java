package com.example.asus.hometeacher;

import cn.bmob.v3.BmobObject;

/**
 * Created by Asus on 2018/6/10.
 */

public class HT_User extends BmobObject {
    private String U_id,U_pass;

    public void setU_id(String u_id) {
        U_id = u_id;
    }

    public void setU_pass(String u_pass) {
        U_pass = u_pass;
    }

    public String getU_id() {
        return U_id;
    }

    public String getU_pass() {
        return U_pass;
    }
}
