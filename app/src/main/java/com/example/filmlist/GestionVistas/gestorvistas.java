package com.example.filmlist.GestionVistas;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.SegundaActivity;
import com.example.filmlist.StringManager;
import com.uwetrottmann.tmdb2.entities.Image;

import java.io.FileReader;

public class gestorvistas {


    public static gestorvistas gestor;
    MainActivity mainActivity;
    SegundaActivity segundaActivity;

    ImageButton InicioButton;

     ImageView erros_img;
     TextView textoerror;
    StringManager stringManager=new StringManager();
    public ViewPager viewPager;
    public MyPagerAdapter adapter;


    public  gestorvistas getInstance() {
        if (gestor == null)
            gestor = new gestorvistas();
        return gestor;
    }
    public void setActivity(MainActivity act) {
        mainActivity = act;
    }
    public void setActivity2(SegundaActivity act) {
        segundaActivity = act;
    }


    public void listeners(){

        InicioButton  = mainActivity.findViewById(R.id.icon_1);
        InicioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Controlador.getInstance().adapter.getItem(0);
                Controlador.getInstance().viewPager.setCurrentItem(0);

            }
        });

        ImageButton myButton4 = mainActivity.findViewById(R.id.icon_4);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Controlador.getInstance().adapter.getItem(1);
                Controlador.getInstance().viewPager.setCurrentItem(1);

            }
        });
        ImageButton myButton1 = mainActivity.findViewById(R.id.icon_2);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().adapter.getItem(2);
                Controlador.getInstance().viewPager.setCurrentItem(2);
                Controlador.getInstance().busqueda();
            }
        });

        ImageButton myButton2 = mainActivity.findViewById(R.id.icon_3);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().adapter.getItem(3);
                Controlador.getInstance().viewPager.setCurrentItem(3);
            }
        });

        Controlador.getInstance().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Aquí actualiza la información en función de la página seleccionada
                switch (position) {
                    case 0:

                        Controlador.getInstance().adapter.getItem(0);

                        break;

                    case 1:
                        // Actualizar información para la página 2
                        Controlador.getInstance().adapter.getItem(1);
                        break;
                    case 2:
                        // Actualizar información para la página 3
                        Controlador.getInstance().adapter.getItem(2);
                        break;
                    case 3:

                        Controlador.getInstance().adapter.getItem(3);

                        break;
                    // Agregar casos para todas las páginas del ViewPager
                }
            }

            // Los siguientes métodos son opcionales, pero es necesario anularlos
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    public void inicializacion(){

    }

    public void NoConexion(){

        erros_img= mainActivity.findViewById(R.id.error_img);
        textoerror=mainActivity.findViewById(R.id.pruebas);
        Glide.with( mainActivity).load(R.drawable.iconopalomitas).into(erros_img);
        (textoerror).setText(stringManager.sinconexion);
    }

    public void controlViewpage (){

        viewPager = mainActivity.findViewById(R.id.viewpage);
        adapter = new MyPagerAdapter(mainActivity.getSupportFragmentManager(),mainActivity);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        adapter.notifyDataSetChanged();



    }

    public void cargainfoInicio(int n){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);

        LeerJsonPelisCartelera miau=Controlador.getInstance().LJPC;

        titulo.setText(miau.getListaP(1).get(n).getName());
        valoracion.setText(miau.getListaP(1).get(n).getValoration());
        sinopsis.setText(miau.getListaP(1).get(n).getDescription());
        añosalida.setText(miau.getListaP(1).get(n).getReleasedate());
        Glide.with( mainActivity).load(miau.getListaP(1).get(n).getImg_path()).into(posterinfo);



    }


    public void cargaInfoBusqueda(int n){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);


        LeerJsonPelisCartelera miau=Controlador.getInstance().LJPC;

        titulo.setText(miau.getListaP(2).get(n).getName());
        valoracion.setText(miau.getListaP(2).get(n).getValoration());
        sinopsis.setText(miau.getListaP(2).get(n).getDescription());
        añosalida.setText(miau.getListaP(2).get(n).getReleasedate());
        Glide.with( mainActivity).load(miau.getListaP(2).get(n).getImg_path()).into(posterinfo);



    }
    public void cargaInfoPopulares(int n){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);


        LeerJsonPelisCartelera miau=Controlador.getInstance().LPP;

        titulo.setText(miau.getListaP(3).get(n).getName());
        valoracion.setText(miau.getListaP(3).get(n).getValoration());
        sinopsis.setText(miau.getListaP(3).get(n).getDescription());
        añosalida.setText(miau.getListaP(3).get(n).getReleasedate());
        Glide.with( mainActivity).load(miau.getListaP(3).get(n).getImg_path()).into(posterinfo);



    }
    public void cargaInfoEstrenos(int n){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);


        LeerJsonPelisCartelera miau=Controlador.getInstance().LPE;

        titulo.setText(miau.getListaP(4).get(n).getName());
        valoracion.setText(miau.getListaP(4).get(n).getValoration());
        sinopsis.setText(miau.getListaP(4).get(n).getDescription());
        añosalida.setText(miau.getListaP(4).get(n).getReleasedate());
        Glide.with( mainActivity).load(miau.getListaP(4).get(n).getImg_path()).into(posterinfo);



    }
    public void cargaInfoToprated(int n){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);


        LeerJsonPelisCartelera miau=Controlador.getInstance().LPTP;

        titulo.setText(miau.getListaP(5).get(n).getName());
        valoracion.setText(miau.getListaP(5).get(n).getValoration());
        sinopsis.setText(miau.getListaP(5).get(n).getDescription());
        añosalida.setText(miau.getListaP(5).get(n).getReleasedate());
        Glide.with( mainActivity).load(miau.getListaP(5).get(n).getImg_path()).into(posterinfo);



    }

    public void framelayout(int n){
        FrameLayout FM=mainActivity.findViewById(R.id.framelayout);

        if(n==0){
            FM.setVisibility(View.INVISIBLE);}
        if(n==1){
            FM.setVisibility(View.VISIBLE);}
    }






}
