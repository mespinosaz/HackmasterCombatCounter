package com.whs.hmcc.Database.Cursor;

import android.database.Cursor;

public class CursorProcessor {
    Cursor theCursor;

    public CursorProcessor(Cursor cursor) {
        theCursor = cursor;
    }

    public String getStringValue(String field) {
        return theCursor.getString(getColumnIndex(field));
    }

    private int getColumnIndex(String field) {
        return theCursor.getColumnIndex(field);
    }

    public int getIntegerValue(String field) {
        return theCursor.getInt(getColumnIndex(field));
    }
}
