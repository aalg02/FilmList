package com.example.filmlist.RV_Toprated;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_TR extends RecyclerView.ViewHolder {

    public RVAdapter_TR mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder_TR(@NonNull View itemView, RVAdapter_TR myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
