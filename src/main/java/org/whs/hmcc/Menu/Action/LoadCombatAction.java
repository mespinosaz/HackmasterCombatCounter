package org.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.content.Intent;

import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Activity.CombatListActivity;
import org.whs.hmcc.Menu.MenuAction;


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
