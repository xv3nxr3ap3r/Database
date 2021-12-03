package com.example.finalproject;

import android.content.Context;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimeDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = " myanime.db"; //change my contacts
    private static int DATABASE_VERSION = 1;

    public AnimeDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCommand = "create table anime (_id integer primary key autoincrement, " //edit this down to , look down ) to store whatever i want
                + "Animename text not null, "
                + "genre text, "
                + "author text); ";

//here
        sqLiteDatabase.execSQL(sqlCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS anime");
        onCreate(sqLiteDatabase);
    }
}