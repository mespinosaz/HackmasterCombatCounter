package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.EditText;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Model.DbAdapter;

public class SaveCombatDialogOnclickListener implements DialogInterface.OnClickListener {
    private EditText theInput;
    private Board theBoard;
    private Activity myActivity;

    public SaveCombatDialogOnclickListener(Activity activity, Board board, EditText input) {
        myActivity = activity;
        theBoard = board;
        theInput = input;
    }

    public void onClick(DialogInterface dialog, int whichButton) {
        String value = theInput.getText().toString();
        DbAdapter db = new DbAdapter(myActivity.getApplicationContext());
        db.open();
        long combat_id = db.createCombat(value, theBoard.currentCount());
        for (int i = 0; i < theBoard.getCombatantList().size(); i++) {
            db.createCombatant(theBoard.getCombatantList().get(i), combat_id);
        }
        db.close();

    }
}
