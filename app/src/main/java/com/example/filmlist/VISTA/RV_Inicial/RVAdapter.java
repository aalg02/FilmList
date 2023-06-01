package com.example.filmlist.VISTA.RV_Inicial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;

import java.util.LinkedList;

public class RVAdapter extends RecyclerView.Adapter<RVViewHolder> {

    private final LinkedList<Film> informe;
    private final LayoutInflater mInflater;
    public StringManager SM;
    Context context;
    String opcion;
    Controlador controlador;

    public RVAdapter(Context context,Controlador controlador, LinkedList<Film> informe, String opcion) {
        this.informe=informe;
        this.context=context;
        this.opcion=opcion;
        SM=new StringManager();
        this.controlador=controlador;
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
            if(informe.get(position)==null||informe.get(position).getImg_path()==null){
                //informe.remove(position);
                if(opcion.equals("INICIAL")){
                    controlador.LISTASINICIAL.getListaFCartelera().remove(position);
                    }
                if(opcion.equals("BUSQUEDA")){
                    controlador.LISTASINICIAL.getListaFBusqueda().remove(position);
                    }

                if(opcion.equals( "POPULARES")){
                    controlador.LISTASINICIAL.getListaFpopulares().remove(position);
                   }

                if(opcion.equals("TOPRATED")){
                    controlador.LISTASINICIAL.getListaFtoprated().remove(position);
                  }

                if(opcion.equals("ESTRENOS")){
                    controlador.LISTASINICIAL.getListaFestrenos().remove(position);

                } if(opcion.equals("RECOMENDACIONES")){

                    controlador.LISTASINICIAL.getListaFrecomendaciones().remove(position);
                }if(opcion.equals("GENERO")){

                    controlador.LISTASINICIAL.getListaFGenero().remove(position);
                }if(opcion.equals("PELISACTORES")){
                   controlador.LISTASINICIAL.getListaFActores().remove(position);

                }if(opcion.equals("ACTORESFAV")){
                    controlador.LISTASINICIAL.getListaFActores().remove(position);
                }
            }else{
                controlador.controladorPeliculas.CliclckPelisola(holder.poster, informe.get(position));
                setAnimation(holder.poster, position);
                Glide.with(context).load(informe.get(position).getImg_path()).into(holder.poster);

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
        return informe.size();
    }

}