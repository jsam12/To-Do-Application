package com.inti.sam.todoapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button nextButton;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nextButton(View view) {
        nextButton = (Button) findViewById(R.id.nextButton);
        username = (EditText) findViewById(R.id.username);

        if (username.getText().toString().trim().length() <= 0) {
            Toast.makeText(MainActivity.this, R.string.no_name, Toast.LENGTH_SHORT).show(); //makes a toast when no input is entered
        } else {
            String username1 = String.valueOf(username.getText().toString());
            Intent i = new Intent(MainActivity.this, Main2Activity.class);
            i.putExtra("name", username1); //passes value into the key
            startActivity(i); //starts the next activity
    }
}
}