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

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Asus on 2018/6/5.
 */

public class Student_Perfectinformation extends Activity {
    private Spinner s_studygrade,s_studyitem,s_studyarea;
    private EditText s_name;
    private Button submit;
    String s_id,s_phone_number,s_password,s_grade,s_item,s_area;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_perfectinformation);
        s_studygrade=(Spinner)findViewById(R.id.S_student_studygrade);
        s_studyitem=(Spinner)findViewById(R.id.S_student_studyitem);
        s_studyarea=(Spinner)findViewById(R.id.S_student_studyarea);
        s_name=(EditText)findViewById(R.id.S_name);
        submit=(Button)findViewById(R.id.S_submit);


        String sql="insert into HT_student values ("+s_name+","+s_id+","+s_password+","+s_phone_number+","+s_grade+","+s_item+","+s_area+") where  "+s_id+"=new centermessage().id";
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ss_studygrade,ss_studyitem,ss_studyarea,sinputid,sinputpassword,sinputphone,ss_name;
                ss_studygrade=s_studygrade.getSelectedItem().toString();
                ss_studyitem=s_studyitem.getSelectedItem().toString();
                ss_studyarea=s_studyarea.getSelectedItem().toString();
                ss_name=s_name.getText().toString();
                Bundle bundle=getIntent().getExtras();
                sinputid=bundle.getString("sinputid");
                sinputpassword=bundle.getString("sinputpassword");
                sinputphone=bundle.getString("sinputphone");
                if(ss_name.equals("")){
                    Toast.makeText(Student_Perfectinformation.this,"请输入您的姓名",Toast.LENGTH_SHORT).show();
                }
                else if(s_studygrade.getSelectedItemId()==0){
                    Toast.makeText(Student_Perfectinformation.this,"请选择您的待补年级",Toast.LENGTH_SHORT).show();
                }
                else if(s_studyitem.getSelectedItemId()==0){
                    Toast.makeText(Student_Perfectinformation.this,"请选择您的待补科目",Toast.LENGTH_SHORT).show();
                }
                else if(s_studyarea.getSelectedItemId()==0){
                    Toast.makeText(Student_Perfectinformation.this,"请选择您的补课区域",Toast.LENGTH_SHORT).show();
                }
                else {
                    HT_User ht_user = new HT_User();
                    ht_user.setU_id(sinputid);
                    ht_user.setU_pass(sinputpassword);
                    ht_user.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                            } else {
                                ///Toast.makeText(Student_Perfectinformation.this, "注册失败，已有相同用户！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                        HT_Student ht_student = new HT_Student();
                        ht_student.setS_id(sinputid);
                        ht_student.setS_name(ss_name);
                        ht_student.setS_pass(sinputpassword);
                        ht_student.setS_phone_number(sinputphone);
                        ht_student.setS_grade(ss_studygrade);
                        ht_student.setS_item(ss_studyitem);
                        ht_student.setS_area(ss_studyarea);
                        ht_student.setS_credit("100");
                        ht_student.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
                                    Toast.makeText(Student_Perfectinformation.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent();
                                    intent.setClass(Student_Perfectinformation.this, Mainpager.class);
                                    intent.putExtra("id", sinputid);
                                    intent.putExtra("phone", sinputphone);
                                    intent.putExtra("identy", "学生");
                                    intent.putExtra("name", ss_name);
                                    intent.putExtra("studyhistory", ss_studygrade);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Student_Perfectinformation.this, "注册失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }
            }
        });
    }
}
