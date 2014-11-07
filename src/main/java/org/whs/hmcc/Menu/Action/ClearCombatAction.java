package org.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.app.AlertDialog;

import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Layout.Listener.ClearCombatDialogOnclickListener;
import org.whs.hmcc.Layout.Listener.NullDialogOnclickListener;
import org.whs.hmcc.Menu.MenuAction;
import org.whs.hmcc.R;

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
