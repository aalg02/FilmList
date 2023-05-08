package com.example.filmlist.JsonRead;

import java.util.LinkedList;

public class ListaPelis {
    LinkedList<Film> ListaFCartelera;
    LinkedList<Film> ListaFBusqueda;
    LinkedList<Film> ListaFpopulares;
    LinkedList<Film> ListaFestrenos;
    LinkedList<Film> ListaFtoprated;
    LinkedList<Film> ListaFrecomendaciones;

    LinkedList<Film> ListaFConsultadas;

    public ListaPelis(){
        ListaFCartelera=new LinkedList<>();
        ListaFBusqueda=new LinkedList<>();
        ListaFpopulares=new LinkedList<>();
        ListaFestrenos=new LinkedList<>();
        ListaFtoprated=new LinkedList<>();
        ListaFrecomendaciones=new LinkedList<>();
        ListaFConsultadas=new LinkedList<>();
    }



    public LinkedList<Film> damelista(String opcion){
        switch (opcion){
            case "INICIAL":
                return this.ListaFCartelera;
            case "BUSQUEDA":
                return this.ListaFBusqueda;
            case "POPULARES":
                return this.ListaFpopulares;

            case "TOPRATED":
                return this.ListaFtoprated;

            case "ESTRENOS":
                return this.ListaFestrenos;
            case "RECOMENDACIONES":
                return this.ListaFrecomendaciones;

        }
        return null;
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

    public LinkedList<Film> getListaFrecomendaciones() {
        return ListaFrecomendaciones;
    }

public LinkedList<Film> getListaFConsultadas() {
        return ListaFConsultadas;
    }

    public Film getListaFI(int id) {
        return ListaFCartelera.get(id);
    }
}
