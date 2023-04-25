package com.example.filmlist.RV_Estrenos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.R;

public class RVAdapter_E extends RecyclerView.Adapter<RVViewHolder_E> {

    private final LeerJsonPelisCartelera informe;
    private final LayoutInflater mInflater;
    Context context;

    public RVAdapter_E(Context context, LeerJsonPelisCartelera informe) {
        this.informe=informe;
        this.context=context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RVViewHolder_E onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate( R.layout.peliinfo_layout, parent, false);
       // View mItemView2 = mInflater.inflate(R.layout.generos_layout, parent, false);

        return new RVViewHolder_E(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder_E holder, int position) {
        //holder.titulo.setText(informe.getListaP().get(position).getName());
        Glide.with(context).load(informe.getListaP(4).get(position).getImg_path()).into(holder.poster);
        Controlador.getInstance().clicPeli(holder.poster,position,4);


    }

    @Override
    public int getItemCount() {
        return informe.getListaP(4).size();
    }

}