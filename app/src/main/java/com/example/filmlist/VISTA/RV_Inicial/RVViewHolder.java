package com.example.filmlist.VISTA.RV_Inicial;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder extends RecyclerView.ViewHolder {

    public RVAdapter mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder(@NonNull View itemView, RVAdapter myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
