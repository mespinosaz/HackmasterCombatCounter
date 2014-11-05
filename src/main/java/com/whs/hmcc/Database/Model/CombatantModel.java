package com.whs.hmcc.Database.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.whs.hmcc.Database.Helper.DatabaseHelper;

public class CombatantModel extends Model {
    DatabaseHelper myHelper;
    public CombatantModel(Context context) {
        super(context);
        myHelper = new DatabaseHelper(context);
    }

    public long createCombatant(String name, Integer speed, Integer count, long combatId) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.COMBATANT_NAME_FIELD, name);
        initialValues.put(DatabaseHelper.COMBATANT_SPEED_FIELD, speed);
        initialValues.put(DatabaseHelper.COMBATANT_COUNT_FIELD, count);
        initialValues.put(DatabaseHelper.COMBATANT_COMBAT_ID_FIELD, combatId);
        return myDB.insert(DatabaseHelper.COMBATANT_TABLE, null, initialValues);
    }

    public Cursor combatantsByCombat(long combatId) throws SQLException {
        return executeQuery(
            DatabaseHelper.COMBATANT_TABLE,
            new String[]{
                DatabaseHelper.COMBATANT_ID_FIELD,
                DatabaseHelper.COMBATANT_NAME_FIELD,
                DatabaseHelper.COMBATANT_SPEED_FIELD,
                DatabaseHelper.COMBATANT_COUNT_FIELD,
                DatabaseHelper.COMBATANT_COMBAT_ID_FIELD
            },
            DatabaseHelper.COMBATANT_COMBAT_ID_FIELD + " = " + String.valueOf(combatId)
        );
    }
}
