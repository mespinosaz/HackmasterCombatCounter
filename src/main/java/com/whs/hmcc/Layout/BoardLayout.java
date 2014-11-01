package com.whs.hmcc.Layout;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.R;

public class BoardLayout {
    public static final String COUNT_TITLE = "Count ";
    private TextViewTable mTextViewList;

    private Board theBoard;
    private Activity theActivity;
    private LinearLayout mainLayout;

    public BoardLayout(Activity activity, Board board) {
        theActivity = activity;
        theBoard = board;
        mainLayout = (LinearLayout) theActivity.findViewById(R.id.combatantLayout);

    }

    public void setup() {
        setupLayoutButtons();
    }

    private void setupLayoutButtons() {
        setupResetButton();
        setupPreviousButton();
        setupNextButton();
    }

    private void setupNextButton() {
        setupButton(R.id.next_button, new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                addCount();
            }
        });
    }

    private void setupPreviousButton() {
        setupButton(R.id.previous_button, new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                decreaseCount();
            }
        });
    }

    private void setupResetButton() {
        setupButton(R.id.reset_button, new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                resetCount();
            }
        });
    }

    private void setupButton(int resourceId, View.OnClickListener listener) {
        Button button = (Button) theActivity.findViewById((resourceId));
        button.setOnClickListener(listener);
    }
    public void addCombatant(String name, int start_count, int speed) {
        theBoard.addCombatant(name, start_count, speed);
        refreshGui();
    }

    public void removeCombatant(int index) {
        theBoard.removeCombatant(index);
        refreshGui();
    }

    public void addCombatantCount(int index) {
        theBoard.addCombatantCount(index);
        mTextViewList.setRowTextColor(index,Color.GREEN);
        mTextViewList.get(index, 2).setText(String.valueOf(theBoard.getCombatantCount(index)));
    }

    public void decreaseCombatantCount(int index) {
        theBoard.decreaseCombatantCount(index);
        mTextViewList.setRowTextColor(index, Color.GREEN);
        mTextViewList.get(index, 2).setText(String.valueOf(theBoard.getCombatantCount(index)));
    }

    public void addCount() {
        theBoard.increaseCount();
        theBoard.updateCombatants();
        refreshGui();
    }

    public void decreaseCount() {
        theBoard.decreaseCount();
        refreshGui();
    }

    public void resetCount() {
        theBoard.resetCount();
        refreshGui();
    }

    public void refreshGui() {
        refreshCount();
        rearrange();
    }
    public void refreshCount() {
        TextView t=(TextView) theActivity.findViewById(R.id.countHeader);
        t.setText(COUNT_TITLE + theBoard.currentCount());
    }

    public void rearrange() {
        mTextViewList = new TextViewTable();
        theBoard.sortCombatants();
        mainLayout.removeViews(2, mainLayout.getChildCount() - 2);

        LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(dpToPx(35), dpToPx(35));
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(dpToPx(100), LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(dpToPx(40), LinearLayout.LayoutParams.WRAP_CONTENT);


        for(int i=0;i< theBoard.getNumberOfCombatants();i++) {
            final int finalI = i;
            LinearLayout ll = new LinearLayout(theActivity.getApplicationContext());
            ll.setLayoutParams(tableParams);

            Button b1 = new Button(theActivity.getApplicationContext(),null,android.R.attr.buttonStyleSmall);
            b1.setText("+");
            b1.setTextColor(Color.BLACK);
            b1.setLayoutParams(buttonParams);
            b1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    addCombatantCount(finalI);
                }
            });


            Button b2 = new Button(theActivity.getApplicationContext(),null,android.R.attr.buttonStyleSmall);
            b2.setText("-");
            b2.setTextColor(Color.BLACK);
            b2.setLayoutParams(buttonParams);

            b2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    decreaseCombatantCount(finalI);
                }
            });

            Button b3 = new Button(theActivity.getApplicationContext(),null,android.R.attr.buttonStyleSmall);
            b3.setText("x");
            b3.setTextColor(Color.BLACK);
            b3.setLayoutParams(buttonParams);


            b3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    removeCombatant(finalI);
                }
            });

            ll.addView(b1);
            ll.addView(b2);
            ll.addView(b3);

            int color;
            if (theBoard.getCombatantCount(i) == theBoard.currentCount()) {
                color = Color.RED;
            } else {
                color = Color.BLACK;
            }

            mTextViewList.initializeRow(i);

            TextView t1 = new TextView(theActivity.getApplicationContext());
            t1.setText(theBoard.getCombatant(i).getName());
            t1.setTextColor(color);
            t1.setLayoutParams(nameParams);

            mTextViewList.set(i, 0, t1);


            TextView t2 = new TextView(theActivity.getApplicationContext());
            t2.setText(String.valueOf(theBoard.getCombatant(i).getSpeed()));
            t2.setTextColor(color);
            t2.setLayoutParams(new TableRow.LayoutParams(5));
            t2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            t2.setGravity(Gravity.CENTER);
            t2.setLayoutParams(numberParams);

            mTextViewList.set(i, 1, t2);

            TextView t3 = new TextView(theActivity.getApplicationContext());
            t3.setText(String.valueOf(theBoard.getCombatantCount(i)));
            t3.setTextColor(color);
            t3.setGravity(Gravity.CENTER);
            t3.setLayoutParams(numberParams);
            t3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

            mTextViewList.set(i, 2, t3);

            ll.addView(t1);
            ll.addView(t2);
            ll.addView(t3);

            mainLayout.addView(ll);
        }
    }

    public int dpToPx(int dp) {
        final float scale = theActivity.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
