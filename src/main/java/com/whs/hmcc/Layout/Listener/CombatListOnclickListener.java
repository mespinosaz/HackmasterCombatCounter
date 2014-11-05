package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;

import com.whs.hmcc.R;

public class CombatListOnclickListener implements AdapterView.OnItemClickListener {
    private Activity myActivity;

    public CombatListOnclickListener(Activity activity) {
        myActivity = activity;
    }

    public void onItemClick(AdapterView<?> listView, View view, int position, long id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myActivity);
        builder.setMessage(R.string.clearCombatDialog)
            .setNegativeButton(R.string.delete, new CombatListDialogOnclickDeleteListener(myActivity, id))
            .setNeutralButton(R.string.cancel, new NullDialogOnclickListener())
            .setPositiveButton(R.string.load, new CombatListDialogOnclickLoadListener(myActivity, id))
            .show();
    }
}
