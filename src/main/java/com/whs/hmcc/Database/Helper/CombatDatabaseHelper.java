package com.whs.hmcc.Database.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CombatDatabaseHelper extends DatabaseHelper {
    public static final String COMBAT_ID_FIELD = "_id";
    public static final String COMBAT_NAME_FIELD = "name";
    public static final String COMBAT_COUNT_FIELD = "count";
    public static final String KEY_CCURRENT = "continent";
    public static final String COMBAT_TABLE = "Combat";

    private static final String COMBAT_CREATE_TABLE_QUERY =
            "CREATE TABLE " + COMBAT_TABLE + " (" +
                    COMBAT_ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COMBAT_NAME_FIELD + " TEXT," +
                    COMBAT_COUNT_FIELD + " INTEGER," +
                    KEY_CCURRENT + " INTEGER DEFAULT 0);";

    public CombatDatabaseHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMBAT_CREATE_TABLE_QUERY);
    }
}
