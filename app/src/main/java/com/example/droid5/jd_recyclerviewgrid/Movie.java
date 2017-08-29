package com.example.droid5.jd_recyclerviewgrid;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by droid5 on 23/8/17.
 */

public class Movie implements Parcelable{

   private String title,rating,releaseyear,imageurl;

    public Movie(String title, String rating, String releaseyear, String imageurl) {
        this.title = title;
        this.rating = rating;
        this.releaseyear = releaseyear;
        this.imageurl = imageurl;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        rating = in.readString();
        releaseyear = in.readString();
        imageurl = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() { Movie tempItem;

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(String releaseyear) {
        this.releaseyear = releaseyear;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(rating);
        parcel.writeString(releaseyear);
        parcel.writeString(imageurl);
    }
}
