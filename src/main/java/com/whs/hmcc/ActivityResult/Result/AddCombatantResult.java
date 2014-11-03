package com.whs.hmcc.ActivityResult.Result;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.ActivityResult.ActivityResult;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Board.Combatant.Combatant;

public class AddCombatantResult extends ActivityResult {
    public AddCombatantResult(Activity activity, Intent intent, Board board) {
        super(activity, intent,board);
    }
    @Override
    public void process() {
        theBoard.addCombatant(
                myIntent.getStringExtra(Combatant.NAME_FIELD),
                myIntent.getIntExtra(Combatant.COUNT_FIELD, 1),
                myIntent.getIntExtra(Combatant.SPEED_FIELD, 1)
        );
    }
}
