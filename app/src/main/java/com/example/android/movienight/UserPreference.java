package com.example.android.movienight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    public void findMovies(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void checkboxes(View view) {
        switch (view.getId()) {
            case R.id.action:
                break;
            case R.id.adventure:
                break;
            case R.id.thriller:
                break;
            case R.id.comedy:
                break;
            case R.id.romance:
                break;
            case R.id.animation:
                break;
            case R.id.family:
                break;
            case R.id.sci_fi:
                break;
            case R.id.mystery:
                break;
            default:
                finish();
                Log.e("in User preference", "Incorrect checkbox chosen");
                break;
        }
    }
}
