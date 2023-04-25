package com.example.filmlist.RV_Busqueda;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_B extends RecyclerView.ViewHolder {

    public RVAdapter_B mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder_B(@NonNull View itemView, RVAdapter_B myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
