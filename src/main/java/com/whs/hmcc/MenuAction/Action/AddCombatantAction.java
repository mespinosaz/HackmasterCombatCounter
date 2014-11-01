package com.whs.hmcc.MenuAction.Action;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.AddActivity;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.MenuAction.MenuAction;

public class AddCombatantAction extends MenuAction {
    public AddCombatantAction(Activity activity, Board board) {
        super(activity,board);
    }
    public boolean doAction() {
        Intent intent;
        intent = new Intent(myActivity, AddActivity.class);
        myActivity.startActivityForResult(intent, 1);
        return true;
    }
}