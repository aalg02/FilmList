package com.example.filmlist.MODELO.LeerJsons;

import com.example.filmlist.CONTROLADOR.Controlador;
import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LeerJsonActor {

    Actor actor;
    StringManager stringManager = new StringManager();

    public LeerJsonActor(JsonElement json,String opcion) {
        JsonElement json1 = json;
        JsonObject infoactor = json1.getAsJsonObject();
        String id = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.id)).getAsString();
        String name = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.name)).getAsString();
        String img_path = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.profile_path)).getAsString();
        if(opcion.equals("ACTORES")){
            try{
                String Rol = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.character)).getAsString();
                actor=new Actor(name , id,img_path,Rol);
            }catch (Exception e){
                actor=new Actor(name , id,img_path);
            }
        }
        if (opcion.equals("ACTORESFAV")) {
            try{
                String Biografia = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.biography)).getAsString();
                String birthday = infoactor.getAsJsonPrimitive(Controlador.getInstance().miActivity.getString(R.string.birthday)).getAsString();
                actor=new Actor(name,id,img_path,Biografia,birthday);
            }catch (Exception e){
                actor=new Actor(name , id,img_path);
            }
        }




    }

    public Actor getActor() {
        return actor;
    }
}

