package com.example.filmlist.RV_Busqueda;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.RV_Inicial.RVAdapter;

public class RVunionBusqueda {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter_B adaptador;

    private final LeerJsonPelisCartelera informe;



    public RVunionBusqueda(MainActivity activity , LeerJsonPelisCartelera Informe ) {

        recyclerfilms =activity.findViewById(R.id.recycler_busqueda);
        //genresfilms=activity.findViewById(R.id.genre_layout);

        this.informe = Informe;

//        ((TextView) activity.findViewById(R.id.fecha1)).setText(informe.getPrediccion().getInicio());
//        ((TextView) activity.findViewById(R.id.fecha2)).setText(informe.getPrediccion().getFin());

        adaptador = new RVAdapter_B(activity, informe);
        //adaptadorGeneros=new AdapterGenres(activity,)
        recyclerfilms.setLayoutManager(new LinearLayoutManager(activity));
        recyclerfilms.setAdapter(adaptador);




    }


}
