package com.example.android.movienight;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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
private Intent mintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mintent = getIntent();

        ImageView imageView = (ImageView) findViewById(R.id.movieImage);
        Picasso.with(this).load(mintent.getStringExtra(mMovieImageKey)).into(imageView);


        TextView t1 = (TextView) findViewById(R.id.title);
        t1.setText(mintent.getStringExtra(mMovieTitleKey));

        t1 = (TextView) findViewById(R.id.overview);
        t1.setText(mintent.getStringExtra(mMovieOverviewKey));
//rating is out of ten
        float rating = mintent.getFloatExtra(mMovieVoteCountKey, 0.0f);
        t1 = (TextView) findViewById(R.id.rating);
        t1.setText((rating/2) + "/5");

        t1 = (TextView) findViewById(R.id.release_date);
        t1.setText(mintent.getStringExtra(mMovieReleaseDateKey));

        RatingBar ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setRating(rating / 2);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void launchYoutube(View view){
        //Sometimes, the returned url can be null, due to a runtime runtime error
        String youtubeLink = mintent.getStringExtra(mMovieTrailerUrlKey);
        Log.e(LOG_TAG, "youtube link in detailsActivity : " + youtubeLink);
        if(youtubeLink != null){
            //TODO: launch youtube app with the link using mintent
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink)));

        }else {
            Toast.makeText(this, "Check network connection", Toast.LENGTH_SHORT).show();
        }

    }
}
