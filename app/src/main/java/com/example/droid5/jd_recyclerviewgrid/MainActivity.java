package com.example.droid5.jd_recyclerviewgrid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private RecyclerView recyclerView;
    private RecycleviewGridAdapter RecycleviewGridAdapter;
    private GridLayoutManager gridLayoutManager;

    private JsonArrayRequest jsonArrayRequest;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();


        recyclerView = findViewById(R.id.mainrecyclerview);
        RecycleviewGridAdapter = new RecycleviewGridAdapter(this,movieList);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(RecycleviewGridAdapter);


        // click lister
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
                Log.d(TAG, "onClick: hey i am pressed, the title is  "+ movie.getTitle());
                Intent intent = new Intent(MainActivity.this, MovieDescriptionActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
                finish();

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareList();


    }

    private void prepareList() {
        String url = "https://api.androidhive.info/json/movies.json";


        jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject movieObj = response.getJSONObject(i);
                        String title = movieObj.getString("title");
                        String imageUrl = movieObj.getString("image");
                        imageUrl.replace("http", "https");
                        String rating = movieObj.getString("rating");
                        String releaseYear = movieObj.getString("releaseYear");


                        Movie tempItem = new Movie(title, rating, releaseYear, imageUrl);
                        movieList.add(i, tempItem);
                        //RecycleviewGridAdapter.notifyItemInserted(i);

                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                    RecycleviewGridAdapter.notifyDataSetChanged();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: error");

            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);


    }
}


