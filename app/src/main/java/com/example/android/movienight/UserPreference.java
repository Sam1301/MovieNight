package com.example.android.movienight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserPreference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);


        //for actors drop down box
        Spinner spinner = (Spinner) findViewById(R.id.actor_Spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actors_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //for actresses drop down box
        spinner = (Spinner) findViewById(R.id.actress_Spinner);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.actresses_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        startActivity(new Intent(this, MainActivity.class));
    }
}
