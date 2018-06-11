package com.example.asus.hometeacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by 说散就散 on 2018/6/10.
 */

public class My_student extends Activity {
    private ListView mystudent;
    private List<Mystudent> mystudentList=new ArrayList<>();
    private  static int   isfind=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_student);
        mystudent=(ListView)findViewById(R.id.my_student);

        initliset();

        mystudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Mystudent mystudent=mystudentList.get(position);
                Intent intent=new Intent(My_student.this,Change_score.class);
                intent.putExtra("who","2");
                intent.putExtra("who_name",mystudent.getStudent_name());
                startActivity(intent);
            }
        });
    }

    private void initliset() {
        BmobQuery<HT_student_teacher> query=new BmobQuery<HT_student_teacher>();
        query.addWhereEqualTo("U_id",new centermessage().id);
        query.findObjects(new FindListener<HT_student_teacher>() {
            String  U_id,U_name;

            @Override
            public void done(List<HT_student_teacher> list, BmobException e) {
                if(e==null){
                    for(HT_student_teacher ht_student_teacher: list){
                        U_id=ht_student_teacher.getU_id();
                        U_name=ht_student_teacher.getU_name();
                        Mystudent mystudent=new Mystudent(R.mipmap.studenttouxiang,U_name);
                        mystudentList.add(mystudent);
                    }
                    MystudentAdapter adapter=new MystudentAdapter(My_student.this,R.layout.my_studentitem,mystudentList);
                    mystudent.setAdapter(adapter);
                }
            }
        });
    }
    public void findstudent(String U_name){
        BmobQuery<HT_Student> query2=new BmobQuery<HT_Student>();
        query2.addWhereEqualTo("S_name",U_name);
        query2.findObjects(new FindListener<HT_Student>() {
            @Override
            public void done(List<HT_Student> list, BmobException e) {
                if(e==null){
                    for(HT_Student ht_student:list){
                        System.out.println(ht_student.getS_name());
                    }
                    if(list.size()!=0){
                        isfind=2;
                    }
                }
            }
        });

    }
    public void  findteacher( String U_name){
        BmobQuery<HT_Teacher> query1=new BmobQuery<HT_Teacher>();
        query1.addWhereEqualTo("T_name",U_name);
        query1.findObjects(new FindListener<HT_Teacher>(){
            @Override
            public void done(List<HT_Teacher> list, BmobException e) {
                if(e==null){
                    for(HT_Teacher ht_teacher:list){
                        System.out.println(ht_teacher.getT_name());
                    }
                    if(list.size()!=0){
                        isfind=1;
                    }
                }
            }
        });
    }
}