package com.example.asus.hometeacher;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Asus on 2017/10/26.
 */

public class Myteacherfgment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_teacher_firstpager,container, false);
        Button myteacherbutton=(Button)view.findViewById(R.id.my_teacher_button);
        myteacherbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(getActivity(),My_teacher.class);
                startActivity(intent);
            }
        });

        return view;
    }
}