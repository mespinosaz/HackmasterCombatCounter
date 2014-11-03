package com.whs.hmcc;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whs.hmcc.Board.Combatant.Combatant;
import com.whs.hmcc.Board.Combatant.CombatantComparator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;


public class LibraryActivity extends Activity {
    private static final String FILENAME = "hmcc2-combatants.data";
    ArrayList<Combatant> mCombatantArrayList;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library);
        load();
      //  for(int i=0;i<mCombatantArrayList.size();i++)
        //    System.out.println(mCombatantArrayList.get(i).name() + " " + mCombatantArrayList.get(i).speed() + " " + mCombatantArrayList.get(i).currentCount() + "\n");


    }

    private void save() {
        try {
            FileOutputStream fos = fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            for(int i=0;i<mCombatantArrayList.size();i++)
                fos.write((mCombatantArrayList.get(i).name() + " " + mCombatantArrayList.get(i).speed() + " " + mCombatantArrayList.get(i).count() + "\n").getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void load() {
        mCombatantArrayList = new ArrayList<Combatant>();
        try {
            FileInputStream fis = openFileInput(FILENAME);
            DataInputStream dis = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(dis));

            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                mCombatantArrayList.add(new Combatant(strLine.split(" ")[0], Integer.parseInt(strLine.split(" ")[2]), Integer.parseInt(strLine.split(" ")[1])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rearrange();
    }

    public void rearrange() {

        Collections.sort(mCombatantArrayList, new CombatantComparator(CombatantComparator.ORDER_BY_NAME));
        LinearLayout l = (LinearLayout) findViewById(R.id.libraryCombatantLayout);
        l.removeViews(2, l.getChildCount() - 2);

        LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(dpToPx(35), dpToPx(35));
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(dpToPx(150), LinearLayout.LayoutParams.WRAP_CONTENT);
//        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(dpToPx(40), LinearLayout.LayoutParams.WRAP_CONTENT);
//
//
        for(int i=0;i<mCombatantArrayList.size();i++) {
            final int finalI = i;
            LinearLayout ll = new LinearLayout(getApplicationContext());
            tableParams.setMargins(dpToPx(15),dpToPx(10),0,0);
            ll.setLayoutParams(tableParams);
//
//            Button b1 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
//            b1.setText("+");
//            b1.setTextColor(Color.BLACK);
//            b1.setLayoutParams(buttonParams);
//            b1.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View vw) {
//                    addCombatantCount(finalI);
//                }
//            });
//
//
//            Button b2 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
//            b2.setText("-");
//            b2.setTextColor(Color.BLACK);
//            b2.setLayoutParams(buttonParams);
//
//            b2.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View vw) {
//                    decreaseCombatantCount(finalI);
//                }
//            });
//
//            Button b3 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
//            b3.setText("x");
//            b3.setTextColor(Color.BLACK);
//            b3.setLayoutParams(buttonParams);
//
//
//            b3.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View vw) {
//                    removeCombatant(finalI);
//                }
//            });
//
//            ll.addView(b1);
//            ll.addView(b2);
//            ll.addView(b3);
//
//            int color;
//            if (combatantList.get(i).currentCount() == mCount) {
//                color = Color.RED;
//            } else {
//                color = Color.BLACK;
//            }
//
            TextView t1 = new TextView(getApplicationContext());
            t1.setText(mCombatantArrayList.get(i).name());
            nameParams.setMargins(dpToPx(30),0,0,0);
            t1.setTextColor(Color.BLACK);
            t1.setLayoutParams(nameParams);
//
//
//            TextView t2 = new TextView(getApplicationContext());
//            t2.setText(String.valueOf(combatantList.get(i).speed()));
//            t2.setTextColor(color);
//            t2.setLayoutParams(new TableRow.LayoutParams(5));
//            t2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
//            t2.setGravity(Gravity.CENTER);
//            t2.setLayoutParams(numberParams);
//
//            TextView t3 = new TextView(getApplicationContext());
//            t3.setText(String.valueOf(combatantList.get(i).currentCount()));
//            t3.setTextColor(color);
//            t3.setGravity(Gravity.CENTER);
//            t3.setLayoutParams(numberParams);
//            t3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
//
            ll.addView(t1);
//            ll.addView(t2);
//            ll.addView(t3);
//
            l.addView(ll);
        }
    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

}
