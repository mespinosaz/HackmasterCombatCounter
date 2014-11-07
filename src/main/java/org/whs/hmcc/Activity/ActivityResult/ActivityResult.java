package org.whs.hmcc.Activity.ActivityResult;

import android.app.Activity;
import android.content.Intent;

import org.whs.hmcc.Board.Board;

public abstract class ActivityResult implements ActivityResultInterface {
    protected Activity myActivity;
    protected Intent myIntent;
    protected Board theBoard;

    public ActivityResult(Activity activity, Intent intent, Board board) {
        myActivity = activity;
        theBoard = board;
        myIntent = intent;
    }
}
