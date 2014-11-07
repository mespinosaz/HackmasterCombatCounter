package org.whs.hmcc.Database.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.whs.hmcc.Database.Helper.DatabaseHelper;

abstract public class Model {
    private Context theContext;
    private DatabaseHelper myDBHelper;
    protected SQLiteDatabase myDB;

    public Model(Context context) {
        theContext = context;
    }

    public Model open() throws SQLException {
        myDBHelper = new DatabaseHelper(theContext);
        myDB = myDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (myDBHelper != null) {
            myDBHelper.close();
        }
    }

    protected Cursor executeQuery (String table, String[] columns, String selection) {
        Cursor aCursor;
        aCursor = myDB.query(table, columns, selection, null,null,null,null,null);
        if (aCursor != null) {
            aCursor.moveToFirst();
        }
        return aCursor;
    }
}
