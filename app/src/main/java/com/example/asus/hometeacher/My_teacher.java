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

public class My_teacher extends Activity {
    private ListView myteacher;
    private List<Myteacher> myteacherList=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_teacher);
        myteacher=(ListView)findViewById(R.id.my_teacher);

        initliset();
        myteacher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Myteacher myteacher=myteacherList.get(position);
                Intent intent=new Intent(My_teacher.this,Change_score.class);
                intent.putExtra("who","1");
                intent.putExtra("who_name",myteacher.getTeacher_name());
                startActivity(intent);
            }
        });

    }

    private void initliset() {

        BmobQuery<HT_student_teacher> query=new BmobQuery<HT_student_teacher>();
        query.addWhereEqualTo("U_id",new centermessage().id);
        query.findObjects(new FindListener<HT_student_teacher>() {
            String  U_id,U_name;
            private  int isfind=0;
            @Override
            public void done(List<HT_student_teacher> list, BmobException e) {

                if(e==null){
                    for(HT_student_teacher ht_student_teacher: list){
                        isfind=0;
                        U_id=ht_student_teacher.getU_id();
                        U_name=ht_student_teacher.getU_name();
                        Myteacher myteacher=new Myteacher(R.mipmap.touxiang,U_name);
                        myteacherList.add(myteacher);
                    }
                    MyteacherAdapter adapter=new MyteacherAdapter(My_teacher.this,R.layout.my_teacheritem,myteacherList);
                    myteacher.setAdapter(adapter);
                }
            }
          public void  findteacher(){
                BmobQuery<HT_Teacher> query1=new BmobQuery<HT_Teacher>();
              query1.addWhereEqualTo("T_id",U_id);
              query1.findObjects(new FindListener<HT_Teacher>() {
                  @Override
                  public void done(List<HT_Teacher> list, BmobException e) {
                      if(e==null){
                          if(list.size()==1){
                              isfind=1;
                          }
                      }
                  }
              });
            }

            public void findstudent(){
                BmobQuery<HT_Student> query2=new BmobQuery<HT_Student>();
                query2.addWhereEqualTo("S_id",U_id);
                query2.findObjects(new FindListener<HT_Student>() {
                    @Override
                    public void done(List<HT_Student> list, BmobException e) {
                        if(e==null){
                            if(list.size()==1){
                                isfind=2;
                            }
                        }
                    }
                });

            }
        });
    }
}
