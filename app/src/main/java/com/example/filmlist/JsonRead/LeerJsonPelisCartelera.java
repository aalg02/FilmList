package com.example.filmlist.JsonRead;

import com.example.filmlist.Controlador;
import com.example.filmlist.StringManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.LinkedList;

public class LeerJsonPelisCartelera {
    LeerJsonPeli LJP;
    ListaPelis listaP;
    StringManager SM=new StringManager();

    public LeerJsonPelisCartelera(String json , int n){

        try {
            JsonElement pelisactuales = JsonParser.parseString(json);
            JsonObject pelisactualesO = pelisactuales.getAsJsonObject();
            JsonArray listapelis = pelisactualesO.get(SM.results).getAsJsonArray();


            for (JsonElement p : listapelis) {
                LJP = new LeerJsonPeli(p);

                if(n==1) {

                    Controlador.getInstance().LISTASINICIAL.getListaFCartelera().add(LJP.getPeli());
                }if(n==2){
                    Controlador.getInstance().LISTASINICIAL.getListaFBusqueda().add(LJP.getPeli());
                }if(n==3) {
                    Controlador.getInstance().LISTASINICIAL.getListaFpopulares().add(LJP.getPeli());
                }if(n==4){
                    Controlador.getInstance().LISTASINICIAL.getListaFtoprated().add(LJP.getPeli());
                }if(n==5) {
                    Controlador.getInstance().LISTASINICIAL.getListaFestrenos().add(LJP.getPeli());
                }if(n==6) {
                    Controlador.getInstance().LISTASINICIAL.getListaFrecomendaciones().add(LJP.getPeli());
                }
            }


        }catch (Exception e){
            Controlador.getInstance().NoConexion();
        }

    }

    public ListaPelis getListaPmiau() {
        return listaP;
    }

    public LinkedList<Film> getListaP(int n) {
        if(n==1) {
            return listaP.ListaFCartelera;
        }if(n==2){
            return listaP.ListaFBusqueda;
        }if(n==3) {
            return listaP.ListaFpopulares;
        }if(n==4){
            return listaP.ListaFestrenos;
        }if(n==5){
            return listaP.ListaFtoprated;
        }if(n==6){
            return listaP.ListaFrecomendaciones;
        }

        return null;
    }



}
