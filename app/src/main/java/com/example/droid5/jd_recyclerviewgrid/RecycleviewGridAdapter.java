package com.example.droid5.jd_recyclerviewgrid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by droid5 on 23/8/17.
 */

public class RecycleviewGridAdapter extends RecyclerView.Adapter<RecycleviewGridAdapter.ItemHolder>   {


    private List<Movie> movieList;
    private Context mcontext;
//    public RecycleviewGridAdapterListener listener;



    public static class ItemHolder extends RecyclerView.ViewHolder  {


        public ImageView photo;
        public TextView title, release_year, rating;


        public ItemHolder(View itemView) {

            super(itemView);
            photo = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.title);

            rating = itemView.findViewById(R.id.rating);
        }


    }

    public RecycleviewGridAdapter(Context context,List<Movie> List) {
        this.mcontext = context;

        this.movieList = List;
    }


    @Override
    public RecycleviewGridAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View ItemsInRecycler = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_items_layout, parent, false);
        return new ItemHolder(ItemsInRecycler);
    }

    @Override
    public void onBindViewHolder(RecycleviewGridAdapter.ItemHolder holder, final int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());

        holder.rating.setText("Rating "+movie.getRating());
        Glide.with(mcontext)
                .load(movie.getImageurl())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.photo);





//        holder.title.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onPosterClicked(position);
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

//    public interface RecycleviewGridAdapterListener{
//        void onPosterClicked(int position);
//    }


}
