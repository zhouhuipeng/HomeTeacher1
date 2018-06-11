package com.example.asus.hometeacher;

import android.content.Context;
import android.media.Image;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 2018/6/8.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    private  int resourceID;

    public MessageAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Message> objects) {
        super(context, resource, objects);
        resourceID=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Message message=getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceID,parent,false);
        ImageView conversationpicture=(ImageView) view.findViewById(R.id.conversation_picture);
        TextView conversationmessage=(TextView)view.findViewById(R.id.conversation_content);
        TextView conversationname=(TextView)view.findViewById(R.id.conversation_name);
        conversationmessage.setText(message.getMessage());
        conversationpicture.setImageResource(message.getImageID());
        conversationname.setText(message.getName());
        return view;
    }
}
