package com.example.asus.hometeacher;

import cn.bmob.v3.BmobObject;

/**
 * Created by Asus on 2018/6/8.
 */

public class HT_conversation extends BmobObject {
    private String C_sender,C_receive,C_content;
   public  HT_conversation(){};

    public String getC_content() {
        return C_content;
    }

    public String getC_receive() {
        return C_receive;
    }

    public String getC_sender() {
        return C_sender;
    }

    public void setC_content(String c_content) {
        C_content = c_content;
    }

    public void setC_receive(String c_receive) {
        C_receive = c_receive;
    }

    public void setC_sender(String c_sender) {
        C_sender = c_sender;
    }
}
