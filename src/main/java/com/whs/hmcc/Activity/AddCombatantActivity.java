package com.whs.hmcc.Activity;

import android.os.Bundle;
import android.app.Activity;

import com.whs.hmcc.Layout.AddCombatantLayout;
import com.whs.hmcc.R;

public class AddCombatantActivity extends Activity {
    AddCombatantLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        layout = new AddCombatantLayout(this);
        layout.draw();
    }
}
