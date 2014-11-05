package com.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.content.Intent;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Activity.CombatListActivity;
import com.whs.hmcc.Menu.MenuAction;


public class LoadCombatAction extends MenuAction {
    public static final int REQUEST_CODE = 2;

    public LoadCombatAction(Activity activity, Board board) {
        super(activity,board);
    }
    @Override
    public boolean doAction() {
        Intent intent = new Intent(myActivity, CombatListActivity.class);
        myActivity.startActivityForResult(intent, REQUEST_CODE);
        return true;
    }
}
