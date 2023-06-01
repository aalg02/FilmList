package com.example.filmlist.VISTA.GestionVistas;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class gestorLayoutInfoActor {


    public FrameLayout FMI;
    MainActivity mainActivity;
    gestorVistasGeneral gestorVistasGeneral;
    Controlador controlador;

    public gestorLayoutInfoActor(MainActivity mainActivity, Controlador controlador, gestorVistasGeneral gestorVistasGeneral) {
        this.mainActivity = mainActivity;
        this.gestorVistasGeneral = gestorVistasGeneral;
        this.controlador = controlador;
    }


    public void listenerActorinfo(Actor actor) {


        ImageView flecha = mainActivity.findViewById(R.id.flechatras2);
        ImageView flecha2 = mainActivity.findViewById(R.id.flechatras3);
        FloatingActionButton botonFavActor = mainActivity.findViewById(R.id.floatingActionButtonActoresFav);
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutActor(0);

            }
        });
        flecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutActorFav(0);

            }
        });

        botonFavActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.controladorListas.controlActoresFav(actor);
                controlador.RefrescaActoresFav();
                controlador.controladorFirebase.firebaseDatabasesetdatos();
            }
        });


    }


    public void framelayoutActor(int n) {
        FMI = mainActivity.findViewById(R.id.framelayoutActor);

        if (n == 0) {
            FMI.setVisibility(View.INVISIBLE);

        }


        if (n == 1) {
            FMI.setVisibility(View.VISIBLE);


        }

    }

    public void framelayoutActorFav(int n) {
        FMI = mainActivity.findViewById(R.id.framelayoutActorfav);

        if (n == 0) {
            FMI.setVisibility(View.INVISIBLE);

        }


        if (n == 1) {
            FMI.setVisibility(View.VISIBLE);


        }
    }


    public void cargaInfoActor(Actor actor) {

        TextView titulo = mainActivity.findViewById(R.id.NombreActor);
        TextView valoracion = mainActivity.findViewById(R.id.Rol);
        ImageView posterinfo = mainActivity.findViewById(R.id.FotoActor);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);


        titulo.setText(actor.getNombre());
        valoracion.setText(actor.getRol());
        posterinfo.startAnimation(animation);
        Glide.with(mainActivity).load(actor.getProfile_path()).into(posterinfo);


    }

    public void cargaInfoActorFav(Actor actor) {

        TextView titulo = mainActivity.findViewById(R.id.NombreActorFav);
        TextView genero = mainActivity.findViewById(R.id.generoActorFav);
        TextView biografia = mainActivity.findViewById(R.id.biografiaActorFav);
        ImageView posterinfo = mainActivity.findViewById(R.id.FotoActorFav);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);


        titulo.setText(actor.getNombre());
        genero.setText(actor.getCumpleaños());
        biografia.setText(actor.getBiografia());
        posterinfo.startAnimation(animation);
        Glide.with(mainActivity).load(actor.getProfile_path()).into(posterinfo);


    }
}
