package com.example.filmlist.RV_listaVistas;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.R;


public class RVViewHolder_LV extends RecyclerView.ViewHolder {

    public RVAdapter_LV mAdapter;

    public TextView titulo;
    public ImageView poster;
    public TextView valoracion;
    public ImageView posterVal;


    public RVViewHolder_LV(@NonNull View itemView, RVAdapter_LV myAdapter) {
        super(itemView);
        //this.titulo = itemView.findViewById(R.id.film_title);
        this.poster = itemView.findViewById(R.id.peliListasImg);
        this.mAdapter = myAdapter;
        this.posterVal=itemView.findViewById(R.id.peliValImg);
        this.valoracion=itemView.findViewById(R.id.valTV);
    }
}
