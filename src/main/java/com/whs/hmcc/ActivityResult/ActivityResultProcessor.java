package com.whs.hmcc.ActivityResult;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import com.whs.hmcc.ActivityResult.Result.AddCombatantResult;
import com.whs.hmcc.ActivityResult.Result.LoadCombatResult;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.MainActivity;

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
        resultMap.append(AddCombatantResult.RESULT_CODE,new AddCombatantResult(myActivity, theIntent, theBoard));
        resultMap.append(LoadCombatResult.RESULT_CODE,new LoadCombatResult(myActivity, theIntent, theBoard));
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
