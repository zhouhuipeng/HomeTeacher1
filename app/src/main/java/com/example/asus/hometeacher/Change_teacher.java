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
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Asus on 2018/6/9.
 */

public class Change_teacher extends Activity {
    private Button change_T_submit;
    private EditText change_T_name;
    private Spinner change_T_student_studygrade,change_T_student_studyitem,change_T_student_studyarea;
    private String  change_name,change_studygrade,change_studyitem,change_studyarea;
    String sql="update HT_teacher set s_name="+change_name+" ,s_grade="+change_studygrade+" ,s_item="+change_studyitem+",s_area="+change_studyarea+" where s_id="+new centermessage().id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_teacher);
        change_T_submit=(Button)findViewById(R.id.change_T_submit);
        change_T_name=(EditText) findViewById(R.id.change_T_name);
        change_T_student_studygrade=(Spinner)findViewById(R.id.change_T_teacher_studygrade);
        change_T_student_studyitem=(Spinner)findViewById(R.id.change_T_teacher_studyitem);
        change_T_student_studyarea=(Spinner)findViewById(R.id.change_T_teacher_studyarea);


        change_T_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_name=change_T_name.getText().toString();
                change_studygrade=change_T_student_studygrade.getSelectedItem().toString();
                change_studyitem=change_T_student_studyitem.getSelectedItem().toString();
                change_studyarea=change_T_student_studyarea.getSelectedItem().toString();
                if(change_name.equals("")){
                    Toast.makeText(Change_teacher.this,"请输入您的姓名",Toast.LENGTH_SHORT).show();
                }
                else if(change_studygrade.equals("")){
                    Toast.makeText(Change_teacher.this,"请选择您的补课年级",Toast.LENGTH_SHORT).show();
                }
                else if(change_studyitem.equals("")){
                    Toast.makeText(Change_teacher.this,"请选择您的补课科目",Toast.LENGTH_SHORT).show();
                }
                else if(change_studyarea.equals("")){
                    Toast.makeText(Change_teacher.this,"请选择您的补课区域",Toast.LENGTH_SHORT).show();
                }
                else {
                    HT_Teacher ht_student=new HT_Teacher();
                    ht_student.setT_name(change_name);
                    ht_student.setT_professor_grade(change_studygrade);
                    ht_student.setT_item(change_studyitem);
                    ht_student.setT_area(change_studyarea);
                    String sql="update HT_Student set S_name ="+change_name+", S_grade";
                    ht_student.update(new centermessage().objectid, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(Change_teacher.this,"修改成功，请登陆",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Change_teacher.this,login.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(Change_teacher.this,"修改失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
}
