package com.whs.hmcc.Menu;

import android.app.Activity;

import com.whs.hmcc.Board.Board;

abstract public class MenuAction implements MenuActionInterface {
    protected Activity myActivity;
    protected Board theBoard;

    public MenuAction(Activity activity, Board board) {
        myActivity = activity;
        theBoard = board;
    }
}
