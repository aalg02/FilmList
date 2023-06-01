package com.example.filmlist.CONTROLADOR.Controladores;

import com.example.filmlist.CONTROLADOR.PeticionWeb.peticion2;
import com.example.filmlist.CONTROLADOR.PeticionWeb.youtube;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MainActivity;
import com.example.filmlist.StringManager;

public class ControladorPeticiones {

    MainActivity miActivity;
    Controlador controlador;
    peticion2 peticionapi ;
    StringManager stringManager;
    youtube youtube;
    public ControladorPeticiones(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;
        peticionapi = new peticion2(controlador);
        stringManager=new StringManager();

    }


    public void peticionaWeb(MainActivity fromActivity, String url, int n) {

        try {
            this.miActivity = fromActivity;
            peticionapi.requestData(url, n);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    //-------------------------TODOS LOS LANZAMIENTOS DE PETICION----------------------//

    public void rellenarRVGeneros(int n) {
        controlador.LISTASINICIAL.getListaFGenero().clear();
        peticionaWeb(miActivity,stringManager.urlgeneros + stringManager.IDGEN[n], 11);

    }

    public void rellenarRVActores(String idPeli) {
        controlador.LISTASACTORES.getListaActoresPeli().clear();
        peticionaWeb(miActivity,stringManager.apiUrl + idPeli + stringManager.urlcast + stringManager.español, 12);
    }


    public void busquedaActor(String idActor) {
        peticionaWeb(miActivity, stringManager.urlactor + idActor + stringManager.apiKey + stringManager.español, 13);
    }

    public void OtrasPelisActor(String idActor) {

        controlador.LISTASINICIAL.getListaFActores().clear();
        peticionaWeb(miActivity, stringManager.urlactor + idActor + stringManager.urlPliActores + stringManager.español, 14);
    }

    public void OtrasPelisActorFav(String idActor) {

        controlador.LISTASINICIAL.getListaFActores().clear();
        peticionaWeb(miActivity, stringManager.urlactor + idActor + stringManager.urlPliActores + stringManager.español, 15);
    }


    public void BusquedaTrailer(String titulo) {
        youtube = new youtube(controlador);
        youtube.busqueda(titulo);
    }

    public void cargarGaleriapeli(Film f) {
        f.getFotospeli().clear();
        peticionaWeb(miActivity, "https://api.themoviedb.org/3/movie/" + f.getId() + "/images?api_key=18f552217e447f369638f70fa4f06a20", 16);
        //gestorvistas.dialogGaleria(f);
    }


}
