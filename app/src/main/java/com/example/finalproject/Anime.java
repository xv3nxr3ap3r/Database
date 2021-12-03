package com.example.finalproject;

public class Anime {

    private int AnimeID;
    private String anime;
    private String author;
    private String genre;

    public Anime() {
        AnimeID = -1; //new Anime
    }

    public int getAnimeID() {
        return AnimeID;
    }

    public void setAnimeID(int AnimeID) {
        this.AnimeID = AnimeID;
    }

    public String getAnime(){ return anime;}
    public void setAnime(String anime){this.anime = anime;}

    public String getAuthor(){return author;}
    public void setAuthor(String author){this.author = author;}

    public String getGenre(){return genre;}
    public void setGenre(String genre){this.genre = genre;}

}