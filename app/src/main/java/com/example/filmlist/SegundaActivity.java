package com.example.filmlist;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.filmlist.VISTA.FragmentManager.MyFragment;
import com.example.filmlist.VISTA.GestionVistas.gestorvistas;

public class    SegundaActivity extends AppCompatActivity {
    gestorvistas gestor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.segundaactivity);
        addFragment(new MyFragment(R.layout.info_peliculas));

    }

    public void addFragment(MyFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
