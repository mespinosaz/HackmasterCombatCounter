package com.whs.hmcc.Menu.Action;

import android.app.Activity;
import android.app.AlertDialog;
import android.widget.EditText;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.Listener.NullDialogOnclickListener;
import com.whs.hmcc.Layout.Listener.SaveCombatDialogOnclickListener;
import com.whs.hmcc.Menu.MenuAction;
import com.whs.hmcc.R;

public class SaveCombatAction extends MenuAction {

    public SaveCombatAction(Activity activity, Board board) {
        super(activity,board);
    }

    @Override
    public boolean doAction() {
        final EditText input = new EditText(myActivity);
        AlertDialog.Builder alert = new AlertDialog.Builder(myActivity);
        alert.setTitle(R.string.saveCombatTitle)
            .setMessage(R.string.saveCombatDescription)
            .setView(input)
            .setPositiveButton(R.string.ok, new SaveCombatDialogOnclickListener(myActivity,theBoard,input))
            .setNegativeButton(R.string.cancel, new NullDialogOnclickListener())
            .show();

        return true;
    }
}
