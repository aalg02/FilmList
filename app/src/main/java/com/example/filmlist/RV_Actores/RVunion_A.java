package com.example.filmlist.RV_Actores;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.JsonRead.Actor;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVunion_A {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter_A adaptador;

    private final LinkedList<Actor> informe;



    public RVunion_A(MainActivity activity , LinkedList<Actor> Informe , String opcion) {


        recyclerfilms =activity.findViewById(R.id.rvactores);


        //genresfilms=activity.findViewById(R.id.genre_layout);



        this.informe = Informe;

//        ((TextView) activity.findViewById(R.id.fecha1)).setText(informe.getPrediccion().getInicio());
//        ((TextView) activity.findViewById(R.id.fecha2)).setText(informe.getPrediccion().getFin());

        adaptador = new RVAdapter_A(activity, informe,opcion);

        //adaptadorGeneros=new AdapterGenres(activity,)
        recyclerfilms.setLayoutManager(new LinearLayoutManager(activity));
        recyclerfilms.setAdapter(adaptador);
        LinearLayoutManager layoutManager = null;
        if(opcion=="BUSQUEDA"){
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        }else{
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);}
        recyclerfilms.setLayoutManager(layoutManager);




    }


}
