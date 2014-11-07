package org.whs.hmcc.Menu;

import android.util.SparseArray;
import android.view.MenuItem;

import org.whs.hmcc.Board.Board;
import org.whs.hmcc.Activity.MainActivity;
import org.whs.hmcc.Menu.Action.AddCombatantAction;
import org.whs.hmcc.Menu.Action.ClearCombatAction;
import org.whs.hmcc.Menu.Action.LoadCombatAction;
import org.whs.hmcc.Menu.Action.SaveCombatAction;
import org.whs.hmcc.R;



public class MenuActionProcessor {
    private MainActivity myActivity;
    private MenuItem itemToProcess;
    private SparseArray<MenuAction> actionMap;

    public MenuActionProcessor(MainActivity activity, MenuItem item, Board board) {
        myActivity = activity;
        itemToProcess = item;
        setupActionMap(board);
    }

    private void setupActionMap(Board board) {
        actionMap = new SparseArray<MenuAction>();
        actionMap.append(R.id.action_addcombat,new AddCombatantAction(myActivity, board));
        actionMap.append(R.id.action_clearcombat,new ClearCombatAction(myActivity, board));
        actionMap.append(R.id.action_loadcombat,new LoadCombatAction(myActivity, board));
        actionMap.append(R.id.action_savecombat,new SaveCombatAction(myActivity, board));
    }

    public boolean process() {
        int option = itemToProcess.getItemId();
        return actionMap.get(option).doAction();
    }
}
