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
 * Created by Asus on 2018/6/10.
 */

public class Mystudentfgment extends Fragment {
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.my_student_firstpager,container, false);
        Button mystudentbutton =(Button)view.findViewById(R.id.my_student_button);

        mystudentbutton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            Intent intent=new Intent();
             intent.setClass(getActivity(),My_student.class);
             startActivity(intent);
         }
     });

        return view;
    }
}
