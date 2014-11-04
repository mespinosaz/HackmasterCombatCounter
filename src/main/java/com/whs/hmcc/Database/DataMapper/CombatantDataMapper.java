package com.whs.hmcc.Database.DataMapper;

import android.content.Context;
import android.database.Cursor;

import com.whs.hmcc.Board.Combatant.Combatant;
import com.whs.hmcc.Board.Combatant.CombatantCollection;
import com.whs.hmcc.Database.Cursor.CursorProcessor;
import com.whs.hmcc.Database.Helper.CombatantDatabaseHelper;
import com.whs.hmcc.Database.Model.CombatantModel;

public class CombatantDataMapper  extends DataMapper {
    private CombatantModel theModel;
    public CombatantDataMapper(Context context) {
        super(context);
    }

    @Override
    protected void setupModel() {
        theModel = new CombatantModel(myContext);
    }

    public CombatantCollection combatantsFromCombat(long combatId) {
        Cursor cursor = theModel.combatantsByCombat(combatId);
        CombatantCollection combatants = new CombatantCollection();
        while(!cursor.isAfterLast()) {
            combatants.add(
                prepareCombatantFromCursor(cursor)
            );
            cursor.moveToNext();
        }
        return combatants;
    }

    private Combatant prepareCombatantFromCursor(Cursor cursor) {
        CursorProcessor processor = new CursorProcessor(cursor);
        return new Combatant(
            processor.getStringValue(CombatantDatabaseHelper.COMBATANT_NAME_FIELD),
            processor.getIntegerValue(CombatantDatabaseHelper.COMBATANT_COUNT_FIELD),
            processor.getIntegerValue(CombatantDatabaseHelper.COMBATANT_SPEED_FIELD)
        );
    }

    public void addCombatantToCombat(Combatant combatant, long combatId) {
        theModel.createCombatant(
                combatant.name(),
                combatant.speed(),
                combatant.count(),
                combatId
        );
    }
}
