package com.example.filmlist.GestionVistas;



import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.res.Configuration;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.filmlist.Controlador;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.JsonRead.Actor;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.SegundaActivity;
import com.example.filmlist.StringManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class gestorvistas {


    public static gestorvistas gestor;
    MainActivity mainActivity;
    SegundaActivity segundaActivity;
    private boolean isDarkModeEnabled = false;
    ImageButton InicioButton;

     ImageView erros_img;
     TextView textoerror;
    StringManager stringManager=new StringManager();
    public ViewPager viewPager;
    public MyPagerAdapter adapter;
    public FrameLayout FMI;

    ArrayList<ImageView> estrellas;



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
                Listenergeneros();



            }
        });

        ImageButton myButton4 = mainActivity.findViewById(R.id.icon_4);
        myButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().ocultateclado();
                Controlador.getInstance().adapter.getItem(1);
                Controlador.getInstance().viewPager.setCurrentItem(1);

                Controlador.getInstance().RefrescaVistas();


            }
        });
        ImageButton myButton1 = mainActivity.findViewById(R.id.icon_2);
        myButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().ocultateclado();
                Controlador.getInstance().adapter.getItem(2);
                Controlador.getInstance().viewPager.setCurrentItem(2);

                listenersbusqueda();
            }
        });

        ImageButton myButton2 = mainActivity.findViewById(R.id.icon_3);
        myButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().ocultateclado();
                Controlador.getInstance().adapter.getItem(3);
                Controlador.getInstance().viewPager.setCurrentItem(3);
                Controlador.getInstance().RefrescaValoraciones();
                Controlador.getInstance().RefrescaActoresFav();
                listenerscuandologueado();

                listenersperfilusuario();
            }
        });

        Controlador.getInstance().viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // Aquí actualiza la información en función de la página seleccionada
                switch (position) {
                    case 0:
                        Controlador.getInstance().ocultateclado();
                        Controlador.getInstance().adapter.getItem(0);
                        Listenergeneros();
                        Controlador.getInstance().RefrscaInicial();


                        break;

                    case 1:
                        Controlador.getInstance().ocultateclado();
                        // Actualizar información para la página 2
                        Controlador.getInstance().adapter.getItem(1);
                        Controlador.getInstance().RefrescaVistas();


                        break;
                    case 2:
                        Controlador.getInstance().ocultateclado();
                        // Actualizar información para la página 3
                        Controlador.getInstance().adapter.getItem(2);
                        listenersbusqueda();
                        break;
                    case 3:
                        Controlador.getInstance().ocultateclado();
                        Controlador.getInstance().adapter.getItem(3);
                        listenerscuandologueado();

                        listenersperfilusuario();
                        Controlador.getInstance().RefrescaValoraciones();
                        Controlador.getInstance().RefrescaActoresFav();

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


    public void cargainfopeli(Film pelichula){

        TextView   titulo=mainActivity.findViewById(R.id.nombrepeli);
        TextView   valoracion=mainActivity.findViewById(R.id.valoracionpeli);
        TextView   sinopsis=mainActivity.findViewById(R.id.sinopsispeli);
        TextView   añosalida=mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoPosterInfo);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);

        LeerJsonPelisCartelera miau=Controlador.getInstance().LJPC;

        titulo.setText(pelichula.getNombre());
        valoracion.setText(pelichula.getValoration());
        sinopsis.setText(pelichula.getSinopsis());
        añosalida.setText(pelichula.getReleasedate());
        posterinfo.startAnimation(animation);
        Glide.with( mainActivity).load(pelichula.getImg_path()).into(posterinfo);


    }
    public void cargaInfoActor(Actor actor){

        TextView   titulo=mainActivity.findViewById(R.id.NombreActor);
        TextView   valoracion=mainActivity.findViewById(R.id.Rol);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoActor);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);


        titulo.setText(actor.getNombre());
        valoracion.setText(actor.getRol());
        posterinfo.startAnimation(animation);
        Glide.with( mainActivity).load(actor.getProfile_path()).into(posterinfo);


    }
    public void cargaInfoActorFav(Actor actor){

        TextView   titulo=mainActivity.findViewById(R.id.NombreActorFav);
        TextView   genero=mainActivity.findViewById(R.id.generoActorFav);
        TextView   biografia=mainActivity.findViewById(R.id.biografiaActorFav);
        ImageView  posterinfo=mainActivity.findViewById(R.id.FotoActorFav);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);


        titulo.setText(actor.getNombre());
        genero.setText(actor.getCumpleaños());
        biografia.setText(actor.getBiografia());
        posterinfo.startAnimation(animation);
        Glide.with( mainActivity).load(actor.getProfile_path()).into(posterinfo);


    }



    public void framelayoutinicio(int n){
         FMI=mainActivity.findViewById(R.id.framelayoutpelis);

        if(n==0){
            FMI.setVisibility(View.INVISIBLE);
        Controlador.getInstance().adapter.Fragment1.setMenuVisibility(true);
        }


        if(n==1){
            FMI.setVisibility(View.VISIBLE);
            Controlador.getInstance().adapter.Fragment1.setMenuVisibility(false);


        }

    }
    public void framelayoutActor(int n){
        FMI=mainActivity.findViewById(R.id.framelayoutActor);

        if(n==0){
            FMI.setVisibility(View.INVISIBLE);

        }


        if(n==1){
            FMI.setVisibility(View.VISIBLE);



        }

    }

    public void framelayoutActorFav(int n){
        FMI=mainActivity.findViewById(R.id.framelayoutActorfav);

        if(n==0){
            FMI.setVisibility(View.INVISIBLE);

        }


        if(n==1){
            FMI.setVisibility(View.VISIBLE);



        }
    }



    public void framelayoutLogin(int n){
        FrameLayout FM=mainActivity.findViewById(R.id.framelayoutlogin);


        if(n==0){

            FM.setVisibility(View.INVISIBLE);

        }
        if(n==1){

            FM.setVisibility(View.VISIBLE);

        }

    }






    public void floatingMenu(Film f){
        FloatingActionButton fabMain = mainActivity.findViewById(R.id.floatingActionButton);
        FloatingActionButton faT = mainActivity.findViewById(R.id.floatingActionButtonTrailer);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);


        fabMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabMain.startAnimation(animation);
                Toast.makeText(mainActivity, "SELECIONA LA LISTA A LA QUE QUIERES AÑADIRLA", Toast.LENGTH_SHORT).show();
                listenersMenu(f);


            }
        });
        faT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Controlador.getInstance().BusquedaTrailer(f.getNombre());

            }
        });

        ImageView flecha=mainActivity.findViewById(R.id.flechatras);
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutinicio(0);
                vaciarestrellas();
            }
        });

    }


    public void listenersMenu(Film f){

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.floating_menu);

        FloatingActionButton butonvistas = dialog.findViewById(R.id.FBvistas);
        FloatingActionButton butonfavoritas = dialog.findViewById(R.id.FBfavoritas);
        FloatingActionButton butonpendientes = dialog.findViewById(R.id.FBpendientes);


        butonvistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Controlador.getInstance().controlaPeliListaVistas(f);
                dialog.dismiss();
                Controlador.getInstance().firebaseDatabasesetdatos();


            }
        });
        butonfavoritas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Controlador.getInstance().controlaPeliListaFavoritas(f);
                dialog.dismiss();
                Controlador.getInstance().firebaseDatabasesetdatos();


            }
        });
        butonpendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Controlador.getInstance().controlaPeliListaPendientes(f);
                dialog.dismiss();
                Controlador.getInstance().firebaseDatabasesetdatos();


            }
        });


      if(dialog!=null){
          dialog.show();
      }
    }

    public void listenersperfil(){
        Button boton=mainActivity.findViewById(R.id.logoutB);
        Button registro=mainActivity.findViewById(R.id.informacionB);
        Button guardarlistas=mainActivity.findViewById(R.id.cambiarModoB);
        EditText nombret=mainActivity.findViewById(R.id.editTextTextEmailAddress);
        EditText pasword=mainActivity.findViewById(R.id.editTextTextPassword);



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(TextUtils.isEmpty(nombret.getText().toString())||TextUtils.isEmpty(pasword.getText().toString())){
                Toast.makeText(mainActivity, "faltan campos", Toast.LENGTH_LONG).show();

            }else {
                ponerfoto("android.resource://com.example.filmlist/" + R.drawable.iconoperfil );
                Controlador.getInstance().ocultateclado();
                Controlador.getInstance().authenticationLogin(nombret.getText().toString(), pasword.getText().toString());
            }
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(nombret.getText().toString())||TextUtils.isEmpty(pasword.getText().toString())){
                    Toast.makeText(mainActivity, "faltan campos", Toast.LENGTH_LONG).show();

                }else {
                    ponerfoto("android.resource://com.example.filmlist/" + R.drawable.iconoperfil );
                    Controlador.getInstance().ocultateclado();
                    Controlador.getInstance().authenticationRegistro(nombret.getText().toString(), pasword.getText().toString());
                }
            }
        });
        guardarlistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                framelayoutLogin(0);
                Controlador.getInstance().usuario.setGmail("invitado@gmail.com");
                Controlador.getInstance().usuario.setContraseña("invitado");
                ponerfoto("android.resource://com.example.filmlist/" + R.drawable.iconoperfil );

            }
        });

    }




    public void listenersbusqueda(){
        ImageView imagen = mainActivity.findViewById(R.id.search_icon);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().ocultateclado();
                Controlador.getInstance().busqueda();
            }
        });



    }

    public void listenerscuandologueado(){
        TextView corretotx=mainActivity.findViewById(R.id.gmailtxv);
        corretotx.setText(Controlador.getInstance().usuario.getGmail());
        if(Controlador.getInstance().usuario.getFotoperfil()==""){
            //ponerfoto(Uri.parse("android.resource://com.example.filmlist/" + R.drawable.iconoperfil) );
        }else {
            ponerfoto(Controlador.getInstance().usuario.getFotoperfil());
            //ponerfoto(Uri.parse("android.resource://com.example.filmlist/" + R.drawable.iconoperfil) );

        }
    }

    public void listenersperfilusuario(){

        viewPager=mainActivity.findViewById(R.id.viewpage);
        ImageView fotoperfil = mainActivity.findViewById(R.id.profile_image);
        FloatingActionButton ajustes=mainActivity.findViewById(R.id.floatingAjustes);


        ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAjustes().show();
            }
        });

        fotoperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().galeria();
            }
        });
    }




    public Dialog dialogAjustes(){

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.ajustes);


        Button logout=dialog.findViewById(R.id.logoutB);
        Button informacion=dialog.findViewById(R.id.informacionB);
        Button modo=dialog.findViewById(R.id.cambiarModoB);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutLogin(1);
                Controlador.getInstance().authenticationlogout();
                viewPager.setCurrentItem(0);

            }
        });

        informacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mainActivity, "no hay informacion disponible aun lo siento", Toast.LENGTH_LONG).show();

            }
        });

        modo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dialog.dismiss();

            }
        });
        return dialog;
    }
    public void listenerActorinfo(Actor actor){


        ImageView flecha=mainActivity.findViewById(R.id.flechatras2);
        ImageView flecha2=mainActivity.findViewById(R.id.flechatras3);
        FloatingActionButton botonFavActor=mainActivity.findViewById(R.id.floatingActionButtonActoresFav);
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutActor(0);

            }
        });
        flecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutActorFav(0);

            }
        });

        botonFavActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Controlador.getInstance().controlActoresFav(actor);
                Controlador.getInstance().RefrescaActoresFav();
                Controlador.getInstance().firebaseDatabasesetdatos();
            }
        });


    }






    //--------------modo oscuro/claro-------------//

    @SuppressLint("WrongConstant")
    public void claroscuro(){
        int modoActual = mainActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        int nuevoModo;
        if (modoActual == Configuration.UI_MODE_NIGHT_YES) {
            nuevoModo = Configuration.UI_MODE_NIGHT_NO;
        } else {
            nuevoModo = Configuration.UI_MODE_NIGHT_YES;
        }
        AppCompatDelegate.setDefaultNightMode(nuevoModo);

    }
    public void ponerfoto(String url) {
        ImageView fotoperfil = mainActivity.findViewById(R.id.profile_image);
        Glide.with(mainActivity).load(url).transform(new CircleCrop()).into(fotoperfil);
    }

    //------------------------------------------------------//
    public void dialogEliminarPeli(String opcion ,int n){

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.eliminar2);

        Button eliminarB2=dialog.findViewById(R.id.buttonSI2);
        Button NoB2=dialog.findViewById(R.id.buttonNO2);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);



        eliminarB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(opcion=="ACTORESFAV"){

                    Controlador.getInstance().RefrescaActoresFav();
                    Controlador.getInstance().eliminaractor(n);
                    dialog.dismiss();
                }else {

                    Controlador.getInstance().eliminarpeli(opcion, n);
                    Controlador.getInstance().RefrescaVistas();
                    Controlador.getInstance().RefrescaValoraciones();
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

        if(dialog!=null){
            dialog.show();
        }
    }


    public void listenerEstrellas(Film f){
        ImageView estrella1=mainActivity.findViewById(R.id.estrella1);
        ImageView estrella2=mainActivity.findViewById(R.id.estrella2);
        ImageView estrella3=mainActivity.findViewById(R.id.estrella3);
        ImageView estrella4=mainActivity.findViewById(R.id.estrella4);
        ImageView estrella5=mainActivity.findViewById(R.id.estrella5);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);
        estrellas=new ArrayList<>();
        estrellas.add(estrella1);
        estrellas.add(estrella2);
        estrellas.add(estrella3);
        estrellas.add(estrella4);
        estrellas.add(estrella5);

        estrella1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella1.startAnimation(animation);
                rellenarestrellas(0,f);

            }
        });
        estrella2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella2.startAnimation(animation);
                rellenarestrellas(1,f);
            }
        });
        estrella3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella3.startAnimation(animation);
                rellenarestrellas(2,f);
            }
        });
        estrella4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella4.startAnimation(animation);
                rellenarestrellas(3,f);
            }
        });
        estrella5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella5.startAnimation(animation);
                rellenarestrellas(4,f);
            }
        });

    }

    public void rellenarestrellas(int n, Film f){
        Toast.makeText(mainActivity, "VALORADA CON "+(n+1)+" ESTRELLAS", Toast.LENGTH_SHORT).show();
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);
        for(int i=0; i<=n;i++){
            estrellas.get(i).startAnimation(animation);
            Glide.with( mainActivity).load(R.drawable.star).into(estrellas.get(i));

        }
        for(int i=4; i>n;i--){
            Glide.with( mainActivity).load(R.drawable.baseline_star_vacia).into(estrellas.get(i));

        }
        if(f!=null){
        f.setMivaloracion(n);
        Controlador.getInstance().controlPelisVaolradas(f);
        Controlador.getInstance().usuario.getValoraciones().put(f.idFilm,n);
        Controlador.getInstance().firebaseDatabasesetdatos();
        }

    }

    public void vaciarestrellas(){
        for(ImageView estrella:estrellas){
            Glide.with( mainActivity).load(R.drawable.baseline_star_vacia).into(estrella);

        }
    }

    //------------------------------------------------------//




    public void Listenergeneros(){
        CardView card=mainActivity.findViewById(R.id.mau0);
        for (int i = 0; i <= 17; i++) {
            int n = i;
            int id = mainActivity.getResources().getIdentifier("mau" + i, "id", mainActivity.getPackageName());
            card = mainActivity.findViewById(id);
            if (card != null) {
                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Glide.with( mainActivity).load(0).into((ImageView)mainActivity.findViewById(R.id.imagenbusqueda));
                        Controlador.getInstance().rellenarRVGeneros(n);
                        Controlador.getInstance().RefrescaGenero();
                    }
                });
            }
        }



    }

    public void putoDialogtrailer(String url){
        Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.layoutreproduccionvideo);

        WebView webView = dialog.findViewById(R.id.webviewtrailer);

        // URL del video que deseas reproducir
        String videoUrl =url;

        // Configura el MediaController para controlar la reproducción del video
        webView.loadUrl(videoUrl);


         dialog.show();
    }


}
