package com.example.finalproject;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AnimeDataSource {

    private SQLiteDatabase database;
    private AnimeDBHelper dbHelper;

    public AnimeDataSource(Context context){
        dbHelper = new AnimeDBHelper(context);
    }
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();

    }

    public void close(){
        dbHelper.close();
    }
    public boolean updateAnime(Anime a){ //might need this
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("Animename", a.getAnime());
            values.put("Genre", a.getGenre());
            values.put("Author", a.getAuthor());
            Long id = (long)a.getAnimeID();
            didSucceed = database.update("anime", values, "_id="+id, null) > 0;
        }catch(Exception e){

        }
        return didSucceed;
    }

    public boolean insertAnime(Anime a){ //need this but dont need it all just was relevant
        boolean didSucceed = false;
        try {
            ContentValues values = new ContentValues();
            values.put("Animename", a.getAnime());
            values.put("author", a.getAuthor());
            values.put("genre", a.getGenre());
            didSucceed = database.insert("anime", null,values) > 0;
        }catch(Exception e){

        }
        return didSucceed;
    }

    public int getLastAnimeID(){
        int lastID = -1;
        try{
            String query = "Select MAX(_id) from anime";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            lastID = cursor.getInt(0);
            cursor.close();
        }catch (Exception e){

        }
        return lastID;
    }
    public ArrayList<String> getAnimeNames(){
        ArrayList<String> anime = new ArrayList<String>();
        try{
            String query = "Select contactname from contact";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                anime.add(cursor.getString(0));
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){

        }
        return anime;
    }
    public ArrayList<Anime> getAnime(){   //might need this
        ArrayList<Anime> anime = new ArrayList<Anime>();
        try{
            String query = "Select * from anime";
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Anime a = new Anime();
                a.setAnime(cursor.getString(1));
                a.setAuthor(cursor.getString(2));
                a.setGenre((cursor.getString(3)));
                anime.add(a);
                cursor.moveToNext();
            }
            cursor.close();
        }catch (Exception e){

        }
        return anime;
    }
    public Anime getAnime(int id){
        Anime a = new Anime();
        try{
            String query = "Select * from anime where _id="+id;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            a.setAnimeID(id);
            a.setAnime(cursor.getString(1));
            a.setAuthor(cursor.getString(2));
            a.setGenre((cursor.getString(3)));
            cursor.close();
        }catch (Exception e){

        }
        return a;
    }
}
