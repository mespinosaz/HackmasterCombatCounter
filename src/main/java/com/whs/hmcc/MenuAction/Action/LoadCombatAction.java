package com.whs.hmcc.MenuAction.Action;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.CombatListActivity;
import com.whs.hmcc.MenuAction.MenuAction;


public class LoadCombatAction extends MenuAction{
    public LoadCombatAction(Activity activity, Board board) {
        super(activity,board);
    }
    @Override
    public boolean doAction() {
        Intent intent = new Intent(myActivity, CombatListActivity.class);
        myActivity.startActivityForResult(intent, 2);
        return true;
    }
}
