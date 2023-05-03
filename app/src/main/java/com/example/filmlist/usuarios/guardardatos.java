package com.example.filmlist.usuarios;

import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Film;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class guardardatos {
    ArrayList<String> listavistas=new ArrayList<>();
    ArrayList<String>  listafavoritas=new ArrayList<>();
    ArrayList<String>  listapendientes=new ArrayList<>();

    public void guardalistasusuarios(){

        for(Film f:Controlador.getInstance().LISTAS.getListaFvistas()){
            listavistas.add(f.getId());
        }
        for(Film f:Controlador.getInstance().LISTAS.getListaFfavoritas()){
            listafavoritas.add(f.getId());
        }
        for(Film f:Controlador.getInstance().LISTAS.getListaFpendientes()){
            listapendientes.add(f.getId());
        }



        //-----------------//




        Controlador.getInstance().usuario.setListavistas(listavistas);
        Controlador.getInstance().usuario.setListafavoritas(listafavoritas);
        Controlador.getInstance().usuario.setListapendientes(listapendientes);


    }

    public void guardarusuario(String gmail , String contraseña){

        Controlador.getInstance().usuario.setGmail(gmail);
        Controlador.getInstance().usuario.setContraseña(contraseña);

    }
}
