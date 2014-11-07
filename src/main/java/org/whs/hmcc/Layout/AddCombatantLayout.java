package org.whs.hmcc.Layout;

import android.app.Activity;
import android.widget.Button;

import org.whs.hmcc.Activity.AddCombatantActivity;
import org.whs.hmcc.Layout.Listener.AddCombatantOnclickListener;
import org.whs.hmcc.R;

public class AddCombatantLayout implements LayoutInterface {
    private Activity myActivity;

    public AddCombatantLayout(AddCombatantActivity activity) {
        myActivity = activity;
    }

    public void draw() {
        Button addButton = (Button) myActivity.findViewById(R.id.add_button);
        addButton.setOnClickListener(new AddCombatantOnclickListener(myActivity));
    }
}
