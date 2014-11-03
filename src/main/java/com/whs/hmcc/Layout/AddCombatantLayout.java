package com.whs.hmcc.Layout;

import android.app.Activity;
import android.widget.Button;

import com.whs.hmcc.AddActivity;
import com.whs.hmcc.Layout.Listener.AddCombatantOnclickListener;
import com.whs.hmcc.R;

public class AddCombatantLayout implements LayoutInterface {
    private Activity myActivity;

    public AddCombatantLayout(AddActivity activity) {
        myActivity = activity;
    }

    public void draw() {
        Button addButton = (Button) myActivity.findViewById(R.id.add_button);
        addButton.setOnClickListener(new AddCombatantOnclickListener(myActivity));
    }
}
