package org.whs.hmcc.Activity;

import android.app.Activity;
import android.os.Bundle;

import org.whs.hmcc.Layout.CombatListLayout;
import org.whs.hmcc.R;

public class CombatListActivity extends Activity {
    CombatListLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        layout = new CombatListLayout(this);
        layout.draw();
    }
}