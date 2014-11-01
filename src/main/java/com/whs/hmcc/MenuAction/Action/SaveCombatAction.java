package com.whs.hmcc.MenuAction.Action;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.EditText;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.MenuAction.MenuAction;
import com.whs.hmcc.Model.DbAdapter;

public class SaveCombatAction extends MenuAction {

    public SaveCombatAction(Activity activity, Board board) {
        super(activity,board);
    }

    @Override
    public boolean doAction() {
        AlertDialog.Builder alert = new AlertDialog.Builder(myActivity);

        buildDialog(alert);

        alert.show();
        return true;
    }

    private void buildDialog(AlertDialog.Builder alert) {
        alert.setTitle("Save combat");
        alert.setMessage("The current combat status will be saved.");

        defineDialogButtons(alert);
    }

    private void defineDialogButtons(AlertDialog.Builder alert) {
        final EditText input = new EditText(myActivity);
        alert.setView(input);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                DbAdapter db = new DbAdapter(myActivity.getApplicationContext());
                db.open();
                long combat_id = db.createCombat(value,theBoard.currentCount());
                for(int i=0;i < theBoard.getCombatantList().size();i++) {
                    db.createCombatant(theBoard.getCombatantList().get(i),combat_id);
                }
                db.close();
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
    }
}
