package com.example.lab12.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MessageDBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MessageDB";
    public MessageDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query2 = "CREATE TABLE " + MessageMaster.Messages.TABLE_NAME + " (" +
                MessageMaster.Messages._ID + " INTEGER PRIMARY KEY," +
                MessageMaster.Messages.COLUMN_TEACHER_NAME + " TEXT," +
                MessageMaster.Messages.COLUMN_SUBJECT + " TEXT," +
                MessageMaster.Messages.COLUMN_MESSAGE + " TEXT)";
        db.execSQL(Query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) { }

    public long addMessage(String name, String subject, String message) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MessageMaster.Messages.COLUMN_TEACHER_NAME, name);
        values.put(MessageMaster.Messages.COLUMN_SUBJECT, subject);
        values.put(MessageMaster.Messages.COLUMN_MESSAGE, message);

        long id = db.insert(MessageMaster.Messages.TABLE_NAME, null, values);
        return id;
    }

    public Cursor getAllMessages() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                MessageMaster.Messages.TABLE_NAME,
                new String[] {MessageMaster.Messages.COLUMN_TEACHER_NAME, MessageMaster.Messages.COLUMN_SUBJECT, MessageMaster.Messages.COLUMN_MESSAGE},
                null,
                null,
                null,
                null,
                MessageMaster.Messages.COLUMN_SUBJECT + " DESC"
        );

        return  cursor;
    }

    public List getLastMessage() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(
                MessageMaster.Messages.TABLE_NAME,
                new String[] {MessageMaster.Messages.COLUMN_TEACHER_NAME, MessageMaster.Messages.COLUMN_SUBJECT, MessageMaster.Messages.COLUMN_MESSAGE},
                null,
                null,
                null,
                null,
                MessageMaster.Messages.COLUMN_SUBJECT + " DESC LIMIT 1"
        );

        List messages = new ArrayList();

        while (cursor.moveToNext()) {
            String teacherName = cursor.getString(cursor.getColumnIndexOrThrow(MessageMaster.Messages.COLUMN_TEACHER_NAME));
            String message = cursor.getString(cursor.getColumnIndexOrThrow(MessageMaster.Messages.COLUMN_MESSAGE));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow(MessageMaster.Messages.COLUMN_SUBJECT));
            messages.add(teacherName);
            messages.add(message);
            messages.add(subject);
        }

        return  messages;
    }
}
