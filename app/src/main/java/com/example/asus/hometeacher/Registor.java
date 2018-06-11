package com.example.asus.hometeacher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Asus on 2018/5/28.
 */

public class Registor extends Activity {
    private EditText inputid,inputpassword,inputpassword2,inputphone;
    private RadioButton choosestudent,chooseteacher;
    private String sinputid,sinputpassword,sinputpassword2,sinputphone;
    private Button registor;
    private TextView backtologin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registor);
        inputid=(EditText) findViewById(R.id.inputid);
        inputpassword=(EditText) findViewById(R.id.inputpassword);
        inputpassword2=(EditText) findViewById(R.id.inputpassword2);
        inputphone=(EditText) findViewById(R.id.inputphone);
        choosestudent=(RadioButton) findViewById(R.id.choosestudent);
        chooseteacher=(RadioButton) findViewById(R.id.chooseteacher);
        backtologin=(TextView) findViewById(R.id.backtologin);
        registor=(Button) findViewById(R.id.resgitor_);

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


        registor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Registor.this,"请检查注册信息是否正确！",Toast.LENGTH_LONG).show();
                sinputid=inputid.getText().toString();
                sinputpassword=inputpassword.getText().toString();
                sinputpassword2=inputpassword2.getText().toString();
                sinputphone=inputphone.getText().toString();

                if(sinputid.equals("")){
                    Toast.makeText(Registor.this,"账号不能为空！",Toast.LENGTH_SHORT).show();
                }else if(sinputpassword.equals("")){
                    Toast.makeText(Registor.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }else if(sinputpassword.length()!=6){
                    Toast.makeText(Registor.this,"请输入6位密码！",Toast.LENGTH_SHORT).show();
                }
                else if(sinputpassword2.equals("")){
                    Toast.makeText(Registor.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                }
                else if(!sinputpassword2.equals(sinputpassword)){
                    Toast.makeText(Registor.this,"两次输入密码不一致！",Toast.LENGTH_SHORT).show();
                }
                else if(sinputphone.equals("")||sinputphone.length()!=11){
                    Toast.makeText(Registor.this,"请输入正确的手机号码！",Toast.LENGTH_SHORT).show();
                }
                else if(!chooseteacher.isChecked()&&!choosestudent.isChecked()){
                    Toast.makeText(Registor.this,"请选择您的身份！",Toast.LENGTH_LONG).show();
                }
                else {
                    if(choosestudent.isChecked()){//学生注册
                        Intent intent=new Intent();
                        intent.setClass(Registor.this,Student_Perfectinformation.class);
                        intent.putExtra("sinputid",sinputid);
                        intent.putExtra("sinputpassword",sinputpassword);
                        intent.putExtra("sinputphone",sinputphone);
                        startActivity(intent);
                    }
                    else if(chooseteacher.isChecked()){//教师注册
                        Intent intent=new Intent();
                        intent.setClass(Registor.this,Teacher_Perfectinformation.class);
                        intent.putExtra("sinputid",sinputid);
                        intent.putExtra("sinputpassword",sinputpassword);
                        intent.putExtra("sinputphone",sinputphone);
                        startActivity(intent);
                    }
                }
            }
        });

        backtologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(Registor.this,login.class);
                startActivity(intent);
                finish();
            }
        });

    }
}

