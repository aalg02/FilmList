package com.example.filmlist.VISTA.RV_Actores;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVunion_A {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter_A adaptador;

    private final LinkedList<Actor> informe;



    public RVunion_A(MainActivity activity , Controlador controlador, LinkedList<Actor> Informe , String opcion) {

        if (opcion.equals("ACTORES")){
        recyclerfilms =activity.findViewById(R.id.rvactores);}
        if(opcion.equals("ACTORESFAV")){
            recyclerfilms =activity.findViewById(R.id.rvActoresFav);
        }


        //genresfilms=activity.findViewById(R.id.genre_layout);



        this.informe = Informe;

//        ((TextView) activity.findViewById(R.id.fecha1)).setText(informe.getPrediccion().getInicio());
//        ((TextView) activity.findViewById(R.id.fecha2)).setText(informe.getPrediccion().getFin());

        adaptador = new RVAdapter_A(activity,controlador, informe,opcion);

        //adaptadorGeneros=new AdapterGenres(activity,)
        recyclerfilms.setLayoutManager(new LinearLayoutManager(activity));
        recyclerfilms.setAdapter(adaptador);
        LinearLayoutManager layoutManager = null;
        if(opcion.equals("BUSQUEDA")){
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        }else{
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);}
        recyclerfilms.setLayoutManager(layoutManager);




    }


}
