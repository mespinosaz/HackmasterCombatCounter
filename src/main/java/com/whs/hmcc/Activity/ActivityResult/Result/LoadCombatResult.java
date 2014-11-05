package com.whs.hmcc.Activity.ActivityResult.Result;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.Activity.ActivityResult.ActivityResult;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Board.Combatant.CombatantCollection;
import com.whs.hmcc.Database.DataMapper.CombatDataMapper;
import com.whs.hmcc.Database.DataMapper.CombatantDataMapper;

public class LoadCombatResult extends ActivityResult {
    public static final String SELECTED_COMBAT_ID_FIELD_NAME = "id";
    public static final String ACTION_FIELD_NAME = "action";
    public static final int ACTION_LOAD = 1;
    public static final int ACTION_DELETE = 2;

    private int theAction;
    private long selectedCombatId;

    public LoadCombatResult(Activity activity, Intent intent, Board board) {
        super(activity, intent,board);
        if (myIntent == null) {
            theAction = -1;
        } else {
            theAction = myIntent.getIntExtra(ACTION_FIELD_NAME, -1);
            selectedCombatId = myIntent.getLongExtra(SELECTED_COMBAT_ID_FIELD_NAME,-1);
        }
    }

    @Override
    public void process() {
        switch(theAction) {
            case ACTION_LOAD:
                setupSelectedCombat();
                break;
            case ACTION_DELETE:
                deleteSelectedCombat();
                break;
        }
    }

    private void deleteSelectedCombat() {
        CombatDataMapper data = new CombatDataMapper(myActivity);
        data.delete(selectedCombatId);
    }

    private void setupSelectedCombat() {
        setupCount();
        setupCombatants();
    }

    private void setupCombatants() {
        CombatantDataMapper data = new CombatantDataMapper(myActivity);
        CombatantCollection combatants = data.combatantsFromCombat(selectedCombatId);
        theBoard.resetCombatants();
        for(int index = 0; index < combatants.size(); index++) {
            theBoard.addCombatant(combatants.get(index));
        }
    }

    private void setupCount() {
        CombatDataMapper data = new CombatDataMapper(myActivity);
        int count = data.countFromCombat(selectedCombatId);
        theBoard.setCurrentCount(count);
    }
}
