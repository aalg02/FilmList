package com.example.filmlist.CONTROLADOR.ManejoImagenes;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = "DownloadImageTask";
    private static final String IMAGE_DIRECTORY = "/FilmList/Wallapers/";
    String nombre;
    Controlador controlador;

    public DownloadImageTask (Controlador controlador){
        this.controlador=controlador;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        String imageUrl = params[0];
         nombre=params[1];
        Bitmap bitmap = null;
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e(TAG, "Error downloading image: " + e.getMessage());
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (result != null) {
            saveImage(result);
        } else {
            Log.e(TAG, "Failed to download image");
        }
    }

    private void saveImage(Bitmap bitmap) {

        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME, nombre);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

        ContentResolver resolver = controlador.miActivity.getContentResolver();
        Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        try {
            OutputStream outputStream = resolver.openOutputStream(uri);
            if (outputStream != null) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(controlador.miActivity, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(controlador.miActivity, "No se pudo guardar la imagen", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            Toast.makeText(controlador.miActivity, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
