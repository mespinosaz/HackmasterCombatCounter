package com.whs.hmcc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.graphics.Color;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends Activity {
    private int mCount;
    ArrayList<Combatant> mCombatantList;
    ArrayList<ArrayList<TextView>> mTextViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCount = 1;
        mCombatantList = new ArrayList();

        setContentView(R.layout.activity_main);
        Button resetButton = (Button) findViewById(R.id.reset_button);
        Button previousButton = (Button) findViewById(R.id.previous_button);
        Button nextButton = (Button) findViewById(R.id.next_button);
        resetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vw) {
                resetCount();
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vw) {
                substractCount();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vw) {
                addCount();
            }
        });

      /*  addCombatant("isabella",1,7);
        addCombatant("yngwe",1,10);
        addCombatant("violet",3,7);
        addCombatant("helga",4,10);
        addCombatant("ivan",5,11);
        addCombatant("rodrigo",5,8);
        addCombatant("garagrim",8,12);
        addCombatant("trevor",10,7);
        addCombatant("orc 1",4,9);
        addCombatant("orc 1",7,9);
        addCombatant("orc 1",7,9);
        addCombatant("orc 1",9,9);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_addcombat:
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
                return true;
            case R.id.action_clearcombat:
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                mCombatantList = new ArrayList();
                                refreshGui();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Are you sure you want to clear all the combatants?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

                return true;
            case R.id.action_loadcombat:
                intent = new Intent(MainActivity.this, CombatListActivity.class);
                startActivityForResult(intent, 2);
                return true;
            case R.id.action_savecombat:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                alert.setTitle("Save combat");
                alert.setMessage("The current combat status will be saved.");

                // Set an EditText view to get user input
                final EditText input = new EditText(this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String value = input.getText().toString();
                        DbAdapter db = new DbAdapter(getApplicationContext());
                        db.open();
                        long combat_id = db.createCombat(value,mCount);
                        for(int i=0;i<mCombatantList.size();i++) {
                            db.createCombatant(mCombatantList.get(i),combat_id);
                        }
                        db.close();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
                return true;
        }
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK){
                    addCombatant(data.getStringExtra("name"),data.getIntExtra("count", 1),data.getIntExtra("speed", 1));
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    DbAdapter db = new DbAdapter(getApplicationContext());
                    db.open();
                    long combat_id = data.getLongExtra("id",-1);
                    
                    Cursor c = db.getCombatById(combat_id);
                    mCount = c.getInt(c.getColumnIndex(db.KEY_CCOUNT));

                    c = db.getCombatantsByCombat(combat_id);
                    mCombatantList = new ArrayList();
                    while(!c.isAfterLast()) {
                        addCombatant(c.getString(c.getColumnIndex(db.KEY_CNAME)),c.getInt(c.getColumnIndex(db.KEY_CCOUNT)),c.getInt(c.getColumnIndex(db.KEY_CSPEED)));
                        c.moveToNext();

                    }
                    db.close();
                    refreshGui();
                }
                break;
        }
    }

    public void addCombatant(String name, int start_count, int speed) {
        mCombatantList.add(new Combatant(name,start_count,speed));
        refreshGui();
    }

    public void removeCombatant(int index) {
        mCombatantList.remove(index);
        refreshGui();
    }

    public void addCombatantCount(int index) {
        mCombatantList.get(index).addCount();
        mTextViewList.get(index).get(0).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(1).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(2).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(2).setText(String.valueOf(mCombatantList.get(index).getCount()));
       // refreshGui();
    }

    public void substractCombatantCount(int index) {
        mCombatantList.get(index).substractCount();
        mTextViewList.get(index).get(0).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(1).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(2).setTextColor(Color.GREEN);
        mTextViewList.get(index).get(2).setText(String.valueOf(mCombatantList.get(index).getCount()));
      //  refreshGui();
    }
    public void addCount() {

        mCount++;
        updateCombatants();
        refreshGui();
    }

    private void updateCombatants() {
        for(int i=0;i<mCombatantList.size();i++) {
            while(mCombatantList.get(i).getCount()<mCount) {
                mCombatantList.get(i).setCount(mCombatantList.get(i).getCount()+mCombatantList.get(i).getSpeed());
            }
        }
    }

    public void substractCount() {
        mCount = Math.max(mCount-1,1);
        refreshGui();
    }

    public void resetCount() {
        mCount = 1;
        refreshGui();
    }

    public void refreshGui() {
        refreshCount();
        rearrange();
    }
    public void refreshCount() {
        TextView t=(TextView)findViewById(R.id.countHeader);
        t.setText("Count " + mCount);
    }
    public void rearrange() {
        mTextViewList = new ArrayList();
        Collections.sort(mCombatantList, new CombatantComparator());
        LinearLayout l = (LinearLayout) findViewById(R.id.combatantLayout);
        l.removeViews(2, l.getChildCount() - 2);

        LinearLayout.LayoutParams tableParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(dpToPx(35), dpToPx(35));
        LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(dpToPx(100), LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams numberParams = new LinearLayout.LayoutParams(dpToPx(40), LinearLayout.LayoutParams.WRAP_CONTENT);


        for(int i=0;i<mCombatantList.size();i++) {
            final int finalI = i;
            LinearLayout ll = new LinearLayout(getApplicationContext());
            ll.setLayoutParams(tableParams);

            Button b1 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
            b1.setText("+");
            b1.setTextColor(Color.BLACK);
            b1.setLayoutParams(buttonParams);
            b1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    addCombatantCount(finalI);
                }
            });


            Button b2 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
            b2.setText("-");
            b2.setTextColor(Color.BLACK);
            b2.setLayoutParams(buttonParams);

            b2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View vw) {
                    substractCombatantCount(finalI);
                }
            });

            Button b3 = new Button(getApplicationContext(),null,android.R.attr.buttonStyleSmall);
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
            if (mCombatantList.get(i).getCount() == mCount) {
                color = Color.RED;
            } else {
                color = Color.BLACK;
            }

            mTextViewList.add(i,new ArrayList());

            TextView t1 = new TextView(getApplicationContext());
            t1.setText(mCombatantList.get(i).getName());
            t1.setTextColor(color);
            t1.setLayoutParams(nameParams);

            mTextViewList.get(i).add(0,t1);


            TextView t2 = new TextView(getApplicationContext());
            t2.setText(String.valueOf(mCombatantList.get(i).getSpeed()));
            t2.setTextColor(color);
            t2.setLayoutParams(new TableRow.LayoutParams(5));
            t2.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);
            t2.setGravity(Gravity.CENTER);
            t2.setLayoutParams(numberParams);

            mTextViewList.get(i).add(1,t2);

            TextView t3 = new TextView(getApplicationContext());
            t3.setText(String.valueOf(mCombatantList.get(i).getCount()));
            t3.setTextColor(color);
            t3.setGravity(Gravity.CENTER);
            t3.setLayoutParams(numberParams);
            t3.setTextSize(TypedValue.COMPLEX_UNIT_DIP,20);

            mTextViewList.get(i).add(2,t3);

            ll.addView(t1);
            ll.addView(t2);
            ll.addView(t3);

            l.addView(ll);
        }

    }

    public int dpToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    
}
