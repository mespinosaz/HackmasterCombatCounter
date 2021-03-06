package org.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.content.Intent;

import org.whs.hmcc.Activity.AddCombatantActivity;
import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Menu.MenuAction;

public class AddCombatantAction extends MenuAction {
    public static final int REQUEST_CODE = 1;

    public AddCombatantAction(Activity activity, Board board) {
        super(activity,board);
    }
    public boolean doAction() {
        Intent intent;
        intent = new Intent(myActivity, AddCombatantActivity.class);
        myActivity.startActivityForResult(intent, REQUEST_CODE);
        return true;
    }
}