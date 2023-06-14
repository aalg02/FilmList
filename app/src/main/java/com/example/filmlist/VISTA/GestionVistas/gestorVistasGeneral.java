package com.example.filmlist.VISTA.GestionVistas;


import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;

import com.example.filmlist.VISTA.FragmentManager.MyPagerAdapter;

public class gestorVistasGeneral {


    public gestorLayoutInfoPeli gestorinfopeli;
    public com.example.filmlist.VISTA.GestionVistas.gestorVentanaBusqueda gestorVentanaBusqueda;
    public gestorVentanaPerfil gestorVentanaPerfil;
    public gestorLayoutInfoActor gestorLayoutInfoActor;
    public ViewPager viewPager;
    public MyPagerAdapter adapter;
    public FrameLayout FMI;
    MainActivity mainActivity;
    ImageButton InicioButton;
    ImageView erros_img;
    TextView textoerror;
    Controlador controlador;


    public gestorVistasGeneral(MainActivity mainActivity,Controlador controlador) {
        this.mainActivity = mainActivity;
        this.controlador=controlador;
        gestorinfopeli = new gestorLayoutInfoPeli(mainActivity,controlador, this);
        gestorVentanaBusqueda = new gestorVentanaBusqueda(mainActivity,controlador, this);
        gestorVentanaPerfil = new gestorVentanaPerfil(mainActivity,controlador, this);
        gestorLayoutInfoActor =new gestorLayoutInfoActor(mainActivity,controlador,this);

    }

    //----------LISTENER DE EL MENU PRINCIPAL DE LA APP

