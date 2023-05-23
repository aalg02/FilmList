package com.example.filmlist.JsonRead;

import com.example.filmlist.Controlador;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class leerJsongaleriafotos {

    ArrayList<String> maiu=new ArrayList<>();
    public leerJsongaleriafotos(JsonElement generos) {
        JsonObject objeto = generos.getAsJsonObject();
        JsonArray generosA = objeto.get("backdrops").getAsJsonArray();
        for (JsonElement JE : generosA) {
            JsonObject JEO = JE.getAsJsonObject();
            String file_path = JEO.getAsJsonPrimitive("file_path").getAsString();
          maiu.add(file_path);

        }

        Controlador.getInstance().gestorvistas.dialogGaleria(maiu);


    }

    public ArrayList<String> getMaiu() {
        return maiu;
    }
}