package com.example.android.movienight;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private String mMoviesJsonString;
    private ImageAdapter imageAdapter;
    public static final String mMovieImageKey = "image";
    public static final String mMovieTitleKey = "title";
    public static final String mMovieOverviewKey = "overview";
    public static final String mMovieVoteCountKey = "vote count";
    public static final String mMovieReleaseDateKey = "release date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        final Intent intent = new Intent(this, DetailsActivity.class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                String movieDetails = null;
                try {
                    intent.putExtra(mMovieImageKey, getMovieImage(position));
                    intent.putExtra(mMovieTitleKey, getMovieTitle(position));
                    intent.putExtra(mMovieOverviewKey, getMovieOverview(position));
                    intent.putExtra(mMovieVoteCountKey, getMovieVoteCount(position));
                    intent.putExtra(mMovieReleaseDateKey, getMovieReleaseDate(position));

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "json exception in gridView listener");
                }
//                Toast.makeText(MainActivity.this, movieDetails,
//                        Toast.LENGTH_LONG).show();
//                intent.putExtra(movieImage, movieDetails);
//                intent.putExtra(movieTitle, movieDetails);
//                intent.putExtra(movieOverview, movieDetails);
//                intent.putExtra(movieVoteCount, movieDetails);
//                intent.putExtra(movieReleaseDate, movieDetails);
                startActivity(intent);
            }
        });
        Log.i(LOG_TAG, "onCreate()");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOG_TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMovies();
        Log.i(LOG_TAG, "onStart()");
    }

    private void updateMovies() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String preferredSortOrder = sharedPref.getString(getString(R.string.sortByPreferenceKey), getString(R.string.sortByPreferenceDefaultValue));
        new FetchMoviesTask().execute(preferredSortOrder);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_refresh) {
            updateMovies();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getMovieImage(int position) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        if (mMoviesJsonString != null) {


            final String TMD_LIST = "results";
            final String TMD_IMAGE = "poster_path";

            JSONObject moviesJson = new JSONObject(mMoviesJsonString);
            JSONArray results = moviesJson.getJSONArray(TMD_LIST);

            String baseUrl = "http://image.tmdb.org/t/p/w342";

            JSONObject movie = results.getJSONObject(position);
            String imageUrl = baseUrl + movie.getString(TMD_IMAGE);
            return imageUrl;

        }
        return null;
    }

    private double getMovieVoteCount(int position) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        if (mMoviesJsonString != null) {


            final String TMD_LIST = "results";
            final String TMD_VOTE = "vote_average";

            JSONObject moviesJson = new JSONObject(mMoviesJsonString);
            JSONArray results = moviesJson.getJSONArray(TMD_LIST);

            JSONObject movie = results.getJSONObject(position);
            double formattedVote = Math.round(movie.getDouble(TMD_VOTE) * 10) / 10.0;
            return formattedVote;

        }
        return 0;
    }

    private String getMovieTitle(int position) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        if (mMoviesJsonString != null) {


            final String TMD_LIST = "results";
            final String TMD_TITLE = "original_title";

            JSONObject moviesJson = new JSONObject(mMoviesJsonString);
            JSONArray results = moviesJson.getJSONArray(TMD_LIST);

            JSONObject movie = results.getJSONObject(position);
            String title = movie.getString(TMD_TITLE);
            return title;

        }
        return null;
    }

    private String getMovieOverview(int position) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        if (mMoviesJsonString != null) {


            final String TMD_LIST = "results";
            final String TMD_OVERVIEW = "overview";

            JSONObject moviesJson = new JSONObject(mMoviesJsonString);
            JSONArray results = moviesJson.getJSONArray(TMD_LIST);

            JSONObject movie = results.getJSONObject(position);
            String overview = movie.getString(TMD_OVERVIEW);
            return overview;

        }
        return null;
    }

    private String getMovieReleaseDate(int position) throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        if (mMoviesJsonString != null) {


            final String TMD_LIST = "results";
            final String TMD_RELEASE_DATE = "release_date";

            JSONObject moviesJson = new JSONObject(mMoviesJsonString);
            JSONArray results = moviesJson.getJSONArray(TMD_LIST);

            JSONObject movie = results.getJSONObject(position);
            String releaseDate = movie.getString(TMD_RELEASE_DATE);
            if (releaseDate.length() >= 4)
                return releaseDate.substring(0, 4);
            else
                return null;
        }
        return null;
    }

    //Fetching movies data off the main thread using asynctask

    public class FetchMoviesTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            // If there's no sort option, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            mMoviesJsonString = null;


            try {
                // Construct the URL for the OpenWeatherMap query
                // Possible parameters are avaiable at OWM's forecast API page, at
                // http://openweathermap.org/API#forecast
                final String MOVIES_BASE_URL =
                        "http://api.themoviedb.org/3/discover/movie?";
                final String SORT_BY_PARAM = "sort_by";
                final String APPID_PARAM = "api_key";

                Uri builtUri = Uri.parse(MOVIES_BASE_URL).buildUpon()
                        .appendQueryParameter(SORT_BY_PARAM, params[0])
                        .appendQueryParameter(APPID_PARAM, BuildConfig.THE_MOVIE_DB_API_KEY)
                        .build();

                URL url = new URL(builtUri.toString());

                Log.e(LOG_TAG, "Built URI " + builtUri.toString());

                // Create the request to TheMoviesDB, and open the connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                mMoviesJsonString = buffer.toString();

                //Log.v(LOG_TAG, "Movies string: " + mMoviesJsonString);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                setMoviesImage(mMoviesJsonString);
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(LOG_TAG, "unable to call setMoviesImage");
            }
        }

        private void setMoviesImage(String jsonStr) throws JSONException {
            // These are the names of the JSON objects that need to be extracted.
            if (jsonStr != null) {


                final String TMD_LIST = "results";
                final String TMD_IMAGE = "poster_path";

                JSONObject moviesJson = new JSONObject(jsonStr);
                JSONArray results = moviesJson.getJSONArray(TMD_LIST);

                String[] imageUrls = new String[results.length()];
                String baseUrl = "http://image.tmdb.org/t/p/w185";
                for (int i = 0; i < results.length(); ++i) {
                    JSONObject movie = results.getJSONObject(i);
                    imageUrls[i] = baseUrl + movie.getString(TMD_IMAGE);
                }
                imageAdapter.setThumbUrls(imageUrls);

            }
        }
    }


    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private String[] mThumbUrls;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public void setThumbUrls(String[] imageUrls) {
            mThumbUrls = imageUrls;
            notifyDataSetChanged();
        }

        public int getCount() {
            if (mThumbUrls == null)
                return 0;

            return mThumbUrls.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //imageView.setPadding(8, 8, 8, 8);
            } else {
                imageView = (ImageView) convertView;
            }

            //imageView.setImageResource(mThumbUrls[position]);
            Picasso.with(mContext).load(mThumbUrls[position]).into(imageView);
            return imageView;
        }


    }

}
