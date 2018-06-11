package com.example.asus.hometeacher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by Asus on 2018/6/11.
 */

public class Change_score extends Activity {
    private ImageView imageView;
    private EditText inputscore;
    private Button submit;
    String score,who,who_name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changescore);
        imageView=(ImageView)findViewById(R.id.change_scorepicture);
        inputscore=(EditText) findViewById(R.id.inputscore);
        submit=(Button)findViewById(R.id.score_submit);
        final String[] objectid = new String[1];
        final Bundle[] bundle = {getIntent().getExtras()};
        who= bundle[0].getString("who");
        who_name= bundle[0].getString("who_name");
        if(who.equals("1")){

            imageView.setImageResource(R.mipmap.touxiang);
            BmobQuery<HT_Teacher> query=new BmobQuery<HT_Teacher>();
            query.addWhereEqualTo("T_name",who_name);
            query.findObjects(new FindListener<HT_Teacher>() {
                @Override
                public void done(List<HT_Teacher> list, BmobException e) {
                    if(e==null){

                        for(HT_Teacher ht_teacher: list){
                            objectid[0] =ht_teacher.getObjectId();
                        }
                    }
                }
            });

        }
        else{
            imageView.setImageResource(R.mipmap.studenttouxiang);
            BmobQuery<HT_Student> query=new BmobQuery<HT_Student>();
            query.addWhereEqualTo("S_name",who_name);
            query.findObjects(new FindListener<HT_Student>() {
                @Override
                public void done(List<HT_Student> list, BmobException e) {
                    if(e==null){

                        for(HT_Student ht_student: list){
                            objectid[0] =ht_student.getObjectId();
                        }
                    }
                }
            });

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score=inputscore.getText().toString();
                if(who.equals("1")){
                    HT_Teacher ht_teacher=new HT_Teacher();
                    ht_teacher.setT_power(score);
                    ht_teacher.update(objectid[0],new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(Change_score.this,"评价成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else{
                    HT_Student ht_student=new HT_Student();
                    ht_student.setS_credit(score);
                    ht_student.update(objectid[0],new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(Change_score.this,"评价成功",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
