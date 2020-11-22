package com.example.lab12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab12.Database.MessageDBHandler;

import java.util.ArrayList;
import java.util.List;

public class StudentActivity extends AppCompatActivity {

    private RecyclerView studentMessagesRecyclerView;
    private TextView welcomeNote;
    private MessageAdapter mMessageAdapter;
    private Bundle mBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        MessageDBHandler dbHandler = new MessageDBHandler(this);
        mBundle = getIntent().getExtras();

        studentMessagesRecyclerView = findViewById(R.id.message_list);
        welcomeNote = findViewById(R.id.welcome_student);

        String note= "Welcome, " + mBundle.getString("name");

        welcomeNote.setText(note);

        studentMessagesRecyclerView.setHasFixedSize(true);
        studentMessagesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMessageAdapter = new MessageAdapter(this, dbHandler.getAllMessages());

        studentMessagesRecyclerView.setAdapter(mMessageAdapter);

        // Notification settings

        List latestMessage = dbHandler.getLastMessage();

        String subArray = latestMessage.toString().substring(1, latestMessage.toString().length() - 1);
        String[] messageDataArray = subArray.split(", ");
        ArrayList list = new ArrayList<String>();

        for (int i=0; i<messageDataArray.length; i++) {
            list.add(messageDataArray[i]);
        }

        String subName = list.get(2).toString();
        String mes = list.get(1).toString();

        String notificationMessage = "You have new message";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_message)
                .setContentTitle(mBundle.getString("name"))
                .setContentText(notificationMessage);

        Intent intent = new Intent(StudentActivity.this, MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("subject_name", subName);
        intent.putExtra("message", mes);

        PendingIntent pendingIntent = PendingIntent.getActivity(StudentActivity.this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }
}