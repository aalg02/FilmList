package com.example.filmlist.JsonRead;

import java.util.LinkedList;

public class ListaPelis {
    LinkedList<Film> ListaFCartelera;
    LinkedList<Film> ListaFBusqueda;
    LinkedList<Film> ListaFpopulares;
    LinkedList<Film> ListaFestrenos;
    LinkedList<Film> ListaFtoprated;


    public ListaPelis(){
        ListaFCartelera=new LinkedList<>();
        ListaFBusqueda=new LinkedList<>();
        ListaFpopulares=new LinkedList<>();
        ListaFestrenos=new LinkedList<>();
        ListaFtoprated=new LinkedList<>();
    }

    public LinkedList<Film> getListaFCartelera() {
        return this.ListaFCartelera;
    }
    public LinkedList<Film> getListaFBusqueda() {
        return this.ListaFBusqueda;
    }

    public LinkedList<Film> getListaFpopulares() {return this.ListaFpopulares;}

    public LinkedList<Film> getListaFestrenos() {
        return this.ListaFestrenos;
    }

    public LinkedList<Film> getListaFtoprated() {
        return ListaFtoprated;
    }

    public Film getListaFI(int id) {
        return ListaFCartelera.get(id);
    }
}
