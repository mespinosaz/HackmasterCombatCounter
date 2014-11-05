package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;

import com.whs.hmcc.Activity.Result.Result.LoadCombatResult;

public class CombatListDialogOnclickListener implements DialogInterface.OnClickListener {
    private Activity myActivity;
    private long selectedItemId;

    public CombatListDialogOnclickListener(Activity activity, long id) {
        myActivity = activity;
        selectedItemId = id;
    }
    @Override
    public void onClick(DialogInterface dialog, int selection) {
        if (selection == DialogInterface.BUTTON_POSITIVE) {
            returnSelection();
        }
    }

    private void returnSelection() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(LoadCombatResult.SELECTED_ITEM_FIELD_NAME, selectedItemId);
        myActivity.setResult(myActivity.RESULT_OK,returnIntent);
        myActivity.finish();
    }
}
