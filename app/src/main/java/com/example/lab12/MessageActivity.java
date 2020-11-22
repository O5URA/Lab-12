package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView subjectName;
    private TextView message;
    private Bundle mBundle;
    private String mess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mBundle = getIntent().getExtras();

        subjectName = findViewById(R.id.sub_name);
        message = findViewById(R.id.msg);

        mess = mBundle.getString("item");
        String[] arrayList = mess.split("\n");
        String subName = arrayList[1];
        String meg = arrayList[2];
//        System.out.println("This is message activity " + arrayList[0]);
        subjectName.setText(subName);
        message.setText(meg);
    }
}