package com.whs.hmcc.ActivityResult.Result;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.ActivityResult.ActivityResult;
import com.whs.hmcc.Board.Board;

public class AddCombatantResult extends ActivityResult
{
    public static final int RESULT_CODE = 1;

    public AddCombatantResult(Activity activity, Intent intent, Board board) {
        super(activity, intent,board);
    }
    @Override
    public void process() {
        theBoard.addCombatant(
                myIntent.getStringExtra("name"),
                myIntent.getIntExtra("count", 1),
                myIntent.getIntExtra("speed", 1)
        );
    }
}
