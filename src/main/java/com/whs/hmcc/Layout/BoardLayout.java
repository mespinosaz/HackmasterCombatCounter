package com.whs.hmcc.Layout;

import android.app.Activity;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whs.hmcc.Board.Board;
import com.whs.hmcc.Layout.Params.BoardLayoutParams;
import com.whs.hmcc.R;

public class BoardLayout {
    private static final String COUNT_TITLE = "Count ";
    private static final int COUNT_VALUE_POSITION = 2;
    private static final int COMBATANT_LIST_POSITION = 2;
    public static final String DECREASE_COUNT_BUTTON_TEXT = "-";
    public static final String REMOVE_COMBATANT_BUTTON_TEXT = "x";
    public static final String INCREASE_COUNT_BUTTON_TEXT = "+";
    public static final int DEFAULT_DIP_FONT_SIZE = 20;
    public static final int COMBATANT_COUNT_COLUMN_POSITION = 2;
    public static final int COMBATANT_SPEED_COLUMN_POSITION = 1;
    public static final int COMBATANT_NAME_COLUMN_POSITION = 0;

    private TextViewTable theTextViewTable;
    private Board theBoard;
    private Activity theActivity;
    private LinearLayout mainLayout;
    private BoardLayoutParams layoutParams;

    public BoardLayout(Activity activity, Board board) {
        theActivity = activity;
        theBoard = board;
        mainLayout = (LinearLayout) theActivity.findViewById(R.id.combatantLayout);
        layoutParams = new BoardLayoutParams(theActivity);

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

    private void removeCombatant(int index) {
        theBoard.removeCombatant(index);
        refreshGui();
    }

    private void addCombatantCount(int index) {
        theBoard.addCombatantCount(index);
        markCombatantAsModified(index);
    }

    private void decreaseCombatantCount(int index) {
        theBoard.decreaseCombatantCount(index);
        markCombatantAsModified(index);
    }

    private void markCombatantAsModified(int index) {
        theTextViewTable.setRowTextColor(index, Color.GREEN);
        int combatantCount = theBoard.getCombatantCount(index);
        theTextViewTable.get(index, COUNT_VALUE_POSITION).setText(String.valueOf(combatantCount));
    }

    private void addCount() {
        theBoard.increaseCount();
        refreshGui();
    }

    private void decreaseCount() {
        theBoard.decreaseCount();
        refreshGui();
    }

    private void resetCount() {
        theBoard.resetCount();
        refreshGui();
    }

    public void refreshGui() {
        refreshCount();
        rearrange();
    }
    private void refreshCount() {
        TextView textView = (TextView) theActivity.findViewById(R.id.countHeader);
        textView.setText(COUNT_TITLE + theBoard.currentCount());
    }

    private void rearrange() {
        resetTable();
        theBoard.sortCombatants();
        clearLayout();
        fillView();
    }

    private void fillView() {
        for(int index=0;index<theBoard.getNumberOfCombatants();index++) {
            drawCombatant(index);
        }
    }

    private void drawCombatant(int combatantIndex) {
        LinearLayout newLayout = createNewLayout();
        drawButtons(combatantIndex, newLayout);
        drawCombatantInformation(combatantIndex, newLayout);
        mainLayout.addView(newLayout);
    }

    private LinearLayout createNewLayout() {
        LinearLayout.LayoutParams tableParams = layoutParams.tableParams();
        LinearLayout newLayout = new LinearLayout(theActivity.getApplicationContext());
        newLayout.setLayoutParams(tableParams);
        return newLayout;
    }

    private void drawCombatantInformation(int combatantIndex, LinearLayout newLayout) {
        theTextViewTable.initializeRow(combatantIndex);
        drawCombatantName(combatantIndex, newLayout);
        drawCombatantSpeed(combatantIndex, newLayout);
        drawCombatantCount(combatantIndex, newLayout);
    }

    private void drawCombatantCount(int combatantIndex, LinearLayout newLayout) {
        LinearLayout.LayoutParams numberParams = layoutParams.numberParams();
        int color = computeCombatantColor(combatantIndex);

        TextView newTextView = new TextView(theActivity.getApplicationContext());
        newTextView.setText(String.valueOf(theBoard.getCombatantCount(combatantIndex)));
        newTextView.setTextColor(color);
        newTextView.setGravity(Gravity.CENTER);
        newTextView.setLayoutParams(numberParams);
        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DIP_FONT_SIZE);

        theTextViewTable.set(combatantIndex, COMBATANT_COUNT_COLUMN_POSITION, newTextView);
        newLayout.addView(newTextView);
    }

