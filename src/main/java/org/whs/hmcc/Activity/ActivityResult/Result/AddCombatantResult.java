package org.whs.hmcc.Activity.ActivityResult.Result;

import android.app.Activity;
import android.content.Intent;

import org.whs.hmcc.Activity.ActivityResult.ActivityResult;
import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Board.Combatant.Combatant;

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
