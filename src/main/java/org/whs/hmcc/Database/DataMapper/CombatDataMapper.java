package org.whs.hmcc.Database.DataMapper;

import android.content.Context;
import android.database.Cursor;

import org.whs.hmcc.Database.Cursor.CursorProcessor;
import org.whs.hmcc.Database.Helper.DatabaseHelper;
import org.whs.hmcc.Database.Model.CombatModel;

public class CombatDataMapper extends DataMapper {
    public CombatDataMapper(Context context) {
        super(context);
    }

    @Override
    protected void setupModel() {
        theModel = new CombatModel(myContext);
    }

    public int countFromCombat(long combatId) {
        Cursor cursor = model().combatById(combatId);
        CursorProcessor processor = new CursorProcessor(cursor);
        return processor.getIntegerValue(DatabaseHelper.COMBAT_COUNT_FIELD);
    }

    private CombatModel model() {
        return (CombatModel)theModel;
    }

    public long create(String name, int count) {
        return model().createCombat(name, count);
    }

    public void delete(long combatId) {
        model().deleteCombat(combatId);
    }
}
