package org.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;

import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Layout.BoardLayout;

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
