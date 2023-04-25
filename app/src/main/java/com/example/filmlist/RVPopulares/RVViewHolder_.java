package com.example.filmlist.RVPopulares;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_ extends RecyclerView.ViewHolder {

    public RVAdapter_P mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder_(@NonNull View itemView, RVAdapter_P myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
