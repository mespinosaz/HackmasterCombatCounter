package com.whs.hmcc.Layout;

import android.app.Activity;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.whs.hmcc.Layout.Listener.CombatListOnclickListener;
import com.whs.hmcc.Layout.Watcher.TextFilterWatcher;
import com.whs.hmcc.Database.Helper.DatabaseHelper;
import com.whs.hmcc.Database.Model.CombatModel;
import com.whs.hmcc.Database.Filter.CombatNameFilterQueryProvider;
import com.whs.hmcc.R;

public class CombatListLayout implements LayoutInterface {
    private Activity myActivity;
    private CombatModel myCombatModel;
    private SimpleCursorAdapter myAdapter;

    public CombatListLayout(Activity activity) {
        myActivity = activity;
    }

    @Override
    public void draw() {
        setupAdapter();
        drawListView();
        setupSearchFilter();

    }

    private void drawListView() {
        ListView listView = (ListView) myActivity.findViewById(R.id.listView1);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new CombatListOnclickListener(myActivity));
    }

    private void setupSearchFilter() {
        EditText myFilter = (EditText) myActivity.findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextFilterWatcher(myAdapter));
        myAdapter.setFilterQueryProvider(new CombatNameFilterQueryProvider(myCombatModel));
    }

    private void setupAdapter() {
        setupModel();
        createAdapter();
    }

    private void setupModel() {
        myCombatModel = new CombatModel(myActivity);
        myCombatModel.open();
    }

    private void createAdapter() {
        Cursor cursor = myCombatModel.combats();

        String[] columns = new String[] {
            DatabaseHelper.COMBAT_NAME_FIELD
        };

        int[] to = new int[] {
                R.id.name,
        };

        myAdapter = new SimpleCursorAdapter(
                myActivity,
                R.layout.combat,
                cursor,
                columns,
                to
        );
    }
}
