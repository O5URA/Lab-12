package com.example.lab12;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab12.Database.UserDBHandler;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private CheckBox teacher;
    private CheckBox student;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.register_user_name);
        password = findViewById(R.id.register_password);
        teacher = findViewById(R.id.teacher);
        student = findViewById(R.id.student);
        registerButton = findViewById(R.id.register_register_button);
    }

    public void addUser(View view) {
        UserDBHandler dbHandler = new UserDBHandler(this);
        String name = "", pass = "", type = "";

        if (teacher.isChecked()) {
            name = userName.getText().toString().trim();
            pass = password.getText().toString().trim();
            type = "Teacher";
        }

        if (student.isChecked()) {
            name = userName.getText().toString().trim();
            pass = password.getText().toString().trim();
            type = "Student";
        }

        long value = dbHandler.addInfo(name, pass, type);

        if (value > 0) {
            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Registration Fail", Toast.LENGTH_SHORT).show();
        }
    }
}