package com.example.filmlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.filmlist.GestionVistas.gestorvistas;
import com.google.firebase.FirebaseApp;
//import com.example.filmlist.JsonRead.Film;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    protected Controlador miControlador;
    // public  FirebaseApp firebase;
    gestorvistas gestor;
  //  FirebaseFirestore firestore;
    SegundaActivity segundaActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        miControlador = Controlador.getInstance();
        miControlador.setActivity(this);
        miControlador.controlViewpage();
        gestor=new gestorvistas();
        gestor.setActivity(this);
        gestor.listeners();
        gestor.framelayoutinicio(0);
        miControlador.setVistamanager(gestor);
        FirebaseApp.initializeApp(this);
        


        // gestor=gestorvistas.getInstance();

        // miControlador.cargarRV(this);


    }
    @Override
    public void onBackPressed() {
        //miControlador.guardadatos();
        gestor.framelayoutinicio(0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Controlador.getInstance().guardadatos();

    }

}