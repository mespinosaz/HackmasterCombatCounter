package org.whs.hmcc.Activity.ActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import org.whs.hmcc.Activity.ActivityResult.Result.AddCombatantResult;
import org.whs.hmcc.Activity.ActivityResult.Result.LoadCombatResult;
import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Activity.MainActivity;
import org.whs.hmcc.Menu.Action.AddCombatantAction;
import org.whs.hmcc.Menu.Action.LoadCombatAction;

public class ActivityResultProcessor {
    private MainActivity myActivity;
    private SparseArray<ActivityResult> resultMap;
    private int theRequestCode;
    private int theResultCode;
    private Intent theIntent;
    private Board theBoard;


    public ActivityResultProcessor(MainActivity activity, Board board) {
        myActivity = activity;
        theBoard = board;
    }

    private void setupResultMap() {
        resultMap = new SparseArray<ActivityResult>();
        resultMap.append(AddCombatantAction.REQUEST_CODE,new AddCombatantResult(myActivity, theIntent, theBoard));
        resultMap.append(LoadCombatAction.REQUEST_CODE,new LoadCombatResult(myActivity, theIntent, theBoard));
    }

    public void setParameters(int requestCode, int resultCode, Intent intent) {
        theRequestCode = requestCode;
        theResultCode = resultCode;
        theIntent = intent;
        setupResultMap();
    }

    public void process() {
        if(theResultCode == Activity.RESULT_OK) {
            resultMap.get(theRequestCode).process();
        }
    }

}
