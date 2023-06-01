package com.example.filmlist.CONTROLADOR.Controladores;

import android.widget.Toast;

import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MainActivity;

import java.util.Iterator;

public class ControladorListas {

    MainActivity miActivity;
    Controlador controlador;

    public ControladorListas(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;


    }

     //--------------------CONTROLAR SI LA PELI ESTA EN LA LISTA-------------------------//



    public void controlaPeliListaVistas(Film f) {
        boolean miaubool = true;
        Iterator<Film> iterator = controlador.LISTAS.getListaFvistas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A VISTAS", Toast.LENGTH_SHORT).show();
            controlador.LISTAS.getListaFvistas().add(f);
        }
    }

    public void controlaPeliListaFavoritas(Film f) {
        boolean miaubool = true;
        Iterator<Film> iterator = controlador.LISTAS.getListaFfavoritas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A FAVORITAS", Toast.LENGTH_SHORT).show();
            controlador.LISTAS.getListaFfavoritas().add(f);
        }
    }

    public void controlaPeliListaPendientes(Film f) {

        boolean miaubool = true;
        Iterator<Film> iterator = controlador.LISTAS.getListaFpendientes().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A PENDIENTES", Toast.LENGTH_SHORT).show();
            controlador.LISTAS.getListaFpendientes().add(f);

        }
    }


    public void controlPelisVaolradas(Film f) {

        boolean miaubool = true;
        Iterator<Film> iterator = controlador.LISTAS.getListaFvaloradas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya la valoraste anteriormente", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            controlador.LISTAS.getListaFvaloradas().add(f);
        }
    }

    public void controlActoresFav(Actor actor) {
        boolean miaubool = true;
        Iterator<Actor> iterator = controlador.LISTASACTORES.getListaActorFav().iterator();
        while (iterator.hasNext()) {
            Actor Actori = iterator.next();
            if (Actori == null || Actori.getId().equals(actor.getId())) {
                Toast.makeText(miActivity, "Este actor ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDO A FAVORITOS", Toast.LENGTH_SHORT).show();
            controlador.controladorPeticiones.busquedaActor(actor.getId());
        }
    }


}
