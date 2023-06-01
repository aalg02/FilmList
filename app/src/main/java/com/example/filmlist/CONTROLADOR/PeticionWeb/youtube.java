package com.example.filmlist.CONTROLADOR.PeticionWeb;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class youtube {
    Controlador controlador;
    public youtube(Controlador controlador){
        this.controlador=controlador;
    }
    public  void busqueda(String titulo) {
        String busqueda = titulo+"+trailer+español";
        OkHttpClient client = new OkHttpClient();

        // Realiza una solicitud GET a la URL de búsqueda de YouTube
        Request request = new Request.Builder()
                .url("https://www.youtube.com/results?search_query=" + busqueda)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();


        Call llamada = client.newCall(request);
        llamada.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {
                //Got answer!!!!!
                String respuesta = respuestaServer.body().string();

                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {

                        // Extrae la URL del primer video de los resultados de búsqueda
                        String videoUrl = extractVideoUrl(respuesta);

                        // Llama a un método para reproducir el video utilizando la URL
                        reproducirVideo(videoUrl);

                    }
                });


            }

        });

        }




        private  String extractVideoUrl(String html) {
        // Utiliza una expresión regular para extraer la URL del primer video de la lista
        Pattern pattern = Pattern.compile("\"url\":\"/watch\\?v=(.*?)\"");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String videoId = matcher.group(1);
            return "https://www.youtube.com/watch?v=" + videoId;
        }
        return null;
           }

    private  void reproducirVideo(String videoUrl) {
        controlador.gestorVistasGeneral.gestorinfopeli.putoDialogtrailer(videoUrl);
    }


}
