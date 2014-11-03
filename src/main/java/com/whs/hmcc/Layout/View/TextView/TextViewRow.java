package com.whs.hmcc.Layout.View.TextView;

import android.widget.TextView;

import java.util.ArrayList;

public class TextViewRow {
    private ArrayList<TextView> theRow;

    public TextViewRow() {
        theRow = new ArrayList<TextView>();
    }

    public void set(int index, TextView textView) {
        theRow.add(index,textView);
    }

    public TextView get(int index) {
        return theRow.get(index);
    }

    public void setRowTextColor(int color) {
        for(int i = 0; i < size() ; i++) {
            get(i).setTextColor(color);
        }
    }

    private int size() {
        return theRow.size();
    }
}
