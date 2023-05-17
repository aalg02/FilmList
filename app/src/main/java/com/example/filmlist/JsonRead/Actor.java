package com.example.filmlist.JsonRead;

public class Actor {
    String nombre;
    String id;
    String profile_path="https://image.tmdb.org/t/p/original";
    String Rol;
    String biografia;
    String cumpleaños;
    public Actor(String nombre, String id, String path,String Rol) {
        this.nombre = nombre;
        this.id = id;
        this.profile_path = profile_path+path;
        this.Rol = Rol;
    }
    public Actor(String nombre, String id, String path,String biografia,String cumpleaños) {
        this.nombre = nombre;
        this.id = id;
        this.profile_path = profile_path+path;
        this.biografia = biografia;
        this.cumpleaños=cumpleaños;
    }
    public Actor(String nombre, String id, String path) {
        this.nombre = nombre;
        this.id = id;
        this.profile_path = profile_path+path;

    }


    public String getBiografia() {
        return biografia;
    }

    public String getCumpleaños() {
        return cumpleaños;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        this.Rol = rol;
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
