package org.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import org.whs.hmcc.Activity.ActivityResult.Result.LoadCombatResult;

abstract public class CombatListDialogOnclickListener implements DialogInterface.OnClickListener {
    private Activity myActivity;
    private long selectedItemId;

    public CombatListDialogOnclickListener(Activity activity, long id) {
        myActivity = activity;
        selectedItemId = id;
    }
    @Override
    public void onClick(DialogInterface dialog, int selection) {
        returnSelection();
    }

    private void returnSelection() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(LoadCombatResult.SELECTED_COMBAT_ID_FIELD_NAME, selectedItemId);
        returnIntent.putExtra(LoadCombatResult.ACTION_FIELD_NAME, menuAction());
        myActivity.setResult(myActivity.RESULT_OK,returnIntent);
        myActivity.finish();
    }

    protected abstract int menuAction();
}
