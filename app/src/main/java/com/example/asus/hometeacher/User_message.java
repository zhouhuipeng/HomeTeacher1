package com.example.asus.hometeacher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Asus on 2018/6/6.
 */

public class User_message extends Activity {
    private TextView user_id,user_name,user_phone,user_identy,user_studyhistory,add;
    private Button gophone,goemail,gochat;
    private ImageView user_pictuer;
     String suser_name,suser_phone,suser_identy,suser_studyhistory,user_id1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermessage);
        user_id=(TextView)findViewById(R.id.user_id);
        user_phone=(TextView)findViewById(R.id.user_phone);
        user_identy=(TextView)findViewById(R.id.user_identy);
        user_name=(TextView)findViewById(R.id.user_name);
        user_studyhistory=(TextView)findViewById(R.id.user_studyhistory);
        gophone=(Button)findViewById(R.id.gophone);
        goemail=(Button)findViewById(R.id.goemail);
        gochat=(Button)findViewById(R.id.gochat);
        user_pictuer=(ImageView)findViewById(R.id.user_picture);
        add=(TextView)findViewById(R.id.goadd);


        Bundle bundle=getIntent().getExtras();
        user_id1=bundle.getString("user_id");
        suser_name=bundle.getString("user_name");
        suser_phone=bundle.getString("user_phone");
        suser_identy=bundle.getString("user_identy");
        suser_studyhistory=bundle.getString("user_studyhistory");
        user_name.setText(suser_name);
        user_phone.setText(suser_phone);
        user_identy.setText(suser_identy);
        user_studyhistory.setText(suser_studyhistory);
        if(suser_identy.equals("教师")){
            user_pictuer.setImageResource(R.mipmap.touxiang);
            BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
            query.addWhereEqualTo("T_name",suser_name).addWhereEqualTo("T_phone_number",suser_phone).addWhereEqualTo("T_education_bg",suser_studyhistory);
            query.findObjects(new FindListener<HT_Teacher>() {
                @Override
                public void done(List<HT_Teacher> list, BmobException e) {
                    if(e==null){
                        String idid=null;
                        for(HT_Teacher ht_teacher:list){
                            idid=ht_teacher.getT_id();
                        }
                        user_id.setText(idid);
                    }
                }
            });
        }
        else{
            BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();
            query.addWhereEqualTo("S_name",suser_name).addWhereEqualTo("S_phone_number",suser_phone).addWhereEqualTo("S_grade",suser_studyhistory);
            query.findObjects(new FindListener<HT_Student>() {
                @Override
                public void done(List<HT_Student> list, BmobException e) {
                    if(e==null){
                        String idid=null;
                        for(HT_Student ht_student:list){
                            idid=ht_student.getS_id();
                        }
                        user_id.setText(idid);
                    }
                }
            });
            user_pictuer.setImageResource(R.mipmap.studenttouxiang);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suser_identy.equals(new centermessage().identy)){
                    Toast.makeText(User_message.this, "请添加与您相同身份的用户！", Toast.LENGTH_SHORT).show();
                }else {
                    HT_student_teacher ht_student_teacher = new HT_student_teacher();
                    ht_student_teacher.setU_id(new centermessage().id);
                    ht_student_teacher.setU_name(suser_name);
                    ht_student_teacher.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Toast.makeText(User_message.this, "添加成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(User_message.this, "已经添加，请勿重复添加", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        gophone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.DIAL");
                intent.setData(Uri.parse("tel:"+suser_phone));
                startActivity(intent);
            }
        });

        goemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+suser_phone));
                startActivity(intent);
            }
        });
        gochat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User_message.this,Send_Conversation.class);
                intent.putExtra("receive",suser_name);
                startActivity(intent);
            }
        });

    }
}
