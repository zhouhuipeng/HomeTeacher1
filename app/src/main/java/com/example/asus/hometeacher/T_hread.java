package com.example.asus.hometeacher;

import android.content.Context;
import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Asus on 2018/6/9.
 */

public class T_hread extends Thread {
        Context context;
        public String receive;
        public List<Message> messageList;
        public void setname(String receive){
            this.receive=receive;
        }

        public T_hread(Context context){
            this.context=context;
        }
        @Override
        public void run() {
            BmobQuery<HT_conversation> query=new BmobQuery<HT_conversation>();
            while(true) {
                String sql = "select *from HT_conversation where C_sender in (" + new centermessage().name + "," + receive+")";
                query.setSQL(sql);
                query.doSQLQuery(new SQLQueryListener<HT_conversation>() {
                    @Override
                    public void done(BmobQueryResult<HT_conversation> bmobQueryResult, BmobException e) {
                        if (e == null) {
                            List<HT_conversation> list = (List<HT_conversation>) bmobQueryResult.getResults();
                            if (list != null && list.size() > 0) {
                                for (HT_conversation ht_conversation : list) {
                                    String sender, receive, content;
                                    sender = ht_conversation.getC_sender();
                                    receive = ht_conversation.getC_receive();
                                    content = ht_conversation.getC_content();
                                    int pictureid = R.mipmap.touxiang;
                                    Message m = new Message(new centermessage().name, content, pictureid);
                                    messageList.add(m);
                                }

                            } else {
                                Log.i("smile", "查询成功，无数据返回");
                            }
                        } else {
                            Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                        }
                    }
                });
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
}
