package com.example.filmlist.RV_Inicial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;

import java.util.LinkedList;

public class RVAdapter extends RecyclerView.Adapter<RVViewHolder> {

    private final LinkedList<Film> informe;
    private final LayoutInflater mInflater;
    public StringManager SM;
    Context context;
    String opcion;

    public RVAdapter(Context context, LinkedList<Film> informe, String opcion) {
        this.informe=informe;
        this.context=context;
        this.opcion=opcion;
        SM=new StringManager();

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate( R.layout.peliinfo_layout, parent, false);
       // View mItemView2 = mInflater.inflate(R.layout.generos_layout, parent, false);

        return new RVViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {
        //holder.titulo.setText(informe.getListaP().get(position).getName());

            Controlador.getInstance().CliclckPelisola(holder.poster,informe.get(position));

            Glide.with(context).load(informe.get(position).getImg_path()).into(holder.poster);


    }

    @Override
    public int getItemCount() {
        return informe.size();
    }

}