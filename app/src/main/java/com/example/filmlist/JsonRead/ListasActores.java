package com.example.filmlist.JsonRead;

import java.util.LinkedList;

public class ListasActores {


    public LinkedList<Actor> ListaActoresPeli;
    public LinkedList<Actor> ListaActorFav;

    public ListasActores(){
        ListaActoresPeli=new LinkedList<>();
        ListaActorFav=new LinkedList<>();

    }

    public LinkedList<Actor> getListaActoresPeli() {
        return ListaActoresPeli;
    }

    public void setListaActoresPeli(LinkedList<Actor> listaActoresPeli) {
        ListaActoresPeli = listaActoresPeli;
    }

    public LinkedList<Actor> getListaActorFav() {
        return ListaActorFav;
    }

    public void setListaActorFav(LinkedList<Actor> listaActorFav) {
        ListaActorFav = listaActorFav;
    }
}
