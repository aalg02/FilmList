package com.example.filmlist.Firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.filmlist.Controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    private static final String TAG = "DownloadImageTask";
    private static final String IMAGE_DIRECTORY = "/FilmList/Wallapers/";
    String nombre;
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
        FileOutputStream outputStream;
        String fileName = nombre+".jpg";


        try {

            File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            if (!directory.exists()) {

                if (directory.mkdirs()) {
                    Toast.makeText(Controlador.getInstance().miActivity, "creada", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Controlador.getInstance().miActivity, "nocreada", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            File file = new File(directory, fileName);
            outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Toast.makeText(Controlador.getInstance().miActivity, "descargada", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
          Toast.makeText(Controlador.getInstance().miActivity, " error , NO descargada", Toast.LENGTH_LONG).show();
        }
    }
}
