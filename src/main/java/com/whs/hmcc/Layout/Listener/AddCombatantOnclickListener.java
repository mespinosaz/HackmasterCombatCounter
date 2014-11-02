package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whs.hmcc.R;

public class AddCombatantOnclickListener implements View.OnClickListener {
    private Activity myActivity;

    public AddCombatantOnclickListener(Activity activity) {
        myActivity = activity;
    }

    @Override
    public void onClick(View vw) {
        EditText inputName = (EditText) myActivity.findViewById(R.id.inputName);
        EditText inputCount = (EditText) myActivity.findViewById(R.id.inputCount);
        EditText inputSpeed = (EditText) myActivity.findViewById(R.id.inputSpeed);
        if (inputName.getText().toString().length() > 0
                && inputCount.getText().toString().length() > 0
                && inputSpeed.getText().toString().length() > 0
                && Integer.parseInt(inputCount.getText().toString()) > 0
                && Integer.parseInt(inputSpeed.getText().toString()) > 0) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("name",inputName.getText().toString())
                    .putExtra("count", Integer.parseInt(inputCount.getText().toString()))
                    .putExtra("speed", Integer.parseInt(inputSpeed.getText().toString()));
            myActivity.setResult(Activity.RESULT_OK,returnIntent);
            myActivity.finish();
        } else {
            Toast.makeText(myActivity.getApplicationContext(), "Incorrect parameters", Toast.LENGTH_SHORT).show();
        }
    }
}
