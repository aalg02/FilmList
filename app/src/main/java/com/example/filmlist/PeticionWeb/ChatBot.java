package com.example.filmlist.PeticionWeb;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.filmlist.Controlador;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ChatBot {

    public void chat(String pregunta) {
        pregunta="Hola, ¿cómo estás?,puedes contestar solo como si fueses un experto en peliculas y conversar sobre ellas?,tu nombre es Sr.palomita.";
        String apiKey = "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";
        String endpoint = "https://api.openai.com/v1/engines/davinci/completions";

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");

        String requestBody = "{\"prompt\": \""+pregunta+"\"}";

        RequestBody body = RequestBody.create(mediaType, requestBody);

        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", "application/json")
                .build();


        Call llamada = client.newCall(request);
        llamada.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(Controlador.getInstance().miActivity, "error chatbot" , Toast.LENGTH_SHORT).show();
            }

            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {

                String respuesta = respuestaServer.body().string();

                Handler manejador = new Handler(Looper.getMainLooper());

                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Controlador.getInstance().miActivity, respuesta , Toast.LENGTH_SHORT).show();
                        System.out.println(respuesta);

                    }
                });

            }
        });
    }
}
