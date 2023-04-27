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
import com.example.filmlist.JsonRead.Film;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    protected Controlador miControlador;
    gestorvistas gestor;
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
        gestor.framelayoutinicio(0);
    }

}