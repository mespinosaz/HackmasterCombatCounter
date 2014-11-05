package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.widget.EditText;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Database.DataMapper.CombatDataMapper;
import com.whs.hmcc.Database.DataMapper.CombatantDataMapper;

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
        CombatDataMapper combatDataMapper  = new CombatDataMapper(myActivity);
        CombatantDataMapper combatantDataMapper  = new CombatantDataMapper(myActivity);
        String combatName = theInput.getText().toString();
        int combatCount = theBoard.currentCount();
        long combatId = combatDataMapper.create(combatName, combatCount);
        for (int i = 0; i < theBoard.getCombatantList().size(); i++) {
            combatantDataMapper.addCombatantToCombat(theBoard.getCombatantList().get(i), combatId);
        }
    }
}
