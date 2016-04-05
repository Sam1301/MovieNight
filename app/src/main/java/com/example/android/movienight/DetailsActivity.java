package com.example.android.movienight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailsActivity.class.getSimpleName();
    public static final String mMovieImageKey = "image";
    public static final String mMovieTitleKey = "title";
    public static final String mMovieOverviewKey = "overview";
    public static final String mMovieVoteCountKey = "vote count";
    public static final String mMovieReleaseDateKey = "release date";
    public static final String mMovieTrailerUrlKey = "youtube trailer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        ImageView imageView = (ImageView) findViewById(R.id.movieImage);
        Picasso.with(this).load(intent.getStringExtra(mMovieImageKey)).into(imageView);


        TextView t1 = (TextView) findViewById(R.id.title);
        t1.setText(intent.getStringExtra(mMovieTitleKey));

        t1 = (TextView) findViewById(R.id.overview);
        t1.setText(intent.getStringExtra(mMovieOverviewKey));
//rating is out of ten
        float rating = intent.getFloatExtra(mMovieVoteCountKey, 0.0f);
        t1 = (TextView) findViewById(R.id.rating);
        t1.setText(rating/2 + "/5");

        t1 = (TextView) findViewById(R.id.release_date);
        t1.setText(intent.getStringExtra(mMovieReleaseDateKey));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setRating(rating / 2);

        String youtubeLink = intent.getStringExtra(mMovieTrailerUrlKey);

        //Sometimes, the returned url can be null, due to a runtime runtime error

        if(youtubeLink != null){
            //TODO: launch youtube app with the link using intent


        }else {
            Toast.makeText(this, "Try Again", Toast.LENGTH_SHORT);
        }

        Log.e(LOG_TAG, "youtube link in detailsActivity : " + youtubeLink);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
