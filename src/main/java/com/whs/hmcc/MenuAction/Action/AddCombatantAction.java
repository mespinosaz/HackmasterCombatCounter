package com.whs.hmcc.MenuAction.Action;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.ActivityResult.Result.AddCombatantResult;
import com.whs.hmcc.AddActivity;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.MenuAction.MenuAction;

public class AddCombatantAction extends MenuAction {
    public static final int REQUEST_CODE = 1;

    public AddCombatantAction(Activity activity, Board board) {
        super(activity,board);
    }
    public boolean doAction() {
        Intent intent;
        intent = new Intent(myActivity, AddActivity.class);
        myActivity.startActivityForResult(intent, REQUEST_CODE);
        return true;
    }
}