package com.example.asus.hometeacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.BatchUpdateException;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Asus on 2018/6/4.
 */

public class Teacher_Perfectinformation extends Activity {
    private Spinner perfect_studyhistory,perfect_helpstudygrade,perfect_helpstudyitem,perfect_helpstudyarea;
    private EditText t_name;
    private Button submit;
    String t_id,t_phone_number,t_password,t_grade,t_item,s_area;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_perfectinformation);
        perfect_studyhistory=(Spinner) findViewById(R.id.T_teacher_studyhistory);
        perfect_helpstudygrade=(Spinner) findViewById(R.id.T_teacher_studygrade);
        perfect_helpstudyitem=(Spinner) findViewById(R.id.T_teacher_studyitem);
        perfect_helpstudyarea=(Spinner) findViewById(R.id.T_teacher_studyarea);
        t_name=(EditText)findViewById(R.id.T_name);
        submit=(Button)findViewById(R.id.submit);

        String sql="insert into HT_student values ("+t_name+","+t_id+","+t_password+","+t_phone_number+","+t_grade+","+t_item+","+s_area+") where  "+t_id+"=new centermessage().id";
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String sperfect_studyhistory,sperfect_helpstudygrade,sperfect_helpstudyitem,sperfect_helpstudyarea,sinputid,sinputpassword,sinputphone,tt_name;
                sperfect_studyhistory=perfect_studyhistory.getSelectedItem().toString();
                sperfect_helpstudygrade=perfect_helpstudygrade.getSelectedItem().toString();
                sperfect_helpstudyitem=perfect_helpstudyitem.getSelectedItem().toString();
                sperfect_helpstudyarea=perfect_helpstudyarea.getSelectedItem().toString();
                tt_name=t_name.getText().toString();
                Bundle bundle=getIntent().getExtras();
                sinputid=bundle.getString("sinputid");
                sinputpassword=bundle.getString("sinputpassword");
                sinputphone=bundle.getString("sinputphone");
                if(tt_name.equals("")){
                    Toast.makeText(Teacher_Perfectinformation.this,"请输入您的姓名！",Toast.LENGTH_SHORT).show();
                }
                else if(perfect_studyhistory.getSelectedItemId()==0){
                    Toast.makeText(Teacher_Perfectinformation.this,"请选择您的学历！",Toast.LENGTH_SHORT).show();
                }
                else if(perfect_helpstudygrade.getSelectedItemId()==0){
                    Toast.makeText(Teacher_Perfectinformation.this,"请选择您的补课年级！",Toast.LENGTH_SHORT).show();
                }
                else if(perfect_helpstudyitem.getSelectedItemId()==0){
                    Toast.makeText(Teacher_Perfectinformation.this,"请选择您的补课科目！",Toast.LENGTH_SHORT).show();
                }
                else if(perfect_helpstudyarea.getSelectedItemId()==0){
                    Toast.makeText(Teacher_Perfectinformation.this,"请选择您的补课区域！",Toast.LENGTH_SHORT).show();
                }
                else {
                    HT_User ht_user=new HT_User();
                    ht_user.setU_id(sinputid);
                    ht_user.setU_pass(sinputpassword);
                    ht_user.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){

                            }
                        }
                    });


                    HT_Teacher ht_teacher=new HT_Teacher();
                    ht_teacher.setT_name(tt_name);
                    ht_teacher.setT_id(sinputid);
                    ht_teacher.setT_pass(sinputpassword);
                    ht_teacher.setT_phone_number(sinputphone);
                    ht_teacher.setT_education_bg(sperfect_studyhistory);
                    ht_teacher.setT_professor_grade(sperfect_helpstudygrade);
                    ht_teacher.setT_item(sperfect_helpstudyitem);
                    ht_teacher.setT_area(sperfect_helpstudyarea);
                    ht_teacher.setT_power("100");
                    ht_teacher.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null){
                                Toast.makeText(Teacher_Perfectinformation.this,"注册成功",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent();
                                intent.setClass(Teacher_Perfectinformation.this,Mainpager.class);
                                intent.putExtra("id",sinputid);
                                intent.putExtra("phone",sinputphone);
                                intent.putExtra("identy","教师");
                                intent.putExtra("name",tt_name);
                                intent.putExtra("studyhistory",sperfect_studyhistory);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(Teacher_Perfectinformation.this,"注册失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

}