package com.example.filmlist.JsonRead;

import com.example.filmlist.StringManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class LeerJsonGenerosPelis {

    ArrayList<Genre> ListaGeneros=new ArrayList<Genre>();
    StringManager SM=new StringManager();
    public  LeerJsonGenerosPelis(JsonElement generos){
         JsonObject objeto= generos.getAsJsonObject();
         JsonArray generosA= objeto.get(SM.genres).getAsJsonArray();
         for(JsonElement JE:generosA){
             JsonObject JEO= JE.getAsJsonObject();
             String Id=JEO.getAsJsonPrimitive(SM.id).getAsString();
             String nombrefilm=JEO.getAsJsonPrimitive(SM.name).getAsString();
             ListaGeneros.add(new Genre(Id , nombrefilm));
         }
    }


    public ArrayList<Genre> getListaGeneros() {
        return ListaGeneros;
    }
}
