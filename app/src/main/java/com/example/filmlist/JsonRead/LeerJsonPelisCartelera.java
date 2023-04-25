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
    ListaPelis listaP=new ListaPelis();
    StringManager SM=new StringManager();

    public LeerJsonPelisCartelera(String json , int n){

        try {
            JsonElement pelisactuales = JsonParser.parseString(json);
            JsonObject pelisactualesO = pelisactuales.getAsJsonObject();
            JsonArray listapelis = pelisactualesO.get(SM.results).getAsJsonArray();
            for (JsonElement p : listapelis) {
                LJP = new LeerJsonPeli(p);

                if(n==1) {
                    listaP.getListaFCartelera().add(LJP.getPeli());
                }if(n==2){
                    listaP.getListaFBusqueda().add(LJP.getPeli());
                }if(n==3) {
                    listaP.getListaFpopulares().add(LJP.getPeli());
                }if(n==4){
                    listaP.getListaFestrenos().add(LJP.getPeli());
                }if(n==5) {
                    listaP.getListaFtoprated().add(LJP.getPeli());
                }
            }

        }catch (Exception e){
            Controlador.getInstance().NoConexion();
        }

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
        }

        return null;
    }



}
