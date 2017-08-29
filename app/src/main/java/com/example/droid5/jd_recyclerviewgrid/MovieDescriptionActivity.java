package com.example.droid5.jd_recyclerviewgrid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MovieDescriptionActivity extends AppCompatActivity {

    private Movie movie;

    private TextView title, rating, releaseYear;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);
        movie = getIntent().getParcelableExtra("movie");
        title = findViewById(R.id.title);
        rating = findViewById(R.id.rating);
        imageView = findViewById(R.id.imageofMovie);



        if (movie == null) {
            finish();
            return;
        }

        Toast.makeText(getApplicationContext(), "Movie selected: " + movie.getTitle(), Toast.LENGTH_SHORT).show();


        movie = getIntent().getParcelableExtra("movie");

        Log.d("noting", "onCreate: title is "+ movie.getTitle());

        title.setText(movie.getTitle());
        rating.setText("Rating "+ movie.getRating());
        Glide.with(this).load(movie.getImageurl()).into(imageView);
//        releaseYear.setText( movie.getRating());
    }
}
