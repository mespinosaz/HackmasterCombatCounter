package com.whs.hmcc.Database.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

abstract public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HMCC";
    private static final int DATABASE_VERSION = 1;

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
