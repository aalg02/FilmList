package com.example.filmlist.RV_Actores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Actor;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;

import java.util.LinkedList;

public class RVAdapter_A extends RecyclerView.Adapter<RVViewHolder_A> {

    private final LinkedList<Actor> informe;
    private final LayoutInflater mInflater;
    public StringManager SM;
    Context context;
    String opcion;

    public RVAdapter_A(Context context, LinkedList<Actor> informe, String opcion) {
        this.informe=informe;
        this.context=context;
        this.opcion=opcion;
        SM=new StringManager();

        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RVViewHolder_A onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate( R.layout.rv_actores, parent, false);
       // View mItemView2 = mInflater.inflate(R.layout.generos_layout, parent, false);

        return new RVViewHolder_A(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder_A holder, int position) {
        //holder.titulo.setText(informe.getListaP().get(position).getName());
            setAnimation(holder.poster,position);
            Glide.with(context).load(informe.get(position).getProfile_path()).into(holder.poster);
            if(opcion=="ACTORESFAV"){
                Controlador.getInstance().ClickActor(holder.poster,informe.get(position));
                Controlador.getInstance().MantenerActor(holder.poster,informe.get(position),position);
            }else{
                Controlador.getInstance().ClickActor(holder.poster,informe.get(position));
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