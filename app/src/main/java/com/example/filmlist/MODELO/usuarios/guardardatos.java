package com.example.filmlist.MODELO.usuarios;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MODELO.objetos.Film;

import java.util.ArrayList;
import java.util.HashMap;

public class guardardatos {
    ArrayList<String> listavistas;
    ArrayList<String>  listafavoritas;
    ArrayList<String>  listapendientes;
    ArrayList<String>  listavaloradas;
    ArrayList<String>   listaactores;

    HashMap<String, Integer> valoraciones;
    Controlador controlador;
    
    
    
    public guardardatos(Controlador controlador){
        this.controlador=controlador;
        listavistas=new ArrayList<>();
        listafavoritas=new ArrayList<>();
        listapendientes=new ArrayList<>();
        listavaloradas=new ArrayList<>();
        listaactores=new ArrayList<>();
        valoraciones=new HashMap<>();
    }

    public void guardalistasusuarios(){

        listavistas.clear();
        listafavoritas.clear();
        listapendientes.clear();
        listavaloradas .clear();
        listaactores.clear();




        for(Film f:controlador.LISTAS.getListaFvistas()){
            listavistas.add(f.getId());
        }
        for(Film f:controlador.LISTAS.getListaFfavoritas()){
            listafavoritas.add(f.getId());
        }
        for(Film f:controlador.LISTAS.getListaFpendientes()){
            listapendientes.add(f.getId());
        }
        for(Film f:controlador.LISTAS.getListaFvaloradas()){
            listavaloradas.add(f.getId());
            valoraciones=controlador.usuario.valoraciones;

        }
        for(Actor a:controlador.LISTASACTORES.getListaActorFav()){
            listaactores.add(a.getId());
        }




        //-----------------//




        controlador.usuario.setListavistas(listavistas);
        controlador.usuario.setListafavoritas(listafavoritas);
        controlador.usuario.setListapendientes(listapendientes);
        controlador.usuario.setListavaloradas(listavaloradas);
        controlador.usuario.setValoraciones(valoraciones);
        controlador.usuario.setListaActores(listaactores);





    }

    public void guardarusuario(String gmail , String contraseña){

        controlador.usuario.setGmail(gmail);
        controlador.usuario.setContraseña(contraseña);

    }

    public void invitado(){
        controlador.usuario.setGmail("invitado");
        controlador.usuario.setContraseña("invitado");

    }
}
