package com.whs.hmcc.Database.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CombatantDatabaseHelper extends DatabaseHelper {
    public static final String COMBATANT_ID_FIELD = "_id";
    public static final String COMBATANT_NAME_FIELD = "name";
    public static final String COMBATANT_SPEED_FIELD = "speed";
    public static final String COMBATANT_COUNT_FIELD = "count";
    public static final String COMBATANT_COMBAT_ID_FIELD = "combat_id";
    public static final String COMBATANT_TABLE = "Combatant";

    private static final String COMBATANT_CREATE_TABLE_QUERY =
            "CREATE TABLE " + COMBATANT_TABLE + " (" +
                    COMBATANT_ID_FIELD + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COMBATANT_NAME_FIELD + " TEXT," +
                    COMBATANT_SPEED_FIELD + " INTEGER," +
                    COMBATANT_COUNT_FIELD + " INTEGER," +
                    COMBATANT_COMBAT_ID_FIELD + " INTEGER);";

    CombatantDatabaseHelper(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(COMBATANT_CREATE_TABLE_QUERY);
    }
}
