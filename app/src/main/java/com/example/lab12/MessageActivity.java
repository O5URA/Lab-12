package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    private TextView subjectName;
    private TextView message;
    private Bundle mBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        mBundle = getIntent().getExtras();

        subjectName = findViewById(R.id.sub_name);
        message = findViewById(R.id.msg);

        subjectName.setText(mBundle.getString("subject_name"));
        message.setText(mBundle.getString("message"));
    }
}