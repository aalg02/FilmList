package com.example.filmlist.RV_Estrenos;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_E extends RecyclerView.ViewHolder {

    public RVAdapter_E mAdapter;

    public TextView titulo;
    public ImageView poster;


    public RVViewHolder_E(@NonNull View itemView, RVAdapter_E myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.Poster);
        this.mAdapter = myAdapter;
    }
}
