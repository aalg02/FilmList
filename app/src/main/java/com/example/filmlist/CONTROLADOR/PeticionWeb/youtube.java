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
    public  void busqueda(String titulo,String opcion) {
        String busquedaTrailer = titulo+"+trailer+español";
        String busquedaSoundtrack = titulo+"+soundtrack";
        OkHttpClient client = new OkHttpClient();
        String url=busquedaTrailer;
        if(opcion.equals("TRAILER")){
            url=busquedaTrailer;
        }else if (opcion.equals("MUSICA")){
            url=busquedaSoundtrack;
        }
        // Realiza una solicitud GET a la URL de búsqueda de YouTube
        Request request = new Request.Builder()
                .url("https://www.youtube.com/results?search_query=" + url)
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
                        if(opcion=="TRAILER"){
                        String videoUrl = extractVideoUrl(respuesta);
                        reproducirVideo(videoUrl);
                        }
                        if(opcion=="MUSICA"){
                            String playlistUrl = extractPlaylistUrl(respuesta);
                            reproducirPlaylist(playlistUrl);
                        }

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



    private String extractPlaylistUrl(String html) {
        // Utiliza una expresión regular para extraer la URL de la primera lista de reproducción de los resultados de búsqueda
        Pattern pattern = Pattern.compile("\"url\":\"\\/playlist\\?list=(.*?)\"");
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            String playlistId = matcher.group(1);
            return "https://www.youtube.com/playlist?list=" + playlistId;
        }
        return null;
    }

    private void reproducirPlaylist(String playlistUrl) {
        controlador.gestorVistasGeneral.gestorinfopeli.putoDialogtrailer(playlistUrl);
    }


}
