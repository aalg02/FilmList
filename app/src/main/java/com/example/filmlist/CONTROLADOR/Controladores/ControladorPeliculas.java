package com.example.filmlist.CONTROLADOR.Controladores;

import android.view.View;

import com.example.filmlist.MODELO.LeerJsons.LeerJsonPelisCartelera;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MainActivity;
import com.example.filmlist.StringManager;
import com.example.filmlist.VISTA.RV_Inicial.RVunion;

public class ControladorPeliculas {

    MainActivity miActivity;
    Controlador controlador;
    StringManager stringManager;
    LeerJsonPelisCartelera LJPC;

    public ControladorPeliculas(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;
        stringManager = new StringManager();

    }



    //----------------------FUNCIONALIDADES PARA LAS PELICULAS------------------------------------------//



    //---------------ELIMINARLAS-------------------//
    public void eliminarpeli(String opcion, int n) {
        if (opcion == stringManager.VISTAS) {
            controlador.LISTAS.getListaFvistas().remove(n);
            controlador.controladorFirebase.firebaseDatabasesetdatos();
        }
        if (opcion == stringManager.FAVORITAS) {
            controlador.LISTAS.getListaFfavoritas().remove(n);
            controlador.controladorFirebase.firebaseDatabasesetdatos();
        }
        if (opcion == stringManager.PENDIENTES) {
            controlador.LISTAS.getListaFpendientes().remove(n);
            controlador.controladorFirebase.firebaseDatabasesetdatos();

        }
        if (opcion == stringManager.VALORACIONES) {
            controlador.usuario.getValoraciones().remove(controlador.LISTAS.getListaFvaloradas().get(n).getId());
            controlador.LISTAS.getListaFvaloradas().remove(n);
            controlador.controladorFirebase.firebaseDatabasesetdatos();

        }

    }


   //--------------------------CLICKS Y MANTENER LA PELI----------------------------//
    //----------------------------CLICK EN LA PELICULA---------------------//


    public void CliclckPelisola(View recycler, Film f) {


        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 controlador.cuandoClicasPeli(recycler,f);
            }
        });
    }


    public void MantenerPelicula(View recycler, String opcion, int n, Film f) {
        recycler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

               controlador.cuandoMantienesPeli(opcion,n,f);

                return true; // Retorna true para indicar que se ha manejado el evento correctamente
            }
        });
    }

    //--------------------------LEER JSONS DE LAS DISTINTAS PELIS-------------------------------//


    public void LeerPeliculasCartelera(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 1);
        controlador.LISTASINICIAL.setListaFCartelera(LJPC.getListaPeli());
    }

    public void LeerPeliculasBusqueda(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 2);
        controlador.LISTASINICIAL.setListaFGenero(LJPC.getListaPeli());
    }

    public void LeerPeliculaspopulares(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 3);
        controlador.LISTASINICIAL.setListaFpopulares(LJPC.getListaPeli());

    }

    public void LeerPeliculasestrenos(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 4);
        controlador.LISTASINICIAL.setListaFtoprated(LJPC.getListaPeli());
    }

    public void LeerPeliculasToprated(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 5);
        controlador.LISTASINICIAL.setListaFestrenos(LJPC.getListaPeli());

    }

    public void LeerPeliculasRecomendaciones(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 6);
        controlador.LISTASINICIAL.setListaFrecomendaciones(LJPC.getListaPeli());
        new RVunion(miActivity, controlador,controlador.LISTASINICIAL.getListaFrecomendaciones(), stringManager.RECOMENDACIONES);

    }

    public void LeerPeligenero(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 7);
        controlador.LISTASINICIAL.setListaFGenero(LJPC.getListaPeli());
        new RVunion(miActivity,controlador,controlador.LISTASINICIAL.getListaFGenero(), stringManager.GENERO);

    }

    public void LeerPeliculasActor(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 8);
        controlador.LISTASINICIAL.setListaFActores(LJPC.getListaPeli());
        new RVunion(miActivity,controlador, controlador.LISTASINICIAL.getListaFActores(), stringManager.PELISACTORES);

    }

    public void LeerPeliculasActorFav(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 8);
        controlador.LISTASINICIAL.setListaFActores(LJPC.getListaPeli());
        new RVunion(miActivity,controlador, controlador.LISTASINICIAL.getListaFActores(), stringManager.ACTORESFAV);

    }

}