    private void drawCombatantSpeed(int combatantIndex, LinearLayout newLayout) {
        LinearLayout.LayoutParams numberParams = layoutParams.numberParams();
        int color = computeCombatantColor(combatantIndex);

        TextView newTextView = new TextView(theActivity.getApplicationContext());
        newTextView.setText(String.valueOf(theBoard.getCombatant(combatantIndex).getSpeed()));
        newTextView.setTextColor(color);
        newTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_DIP_FONT_SIZE);
        newTextView.setGravity(Gravity.CENTER);
        newTextView.setLayoutParams(numberParams);

        theTextViewTable.set(combatantIndex, COMBATANT_SPEED_COLUMN_POSITION, newTextView);
        newLayout.addView(newTextView);

    }

    private void drawCombatantName(int combatantIndex, LinearLayout newLayout) {
        LinearLayout.LayoutParams nameParams = layoutParams.nameParams();
        int color = computeCombatantColor(combatantIndex);

        TextView newTextView = new TextView(theActivity.getApplicationContext());
        newTextView.setText(theBoard.getCombatant(combatantIndex).getName());
        newTextView.setTextColor(color);
        newTextView.setLayoutParams(nameParams);

        theTextViewTable.set(combatantIndex, COMBATANT_NAME_COLUMN_POSITION, newTextView);
        newLayout.addView(newTextView);
    }

    private int computeCombatantColor(int combatantIndex) {
        if (theBoard.isCombatantInCurrentCount(combatantIndex)) {
            return Color.RED;
        }
        return Color.BLACK;
    }

    private void drawButtons(final int index, LinearLayout layout) {
        drawIncreaseCountButton(index, layout);
        drawDecreaseCountButton(index, layout);
        drawRemoveCombatantButton(index, layout);
    }

    private void drawRemoveCombatantButton(final int index, LinearLayout layout) {
        LinearLayout.LayoutParams buttonParams = layoutParams.buttonParams();
        Button removeButton = new Button(theActivity.getApplicationContext(),null,android.R.attr.buttonStyleSmall);
        removeButton.setText(REMOVE_COMBATANT_BUTTON_TEXT);
        removeButton.setTextColor(Color.BLACK);
        removeButton.setLayoutParams(buttonParams);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                removeCombatant(index);
            }
        });
        layout.addView(removeButton);
    }

    private void drawDecreaseCountButton(final int index, LinearLayout layout) {
        Button decreaseButton = createButton(DECREASE_COUNT_BUTTON_TEXT, new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                decreaseCombatantCount(index);
            }
        });
        layout.addView(decreaseButton);
    }

    private void drawIncreaseCountButton(final int index, LinearLayout layout) {
        Button increaseButton = createButton(INCREASE_COUNT_BUTTON_TEXT, new View.OnClickListener() {
            @Override
            public void onClick(View vw) {
                addCombatantCount(index);
            }
        });

        layout.addView(increaseButton);
    }

    private Button createButton(String label, View.OnClickListener listener) {
        LinearLayout.LayoutParams buttonParams = layoutParams.buttonParams();
        Button button = new Button(theActivity.getApplicationContext(),null,android.R.attr.buttonStyleSmall);
        button.setTextColor(Color.BLACK);
        button.setLayoutParams(buttonParams);
        button.setText(label);
        button.setOnClickListener(listener);
        return button;
    }

    private void clearLayout() {
        mainLayout.removeViews(COMBATANT_LIST_POSITION, mainLayout.getChildCount() - COMBATANT_LIST_POSITION);
    }

    private void resetTable() {
        theTextViewTable = new TextViewTable();
    }

}
