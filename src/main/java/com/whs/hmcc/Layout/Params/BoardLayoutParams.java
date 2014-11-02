package com.whs.hmcc.Layout.Params;

import android.app.Activity;
import android.widget.LinearLayout;

public class BoardLayoutParams {
    public static final int NAME_LAYOUT_DP_WIDTH = 100;
    public static final int BUTTON_LAYOUT_DP_WIDTH = 35;
    public static final int BUTTON_LAYOUT_DP_HEIGHT = 35;
    public static final int NUMBER_LAYOUT_DP_WIDTH = 40;
    private Activity theActivity;

    private LinearLayout.LayoutParams tableParams;
    private LinearLayout.LayoutParams buttonParams;
    private LinearLayout.LayoutParams nameParams;
    private LinearLayout.LayoutParams numberParams;

    public BoardLayoutParams(Activity activity) {
        theActivity = activity;

        tableParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        buttonParams = new LinearLayout.LayoutParams(
                dpToPx(BUTTON_LAYOUT_DP_WIDTH),
                dpToPx(BUTTON_LAYOUT_DP_HEIGHT)
        );

        nameParams = new LinearLayout.LayoutParams(
                dpToPx(NAME_LAYOUT_DP_WIDTH),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        numberParams = new LinearLayout.LayoutParams(
                dpToPx(NUMBER_LAYOUT_DP_WIDTH),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

    }

    private int dpToPx(int dp) {
        final float scale = theActivity.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public LinearLayout.LayoutParams tableParams() {
        return tableParams;
    }

    public LinearLayout.LayoutParams buttonParams() {
        return buttonParams;
    }

    public LinearLayout.LayoutParams nameParams() {
        return nameParams;
    }

    public LinearLayout.LayoutParams numberParams() {
        return numberParams;
    }
}
