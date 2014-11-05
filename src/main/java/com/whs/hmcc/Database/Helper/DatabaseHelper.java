package com.whs.hmcc.Database.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "HMCC";
    private static final int DATABASE_VERSION = 1;

    public static final String COMBATANT_ID_FIELD = "_id";
    public static final String COMBATANT_NAME_FIELD = "name";
    public static final String COMBATANT_SPEED_FIELD = "speed";
    public static final String COMBATANT_COUNT_FIELD = "count";
    public static final String COMBATANT_COMBAT_ID_FIELD = "combat_id";
    public static final String COMBATANT_TABLE = "Combatant";
    public static final String COMBAT_ID_FIELD = "_id";
    public static final String COMBAT_NAME_FIELD = "name";
    public static final String COMBAT_COUNT_FIELD = "count";
    public static final String COMBAT_TABLE = "Combat";

    private static final String COMBAT_CREATE_TABLE_QUERY =
            "CREATE TABLE " + COMBAT_TABLE + " (" +
                    COMBAT_ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COMBAT_NAME_FIELD + " TEXT," +
                    COMBAT_COUNT_FIELD + " INTEGER);";

    private static final String COMBATANT_CREATE_TABLE_QUERY =
            "CREATE TABLE " + COMBATANT_TABLE + " (" +
                    COMBATANT_ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COMBATANT_NAME_FIELD + " TEXT," +
                    COMBATANT_SPEED_FIELD + " INTEGER," +
                    COMBATANT_COUNT_FIELD + " INTEGER," +
                    COMBATANT_COMBAT_ID_FIELD + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMBAT_CREATE_TABLE_QUERY);
        db.execSQL(COMBATANT_CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
