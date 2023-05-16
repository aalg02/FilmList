package com.example.filmlist.JsonRead;

import com.example.filmlist.StringManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class LeerJsonActor {

    Actor actor;
    StringManager stringManager = new StringManager();

    public LeerJsonActor(JsonElement json) {
        JsonElement json1 = json;
        JsonObject infoactor = json1.getAsJsonObject();
        String id = infoactor.getAsJsonPrimitive(stringManager.id).getAsString();
        String name = infoactor.getAsJsonPrimitive(stringManager.name).getAsString();
        String img_path = infoactor.getAsJsonPrimitive(stringManager.profile_path).getAsString();
        try{
            String biography = infoactor.getAsJsonPrimitive(stringManager.character).getAsString();
            actor=new Actor(name , id,img_path,biography);
        }catch (Exception e){
                actor=new Actor(name , id,img_path);
        }



    }

    public Actor getActor() {
        return actor;
    }
}

