package com.example.filmlist.MODELO.LeerJsons;

import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.StringManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;

public class LeerInfoCastPeli {


    StringManager stringManager = new StringManager();
    LeerJsonActor LJA;
    LinkedList<Actor> listaActore=new LinkedList<>();

    public LeerInfoCastPeli(String Json) {
        try {
            JsonElement elementopelicast = JsonParser.parseString(Json);
            JsonObject objetopelicast = elementopelicast.getAsJsonObject();
            JsonArray listaActores = objetopelicast.get(stringManager.cast).getAsJsonArray();


            for (JsonElement p : listaActores) {
                LJA = new LeerJsonActor(p,stringManager.ACTORES);
                listaActore.add(LJA.getActor());
            }
        } catch (Exception e) {

        }
    }

    public LinkedList<Actor> getListaActore() {
        return listaActore;
    }
}
