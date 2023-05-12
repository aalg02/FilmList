package com.example.filmlist.RV_listaVistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVAdapter_LV extends RecyclerView.Adapter<RVViewHolder_LV> {

    private final LinkedList<Film> vistas;
    private final LayoutInflater mInflater;
    Context context;
    String opcion;

    public RVAdapter_LV(Context context, LinkedList<Film> vistas,String opcion) {
        this.vistas=vistas;
        this.context=context;
        this.opcion=opcion;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RVViewHolder_LV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView;
        if(opcion=="VALORACIONES"){
             mItemView = mInflater.inflate( R.layout.layoutpelisvaloradas, parent, false);
        }else {
             mItemView = mInflater.inflate(R.layout.peliculaslistas, parent, false);
        }
        return new RVViewHolder_LV(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder_LV holder, int position) {
        //holder.titulo.setText(informe.getListaP().get(position).getName());

        if(opcion=="VALORACIONES"){
            setAnimation(holder.posterVal,position);
            Glide.with(context).load(vistas.get(position).getImg_path()).into(holder.posterVal);
            holder.valoracion.setText(""+Controlador.getInstance(). rellenaValoraciones(position));
            Controlador.getInstance().CliclckPelisola(holder.posterVal,vistas.get(position));
            Controlador.getInstance().MantenerPelicula(holder.posterVal, opcion, position,vistas.get(position));

        }else {
            setAnimation(holder.poster,position);
            Controlador.getInstance().CliclckPelisola(holder.poster,vistas.get(position));
            Controlador.getInstance().MantenerPelicula(holder.poster, opcion, position,vistas.get(position));
            Glide.with(context).load(vistas.get(position).getImg_path()).into(holder.poster);
        }


    }
    private void setAnimation(View viewToAnimate, int position) {
        int lastPosition=position-1;
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.cargapelis);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return vistas.size();
    }

}