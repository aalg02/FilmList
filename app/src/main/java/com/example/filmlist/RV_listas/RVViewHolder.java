package com.example.filmlist.RV_listas;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder extends RecyclerView.ViewHolder {

    public RVAdapter_listas mAdapter;

    public ImageView poster;


    public RVViewHolder(@NonNull View itemView, RVAdapter_listas myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
