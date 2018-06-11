package com.example.asus.hometeacher;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * Created by Asus on 2018/5/28.
 */

public class Mainpager extends Activity {

    private RadioButton R1,R2,R3,R4;
    private Button button1;
    String id,name,phone,studyhistory,identy,objectid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpager);


        R1= (RadioButton) findViewById(R.id.ly_biji1);
        R2= (RadioButton) findViewById(R.id.ly_biji2);
        R3= (RadioButton) findViewById(R.id.ly_biji3);
        R4= (RadioButton) findViewById(R.id.ly_biji4);

        Bundle bundle=getIntent().getExtras();
        id=bundle.getString("id");
        name=bundle.getString("name");
        phone=bundle.getString("phone");
        studyhistory=bundle.getString("studyhistory");
        identy=bundle.getString("identy");
        objectid=bundle.getString("objectid");
        new centermessage().setMessage(id,name,phone,identy,studyhistory,objectid);

        if(!identy.equals("教师")) {
            FragmentManager manager = getFragmentManager();//创建fragment对象
            FragmentTransaction transaction = manager.beginTransaction();//获取fragment
            //transaction.add(R.id.ly_under,new things());
            transaction.replace(R.id.ly_center, new Myteacherfgment());//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物
            transaction.commit();
        }
        else{
            FragmentManager manager = getFragmentManager();//创建fragment对象
            FragmentTransaction transaction = manager.beginTransaction();//获取fragment
            //transaction.add(R.id.ly_under,new things());
            transaction.replace(R.id.ly_center, new Mystudentfgment());//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物
            transaction.commit();
        }

        R1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!identy.equals("教师")) {
                    FragmentManager manager = getFragmentManager();//创建fragment对象
                    FragmentTransaction transaction = manager.beginTransaction();//获取fragment
                    //transaction.add(R.id.ly_under,new things());
                    transaction.replace(R.id.ly_center, new Myteacherfgment());//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物
                    transaction.commit();
                }
                else{
                    FragmentManager manager = getFragmentManager();//创建fragment对象
                    FragmentTransaction transaction = manager.beginTransaction();//获取fragment
                    //transaction.add(R.id.ly_under,new things());
                    transaction.replace(R.id.ly_center, new Mystudentfgment());//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物
                    transaction.commit();
                }

            }
        });

        R2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchteacherfgment searchteacherfgment=new searchteacherfgment();
                FragmentManager manager=getFragmentManager();//创建fragment对象
                FragmentTransaction transaction=manager.beginTransaction();//获取fragment
                //transaction.add(R.id.ly_under,new things());
                transaction.replace(R.id.ly_center,searchteacherfgment);//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物

                transaction.commit();

            }
        });

        R3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchstudentfgment s=new searchstudentfgment();
                FragmentManager manager=getFragmentManager();//创建fragment对象
                FragmentTransaction transaction=manager.beginTransaction();//获取fragment
                //transaction.add(R.id.ly_under,new things());
                transaction.replace(R.id.ly_center,s);//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物

                transaction.commit();

            }
        });
        R4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getFragmentManager();//创建fragment对象
                FragmentTransaction transaction=manager.beginTransaction();//获取fragment
                //transaction.add(R.id.ly_under,new things());
                transaction.replace(R.id.ly_center,new selfcenter());//调用fragment中的事物进行动态操作add添加资源id的对象
//提交事物

                transaction.commit();

            }
        });

    }

}
