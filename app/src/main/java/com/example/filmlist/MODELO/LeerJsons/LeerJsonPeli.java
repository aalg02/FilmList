package com.example.filmlist.MODELO.LeerJsons;

import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.StringManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LeerJsonPeli {

    //protected Controlador miControlador;
    Film film;

    StringManager stringManager=new StringManager();
    //ListaPelis listaP=new ListaPelis();
    public LeerJsonPeli(JsonElement json){
        try {
            JsonElement json1 = json;
            JsonObject infopeli = json1.getAsJsonObject();
            String id = infopeli.getAsJsonPrimitive(stringManager.id).getAsString();
            String tittle = infopeli.getAsJsonPrimitive(stringManager.original_title).getAsString();
            String description = infopeli.getAsJsonPrimitive(stringManager.overview).getAsString();
            String valoration = infopeli.getAsJsonPrimitive(stringManager.vote_average).getAsString();
            String img_path = infopeli.getAsJsonPrimitive(stringManager.poster_path).getAsString();
            String date = infopeli.getAsJsonPrimitive(stringManager.release_date).getAsString();

            this.film = new Film(id, tittle, description, valoration, img_path, date, 0);
            //listaP.getListaF().add(getPeli());
        }catch (Exception e){
            String id = " ";
            String tittle = " ";
            String description = "";
            String valoration = " ";
            String img_path = null;
            String date = "";
        }
    }

    public Film getPeli() {
        return this.film;
    }


}
