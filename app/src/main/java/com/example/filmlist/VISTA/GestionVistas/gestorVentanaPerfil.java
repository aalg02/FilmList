package com.example.filmlist.VISTA.GestionVistas;

import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class gestorVentanaPerfil {

    MainActivity mainActivity;
    gestorVistasGeneral gestorVistasGeneral;
    Controlador controlador;
    public gestorVentanaPerfil(MainActivity mainActivity,Controlador controlador, gestorVistasGeneral gestorVistasGeneral){
        this.mainActivity=mainActivity;
        this.gestorVistasGeneral = gestorVistasGeneral;
        this.controlador=controlador;
    }

    public Dialog dialogAjustes() {

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.ajustes);


        Button logout = dialog.findViewById(R.id.logoutB);
        Button informacion = dialog.findViewById(R.id.informacionB);
        Button modo = dialog.findViewById(R.id.cambiarModoB);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestorVistasGeneral.framelayoutLogin(1);
                controlador.controladorFirebase.authenticationlogout();
                gestorVistasGeneral.viewPager.setCurrentItem(0);
                dialog.dismiss();
                controlador.borrarDatosUser();
            }
        });

        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             dialogInfo().show();
            }
        });

        modo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                mainActivity.finish();

                controlador.showNotification(mainActivity,"ADIOS, VUELVE PRONTO!","¿Ya te vas "+controlador.usuario.getGmail()+"?... Vuelve pronto !!");



            }
        });
        return dialog;
    }


    public void listenersperfilusuario() {

        gestorVistasGeneral.viewPager = mainActivity.findViewById(R.id.viewpage);
        ImageView fotoperfil = mainActivity.findViewById(R.id.profile_image);
        FloatingActionButton ajustes = mainActivity.findViewById(R.id.floatingAjustes);


        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAjustes().show();
            }
        });

        fotoperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.controladorImagenes.galeria();
            }
        });
    }



    public void listenerscuandologueado() {
        TextView corretotx = mainActivity.findViewById(R.id.gmailtxv);
        corretotx.setText(controlador.usuario.getGmail());
        if (controlador.usuario.getFotoperfil() == "") {
            //ponerfoto(Uri.parse("android.resource://com.example.filmlist/" + R.drawable.iconoperfil) );
        } else {
            ponerfoto(controlador.usuario.getFotoperfil());
            //ponerfoto(Uri.parse("android.resource://com.example.filmlist/" + R.drawable.iconoperfil) );

        }
    }


    public void ponerfoto(String url) {
        ImageView fotoperfil = mainActivity.findViewById(R.id.profile_image);
        Glide.with(mainActivity).load(url).transform(new CircleCrop()).into(fotoperfil);
    }


    public Dialog dialogInfo() {

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialoginfo);


        ImageView instagram = dialog.findViewById(R.id.instagram);
        ImageView github = dialog.findViewById(R.id.github);
        ImageView linkedin = dialog.findViewById(R.id.linkedin);



        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               controlador.abrirInstagram();
            }
        });

        github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             controlador.abrirGithub();

            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                controlador.abrirLinkedin();

            }
        });


        return dialog;
    }

}
