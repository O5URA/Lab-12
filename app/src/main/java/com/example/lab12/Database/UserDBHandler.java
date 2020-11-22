package com.example.lab12.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class UserDBHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "UserData";

    public UserDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query1 = "CREATE TABLE " + UserMaster.Users.TABLE_NAME + " (" +
                UserMaster.Users._ID + " INTEGER PRIMARY KEY," +
                UserMaster.Users.COLUMN_NAME_USERNAME + " TEXT," +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " TEXT," +
                UserMaster.Users.COLUMN_NAME_TYPE + " TEXT)";

        db.execSQL(Query1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) { }

    public long addInfo(String name, String password, String type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, name);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);
        values.put(UserMaster.Users.COLUMN_NAME_TYPE, type);

        long id = db.insert(UserMaster.Users.TABLE_NAME, null, values);
        return id;
    }

    public List loginUser(String userName, String password) {
        SQLiteDatabase db = getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,
                UserMaster.Users.COLUMN_NAME_TYPE
        };
        String selection = UserMaster.Users.COLUMN_NAME_USERNAME + " = ? and " +
                UserMaster.Users.COLUMN_NAME_PASSWORD + " = ?";
        String[] selectionArgs = {userName, password};

        String sortOrder =
                UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        List userList = new ArrayList();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_TYPE));
            String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));
            userList.add(name);
            userList.add(type);
            userList.add(pass);
        }
        cursor.close();
        return userList;
    }

}
