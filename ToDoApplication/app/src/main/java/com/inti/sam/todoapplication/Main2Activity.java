package com.inti.sam.todoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends ActionBarActivity {

    EditText things_to_do;
    TextView itemDisplay,nameDisplay;
    MyDBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Toast.makeText(Main2Activity.this, R.string.instructions, Toast.LENGTH_LONG).show();

        nameDisplay =(TextView) findViewById(R.id.nameDisplay);
        Intent i = getIntent();
        String name = i.getStringExtra("name"); //receive the passed key from previous activity
        String greeting = new String("Hello "+ name+ ". It is going to be a productive day today!");
        nameDisplay.setText(greeting);

        things_to_do = (EditText) findViewById(R.id.things_to_do);
        itemDisplay = (TextView) findViewById(R.id.itemDisplay);
        dbManager = new MyDBManager(this, null, null, 1); //dbmanager takes in 4 parameter
        printDatabase();
    }

    public void addButtonClicked(View view){ //Add a product to database
        Things things = new Things(things_to_do.getText().toString());
        dbManager.addThings(things);
        printDatabase();
    }


    public void deleteButtonClicked(View view){  //delete
        String inputText = things_to_do.getText().toString();
        dbManager.deleteThings(inputText);
        printDatabase();
    }


    public void printDatabase(){   //Print the database
        String dbString = dbManager.dbToString();
        itemDisplay.setText(dbString);
        things_to_do.setText("");
    }

}