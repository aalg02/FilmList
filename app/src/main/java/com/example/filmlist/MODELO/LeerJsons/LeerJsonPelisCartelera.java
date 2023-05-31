package com.example.filmlist.MODELO.LeerJsons;

import com.example.filmlist.CONTROLADOR.Controlador;
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


                    if (n == 1) {

                        Controlador.getInstance().LISTASINICIAL.getListaFCartelera().add(LJP.getPeli());
                    }
                    if (n == 2) {
                        Controlador.getInstance().LISTASINICIAL.getListaFGenero().add(LJP.getPeli());
                    }
                    if (n == 3) {
                        Controlador.getInstance().LISTASINICIAL.getListaFpopulares().add(LJP.getPeli());
                    }
                    if (n == 4) {
                        Controlador.getInstance().LISTASINICIAL.getListaFtoprated().add(LJP.getPeli());
                    }
                    if (n == 5) {
                        Controlador.getInstance().LISTASINICIAL.getListaFestrenos().add(LJP.getPeli());
                    }
                    if (n == 6) {
                        Controlador.getInstance().LISTASINICIAL.getListaFrecomendaciones().add(LJP.getPeli());
                    }
                    if (n == 7) {
                        Controlador.getInstance().LISTASINICIAL.getListaFGenero().add(LJP.getPeli());
                    }
                    if (n == 8) {
                        Controlador.getInstance().LISTASINICIAL.getListaFActores().add(LJP.getPeli());
                    }
                }catch(Exception e){
                    continue;
                        }
            }

    }





}
