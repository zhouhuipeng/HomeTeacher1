package com.example.asus.hometeacher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SQLQueryListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Asus on 2018/6/8.
 */

public class Send_Conversation extends Activity {
    private TextView conversationid;
    private Button send;
    private ListView conversationhistory_list;
    private EditText inputsendmessage;
    public List<Message> messageList=new ArrayList<>();
   public String receive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        conversationid=(TextView)findViewById(R.id.conversation_id);
        send=(Button)findViewById(R.id.send);
        conversationhistory_list=(ListView)findViewById(R.id.conversationhistory);
        inputsendmessage=(EditText)findViewById(R.id.inputmessage);

        messageList.clear();
        MessageAdapter adapter=new MessageAdapter(Send_Conversation.this,R.layout.conversationlist_item,messageList);
        conversationhistory_list.setAdapter(adapter);
        Bundle bundle=getIntent().getExtras();
        receive=bundle.getString("receive");
        conversationid.setText("与"+receive+"的谈话");
        //T_hread t=new T_hread(this);
       // t.setname(receive);
        //t.start();

        initMessage();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input;
                input=inputsendmessage.getText().toString();
                if(input.equals("")){
                    Toast.makeText(Send_Conversation.this,"请输入信息",Toast.LENGTH_SHORT).show();
                }
                else {
                   HT_conversation ht_conversation=new HT_conversation();
                    ht_conversation.setC_sender(new centermessage().name);
                    ht_conversation.setC_receive(receive);
                    ht_conversation.setC_content(input);
                    ht_conversation.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(Send_Conversation.this,"发送成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    inputsendmessage.setText(null);
                }
            }
        });
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    messageList.clear();
                    BmobQuery<HT_conversation> query = new BmobQuery<HT_conversation>();
                    String sql = "select * from HT_conversation where " +
                            "(C_sender ='" + new centermessage().name + "'and C_receive ='"+receive+"') " +
                            "or " +
                            "(C_sender ='"+receive+"' and C_receive = '"+new centermessage().name+"')";
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
                                        int pictureid = R.mipmap.sendtouxiang;
                                        Message m = new Message("", content, pictureid);
                                        messageList.add(m);
                                    }
                                    MessageAdapter adapter = new MessageAdapter(Send_Conversation.this, R.layout.conversationlist_item, messageList);
                                    conversationhistory_list.setAdapter(adapter);
                                } else {
                                    Log.i("smile", "查询成功，无数据返回");
                                }
                            } else {
                                Log.i("smile", "错误码：" + e.getErrorCode() + "，错误描述：" + e.getMessage());
                            }
                        }
                    });
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    private void initMessage() {
        BmobQuery<HT_conversation> query=new BmobQuery<HT_conversation>();
        String sql = "select * from HT_conversation where (C_sender ='" + new centermessage().name
                + "'and C_receive ='"+receive+"') or (C_sender ='"+receive+"' and C_receive = '"+new centermessage().name+"');";
        query.setSQL(sql);
        query.doSQLQuery(new SQLQueryListener<HT_conversation>() {
            @Override
            public void done(BmobQueryResult<HT_conversation> bmobQueryResult, BmobException e) {
                if(e ==null){
                    List<HT_conversation> list = (List<HT_conversation>) bmobQueryResult.getResults();
                    if(list!=null && list.size()>0){
                        for(HT_conversation ht_conversation :list){
                            String sender,receive,content;
                            sender=ht_conversation.getC_sender();
                            receive=ht_conversation.getC_receive();
                            content=ht_conversation.getC_content();
                            int pictureid=R.mipmap.sendtouxiang;
                            Message m=new Message("",content,pictureid);
                            messageList.add(m);
                        }
                        MessageAdapter adapter=new MessageAdapter(Send_Conversation.this,R.layout.conversationlist_item,messageList);
                        conversationhistory_list.setAdapter(adapter);
                    }else{
                        Log.i("smile", "查询成功，无数据返回");
                    }
                }else{
                    Log.i("smile", "错误码："+e.getErrorCode()+"，错误描述："+e.getMessage());
                }
            }
        });
    }
}

