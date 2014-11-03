package com.whs.hmcc;

import android.app.Activity;
import android.os.Bundle;

import com.whs.hmcc.Layout.CombatListLayout;

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