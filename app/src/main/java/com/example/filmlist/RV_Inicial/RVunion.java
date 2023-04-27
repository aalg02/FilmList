package com.example.filmlist.RV_Inicial;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.Controlador;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVunion {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter adaptador;

    private final LinkedList<Film> informe;



    public RVunion(MainActivity activity , LinkedList<Film> Informe , String opcion) {


        if(opcion== "INICIAL"){
                recyclerfilms =activity.findViewById(R.id.RV_films);}
        if(opcion=="BUSQUEDA"){
                recyclerfilms =activity.findViewById(R.id.recycler_busqueda);}

        if(opcion== "POPULARES"){
                recyclerfilms =activity.findViewById(R.id.rv_populares);}

        if(opcion== "TOPRATED"){
                recyclerfilms =activity.findViewById(R.id.rv_estrenos);}

        if(opcion== "ESTRENOS"){
                recyclerfilms =activity.findViewById(R.id.rv_toprated);

        } if(opcion== "RECOMENDACIONES"){

            recyclerfilms =activity.findViewById(R.id.rv_recomendaciones);
        }



        //genresfilms=activity.findViewById(R.id.genre_layout);



        this.informe = Informe;

//        ((TextView) activity.findViewById(R.id.fecha1)).setText(informe.getPrediccion().getInicio());
//        ((TextView) activity.findViewById(R.id.fecha2)).setText(informe.getPrediccion().getFin());

        adaptador = new RVAdapter(activity, informe,opcion);

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
