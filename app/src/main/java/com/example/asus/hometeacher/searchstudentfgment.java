package com.example.asus.hometeacher;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Asus on 2018/5/29.
 */

public class searchstudentfgment extends Fragment{
    private List<Student> studentList=new ArrayList<>();
    ListView listView;
    String id,grade1,area1,item1;
    String sql="select * from HT_student where s_grade="+grade1;
    String sql1="select * from HT_student where s_area="+area1;
    String sql2="select * from HT_student where s_item="+item1;
    String sql3="select * from HT_student where s_grade="+grade1+" and s_area="+area1;
    String sql4="select * from HT_student where s_area="+area1+" and s_item="+item1;
    String sql5="select * from HT_student where s_grade="+grade1+" and s_item="+item1;
    String sql6="select * from HT_student where s_area="+area1+" and s_item="+item1+" and s_grade="+grade1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.searchstudent,container,false);
        final Spinner student_helpstudygrade,student_helpstudyitem,student_helpstudyarea;

        listView=(ListView) view.findViewById(R.id.student_list);
        initStudent();
        Button searchstudent;

        student_helpstudygrade=(Spinner)view.findViewById(R.id.student_helpstudygrade);
        student_helpstudyitem=(Spinner)view.findViewById(R.id.student_helpstudyitem);
        student_helpstudyarea=(Spinner)view.findViewById(R.id.student_helpstudyarea);
        searchstudent=(Button)view.findViewById(R.id.searchstudent);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String user_id,user_name,user_phone,user_identy="学生",user_studyhistory;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Student student=studentList.get(position);
                Intent intent=new Intent(getActivity(),User_message.class);
                findid();
                intent.putExtra("user_id",id);
                intent.putExtra("user_name",student.getStudentname());
                user_name=student.getStudentname();
                intent.putExtra("user_phone",student.getStudenttelephone());
                user_phone=student.getStudenttelephone();
                intent.putExtra("user_identy",user_identy);
                intent.putExtra("user_studyhistory",student.getStudenthelpstudygrade());
                user_studyhistory=student.getStudenthelpstudygrade();
                startActivity(intent);
            }

            private void findid() {
                BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();
                query.addWhereEqualTo("S_name",user_name).addWhereEqualTo("S_phone_number",user_phone).addWhereEqualTo("S_grade",user_studyhistory);
                query.findObjects(new FindListener<HT_Student>() {
                    @Override
                    public void done(List<HT_Student> list, BmobException e) {
                        if(e==null){
                            for(HT_Student h:list){
                                id=h.getS_id();
                            }
                        }
                    }
                });
            }
        });

        searchstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"正在查询",Toast.LENGTH_SHORT).show();
                studentList.clear();
                BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();
                if(student_helpstudygrade.getSelectedItemId()!=0)
                    query.addWhereEqualTo("S_grade",student_helpstudygrade.getSelectedItem().toString());
                if(student_helpstudyitem.getSelectedItemId()!=0)
                    query.addWhereEqualTo("S_item",student_helpstudyitem.getSelectedItem().toString());
                if(student_helpstudyarea.getSelectedItemId()!=0)
                    query.addWhereEqualTo("S_area",student_helpstudyarea.getSelectedItem().toString());

                query.findObjects(new FindListener<HT_Student>() {
                    @Override
                    public void done(List<HT_Student> list, BmobException e) {
                        studentList.clear();
                        if (e == null) {
                            for (HT_Student ht_student : list) {
                                String id, name, phone, studyitem, area, grade;
                                id=ht_student.getS_id();
                                name=ht_student.getS_name();
                                phone=ht_student.getS_phone_number();
                                grade=ht_student.getS_grade();
                                studyitem=ht_student.getS_item();
                                area=ht_student.getS_area();
                                Student apple=new Student(R.mipmap.studenttouxiang,name,phone,grade,studyitem,area);
                                studentList.add(apple);
                            }
                            StudentAdapter adapter=new StudentAdapter(getActivity(),R.layout.searchstudent_item,studentList);
                            listView.setAdapter(adapter);
                        }
                    }
                });
            }
        });
        return view;
    }

    private void initStudent() {
        BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();

        query.findObjects(new FindListener<HT_Student>() {
            @Override
            public void done(List<HT_Student> list, BmobException e) {
                studentList.clear();
                if (e == null) {
                    for (HT_Student ht_student : list) {
                        String id, name, phone, studyitem, area, grade;
                        id=ht_student.getS_id();
                        name=ht_student.getS_name();
                        phone=ht_student.getS_phone_number();
                        grade=ht_student.getS_grade();
                        studyitem=ht_student.getS_item();
                        area=ht_student.getS_area();
                        Student apple=new Student(R.mipmap.studenttouxiang,name,phone,grade,studyitem,area);
                        studentList.add(apple);
                    }
                    StudentAdapter adapter=new StudentAdapter(getActivity(),R.layout.searchstudent_item,studentList);
                    listView.setAdapter(adapter);
                }
            }
        });
    }
}
