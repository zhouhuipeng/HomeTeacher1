package com.example.asus.hometeacher;

import android.content.Context;
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
 * Created by 说散就散 on 2018/6/10.
 */

public class MyteacherAdapter extends ArrayAdapter<Myteacher> {
    private int resourceID;
    public MyteacherAdapter(@NonNull Context context, int resource, @NonNull List<Myteacher> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Myteacher myteacher=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView my_teacher_picture=(ImageView) view.findViewById(R.id.my_teacher_picture);
        TextView my_teacher_name=(TextView) view.findViewById(R.id.my_teacher_name);
        my_teacher_picture.setImageResource(myteacher.getImageID());
        my_teacher_name.setText(myteacher.getTeacher_name());
        return view;
    }
}
