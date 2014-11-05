package com.whs.hmcc.Activity.Result.Result;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.Activity.Result.ActivityResult;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Board.Combatant.CombatantCollection;
import com.whs.hmcc.Database.DataMapper.CombatDataMapper;
import com.whs.hmcc.Database.DataMapper.CombatantDataMapper;

public class LoadCombatResult extends ActivityResult {
    public static final String SELECTED_ITEM_FIELD_NAME = "id";

    public LoadCombatResult(Activity activity, Intent intent, Board board) {
        super(activity, intent,board);
    }

    @Override
    public void process() {
        setupGui();
    }

    private void setupGui() {
        long combatId = myIntent.getLongExtra(SELECTED_ITEM_FIELD_NAME,-1);
        setupCount(combatId);
        setupCombatants(combatId);
    }

    private void setupCombatants(long combatId) {
        CombatantDataMapper data = new CombatantDataMapper(myActivity);
        CombatantCollection combatants = data.combatantsFromCombat(combatId);
        theBoard.resetCombatants();
        for(int index = 0; index < combatants.size(); index++) {
            theBoard.addCombatant(combatants.get(index));
        }
    }

    private void setupCount(long combatId) {
        CombatDataMapper data = new CombatDataMapper(myActivity);
        int count = data.countFromCombat(combatId);
        theBoard.setCurrentCount(count);
    }
}
