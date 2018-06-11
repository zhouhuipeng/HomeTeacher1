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

public class StudentAdapter extends ArrayAdapter<Student> {
    private  int resourceID;
    public StudentAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student student=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView studentimage=(ImageView) view.findViewById(R.id.student_image);
        TextView studentname=(TextView) view.findViewById(R.id.student_name);
        TextView studenttelephone=(TextView) view.findViewById(R.id.student_telephone);
        TextView studenthelpstudyarea=(TextView) view.findViewById(R.id.student_helpstudyarea);
        TextView studenthelpstudyitem=(TextView)view.findViewById(R.id.Student_Helpstudyitem);
        TextView studenthelpstudygrade=(TextView) view.findViewById(R.id.student_helpstudygrade);
        studentimage.setImageResource(student.getImageID());
        studentname.setText(student.getStudentname());
        studenttelephone.setText(student.getStudenttelephone());
        studenthelpstudyarea.setText(student.getStudenthelpstudyarea());
        studenthelpstudygrade.setText(student.getStudenthelpstudygrade());
        studenthelpstudyitem.setText(student.getStudenthelpstudyitem());
        return view;

    }
}
