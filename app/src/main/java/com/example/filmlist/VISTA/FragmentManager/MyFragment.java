package com.example.filmlist.VISTA.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.filmlist.CONTROLADOR.Controlador;

public class MyFragment extends Fragment {
    int layout;
    public MyFragment(int layout){
      this.layout=layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(layout, container, false);
        Controlador.getInstance().gestorvistas.Listenergeneros();
        // Realiza cualquier otra operación que desees en la vista

        return view;
    }
}
