package com.example.lab12.Database;

import android.provider.BaseColumns;

public class MessageMaster {
    private MessageMaster() {}

    public static class Messages implements BaseColumns {
        public static final String TABLE_NAME = "message";
        public static final String COLUMN_TEACHER_NAME = "teacher_name";
        public static final String COLUMN_SUBJECT = "subject";
        public static final String COLUMN_MESSAGE = "message";
    }
}
