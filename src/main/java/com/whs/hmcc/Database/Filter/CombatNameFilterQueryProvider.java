package com.whs.hmcc.Database.Filter;

import android.database.Cursor;
import android.widget.FilterQueryProvider;

import com.whs.hmcc.Database.Model.CombatModel;

public class CombatNameFilterQueryProvider implements FilterQueryProvider {
    private CombatModel myCombatModel;

    public CombatNameFilterQueryProvider(CombatModel adapter) {
        super();
        myCombatModel = adapter;
    }

    @Override
    public Cursor runQuery(CharSequence constraint) {
        return myCombatModel.combatsByName(constraint.toString());
    }
}
