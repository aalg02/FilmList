package com.example.filmlist;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.FragmentManager.MyFragment;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.GestionVistas.gestorvistas;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonGenerosPelis;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.JsonRead.ListaPelis;
import com.example.filmlist.JsonRead.ListasPropias;
import com.example.filmlist.PeticionWeb.peticion2;


import com.example.filmlist.RV_Inicial.RVunion;


import com.example.filmlist.RV_listaVistas.RVAdapter_LV;
import com.example.filmlist.RV_listaVistas.RVunion_LV;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Controlador {
    private static Controlador miControlador;
    public LeerJsonPelisCartelera LJPC;
    public LeerJsonPelisCartelera LPB;
    public LeerJsonPelisCartelera LPP;
    public LeerJsonPelisCartelera LPE;
    public LeerJsonPelisCartelera LPTP;

    LeerJsonGenerosPelis LJGP;
    StringManager stringManager=new StringManager();
    protected MainActivity miActivity;
    peticion2 peticionapi = new peticion2();

    public ViewPager viewPager;
    public MyPagerAdapter adapter;

    private RecyclerView mRecyclerView;
    public gestorvistas gestorvistas;

    public SegundaActivity miau=new SegundaActivity();
    public ListaPelis LISTASINICIAL=new ListaPelis();
    public ListasPropias LISTAS=new ListasPropias();







    public static Controlador getInstance() {
        if (miControlador == null)
            miControlador = new Controlador();
        return miControlador;
    }

    //-----------------LANZA LA PETICION------------------//

    public void getPrevision(MainActivity fromActivity ,String url,int n) {

        try {
            this.miActivity = fromActivity;
            peticionapi.requestData(url,n);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setActivity(MainActivity act) {
        miActivity = act;
    }
    public void setVistamanager(gestorvistas miau) {
        gestorvistas = miau;
    }





    //---------DISTINTAS BUSQUEDAS QUE SE VAN A USAR-------------//



    public void LeerPeliculasCartelera(String json ){

        LJPC=new LeerJsonPelisCartelera(json,1);

    }

    public void LeerPeliculasBusqueda(String json){

        LJPC=new LeerJsonPelisCartelera(json,2);
    }

    public void LeerPeliculaspopulares(String json){

        LPP=new LeerJsonPelisCartelera(json,3);

    }

    public void LeerPeliculasestrenos(String json){

        LPE=new LeerJsonPelisCartelera(json,4);

    }

    public void LeerPeliculasToprated(String json){

        LPTP=new LeerJsonPelisCartelera(json,5);

    }
    public void LeerPeliculasRecomendaciones(String json){

        LPTP=new LeerJsonPelisCartelera(json,6);

    }




    public void LeerGenerosPeliculas(String json){
        JsonElement generosO= JsonParser.parseString(json);
        LJGP=new LeerJsonGenerosPelis(generosO);
        //new RVunion(miActivity, LJPC);
    }



//---------------------------Si NO tenemos conexion a internet------------------------//
    public void NoConexion(){

        gestorvistas.NoConexion();
    }


    //-------------------------------CONTROL DEL VIEWPAGE-------------------------------------//

    public void controlViewpage (){

        viewPager = miActivity.findViewById(R.id.viewpage);
        adapter = new MyPagerAdapter(miActivity.getSupportFragmentManager(),miActivity);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        adapter.notifyDataSetChanged();



    }


    //------------------------------CONTROL DE BUSQUEDAS-------------------------------------//


    public void busqueda(){


        ImageView imagen = miActivity.findViewById(R.id.search_icon);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText EditT = miActivity.findViewById(R.id.search_bar);
                String text = EditT.getText().toString();
                Glide.with( miActivity).load(0).into((ImageView)miActivity.findViewById(R.id.imagenbusqueda));
                adapter.busca(text);
                adapter.getItem(2);
                LISTASINICIAL.getListaFBusqueda().clear();
                new RVunion(miActivity, LISTASINICIAL.getListaFBusqueda(),stringManager.BUSQUEDA);

            }
        });
    }

    //----------------------------CARGA---------------------//

    public void carga(){
        final long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while (elapsedTime < 200) {

            Glide.with(miActivity)
                    .asGif()
                    .load("loading.gif")
                    .into((ImageView)miActivity.findViewById(R.id.error_img )) ;
            //-------------------------------------//
            elapsedTime = System.currentTimeMillis() - startTime;
        }


    }


    //----------------------------CLICK EN LA PELICULA---------------------//

    public void clicPeli(View recycler, int position,  String opcion, Film f){
        ScrollView scrollview =miActivity.findViewById(R.id.scrollview);
        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollview.smoothScrollTo(0,0);
                gestorvistas.cargainfoInicio(position,opcion);
                gestorvistas.framelayoutinicio(1);
                gestorvistas.floatingMenu(f);
                recomendacion(f.getId());
                new RVunion(miActivity,Controlador.getInstance().LISTASINICIAL.getListaFrecomendaciones(),stringManager.RECOMENDACIONES);

            }
        });


    }
    public void clicPeliPropias(View recycler, int position,  String opcion, Film f){
        ScrollView scrollview =miActivity.findViewById(R.id.scrollview);

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollview.smoothScrollTo(0,0);
                gestorvistas.cargainfoMislistas(position,opcion);
                gestorvistas.framelayoutinicio(1);
                gestorvistas.floatingMenu(f);
                recomendacion(f.getId());
                new RVunion(miActivity,Controlador.getInstance().LISTASINICIAL.getListaFrecomendaciones(),stringManager.RECOMENDACIONES);






            }
        });


    }


   //-----------------GESTION DE LA PAGINA DE LISTAS------------------------------//

    public void RefrscaInicial(){

        new RVunion(miActivity,  LISTASINICIAL.getListaFCartelera(),stringManager.INICIAL);
        new RVunion(miActivity,  LISTASINICIAL.getListaFBusqueda(),stringManager.BUSQUEDA);
        new RVunion(miActivity,  LISTASINICIAL.getListaFpopulares(),stringManager.POPULARES);
        new RVunion(miActivity,  LISTASINICIAL.getListaFestrenos(),stringManager.ESTRENOS);
        new RVunion(miActivity,  LISTASINICIAL.getListaFtoprated(),stringManager.TOPRATED);
    }

    public void RefrescaVistas(){

        new RVunion_LV(miActivity,  LISTAS.getListaFvistas(),1,stringManager.VISTAS);
        new RVunion_LV(miActivity,  LISTAS.getListaFfavoritas(),2,stringManager.FAVORITAS);
        new RVunion_LV(miActivity,  LISTAS.getListaFpendientes(),3,stringManager.PENDIENTES);
    }


    //-----------------------------RECOMENDACION PELICULAS-----------------------------------//

    public void recomendacion(String id){
        clearrecomendacion();
        String url=stringManager.apiUrl+id+stringManager.recommendations+stringManager.apiKey;
        getPrevision(miActivity,url,6);




    }

    public void clearrecomendacion(){
        LISTASINICIAL.getListaFrecomendaciones().clear();
    }



}
