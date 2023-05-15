package com.example.filmlist.RV_Actores;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_A extends RecyclerView.ViewHolder {

    public RVAdapter_A mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder_A(@NonNull View itemView, RVAdapter_A myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.fotoActor);
        this.mAdapter = myAdapter;
    }
}
