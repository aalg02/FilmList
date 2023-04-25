package com.example.filmlist;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.FragmentManager.MyFragment;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.GestionVistas.gestorvistas;
import com.example.filmlist.JsonRead.LeerJsonGenerosPelis;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.PeticionWeb.peticion2;
import com.example.filmlist.RVPopulares.RVunion_P;
import com.example.filmlist.RV_Estrenos.RVunion_E;
import com.example.filmlist.RV_Inicial.RVunion;
import com.example.filmlist.RV_Busqueda.RVunionBusqueda;
import com.example.filmlist.RV_Toprated.RVunion_TR;
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
        if(LJPC!=null){
            LJPC.getListaP(1).clear();
        }
        LJPC=new LeerJsonPelisCartelera(json,1);
        new RVunion(miActivity, LJPC);
    }

    public void LeerPeliculasBusqueda(String json){
        if(LJPC!=null){
            LJPC.getListaP(2).clear();
        }
        LJPC=new LeerJsonPelisCartelera(json,2);
        new RVunionBusqueda(miActivity, LJPC);
    }

    public void LeerPeliculaspopulares(String json){
        if(LPP!=null){
            LPP.getListaP(3).clear();
        }
        LPP=new LeerJsonPelisCartelera(json,3);
        new RVunion_P(miActivity, LPP);
    }

    public void LeerPeliculasestrenos(String json){
        if(LPE!=null){
            LPE.getListaP(4).clear();
        }
        LPE=new LeerJsonPelisCartelera(json,4);
        new RVunion_E(miActivity, LPE);
    }

    public void LeerPeliculasToprated(String json){
        if(LPTP!=null){
            LPTP.getListaP(5).clear();
        }
        LPTP=new LeerJsonPelisCartelera(json,5);
        new RVunion_TR(miActivity, LPTP);
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

                if(LJPC!=null){
                    LJPC.getListaP(2).clear();
                }

                new RVunionBusqueda(miActivity, LJPC);

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

    public void clicPeli(View recycler, int position,int n){

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(miActivity, "TONTO", Toast.LENGTH_SHORT).show();
                if(n==1) {
                    gestorvistas.cargainfoInicio(position);
                }if(n==2){
                    gestorvistas.cargaInfoBusqueda(position);
                }if(n==3) {
                    gestorvistas.cargaInfoPopulares(position);
                }if(n==4){
                    gestorvistas.cargaInfoEstrenos(position);
                }if(n==5) {
                    gestorvistas.cargaInfoToprated(position);
                }
                gestorvistas.framelayout(1);


            }
        });


    }




}
