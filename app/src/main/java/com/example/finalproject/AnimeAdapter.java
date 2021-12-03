package com.example.finalproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AnimeAdapter extends RecyclerView.Adapter{
    private ArrayList<Anime> AnimeData;
    private View.OnClickListener onClickListener;

    public class AnimeViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewAnime;
        public TextView textViewGenre; // change
        public TextView textViewAuthor;
        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAnime = itemView.findViewById(R.id.textViewAnime);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }
        public TextView getTextViewAnime(){
            return textViewAnime;
        }

        public TextView getTextViewGenre(){ return textViewGenre; }

        public TextView getTextViewAuthor(){return textViewAuthor;}


    }

    public void setOnClickListener(View.OnClickListener listener){
        onClickListener = listener;
    }
    public AnimeAdapter(ArrayList<Anime>arrayList){
        AnimeData = arrayList;
    }
    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_item_view, parent, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        AnimeViewHolder cvh = (AnimeViewHolder) holder;
        cvh.getTextViewAnime().setText(AnimeData.get(position).getAnime()); //change get
        cvh.getTextViewGenre().setText(AnimeData.get(position).getGenre());
        cvh.getTextViewAuthor().setText(AnimeData.get(position).getAuthor());
    }

    @Override
    public int getItemCount() { return AnimeData.size();
    }
}
