package com.whs.hmcc.Layout.Listener;

import android.app.Activity;

import com.whs.hmcc.Activity.ActivityResult.Result.LoadCombatResult;

public class CombatListDialogOnclickDeleteListener extends CombatListDialogOnclickListener {

    public CombatListDialogOnclickDeleteListener(Activity activity, long id) {
        super(activity, id);
    }

    @Override
    protected int menuAction() {
        return LoadCombatResult.ACTION_DELETE;
    }
}
