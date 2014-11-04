package com.whs.hmcc.Database.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.whs.hmcc.Database.Helper.CombatantDatabaseHelper;

public class CombatantModel extends Model {
    public CombatantModel(Context context) {
        super(context);
    }

    public long createCombatant(String name, Integer speed, Integer count, long combatId) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CombatantDatabaseHelper.COMBATANT_NAME_FIELD, name);
        initialValues.put(CombatantDatabaseHelper.COMBATANT_SPEED_FIELD, speed);
        initialValues.put(CombatantDatabaseHelper.COMBATANT_COUNT_FIELD, count);
        initialValues.put(CombatantDatabaseHelper.COMBATANT_COMBAT_ID_FIELD, combatId);
        return myDB.insert(CombatantDatabaseHelper.COMBATANT_TABLE, null, initialValues);
    }

    public Cursor combatantsByCombat(long combatId) throws SQLException {
        return executeQuery(
            CombatantDatabaseHelper.COMBATANT_TABLE,
            new String[]{
                CombatantDatabaseHelper.COMBATANT_ID_FIELD,
                CombatantDatabaseHelper.COMBATANT_NAME_FIELD,
                CombatantDatabaseHelper.COMBATANT_SPEED_FIELD,
                CombatantDatabaseHelper.COMBATANT_COUNT_FIELD,
                CombatantDatabaseHelper.COMBATANT_COMBAT_ID_FIELD
            },
            CombatantDatabaseHelper.COMBATANT_COMBAT_ID_FIELD + " = " + String.valueOf(combatId)
        );
    }
}
