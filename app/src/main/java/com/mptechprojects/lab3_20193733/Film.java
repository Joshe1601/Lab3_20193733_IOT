package com.mptechprojects.lab3_20193733;

import android.net.Uri;

import java.util.List;

public class Film {

    private String Title;
    private String Director;
    private String Actors;
    private String Released;
    private String Genre;

    private String Writer;

    private String Plot;

    private List<Ratings> Ratings;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        this.Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        this.Actors = actors;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        this.Released = released;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        this.Writer = writer;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        this.Plot = plot;
    }

    public List<Ratings> getRatings() {
        return Ratings;
    }

    public void setRatings(List<Ratings> ratings) {
        this.Ratings = ratings;
    }
}
