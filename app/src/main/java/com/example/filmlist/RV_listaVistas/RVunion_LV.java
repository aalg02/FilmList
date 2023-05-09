package com.example.filmlist.RV_listaVistas;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVunion_LV {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter_LV adaptador;

    private final LinkedList<Film> vistas;



    public RVunion_LV(MainActivity activity , LinkedList vistas,int n ,String opcion) {


        if(n==1){
            recyclerfilms =activity.findViewById(R.id.rv_listavistas);
        }if(n==2){
            recyclerfilms =activity.findViewById(R.id.rv_listafavoritas);
        }if(n==3){
            recyclerfilms =activity.findViewById(R.id.rv_listapendientes);
        }

        this.vistas = vistas;


        adaptador = new RVAdapter_LV(activity, vistas,opcion);

        //adaptadorGeneros=new AdapterGenres(activity,)
        recyclerfilms.setLayoutManager(new LinearLayoutManager(activity));
        recyclerfilms.setAdapter(adaptador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerfilms.setLayoutManager(layoutManager);




    }


}
