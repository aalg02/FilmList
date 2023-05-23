package com.example.filmlist.JsonRead;

import android.net.Uri;

import java.sql.Array;
import java.util.ArrayList;

public class Film {



    public String idFilm;
    public String Nombre;
    public String sinopsis;
    public String Valoration;

    public String img_path="https://image.tmdb.org/t/p/original";
    public String releasedate;

    public int mivaloracion;
    public ArrayList<String> fotospeli=new ArrayList<>();


    public  Film(String id,String Name ,String description,String Valoration,String path,String releasedate,int mivaloracion){
        this.idFilm=id;
      this.Nombre =Name;
      this.sinopsis=description;
      this.Valoration=Valoration;
      this.img_path=getImg_path()+path;
      this.releasedate=releasedate;
      this.mivaloracion=mivaloracion;
    }

    public ArrayList<String> getFotospeli() {
        return fotospeli;
    }

    public void setFotospeli(ArrayList<String> fotospeli) {
        this.fotospeli = fotospeli;
    }

    public int getMivaloracion() {
        return mivaloracion;
    }

    public void setMivaloracion(int mivaloracion) {
        this.mivaloracion = mivaloracion;
    }

    public String getId() {
        return idFilm;
    }

    public String getNombre() {
        return this.Nombre;
    }

    public String getSinopsis() {
        return this.sinopsis;
    }

    public String getValoration() {
        return Valoration;
    }

    public String getImg_path() {
        return img_path;
    }
    public Uri getUri(){

        return Uri.parse(img_path);
    }

    public String getReleasedate() {
        return releasedate;
    }
}

