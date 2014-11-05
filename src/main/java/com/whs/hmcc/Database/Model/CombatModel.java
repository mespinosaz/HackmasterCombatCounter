package com.whs.hmcc.Database.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.whs.hmcc.Database.Helper.DatabaseHelper;

public class CombatModel extends Model {
    DatabaseHelper myHelper;

    public CombatModel(Context context) {
        super(context);
        myHelper = new DatabaseHelper(context);
    }

    public long createCombat(String name, Integer count) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.COMBAT_NAME_FIELD, name);
        initialValues.put(DatabaseHelper.COMBAT_COUNT_FIELD, count);
        return myDB.insert(DatabaseHelper.COMBAT_TABLE, null, initialValues);
    }

    public Cursor combats() {
        return executeQuery(
            DatabaseHelper.COMBAT_TABLE,
            new String[]{
                DatabaseHelper.COMBAT_ID_FIELD,
                DatabaseHelper.COMBAT_NAME_FIELD,
                DatabaseHelper.COMBAT_COUNT_FIELD
            },
            null
        );
    }

    public Cursor combatsByName(String inputText) throws SQLException {
        if (inputText == null  ||  inputText.length () == 0)  {
            return combats();
        } else {
            return executeQuery(
                DatabaseHelper.COMBAT_TABLE,
                new String[]{
                    DatabaseHelper.COMBAT_ID_FIELD,
                    DatabaseHelper.COMBAT_NAME_FIELD,
                    DatabaseHelper.COMBAT_COUNT_FIELD
                },
                DatabaseHelper.COMBAT_NAME_FIELD + " LIKE '%" + inputText + "%'"
            );
        }
    }

    public Cursor combatById(long combat_id) throws SQLException {
        return executeQuery(
            DatabaseHelper.COMBAT_TABLE,
            new String[] {
                DatabaseHelper.COMBAT_ID_FIELD,
                DatabaseHelper.COMBAT_NAME_FIELD,
                DatabaseHelper.COMBAT_COUNT_FIELD
            },
            DatabaseHelper.COMBAT_ID_FIELD + " = " + String.valueOf(combat_id)
        );
    }

    public int deleteCombat(long combatId) {
        return myDB.delete(
            DatabaseHelper.COMBAT_TABLE,
            DatabaseHelper.COMBAT_ID_FIELD + "=" + String.valueOf(combatId) ,
            null
       );
    }
}