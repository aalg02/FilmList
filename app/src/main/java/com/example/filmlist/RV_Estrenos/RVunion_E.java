package com.example.filmlist.RV_Estrenos;



import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

public class RVunion_E {
    RecyclerView recyclerfilms;
    RecyclerView genresfilms;
    RVAdapter_E adaptador;

    private final LeerJsonPelisCartelera informe;



    public RVunion_E(MainActivity activity , LeerJsonPelisCartelera Informe ) {

        recyclerfilms =activity.findViewById(R.id.rv_estrenos);
        //genresfilms=activity.findViewById(R.id.genre_layout);



        this.informe = Informe;

//        ((TextView) activity.findViewById(R.id.fecha1)).setText(informe.getPrediccion().getInicio());
//        ((TextView) activity.findViewById(R.id.fecha2)).setText(informe.getPrediccion().getFin());

        adaptador = new RVAdapter_E(activity, informe);

        //adaptadorGeneros=new AdapterGenres(activity,)
        recyclerfilms.setLayoutManager(new LinearLayoutManager(activity));
        recyclerfilms.setAdapter(adaptador);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false);
        recyclerfilms.setLayoutManager(layoutManager);




    }


}
