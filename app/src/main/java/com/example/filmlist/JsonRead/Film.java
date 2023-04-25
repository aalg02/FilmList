package com.example.filmlist.JsonRead;

import android.net.Uri;

import java.util.ArrayList;

public class Film {

    public String Name;
    public String Description;
    public String Valoration;

    public String img_path="https://image.tmdb.org/t/p/original";
    public String releasedate;

    public  Film(String Name ,String Description,String Valoration,String path,String releasedate){
      this.Name=Name;
      this.Description=Description;
      this.Valoration=Valoration;
      this.img_path=getImg_path()+path;
      this.releasedate=releasedate;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
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

