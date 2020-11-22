package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab12.Database.UserDBHandler;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private Button loginButton;
    private Button registerButton;
    private TextView nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerButton = (Button) findViewById(R.id.register_button);
        userName = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login_button);
        nameUser = (TextView) findViewById(R.id.name);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser(view);
            }
        });
    }

    public void loginuser(View view) {
        StringBuilder stringBuilder = new StringBuilder();
        UserDBHandler dbHandler = new UserDBHandler(this);
        String name, pass;

        name = userName.getText().toString().trim();
        pass = password.getText().toString().trim();

        List user = dbHandler.loginUser(name, pass);
        String userSubarray = user.toString().substring(1, user.toString().length() - 1);
        String[] arrayList = userSubarray.split(", ");
        ArrayList list = new ArrayList<String>();

        for (int i=0; i<arrayList.length; i++) {
            list.add(arrayList[i]);
        }

        String type = list.get(1).toString();
        String sendName = list.get(0).toString();
        String password = list.get(2).toString();

        switch (type) {
            case "Student":
                Intent std = new Intent(MainActivity.this, StudentActivity.class);
                std.putExtra("name", sendName);
                startActivity(std);
                break;
            case "Teacher":
                Intent teacher = new Intent(MainActivity.this, TeacherActivity.class);
                teacher.putExtra("name", sendName);
                startActivity(teacher);
                break;
            default:
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }
}