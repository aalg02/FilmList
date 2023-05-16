package com.example.filmlist.usuarios;

import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Actor;
import com.example.filmlist.JsonRead.Film;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class guardardatos {
    ArrayList<String> listavistas=new ArrayList<>();
    ArrayList<String>  listafavoritas=new ArrayList<>();
    ArrayList<String>  listapendientes=new ArrayList<>();
    ArrayList<String>  listavaloradas=new ArrayList<>();
    ArrayList<String>   listaactores=new ArrayList<>();

    HashMap<String, Integer> valoraciones=new HashMap<>();

    public void guardalistasusuarios(){

        listavistas.clear();
        listafavoritas.clear();
        listapendientes.clear();
        listavaloradas .clear();
        listaactores.clear();




        for(Film f:Controlador.getInstance().LISTAS.getListaFvistas()){
            listavistas.add(f.getId());
        }
        for(Film f:Controlador.getInstance().LISTAS.getListaFfavoritas()){
            listafavoritas.add(f.getId());
        }
        for(Film f:Controlador.getInstance().LISTAS.getListaFpendientes()){
            listapendientes.add(f.getId());
        }
        for(Film f:Controlador.getInstance().LISTAS.getListaFvaloradas()){
            listavaloradas.add(f.getId());
            valoraciones=Controlador.getInstance().usuario.valoraciones;

        }
        for(Actor a:Controlador.getInstance().LISTASACTORES.getListaActorFav()){
            listaactores.add(a.getId());
        }




        //-----------------//




        Controlador.getInstance().usuario.setListavistas(listavistas);
        Controlador.getInstance().usuario.setListafavoritas(listafavoritas);
        Controlador.getInstance().usuario.setListapendientes(listapendientes);
        Controlador.getInstance().usuario.setListavaloradas(listavaloradas);
        Controlador.getInstance().usuario.setValoraciones(valoraciones);
        Controlador.getInstance().usuario.setListaActores(listaactores);





    }

    public void guardarusuario(String gmail , String contraseña){

        Controlador.getInstance().usuario.setGmail(gmail);
        Controlador.getInstance().usuario.setContraseña(contraseña);

    }

    public void invitado(){
        Controlador.getInstance().usuario.setGmail("invitado");
        Controlador.getInstance().usuario.setContraseña("invitado");

    }
}
