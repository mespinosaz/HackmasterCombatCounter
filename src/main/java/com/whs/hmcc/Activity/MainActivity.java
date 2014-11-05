package com.whs.hmcc.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.whs.hmcc.Activity.Result.ActivityResultProcessor;
import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.BoardLayout;
import com.whs.hmcc.Menu.MenuActionProcessor;
import com.whs.hmcc.R;

public class MainActivity extends Activity {
    private BoardLayout layout;
    private Board theBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theBoard = new Board();
        layout = new BoardLayout(this, theBoard);
        layout.draw();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        MenuActionProcessor processor = new MenuActionProcessor(this, item, theBoard);
        return processor.process();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ActivityResultProcessor processor = new ActivityResultProcessor(this, theBoard);
        processor.setParameters(requestCode, resultCode, data);
        processor.process();
        layout.refreshGui();
    }
}