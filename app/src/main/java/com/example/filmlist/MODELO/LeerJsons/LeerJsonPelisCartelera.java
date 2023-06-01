package com.example.filmlist.MODELO.LeerJsons;

import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MODELO.Listas.ListaPelis;
import com.example.filmlist.StringManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.LinkedList;

public class LeerJsonPelisCartelera {
    LeerJsonPeli LJP;
    ListaPelis listaP;
    StringManager SM=new StringManager();
    LinkedList<Film> listaPeli=new LinkedList<>();

    public LeerJsonPelisCartelera(String json , int n){




                    JsonElement pelisactuales = JsonParser.parseString(json);
                    JsonObject pelisactualesO = pelisactuales.getAsJsonObject();
                    JsonArray listapelis=null;
                    if (n==8){
                        listapelis = pelisactualesO.get("cast").getAsJsonArray();

                    }else{
                         listapelis = pelisactualesO.get(SM.results).getAsJsonArray();

                    }

                    for (JsonElement p : listapelis) {
                        LJP = new LeerJsonPeli(p);
                try {

                    listaPeli.add(LJP.getPeli());

                }catch(Exception e){
                    continue;
                        }
            }

    }


    public LinkedList<Film> getListaPeli() {
        return listaPeli;
    }
}
