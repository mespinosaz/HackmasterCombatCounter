package com.whs.hmcc.Database.DataMapper;

import android.content.Context;
import android.database.Cursor;

import com.whs.hmcc.Database.Cursor.CursorProcessor;
import com.whs.hmcc.Database.Helper.CombatDatabaseHelper;
import com.whs.hmcc.Database.Model.CombatModel;

public class CombatDataMapper extends DataMapper {
    private CombatModel theModel;

    public CombatDataMapper(Context context) {
        super(context);
    }

    @Override
    protected void setupModel() {
        theModel = new CombatModel(myContext);
    }

    public int countFromCombat(long combatId) {
        Cursor cursor = theModel.combatById(combatId);
        CursorProcessor processor = new CursorProcessor(cursor);
        return processor.getIntegerValue(CombatDatabaseHelper.COMBAT_COUNT_FIELD);
    }

    public long create(String name, int count) {
        return theModel.createCombat(name,count);
    }
}
