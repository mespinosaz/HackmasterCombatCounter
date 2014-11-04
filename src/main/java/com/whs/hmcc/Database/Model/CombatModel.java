package com.whs.hmcc.Database.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.whs.hmcc.Database.Helper.CombatDatabaseHelper;

public class CombatModel extends Model {
    public CombatModel(Context context) {
        super(context);
    }

    public long createCombat(String name, Integer count) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CombatDatabaseHelper.COMBAT_NAME_FIELD, name);
        initialValues.put(CombatDatabaseHelper.COMBAT_COUNT_FIELD,count);
        return myDB.insert(CombatDatabaseHelper.COMBAT_TABLE, null, initialValues);
    }

    public Cursor combats() {
        return executeQuery(
                CombatDatabaseHelper.COMBAT_TABLE,
                new String[]{
                        CombatDatabaseHelper.COMBAT_ID_FIELD,
                        CombatDatabaseHelper.COMBAT_NAME_FIELD,
                        CombatDatabaseHelper.COMBAT_COUNT_FIELD
                },
                null
        );
    }

    public Cursor combatsByName(String inputText) throws SQLException {
        if (inputText == null  ||  inputText.length () == 0)  {
            return combats();
        } else {
            return executeQuery(
                    CombatDatabaseHelper.COMBAT_TABLE,
                    new String[]{
                            CombatDatabaseHelper.COMBAT_ID_FIELD,
                            CombatDatabaseHelper.COMBAT_NAME_FIELD,
                            CombatDatabaseHelper.COMBAT_COUNT_FIELD
                    },
                    CombatDatabaseHelper.COMBAT_NAME_FIELD + " LIKE '%" + inputText + "%'"
            );
        }
    }

    public Cursor combatById(long combat_id) throws SQLException {
        return executeQuery(
            CombatDatabaseHelper.COMBAT_TABLE,
            new String[] {
                CombatDatabaseHelper.COMBAT_ID_FIELD,
                CombatDatabaseHelper.COMBAT_NAME_FIELD,
                CombatDatabaseHelper.COMBAT_COUNT_FIELD
            },
            CombatDatabaseHelper.COMBAT_ID_FIELD + " = " + String.valueOf(combat_id)
        );
    }
}