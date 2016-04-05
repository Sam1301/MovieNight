package com.example.android.movienight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class UserPreference extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private List<String> mQueryGenreValue = new ArrayList<String>();
    private List<String> mQueryCastValue = new ArrayList<String>();
    public String mRequestUrlKey = "request url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_preference);


        //for actors drop down box
        Spinner spinnerActor = (Spinner) findViewById(R.id.actor_Spinner);
        spinnerActor.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.actors_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinnerActor.setAdapter(adapter);

        //for actresses drop down box
        Spinner spinnerActress = (Spinner) findViewById(R.id.actress_Spinner);
        spinnerActress.setOnItemSelectedListener(this);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.actresses_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActress.setAdapter(adapter);



    }

    public void findMovies(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String genreValue = android.text.TextUtils.join(",", mQueryGenreValue);
        String castValue = android.text.TextUtils.join(",", mQueryCastValue);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.themoviedb.org")
                .appendPath("3")
                .appendPath("discover")
                .appendPath("movie")
                .appendQueryParameter("with_genres", genreValue)
                .appendQueryParameter("with_cast", castValue);
        String requestUrl = builder.build().toString();
        Log.e("in user pref : ", requestUrl);
        intent.putExtra(mRequestUrlKey, requestUrl);
        startActivity(intent);


    }

    public void checkboxes(View view) {
        switch (view.getId()) {
            case R.id.action:
                mQueryGenreValue.add("28");
                break;
            case R.id.adventure:
                mQueryGenreValue.add("12");

                break;
            case R.id.thriller:
                mQueryGenreValue.add("53");

                break;
            case R.id.comedy:
                mQueryGenreValue.add("35");

                break;
            case R.id.romance:
                mQueryGenreValue.add("10749");

                break;
            case R.id.animation:
                mQueryGenreValue.add("16");

                break;
            case R.id.family:
                mQueryGenreValue.add("10715");

                break;
            case R.id.sci_fi:
                mQueryGenreValue.add("878");

                break;
            case R.id.mystery:
                mQueryGenreValue.add("9648");

                break;
            default:
                finish();
                Log.e("in User preference", "Incorrect checkbox chosen");
                break;
        }
    }

    private void spinnerActressHelper(int optionSelected){
        switch (optionSelected){
            case 0:
                mQueryCastValue.add("1245");
                break;
            case 1:
                mQueryCastValue.add("28782");
                break;
            case 2:
                mQueryCastValue.add("72129");
                break;
            case 3:
                mQueryCastValue.add("109513");
                break;
            case 4:
                mQueryCastValue.add("5081");
                break;
            case 5:
                mQueryCastValue.add("21911");
                break;
            case 6:
                mQueryCastValue.add("524");
                break;
            case 7:
                mQueryCastValue.add("6384");
                break;
            default:
                Log.e("Err spinnerActressHelp", "not able to select a case");
                break;
        }
    }
    private void spinnerActorHelper(int optionSelected){
        switch (optionSelected){
            case 0:
                mQueryCastValue.add("3223");
                break;
            case 1:
                mQueryCastValue.add("16828");
                break;
            case 2:
                mQueryCastValue.add("880");
                break;
            case 3:
                mQueryCastValue.add("1892");
                break;
            case 4:
                mQueryCastValue.add("1480862");
                break;
            case 5:
                mQueryCastValue.add("6968");
                break;
            case 6:
                mQueryCastValue.add("287");
                break;
            case 7:
                mQueryCastValue.add("1461");
                break;
            default:
                Log.e("Err spinnerActorHelp", "not able to select a case");
                break;
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.actress_Spinner)
        {
            spinnerActressHelper(position);
            Log.e("onItemSelected", "actress_spinner selected");
        }
        else if(spinner.getId() == R.id.actor_Spinner)
        {
            spinnerActorHelper(position);
            Log.e("onItemSelected", "actor_spinner selected");
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
