package com.example.filmlist.JsonRead;

import android.widget.Toast;

import com.example.filmlist.Controlador;
import com.example.filmlist.StringManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class LeerInfoCastPeli {


    StringManager stringManager = new StringManager();
    LeerJsonActor LJA;

    public LeerInfoCastPeli(String Json) {
        try {
            JsonElement elementopelicast = JsonParser.parseString(Json);
            JsonObject objetopelicast = elementopelicast.getAsJsonObject();
            JsonArray listaActores = objetopelicast.get(stringManager.cast).getAsJsonArray();


            for (JsonElement p : listaActores) {
                LJA = new LeerJsonActor(p);
                Controlador.getInstance().LISTASACTORES.getListaActoresPeli().add(LJA.getActor());
            }
        } catch (Exception e) {

        }
    }

}
