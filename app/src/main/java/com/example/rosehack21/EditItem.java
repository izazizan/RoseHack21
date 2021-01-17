package com.example.rosehack21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rosehack21.ui.tasks.TaskFragment;

public class EditItem extends AppCompatActivity {

    EditText editItem;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editItem = findViewById(R.id.editText);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar().setTitle("Edit Item");

        editItem.setText(getIntent().getStringExtra(TaskFragment.KEY_ITEM_TEXT));
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // create an intent which will contain the results
                Intent intent = new Intent();
                // pass the data (results of editing)
                intent.putExtra(TaskFragment.KEY_ITEM_TEXT, editItem.getText().toString());
                intent.putExtra(TaskFragment.KEY_ITEM_POSITION, getIntent().getExtras().getInt(TaskFragment.KEY_ITEM_POSITION));
                // set the result of the intent
                setResult(RESULT_OK, intent);
                // finish activity, close the screen and go back
                finish();
            }
        });
    }
}