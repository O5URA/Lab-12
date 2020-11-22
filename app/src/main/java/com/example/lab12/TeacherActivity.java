package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab12.Database.MessageDBHandler;

public class TeacherActivity extends AppCompatActivity {

    private TextView name;
    private EditText subject;
    private EditText message;
    private Bundle mBundle;
    private Button sendMessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        mBundle = getIntent().getExtras();
        name = findViewById(R.id.welcome_teacher);
        subject = findViewById(R.id.teacher_subject);
        message = findViewById(R.id.messgage_teacher);
        sendMessageButton = findViewById(R.id.message_send_button);

        String welcomeNote = "Welcome, " + mBundle.getString("name");
        name.setText(welcomeNote);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        MessageDBHandler messageDBHandler = new MessageDBHandler(this);
        String teacherName = mBundle.getString("name");
        String formSubject = subject.getText().toString().trim();
        String formMessage = message.getText().toString().trim();

        long insertId = messageDBHandler.addMessage(teacherName, formSubject, formMessage);

        if (insertId > 0) {
            subject.setText(null);
            message.setText(null);
            Toast.makeText(getApplicationContext(), "Message Sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Message Sending Failed", Toast.LENGTH_SHORT).show();
        }
    }
}