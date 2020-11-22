package com.example.lab12.Database;

import android.provider.BaseColumns;

public class UserMaster {
    private UserMaster() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "Password";
        public static final String COLUMN_NAME_TYPE = "type";
    }
}
