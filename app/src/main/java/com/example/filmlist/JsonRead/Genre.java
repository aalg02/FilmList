package com.example.filmlist.JsonRead;

public class Genre {

    String GenreId;
    String genrename;


    public Genre(String GenreId , String genrename){
        this.GenreId=GenreId;
        this.genrename=genrename;
    }

    public String getGenreId() {
        return GenreId;
    }

    public String getGenrename() {
        return genrename;
    }
}
