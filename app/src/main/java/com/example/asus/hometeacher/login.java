package com.example.asus.hometeacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobQueryResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SQLQueryListener;

/**
 * Created by Asus on 2018/5/28.
 */

public class login extends Activity {
    private Button Registor;
    private TextView rebacktoregistor;
    private EditText loginid,loginpassword;
    private RadioButton choosestudent,chooseteacher;
    private String sloginid,sloginpassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Registor=(Button) findViewById(R.id.login);
        rebacktoregistor=(TextView) findViewById(R.id.rebackregistor__);
        loginid=(EditText) findViewById(R.id.login__id);
        loginpassword=(EditText) findViewById(R.id.login__password);
        choosestudent=(RadioButton) findViewById(R.id.choosestudent_);
        chooseteacher=(RadioButton) findViewById(R.id.chooseteacher_);

        Registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sloginid=loginid.getText().toString();
                sloginpassword=loginpassword.getText().toString();
                String input_id=null,input_password=null;
                String sql="select * from _User where user_id="+input_id+" and user_password="+input_password;
                if(choosestudent.isChecked()){//学生登陆
                    BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();
                    query.addWhereEqualTo("S_id",sloginid).addWhereEqualTo("S_pass",sloginpassword);
                    query.findObjects(new FindListener<HT_Student>() {
                        @Override
                        public void done(List<HT_Student> list, BmobException e) {
                            if(e==null){
                                if(list.size()==1){
                                    String phone = null,name=null,id=null,studyhistory=null;
                                    for(HT_Student ht_student:list){
                                        phone=ht_student.getS_phone_number();
                                        name=ht_student.getS_name();
                                        id=ht_student.getS_id();
                                        studyhistory=ht_student.getS_grade();
                                    }
                                    Toast.makeText(login.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent();
                                    intent.setClass(login.this,Mainpager.class);
                                    intent.putExtra("id",id);
                                    intent.putExtra("phone",phone);
                                    intent.putExtra("identy","学生");
                                    intent.putExtra("name",name);
                                    intent.putExtra("studyhistory",studyhistory);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(login.this,"登陆失败！请检查账号密码是否正确！",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(login.this,"查询失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else if(chooseteacher.isChecked()){
                    BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
                    query.addWhereEqualTo("T_id",sloginid).addWhereEqualTo("T_pass",sloginpassword);
                    query.findObjects(new FindListener<HT_Teacher>() {
                        @Override
                        public void done(List<HT_Teacher> list, BmobException e) {
                            if(e==null){
                                String phone = null,name=null,id=null,studyhistory=null,objectid=null;
                                if(list.size()==1){

                                    for(HT_Teacher ht_teacher:list){
                                        phone=ht_teacher.getT_phone_number();
                                        name=ht_teacher.getT_name();
                                        id=ht_teacher.getT_id();
                                        studyhistory=ht_teacher.getT_education_bg();
                                        objectid=ht_teacher.getObjectId();
                                    }
                                    Toast.makeText(login.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent();
                                    intent.setClass(login.this,Mainpager.class);
                                    intent.putExtra("id",id);
                                    intent.putExtra("phone",phone);
                                    intent.putExtra("identy","教师");
                                    intent.putExtra("name",name);
                                    intent.putExtra("studyhistory",studyhistory);
                                    intent.putExtra("objectid",objectid);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(login.this,"登陆失败！请检查账号密码是否正确！",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(login.this,"查询失败！",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
               /* BmobQuery<HT_student> query=new BmobQuery<HT_student>();
                query.addWhereEqualTo("student_id",sloginid).addWhereEqualTo("student_password",sloginpassword);
                query.findObjects(new FindListener<HT_student>() {
                    @Override
                    public void done(List<HT_student> list, BmobException e) {
                        int a=0;
                        String aa,b,c;
                        if(e==null){
                           // Toast.makeText(login.this,"查询成功：共"+list.size()+"条数据。",Toast.LENGTH_SHORT).show();
                            for (HT_student ht_student : list) {

                                    aa=ht_student.getStudent_id();
                                    b=ht_student.getStudent_password();
                                    c=ht_student.getStudent_phone();
                                System.out.println(aa+" "+b+" "+c);

                                }
                                if(list.size()>0){
                                    Toast.makeText(login.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent();
                                    intent.setClass(login.this,Mainpager.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else Toast.makeText(login.this,"登陆失败！请检查账号密码是否正确",Toast.LENGTH_SHORT).show();
                                System.out.println(list);
                            }else{
                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                        }
                    }
                });
                String sql="select * from HT_student where student_id = ? and student_password = ? ";
                System.out.println(sql);
                BmobQuery<HT_student> bmobQuery=new BmobQuery<HT_student>();
                bmobQuery.setSQL(sql);
                bmobQuery.setPreparedParams(new Object[]{sloginid,sloginpassword});
                bmobQuery.doSQLQuery(new SQLQueryListener<HT_student>() {
                    @Override
                    public void done(BmobQueryResult<HT_student> bmobQueryResult, BmobException e) {
                        System.out.println(e);
                        if(e==null) {
                            List<HT_student> list = (List<HT_student>) bmobQueryResult.getResults();
                            if (list != null && list.size() > 0) {

                            }
                            Toast.makeText(login.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent();
                            intent.setClass(login.this,Mainpager.class);
                            startActivity(intent);
                            finish();
                        }
                        else Toast.makeText(login.this,"登陆失败！",Toast.LENGTH_SHORT).show();
                    }
                });*/

            }
        });

        rebacktoregistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(login.this,Registor.class);
                startActivity(intent);
            }
        });

        choosestudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosestudent.setChecked(true);
                chooseteacher.setChecked(false);
            }
        });

        chooseteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosestudent.setChecked(false);
                chooseteacher.setChecked(true);
            }
        });



    }
}
