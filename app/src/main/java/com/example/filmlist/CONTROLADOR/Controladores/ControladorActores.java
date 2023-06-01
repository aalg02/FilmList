package com.example.filmlist.CONTROLADOR.Controladores;

import android.view.View;
import android.widget.ScrollView;

import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

public class ControladorActores {

    MainActivity miActivity;
    Controlador controlador;

    public ControladorActores(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;


    }

    //---------------------------ELIMINAR ACTORES---------------------------//



    public void eliminaractor(int n) {
        controlador.usuario.getListaActores().remove(controlador.LISTASACTORES.ListaActorFav.get(n).getId());
        controlador.LISTASACTORES.getListaActorFav().remove(n);
        controlador.controladorFirebase.firebaseDatabasesetdatos();
    }



    //------------------------CLIC Y MANTENER ACTORES-----------------//

    public void ClickActor(View recycler, Actor actor, String opcion) {
        ScrollView scrollview = miActivity.findViewById(R.id.scrollInfoActor);

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.cuandoClicasActor(recycler,actor,opcion);
            }
        });
    }

    public void MantenerActor(View recycler, Actor actor, int n) {
        recycler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                controlador.cuandoMantienesActor(recycler,actor,n);
                return true; // Retorna true para indicar que se ha manejado el evento correctamente
            }
        });
    }

}
