package com.example.filmlist.JsonRead;

public class Actor {
    String nombre;
    String id;
    String profile_path="https://image.tmdb.org/t/p/original";
    String biografia;

    public Actor(String nombre, String id, String path,String biografia) {
        this.nombre = nombre;
        this.id = id;
        this.profile_path = profile_path+path;
        this.biografia=biografia;
    }
    public Actor(String nombre, String id, String path) {
        this.nombre = nombre;
        this.id = id;
        this.profile_path = profile_path+path;

    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }
}
