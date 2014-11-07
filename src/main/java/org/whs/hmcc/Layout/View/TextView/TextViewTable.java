package org.whs.hmcc.Layout.View.TextView;

import android.widget.TextView;

import java.util.ArrayList;

public class TextViewTable {
    private ArrayList<TextViewRow> theTable;

    public TextViewTable() {
        theTable = new ArrayList<TextViewRow>();
    }

    public void setRow(int rowIndex, TextViewRow row) {
        theTable.add(rowIndex,row);
    }

    public void set(int rowIndex, int columnIndex, TextView textView) {
        theTable.get(rowIndex).set(columnIndex, textView);
    }

    public TextView get(int rowIndex, int columnIndex) {
        return theTable.get(rowIndex).get(columnIndex);
    }

    public void initializeRow(int rowIndex) {
        setRow(rowIndex, new TextViewRow());
    }

    public void setRowTextColor(int rowIndex, int color) {
        theTable.get(rowIndex).setRowTextColor(color);
    }
}
