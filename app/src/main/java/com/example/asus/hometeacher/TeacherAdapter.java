package com.example.asus.hometeacher;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Asus on 2018/5/29.
 */

public class TeacherAdapter extends ArrayAdapter<Teacher> {
    private  int resourceID;
    public TeacherAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Teacher> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Teacher teacher=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView teacherimage=(ImageView) view.findViewById(R.id.teacher_image);
        TextView teachername=(TextView) view.findViewById(R.id.teacher_name);
        TextView teachertelephone=(TextView) view.findViewById(R.id.teacher_telephone);
        TextView teacherstudyhistory=(TextView) view.findViewById(R.id.teacher_studyhistory);
        TextView teacherhelpstudyarea=(TextView) view.findViewById(R.id.teacher_helpstudyarea);
        TextView teacherintroduce=(TextView) view.findViewById(R.id.teacher_introduce);
        teacherimage.setImageResource(teacher.getImageID());
        teachername.setText(teacher.getTeachername());
        teacherstudyhistory.setText(teacher.getTeacherstudyhistory());
        teacherhelpstudyarea.setText(teacher.getTeacherhelpstudyarea());
        teacherintroduce.setText(teacher.getTeacherintroduce());
        teachertelephone.setText(teacher.getTeachertelephone());
        return view;
    }
}
