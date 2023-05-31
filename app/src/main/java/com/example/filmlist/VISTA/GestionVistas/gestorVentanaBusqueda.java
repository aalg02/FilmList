package com.example.filmlist.VISTA.GestionVistas;

import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.filmlist.CONTROLADOR.Controlador;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.VISTA.GestionVistas.gestorVistasGeneral;

public class gestorVentanaBusqueda {
    MainActivity mainActivity;
    gestorVistasGeneral gestorVistasGeneral;
    Controlador controlador;

    public gestorVentanaBusqueda(MainActivity mainActivity,Controlador controlador, gestorVistasGeneral gestorVistasGeneral){
        this.mainActivity=mainActivity;
        this.gestorVistasGeneral = gestorVistasGeneral;
        this.controlador=controlador;

    }

    public void Listenergeneros() {
        CardView card = mainActivity.findViewById(R.id.mau0);
        for (int i = 0; i <= 17; i++) {
            int n = i;
            int id = mainActivity.getResources().getIdentifier("mau" + i, "id", mainActivity.getPackageName());
            card = mainActivity.findViewById(id);
            if (card != null) {
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Glide.with(mainActivity).load(0).into((ImageView) mainActivity.findViewById(R.id.imagenbusqueda));
                        controlador.rellenarRVGeneros(n);
                        controlador.RefrescaGenero();
                    }
                });
            }
        }


    }

    public void listenersbusqueda() {
        ImageView imagen = mainActivity.findViewById(R.id.search_icon);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.ocultateclado();
                controlador.busqueda();
            }
        });


    }
}
