package com.example.filmlist.JsonRead;

import java.util.LinkedList;

public class ListasPropias {



    LinkedList<Film> listaFvistas;
    LinkedList<Film> listaFfavoritas;
    LinkedList<Film> listaFpendientes;


   public  ListasPropias(){
       listaFvistas=new LinkedList<>();
       listaFfavoritas=new LinkedList<>();
       listaFpendientes=new LinkedList<>(); 


   }

   public void addvistas(Film f){listaFvistas.add(f);}
    public void addfavoritas(Film f){listaFfavoritas.add(f);}
    public void addpendientes(Film f){listaFpendientes.add(f);}

    public LinkedList<Film> damelista(String opcion){
        switch (opcion){
            case "VISTAS":
                return this.listaFvistas;
            case "FAVORITAS":
                return this.listaFfavoritas;
            case "PENDIENTES":
                return this.listaFpendientes;



        }
        return null;
    }



    public LinkedList<Film> getListaFvistas() {
        return listaFvistas;
    }

    public LinkedList<Film> getListaFfavoritas() {
        return listaFfavoritas;
    }

    public LinkedList<Film> getListaFpendientes() {
        return listaFpendientes;
    }
}