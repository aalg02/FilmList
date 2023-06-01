package com.example.filmlist.CONTROLADOR.PeticionWeb;


import android.os.Handler;
import android.os.Looper;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;

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
   Controlador controlador;
    public peticion2(Controlador controlador) {
         this.controlador=controlador;
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
                        // wow
                         controlador.LeerOpcionyRefrescar(n,respuesta);
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

                        controlador.LeerOpcionyRefrescar(n,respuesta);

                        controlador.NoConexion();
                        //controlador.leerjson(respuesta);
                    }
                });
            }
        });





    }

}
