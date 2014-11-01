package com.whs.hmcc.MenuAction.Action;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.BoardLayout;
import com.whs.hmcc.MenuAction.MenuAction;

public class ClearCombatAction extends MenuAction {
    public ClearCombatAction(Activity activity, Board board) {
        super(activity,board);
    }

    @Override
    public boolean doAction() {
        DialogInterface.OnClickListener dialogClickListener = defineOnclickListener();
        buildAlertDialog(dialogClickListener);
        return true;
    }

    private void buildAlertDialog(DialogInterface.OnClickListener dialogClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        builder.setMessage("Are you sure you want to clear all the combatants?")
                .setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

    private DialogInterface.OnClickListener defineOnclickListener() {
        return new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which == DialogInterface.BUTTON_POSITIVE) {
                        theBoard.resetBoard();
                        BoardLayout layout = new BoardLayout(myActivity,theBoard);
                        layout.refreshGui();
                    }
                }
            };
    }
}
