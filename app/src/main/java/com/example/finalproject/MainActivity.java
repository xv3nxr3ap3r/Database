package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, EditAnime.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initListButton();


        AnimeDataSource ds = new AnimeDataSource(this);
        ArrayList<Anime> anime;
        try {


            ds.open();
            anime = ds.getAnime();
            ds.close();
            SharedPreferences sharedPref = getSharedPreferences("MyAnimeListPreferences", Context.MODE_PRIVATE);
            RecyclerView animeList = findViewById(R.id.rvAnime);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            animeList.setLayoutManager(layoutManager);
            AnimeAdapter animeAdapter = new AnimeAdapter(anime);
            animeAdapter.setOnClickListener(onClickListener);
            animeList.setAdapter(animeAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }


    private void initListButton(){
        Button listButton = findViewById(R.id.buttonEditAnime);
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditAnime.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

}


