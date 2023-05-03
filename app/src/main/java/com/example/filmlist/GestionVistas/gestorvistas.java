package com.example.filmlist.GestionVistas;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.Controlador;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.SegundaActivity;
import com.example.filmlist.StringManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
                Controlador.getInstance().RefrscaInicial();


            }
        });

        ImageButton myButton4 = mainActivity.findViewById(R.id.icon_4);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Controlador.getInstance().adapter.getItem(1);
                Controlador.getInstance().viewPager.setCurrentItem(1);
                Controlador.getInstance().RefrescaVistas();


            }
        });
        ImageButton myButton1 = mainActivity.findViewById(R.id.icon_2);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().adapter.getItem(2);
                Controlador.getInstance().viewPager.setCurrentItem(2);
                listenersbusqueda();
            }
        });

        ImageButton myButton2 = mainActivity.findViewById(R.id.icon_3);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenersperfil();
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
                        Controlador.getInstance().RefrscaInicial();


                        break;

                    case 1:
                        // Actualizar información para la página 2
                        Controlador.getInstance().adapter.getItem(1);
                        Controlador.getInstance().RefrescaVistas();


                        break;
                    case 2:
                        // Actualizar información para la página 3
                        Controlador.getInstance().adapter.getItem(2);
                        listenersbusqueda();
                        break;
                    case 3:
                        listenersperfil();
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

    public void cargainfoInicio(int n,String opcion){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);

        LeerJsonPelisCartelera miau=Controlador.getInstance().LJPC;

        titulo.setText(Controlador.getInstance().LISTASINICIAL.damelista(opcion).get(n).getNombre());
        valoracion.setText(Controlador.getInstance().LISTASINICIAL.damelista(opcion).get(n).getValoration());
        sinopsis.setText(Controlador.getInstance().LISTASINICIAL.damelista(opcion).get(n).getSinopsis());
        añosalida.setText(Controlador.getInstance().LISTASINICIAL.damelista(opcion).get(n).getReleasedate());
        Glide.with( mainActivity).load(Controlador.getInstance().LISTASINICIAL.damelista(opcion).get(n).getImg_path()).into(posterinfo);


    }
     public void cargainfoMislistas(int n,String opcion){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);

        LeerJsonPelisCartelera miau=Controlador.getInstance().LJPC;

        titulo.setText(Controlador.getInstance().LISTAS.damelista(opcion).get(n).getNombre());
        valoracion.setText(Controlador.getInstance().LISTAS.damelista(opcion).get(n).getValoration());
        sinopsis.setText(Controlador.getInstance().LISTAS.damelista(opcion).get(n).getSinopsis());
        añosalida.setText(Controlador.getInstance().LISTAS.damelista(opcion).get(n).getReleasedate());
        Glide.with( mainActivity).load(Controlador.getInstance().LISTAS.damelista(opcion).get(n).getImg_path()).into(posterinfo);

    }



    public void framelayoutinicio(int n){
        FrameLayout FM=mainActivity.findViewById(R.id.framelayout);

        if(n==0){
            FM.setVisibility(View.INVISIBLE);
        Controlador.getInstance().adapter.Fragment1.setMenuVisibility(true);
        }

            Controlador.getInstance().clearrecomendacion();
        if(n==1){
            FM.setVisibility(View.VISIBLE);
            Controlador.getInstance().adapter.Fragment1.setMenuVisibility(false);


        }

    }

    public void framelayoutFloatingB(int n){
        FrameLayout FM=mainActivity.findViewById(R.id.framelayoutFloatingB);

        if(n==0){
            FM.setVisibility(View.INVISIBLE);

        }
        if(n==1){
            FM.setVisibility(View.VISIBLE);

        }

    }


    public void floatingMenu(Film f){
        FloatingActionButton fabMain = mainActivity.findViewById(R.id.floatingActionButton);

        framelayoutFloatingB(0);


// Configura un clic para el botón principal
        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mainActivity, "SELECIONA LA LISTA A LA QUE QUIERES AÑADIRLA", Toast.LENGTH_SHORT).show();
                framelayoutFloatingB(1);
                listenersMenu(f);


            }
        });

        ImageView flecha=mainActivity.findViewById(R.id.flechatras);
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutinicio(0);
            }
        });

    }


    public void listenersMenu(Film f){

        FloatingActionButton butonvistas = mainActivity.findViewById(R.id.FBvistas);
        FloatingActionButton butonfavoritas = mainActivity.findViewById(R.id.FBfavoritas);
        FloatingActionButton butonpendientes = mainActivity.findViewById(R.id.FBpendientes);


        butonvistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mainActivity, "AÑADIDA A VISTAS", Toast.LENGTH_SHORT).show();
                Controlador.getInstance().LISTAS.addvistas(f);
                framelayoutFloatingB(0);


            }
        });
        butonfavoritas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mainActivity, "AÑADIDA A FAVORITAS", Toast.LENGTH_SHORT).show();
                Controlador.getInstance().LISTAS.addfavoritas(f);
                framelayoutFloatingB(0);


            }
        });
        butonpendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mainActivity, "AÑADIDA A PENDIENTES", Toast.LENGTH_SHORT).show();
                Controlador.getInstance().LISTAS.addpendientes(f);
                framelayoutFloatingB(0);


            }
        });



    }

    public void listenersperfil(){
        Button boton=mainActivity.findViewById(R.id.iniciosesionB);
        Button registro=mainActivity.findViewById(R.id.registroB);
        Button guardarlistas=mainActivity.findViewById(R.id.guardarB);
        Button logout=mainActivity.findViewById(R.id.eliminarB);
        EditText nombret=mainActivity.findViewById(R.id.editTextTextEmailAddress);
        EditText pasword=mainActivity.findViewById(R.id.editTextTextPassword);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            Controlador.getInstance().authenticationLogin(nombret.getText().toString(),pasword.getText().toString());

            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().authenticationRegistro(nombret.getText().toString(),pasword.getText().toString());
            }
        });
        guardarlistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Controlador.getInstance().firebaseDatabasesetdatos();

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Controlador.getInstance().authenticationlogout();;

            }
        });




    }


    public void listenersbusqueda(){
        ImageView imagen = mainActivity.findViewById(R.id.search_icon);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Controlador.getInstance().busqueda();
            }
        });



    }










}
