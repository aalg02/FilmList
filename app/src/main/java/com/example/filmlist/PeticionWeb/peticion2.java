package com.example.filmlist.PeticionWeb;


import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import com.example.filmlist.Controlador;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase Peticion
 *
 * Es utilizado por el controlador. El controlador le proporciona
 * los datos necesarios
 *
 * Se apoyará en OkHttp (librería cliente http/http2)
 *
 */
public class peticion2 {
    //ESTADO
    //Clase utilidad que no necesita nada más que poner a funcionar la peticion HTTPs

    //COMPORTAMIENTO
    public peticion2() {

    }

    public void requestData(String URL,int n) {
        OkHttpClient cliente = new OkHttpClient();

        //construimos la peticion
        Request peticion = new Request.Builder()
                .url(URL)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();


        //realizamos la llamada al server, pero en otro thread (con enqueue)
        Call llamada = cliente.newCall(peticion);
        llamada.enqueue(new Callback() {
            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {
                //Got answer!!!!!
                String respuesta = respuestaServer.body().string();
                // Create a handler that associated with Looper of t    he main thread
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main threadnn
                       if(n==1) {
                           Controlador.getInstance().LeerPeliculasCartelera(respuesta);
                           Controlador.getInstance().RefrscaInicial();
                       }if(n==2){
                           Controlador.getInstance().LeerPeliculasBusqueda(respuesta);
                            Controlador.getInstance().RefrscaInicial();
                        }if(n==3){
                            Controlador.getInstance().LeerPeliculaspopulares(respuesta);
                            Controlador.getInstance().RefrscaInicial();

                        }if(n==4){
                            Controlador.getInstance().LeerPeliculasestrenos(respuesta);
                            Controlador.getInstance().RefrscaInicial();
                        }if(n==5){
                            Controlador.getInstance().LeerPeliculasToprated(respuesta);
                            Controlador.getInstance().RefrscaInicial();
                        }if(n==6){
                            Controlador.getInstance().LeerPeliculasRecomendaciones(respuesta);
                        }if(n==7){
                           Controlador.getInstance().LeerPeliVistas(respuesta);
                        }if(n==8){
                            Controlador.getInstance().LeerPeliFav(respuesta);
                        }if(n==9){
                            Controlador.getInstance().LeerPeliPendiente(respuesta);
                        }if(n==10) {
                            Controlador.getInstance().LeerPelivaloradas(respuesta);
                        }if(n==11){
                                Controlador.getInstance().LeerPeligenero(respuesta);
                       }if(n==12){
                            Controlador.getInstance().LeerActoresPeli(respuesta);
                        }if(n==13){
                           Controlador.getInstance().LeerActor(respuesta);
                        }if(n==14){
                           Controlador.getInstance().LeerPeliculasActor(respuesta);
                        }

                    }
                });


            }

            public void onFailure(Call call, IOException e) {
                String respuesta = e.getMessage();
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread

                        if(n==1) {
                            Controlador.getInstance().LeerPeliculasCartelera(respuesta);
                        }if(n==2){
                            Controlador.getInstance().LeerPeliculasBusqueda(respuesta);
                        }if(n==3){
                            Controlador.getInstance().LeerPeliculaspopulares(respuesta);
                        }if(n==4){
                            Controlador.getInstance().LeerPeliculasestrenos(respuesta);
                        }if(n==5){
                            Controlador.getInstance().LeerPeliculasToprated(respuesta);
                        }if(n==6){
                            Controlador.getInstance().LeerPeliculasRecomendaciones(respuesta);
                        }

                        Controlador.getInstance().NoConexion();
                        //Controlador.getInstance().leerjson(respuesta);
                    }
                });
            }
        });





    }

}
