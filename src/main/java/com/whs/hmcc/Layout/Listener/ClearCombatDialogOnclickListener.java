package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.BoardLayout;

public class ClearCombatDialogOnclickListener implements DialogInterface.OnClickListener {
    private Board theBoard;
    private Activity myActivity;

    public ClearCombatDialogOnclickListener(Activity activity, Board board) {
        myActivity = activity;
        theBoard = board;
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_POSITIVE) {
            theBoard.resetBoard();
            BoardLayout layout = new BoardLayout(myActivity,theBoard);
            layout.refreshGui();
        }
    }
}
