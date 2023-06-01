package com.example.filmlist.CONTROLADOR.Controladores;

import android.content.Intent;
import android.provider.MediaStore;

import com.example.filmlist.CONTROLADOR.ManejoImagenes.DownloadImageTask;
import com.example.filmlist.MainActivity;

public class ControladorImagenes {
    MainActivity miActivity;
    Controlador controlador;

    public ControladorImagenes(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;


    }


    //--------------------ABRIR LA GALERIA---------------------------------//


    public void galeria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        miActivity.startActivityForResult(intent, 123);
    }


    //----------------------DESCARGAR IMAGEN---------------------------------//


    public void descargarWallaper(String url) {
        DownloadImageTask downloadTask = new DownloadImageTask(controlador);
        downloadTask.execute(url, "miau");
    }

}
