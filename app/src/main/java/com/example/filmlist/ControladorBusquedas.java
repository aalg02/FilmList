package com.example.filmlist;

import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;

public class ControladorBusquedas {
    LeerJsonPelisCartelera LJPC;
    Controlador controlador;




    public void ControladorBusquedas(Controlador controlador){
        this.controlador=controlador;
    }


    public void menu(String opcion1){
    String opcion = opcion1;
    switch (opcion){
        case "INICIAL":

            break;
        case "BUSQUEDA":
            break;
        case "POPULARES":
            break;
        case "TOPRATED":
            break;
        case "ESTRENOS":
            break;
    }

    }


}
