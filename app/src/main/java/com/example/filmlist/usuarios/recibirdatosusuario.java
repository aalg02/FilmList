package com.example.filmlist.usuarios;

import com.example.filmlist.Controlador;
import com.example.filmlist.StringManager;

public class recibirdatosusuario {
    StringManager SM=new StringManager();
    String Id;
    String url=SM.apiUrl+Id+SM.apiKey;

    public void recuperarpelis(){
        for(String id: Controlador.getInstance().usuario1.getListavistas()){
            this.Id=id;
            Controlador.getInstance();
        }
    }
}
