package com.whs.hmcc;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        Button addButton = (Button) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vw) {
                EditText inputName   = (EditText)findViewById(R.id.inputName);
                EditText inputCount   = (EditText)findViewById(R.id.inputCount);
                EditText inputSpeed   = (EditText)findViewById(R.id.inputSpeed);
                if (inputName.getText().toString().length() >0 && inputCount.getText().toString().length() > 0 && inputSpeed.getText().toString().length() > 0
                        && Integer.parseInt(inputCount.getText().toString()) > 0 && Integer.parseInt(inputSpeed.getText().toString()) > 0) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("name",inputName.getText().toString())
                        .putExtra("count", Integer.parseInt(inputCount.getText().toString()))
                        .putExtra("speed", Integer.parseInt(inputSpeed.getText().toString()));
                    setResult(RESULT_OK,returnIntent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),"Incorrect parameters",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*Button libraryButton = (Button) findViewById(R.id.add_library);
        libraryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View vw) {
                Intent intent = new Intent(AddActivity.this, LibraryActivity.class);
                startActivityForResult(intent, 2);
            }
        });*/


    }


}
