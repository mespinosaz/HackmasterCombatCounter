package org.whs.hmcc.Layout.Watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.SimpleCursorAdapter;

public class TextFilterWatcher implements TextWatcher {
    SimpleCursorAdapter myDBCursor;

    public TextFilterWatcher(SimpleCursorAdapter cursor) {
        super();
        myDBCursor = cursor;
    }

    public void afterTextChanged(Editable s) {}

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        myDBCursor.getFilter().filter(s.toString());
    }
}
