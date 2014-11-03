package com.whs.hmcc.ActivityResult.Result;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import com.whs.hmcc.ActivityResult.ActivityResult;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Model.DbAdapter;

public class LoadCombatResult extends ActivityResult {
    public static final String SELECTED_TIEM_FIELD_NAME = "id";

    public LoadCombatResult(Activity activity, Intent intent, Board board) {
        super(activity, intent,board);
    }

    @Override
    public void process() {
        setupGui();
    }

    private void setupGui() {
        DbAdapter db = new DbAdapter(myActivity.getApplicationContext());
        db.open();
        long combatId = myIntent.getLongExtra(SELECTED_TIEM_FIELD_NAME,-1);
        setupCount(db, combatId);
        setupCombatants(db, combatId);
        db.close();
    }

    private void setupCombatants(DbAdapter db, long combatId) {
        Cursor cursor = db.getCombatantsByCombat(combatId);
        theBoard.resetCombatants();
        while(!cursor.isAfterLast()) {
            theBoard.addCombatant(
                    cursor.getString(cursor.getColumnIndex(db.KEY_CNAME)),
                    cursor.getInt(cursor.getColumnIndex(db.KEY_CCOUNT)),
                    cursor.getInt(cursor.getColumnIndex(db.KEY_CSPEED))
            );
            cursor.moveToNext();
        }
    }

    private void setupCount(DbAdapter db, long combatId) {
        Cursor cursor = db.getCombatById(combatId);
        theBoard.setCurrentCount(cursor.getInt(cursor.getColumnIndex(db.KEY_CCOUNT)));
    }
}
