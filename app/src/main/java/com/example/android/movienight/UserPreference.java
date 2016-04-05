package com.example.android.movienight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class UserPreference extends AppCompatActivity {

    private String[] mQueryGenreValue = new String[9];
    private String[] mQueryCastValue = new String[2];
    public String mRequestUrlKey = "request url";
private int x;

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
                mQueryGenreValue[x++] += "28";
                break;
            case R.id.adventure:
                mQueryGenreValue[x++] += "12";

                break;
            case R.id.thriller:
                mQueryGenreValue[x++] += "53";

                break;
            case R.id.comedy:
                mQueryGenreValue[x++] += "35";

                break;
            case R.id.romance:
                mQueryGenreValue[x++] += "10749";

                break;
            case R.id.animation:
                mQueryGenreValue[x++] += "16";

                break;
            case R.id.family:
                mQueryGenreValue[x++] += "10715";

                break;
            case R.id.sci_fi:
                mQueryGenreValue[x++] += "878";

                break;
            case R.id.mystery:
                mQueryGenreValue[x++] += "9648";

                break;
            default:
                finish();
                Log.e("in User preference", "Incorrect checkbox chosen");
                break;
        }
    }
}
