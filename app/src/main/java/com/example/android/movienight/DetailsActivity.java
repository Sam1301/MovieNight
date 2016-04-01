package com.example.android.movienight;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    public static final String mMovieImageKey = "image";
    public static final String mMovieTitleKey = "title";
    public static final String mMovieOverviewKey = "overview";
    public static final String mMovieVoteCountKey = "vote count";
    public static final String mMovieReleaseDateKey = "release date";

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

        t1 = (TextView) findViewById(R.id.rating);
        t1.setText(intent.getDoubleExtra(mMovieVoteCountKey, 0.0) + "/10");

        t1 = (TextView) findViewById(R.id.release_date);
        t1.setText(intent.getStringExtra(mMovieReleaseDateKey));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
