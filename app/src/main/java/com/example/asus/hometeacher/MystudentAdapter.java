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

public class MystudentAdapter extends ArrayAdapter<Mystudent  > {
    private int resourceID;
    public MystudentAdapter(@NonNull Context context, int resource, @NonNull List<Mystudent> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Mystudent mystudent=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView my_student_picture=(ImageView) view.findViewById(R.id.my_student_picture);
        TextView my_student_name=(TextView) view.findViewById(R.id.my_student_name);
        my_student_picture.setImageResource(mystudent.getImageID());
        my_student_name.setText(mystudent.getStudent_name());
        return view;
    }
}
