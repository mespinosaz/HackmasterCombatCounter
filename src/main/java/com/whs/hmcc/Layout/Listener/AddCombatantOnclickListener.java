package com.whs.hmcc.Layout.Listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.whs.hmcc.Board.Combatant.Combatant;
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
        if (inputsAreFilled(inputName, inputCount, inputSpeed)) {
            returnIntent(inputName, inputCount, inputSpeed);
        } else {
            Toast.makeText(myActivity.getApplicationContext(), R.string.incorrectParameters, Toast.LENGTH_SHORT).show();
        }
    }

    private void returnIntent(EditText inputName, EditText inputCount, EditText inputSpeed) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Combatant.NAME_FIELD,inputName.getText().toString())
                .putExtra(Combatant.COUNT_FIELD, Integer.parseInt(inputCount.getText().toString()))
                .putExtra(Combatant.SPEED_FIELD, Integer.parseInt(inputSpeed.getText().toString()));
        myActivity.setResult(Activity.RESULT_OK,returnIntent);
        myActivity.finish();
    }

    private boolean inputsAreFilled(EditText inputName, EditText inputCount, EditText inputSpeed) {
        return inputTextIsFilled(inputName)
                && inputNumberIsFilled(inputCount)
                && inputNumberIsFilled(inputSpeed);
    }

    private boolean inputNumberIsFilled(EditText input) {
        return input.getText().toString().length() > 0
                && Integer.parseInt(input.getText().toString()) > 0;
    }

    private boolean inputTextIsFilled(EditText input) {
        return input.getText().toString().length() > 0;
    }
}
