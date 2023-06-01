package com.example.filmlist.VISTA.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;

public class MyFragment extends Fragment {
    int layout;
    Controlador controlador;
    public MyFragment(int layout,Controlador controlador){
      this.layout=layout;
      this.controlador=controlador;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(layout, container, false);
        controlador.gestorVistasGeneral.gestorVentanaBusqueda.Listenergeneros();
        // Realiza cualquier otra operación que desees en la vista

        return view;
    }
}
