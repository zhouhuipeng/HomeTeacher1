package com.example.asus.hometeacher;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Asus on 2018/5/29.
 */

public class selfcenter extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.selfcenter,container,false);
        TextView myid,myname,myphone,myidenty,mystudyhistory;
        myid=(TextView)view.findViewById(R.id.myid);
        myname=(TextView)view.findViewById(R.id.myname);
        myphone=(TextView)view.findViewById(R.id.myphone);
        myidenty=(TextView)view.findViewById(R.id.myidenty);
        mystudyhistory=(TextView)view.findViewById(R.id.mystudyhistory);
        Button rebacklogin=(Button)view.findViewById(R.id.rebacklogin);
        ImageView my_picture=(ImageView)view.findViewById(R.id.my_picture);
        TextView change=(TextView)view.findViewById(R.id.change);

        myid.setText(new centermessage().id);
        myidenty.setText(new centermessage().identy);
        myname.setText(new centermessage().name);
        myphone.setText(new centermessage().phone);
        mystudyhistory.setText(new centermessage().studyhistory);

        if(new centermessage().identy.equals("教师")){
            my_picture.setImageResource(R.mipmap.touxiang);
        }else {
            my_picture.setImageResource(R.mipmap.studenttouxiang);
        }


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new centermessage().identy.equals("教师")){
                   Intent intent=new Intent(getActivity(),Change_teacher.class);
                    startActivity(intent);
                }else {
                    Intent intent=new Intent(getActivity(),Change_student.class);
                    startActivity(intent);
                }
            }
        });
        rebacklogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),login.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
