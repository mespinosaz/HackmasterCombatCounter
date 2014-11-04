package com.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.app.AlertDialog;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.Listener.ClearCombatDialogOnclickListener;
import com.whs.hmcc.Layout.Listener.NullDialogOnclickListener;
import com.whs.hmcc.Menu.MenuAction;
import com.whs.hmcc.R;

public class ClearCombatAction extends MenuAction {
    public ClearCombatAction(Activity activity, Board board) {
        super(activity,board);
    }

    @Override
    public boolean doAction() {
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        builder.setMessage(R.string.clearCombatantsDialog)
            .setPositiveButton(R.string.yes, new ClearCombatDialogOnclickListener(myActivity,theBoard))
            .setNegativeButton(R.string.no, new NullDialogOnclickListener())
            .show();
        return true;
    }
}
