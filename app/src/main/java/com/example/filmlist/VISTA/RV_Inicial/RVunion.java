package com.example.filmlist.VISTA.RV_Inicial;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import java.util.LinkedList;

public class RVunion {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter adaptador;

    private final LinkedList<Film> informe;



    public RVunion(MainActivity activity , LinkedList<Film> Informe , String opcion) {


        if(opcion.equals( "INICIAL")){
                recyclerfilms =activity.findViewById(R.id.RV_films);}
        if(opcion.equals("BUSQUEDA")){

                recyclerfilms =activity.findViewById(R.id.generosrv);}
        if(opcion.equals("POPULARES")){
                recyclerfilms =activity.findViewById(R.id.rv_populares);}

        if(opcion.equals("TOPRATED")){
                recyclerfilms =activity.findViewById(R.id.rv_estrenos);}

        if(opcion.equals("ESTRENOS")){
                recyclerfilms =activity.findViewById(R.id.rv_toprated);

        } if(opcion.equals("RECOMENDACIONES")){

            recyclerfilms =activity.findViewById(R.id.rv_recomendaciones);
        }if(opcion.equals("GENERO")){

            recyclerfilms =activity.findViewById(R.id.generosrv);
        }if(opcion.equals("PELISACTORES")){
            recyclerfilms=activity.findViewById(R.id.pelisActorRV);
        }if(opcion.equals("ACTORESFAV")){
            recyclerfilms=activity.findViewById(R.id.peliculasActorFav);
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
        if(opcion.equals("BUSQUEDA")||opcion.equals("GENERO")){
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        }else{
            layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);}
        recyclerfilms.setLayoutManager(layoutManager);




    }


}
