package com.example.asus.hometeacher;

import android.app.Fragment;
import android.content.Context;
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

public class searchteacherfgment extends Fragment {
    private List<Teacher> teacherList=new ArrayList<>();
    ListView listView;
    String id,grade1,area1,item1,education1;
    String sql="select * from HT_teacher where t_grade="+grade1;
    String sql1="select * from HT_teacher where t_area="+area1;
    String sql2="select * from HT_teacher  where t_item="+item1;
    String sql3="select * from HT_teacher  where t_education="+education1;
    String sql4="select * from HT_teacher  where t_grade="+grade1+" and t_area="+area1;
    String sql5="select * from HT_teacher  where t_grade="+grade1+" and t_item="+item1;
    String sql6="select * from HT_teacher  where t_grade="+grade1+" and t_education="+education1;
    String sql7="select * from HT_teacher  where t_area="+area1+"  and t_item="+item1;
    String sql8="select * from HT_teacher  where t_area="+area1+" and t_education="+education1;
    String sql9="select * from HT_teacher  where t_item="+item1+" and t_education="+education1;
    String sq10="select * from HT_teacher where t_area="+area1+" and t_item="+item1+" and t_grade="+grade1;
    String sql11="select * from HT_student where t_area="+area1+" and t_item="+item1+" and t_education="+education1;
    String sql12="select * from HT_student where t_education="+education1+"and t_item="+item1+"  and t_grade="+grade1;
    String sql13="select * from v_student where t_area="+area1+" and t_item="+item1+"and t_grade="+grade1+" and t_education="+education1;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.searchteacher,container,false);
        final Spinner studyhistory,helpstudygrade,helpstudyitem,helpstudyarea;
        studyhistory=(Spinner)view.findViewById(R.id.studyhistory);
        helpstudygrade=(Spinner)view.findViewById(R.id.helpstudygrade);
        helpstudyitem=(Spinner)view.findViewById(R.id.helpstudyitem);
        helpstudyarea=(Spinner)view.findViewById(R.id.helpstudyarea);

        listView=(ListView) view.findViewById(R.id.teacher_list);
        Button searchteacher=(Button)view.findViewById(R.id.searchteacher);
        initTeacher();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            String user_id,user_name,user_phone,user_identy="教师",user_studyhistory;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Teacher teacher=teacherList.get(position);
                Intent intent=new Intent(getActivity(),User_message.class);
                intent.putExtra("user_id",id);
                finduserid();
                intent.putExtra("user_id",id);
                intent.putExtra("user_name",teacher.getTeachername());
                user_name=teacher.getTeachername();
                intent.putExtra("user_phone",teacher.getTeachertelephone());
                user_phone=teacher.getTeachertelephone();
                intent.putExtra("user_identy",user_identy);
                intent.putExtra("user_studyhistory",teacher.getTeacherstudyhistory());
                user_studyhistory=teacher.getTeacherstudyhistory();
                startActivity(intent);
            }

            private void finduserid() {
                BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
                query.addWhereEqualTo("T_name",user_name).addWhereEqualTo("T_professor_bg",user_studyhistory).addWhereEqualTo("T_phone_number",user_phone);
                query.findObjects(new FindListener<HT_Teacher>() {
                    @Override
                    public void done(List<HT_Teacher> list, BmobException e) {
                     if(e==null){
                         for(HT_Teacher h : list){
                             id=h.getT_id();
                         }
                     }
                    }
                });

            }
        });

        searchteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"正在查询",Toast.LENGTH_SHORT).show();
                teacherList.clear();
                BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
                if(studyhistory.getSelectedItemId()!=0)
                    query.addWhereEqualTo("T_education_bg",studyhistory.getSelectedItem().toString());
                if(helpstudygrade.getSelectedItemId()!=0)
                    query.addWhereEqualTo("T_professor_grade",helpstudygrade.getSelectedItem().toString());
                if(helpstudyitem.getSelectedItemId()!=0)
                    query.addWhereEqualTo("T_item",helpstudyitem.getSelectedItem().toString());
                if(helpstudyarea.getSelectedItemId()!=0)
                    query.addWhereEqualTo("T_area",helpstudyarea.getSelectedItem().toString());

                query.findObjects(new FindListener<HT_Teacher>() {
                    @Override
                    public void done(List<HT_Teacher> list, BmobException e) {
                        if (e == null) {
                            for (HT_Teacher ht_teacher : list) {
                                String  name, phone, studyhistory, area, information;
                                id = ht_teacher.getT_id();
                                name = ht_teacher.getT_name();
                                phone = ht_teacher.getT_phone_number();
                                studyhistory = ht_teacher.getT_education_bg();
                                area = ht_teacher.getT_area();
                                information = "能力值: "+ht_teacher.getT_power();
                                Teacher teacher = new Teacher(R.mipmap.touxiang, name, phone, studyhistory, area, information);
                                teacherList.add(teacher);
                            }
                            TeacherAdapter adapter=new TeacherAdapter(getActivity(),R.layout.searchteacher_item,teacherList);
                            listView.setAdapter(adapter);
                        }
                    }
                });
            }
        });

        return view;
    }
    private void initTeacher() {
        BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
        query.findObjects(new FindListener<HT_Teacher>() {
            @Override
            public void done(List<HT_Teacher> list, BmobException e) {
                if(e==null) {
                    for(HT_Teacher ht_teacher: list){
                        String name, phone, studyhistory, area, information;
                        id=ht_teacher.getT_id();
                        name=ht_teacher.getT_name();
                        phone=ht_teacher.getT_phone_number();
                        studyhistory=ht_teacher.getT_education_bg();
                        area=ht_teacher.getT_area();
                        information = "能力值: "+ht_teacher.getT_power();
                        Teacher teacher=new Teacher(R.mipmap.touxiang,name,phone,studyhistory,area,information);
                        teacherList.add(teacher);
                    }
                    TeacherAdapter adapter=new TeacherAdapter(getActivity(),R.layout.searchteacher_item,teacherList);
                    listView.setAdapter(adapter);
                }

            }
        });
    }

}
