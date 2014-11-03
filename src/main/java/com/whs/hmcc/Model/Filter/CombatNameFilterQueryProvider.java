package com.whs.hmcc.Model.Filter;

import android.database.Cursor;
import android.widget.FilterQueryProvider;

import com.whs.hmcc.Model.DbAdapter;

public class CombatNameFilterQueryProvider implements FilterQueryProvider {
    private DbAdapter myDBAdapter;

    public CombatNameFilterQueryProvider(DbAdapter adapter) {
        super();
        myDBAdapter = adapter;
    }

    @Override
    public Cursor runQuery(CharSequence constraint) {
        return myDBAdapter.getCombatsByName(constraint.toString());
    }
}