    public void menuPrincipalApp() {

        InicioButton = mainActivity.findViewById(R.id.icon_1);
        InicioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                controlador.adapter.getItem(0);
                controlador.viewPager.setCurrentItem(0);
                controlador.RefrscaInicial();
                gestorVentanaBusqueda.Listenergeneros();


            }
        });

        ImageButton BotonListas = mainActivity.findViewById(R.id.icon_4);
        BotonListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.ocultateclado();
                controlador.adapter.getItem(1);
                controlador.viewPager.setCurrentItem(1);

                controlador.RefrescaVistas();


            }
        });
        ImageButton BontonBusqueda = mainActivity.findViewById(R.id.icon_2);
        BontonBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.ocultateclado();
                controlador.adapter.getItem(2);
                controlador.viewPager.setCurrentItem(2);

                gestorVentanaBusqueda.listenersbusqueda();
            }
        });

        ImageButton BotonPerfil = mainActivity.findViewById(R.id.icon_3);
        BotonPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.ocultateclado();
                controlador.adapter.getItem(3);
                controlador.viewPager.setCurrentItem(3);
                controlador.RefrescaValoraciones();
                controlador.RefrescaActoresFav();
                gestorVentanaPerfil.listenerscuandologueado();

                gestorVentanaPerfil.listenersperfilusuario();
            }
        });

        controlador.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Aquí actualiza la información en función de la página seleccionada
                switch (position) {
                    case 0:
                        controlador.ocultateclado();
                        controlador.adapter.getItem(0);
                        gestorVentanaBusqueda.Listenergeneros();
                        controlador.RefrscaInicial();


                        break;

                    case 1:
                        controlador.ocultateclado();
                        // Actualizar información para la página 2
                        controlador.adapter.getItem(1);
                        controlador.RefrescaVistas();


                        break;
                    case 2:
                        controlador.ocultateclado();
                        // Actualizar información para la página 3
                        controlador.adapter.getItem(2);
                        gestorVentanaBusqueda.listenersbusqueda();
                        break;
                    case 3:
                        controlador.ocultateclado();
                        controlador.adapter.getItem(3);
                        gestorVentanaPerfil.listenerscuandologueado();
                        gestorVentanaPerfil.listenersperfilusuario();
                        controlador.RefrescaValoraciones();
                        controlador.RefrescaActoresFav();

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


    public void NoConexion() {

        erros_img = mainActivity.findViewById(R.id.error_img);
        textoerror = mainActivity.findViewById(R.id.pruebas);
        Glide.with(mainActivity).load(R.drawable.iconopalomitas).into(erros_img);
        (textoerror).setText(mainActivity.getString(R.string.sinconexion));
    }

    public void controlViewpage() {

        viewPager = mainActivity.findViewById(R.id.viewpage);
        adapter = new MyPagerAdapter(mainActivity.getSupportFragmentManager(), mainActivity,controlador);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        adapter.notifyDataSetChanged();


    }






    //------------------------FRAMELAYOUTS  GENERALES -------------------------//





    public void framelayoutLogin(int n) {
        FrameLayout FM = mainActivity.findViewById(R.id.framelayoutlogin);


        if (n == 0) {

            FM.setVisibility(View.INVISIBLE);

        }
        if (n == 1) {

            FM.setVisibility(View.VISIBLE);

        }

    }


    //-----------------------LISTENERS GENERALES------------------//

    public void listenersInicioSesion() {
        Button loginB = mainActivity.findViewById(R.id.logoutB);
        Button registro = mainActivity.findViewById(R.id.informacionB);
        Button Invitadob = mainActivity.findViewById(R.id.cambiarModoB);
        EditText nombret = mainActivity.findViewById(R.id.editTextTextEmailAddress);
        EditText pasword = mainActivity.findViewById(R.id.editTextTextPassword);


        loginB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(nombret.getText().toString()) || TextUtils.isEmpty(pasword.getText().toString())) {
                    Toast.makeText(mainActivity, "faltan campos", Toast.LENGTH_LONG).show();

                } else {

                    gestorVentanaPerfil.ponerfoto("android.resource://com.example.filmlist/" + R.drawable.iconoperfil);
                    controlador.ocultateclado();
                    controlador.controladorFirebase.authenticationLogin(nombret.getText().toString(), pasword.getText().toString());
                }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(nombret.getText().toString()) || TextUtils.isEmpty(pasword.getText().toString())) {
                    Toast.makeText(mainActivity, "faltan campos", Toast.LENGTH_LONG).show();

                } else {
                    gestorVentanaPerfil.ponerfoto("https://firebasestorage.googleapis.com/v0/b/filmlist-ed9e7.appspot.com/o/usuario.png?alt=media&token=65e20f64-6750-40de-adc4-385f7c86ec3e");
                    controlador.ocultateclado();
                    controlador.controladorFirebase.authenticationRegistro(nombret.getText().toString(), pasword.getText().toString());
                }
            }
        });
        Invitadob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                framelayoutLogin(0);

                controlador.usuario.setGmail("invitado@gmail.com");
                controlador.usuario.setContraseña("invitado");
                gestorVentanaPerfil.ponerfoto("https://firebasestorage.googleapis.com/v0/b/filmlist-ed9e7.appspot.com/o/usuario.png?alt=media&token=65e20f64-6750-40de-adc4-385f7c86ec3e");
                controlador.showNotification(mainActivity,"HAS ENTRADO COMO INVITADO...","Create una cuenta y guarda tus listas de peliculas , actores favoritos y valoraciones que hagas!!");
            }
        });

    }

    //-------------------------ELIMINAR ELEMENTOS (peliculas y actores )-----------------------------//
    public void dialogEliminar(String opcion, int n) {

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.eliminar2);

        Button eliminarB2 = dialog.findViewById(R.id.buttonSI2);
        Button NoB2 = dialog.findViewById(R.id.buttonNO2);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);


        eliminarB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opcion == "ACTORESFAV") {

                    controlador.RefrescaActoresFav();
                    controlador.controladorActores.eliminaractor(n);
                    dialog.dismiss();
                } else {

                    controlador.controladorPeliculas.eliminarpeli(opcion, n);
                    controlador.RefrescaVistas();
                    controlador.RefrescaValoraciones();
                    dialog.dismiss();
                }
            }
        });

        NoB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        if (dialog != null) {
            dialog.show();
        }
    }





}



