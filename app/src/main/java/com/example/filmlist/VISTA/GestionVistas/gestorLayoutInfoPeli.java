package com.example.filmlist.VISTA.GestionVistas;

import android.app.Dialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.filmlist.CONTROLADOR.Controladores.Controlador;
import com.example.filmlist.MODELO.LeerJsons.LeerJsonPelisCartelera;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class gestorLayoutInfoPeli {
    public FrameLayout FMI;
    MainActivity mainActivity;
    gestorVistasGeneral gestorVistasGeneral;
    ArrayList<ImageView> estrellas;
    Controlador controlador;
    int n;

    public gestorLayoutInfoPeli(MainActivity mainActivity, Controlador controlador, gestorVistasGeneral gestorVistasGeneral) {
        this.mainActivity = mainActivity;
        this.gestorVistasGeneral = gestorVistasGeneral;
        this.controlador = controlador;
        n = 0;
    }


    //------------------------
    public void framelayoutPelis(int n) {
        FMI = mainActivity.findViewById(R.id.framelayoutpelis);

        if (n == 0) {
            FMI.setVisibility(View.INVISIBLE);
        }

        if (n == 1) {
            FMI.setVisibility(View.VISIBLE);
        }

    }

    //----------------------MENU TRES FLOATINGBUTTONS (Trailer , listas , Wallpapers)-------------------//

    public void floatingMenu(Film f) {
        FloatingActionButton fabMain = mainActivity.findViewById(R.id.floatingActionButton);
        FloatingActionButton faT = mainActivity.findViewById(R.id.floatingActionButtonTrailer);
        FloatingActionButton Galeriafotos = mainActivity.findViewById(R.id.floatingActionButtongaleria);
        FloatingActionButton Soundtrack = mainActivity.findViewById(R.id.floatinButtonSoundtrack);

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
                controlador.controladorPeticiones.BusquedaTrailer(f.getNombre());

            }
        });

        Galeriafotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.controladorPeticiones.cargarGaleriapeli(f);

            }
        });

        Soundtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controlador.controladorPeticiones.BusquedaSoundtrack(f.getNombre());


            }
        });

        ImageView flecha = mainActivity.findViewById(R.id.flechatras);
        flecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayoutPelis(0);
                vaciarestrellas();
            }
        });

    }

    //--------------------MENU AÑADIR A LAS DIFERENTES LISTAS (vistas ,favoritas , pendientes)--------------------------//
    public void listenersMenu(Film f) {

        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.floating_menu);

        FloatingActionButton butonvistas = dialog.findViewById(R.id.FBvistas);
        FloatingActionButton butonfavoritas = dialog.findViewById(R.id.FBfavoritas);
        FloatingActionButton butonpendientes = dialog.findViewById(R.id.FBpendientes);


        butonvistas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlador.controladorListas.controlaPeliListaVistas(f);
                dialog.dismiss();
                controlador.controladorFirebase.firebaseDatabasesetdatos();


            }
        });
        butonfavoritas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlador.controladorListas.controlaPeliListaFavoritas(f);
                dialog.dismiss();
                controlador.controladorFirebase.firebaseDatabasesetdatos();


            }
        });
        butonpendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                controlador.controladorListas.controlaPeliListaPendientes(f);
                dialog.dismiss();
                controlador.controladorFirebase.firebaseDatabasesetdatos();


            }
        });


        if (dialog != null) {
            dialog.show();
        }
    }


    //----------------LISTENER ESTRELLAS VALORACION--------------------------//
    public void listenerEstrellas(Film f) {
        ImageView estrella1 = mainActivity.findViewById(R.id.estrella1);
        ImageView estrella2 = mainActivity.findViewById(R.id.estrella2);
        ImageView estrella3 = mainActivity.findViewById(R.id.estrella3);
        ImageView estrella4 = mainActivity.findViewById(R.id.estrella4);
        ImageView estrella5 = mainActivity.findViewById(R.id.estrella5);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);
        estrellas = new ArrayList<>();
        estrellas.add(estrella1);
        estrellas.add(estrella2);
        estrellas.add(estrella3);
        estrellas.add(estrella4);
        estrellas.add(estrella5);

        estrella1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella1.startAnimation(animation);
                rellenarestrellas(0, f);

            }
        });
        estrella2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella2.startAnimation(animation);
                rellenarestrellas(1, f);
            }
        });
        estrella3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella3.startAnimation(animation);
                rellenarestrellas(2, f);
            }
        });
        estrella4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella4.startAnimation(animation);
                rellenarestrellas(3, f);
            }
        });
        estrella5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                estrella5.startAnimation(animation);
                rellenarestrellas(4, f);
            }
        });

    }


    //-------------------------- PINTAR LAS ESTRELLAS Y ANIMACION--------------------------//

    public void rellenarestrellas(int n, Film f) {
        Toast.makeText(mainActivity, "VALORADA CON " + (n + 1) + " ESTRELLAS", Toast.LENGTH_SHORT).show();
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.expansion_estrella);
        for (int i = 0; i <= n; i++) {
            estrellas.get(i).startAnimation(animation);
            Glide.with(mainActivity).load(R.drawable.star).into(estrellas.get(i));

        }
        for (int i = 4; i > n; i--) {
            Glide.with(mainActivity).load(R.drawable.baseline_star_vacia).into(estrellas.get(i));

        }
        if (f != null) {
            try {
                f.setMivaloracion(n);
                controlador.controladorListas.controlPelisVaolradas(f);
                controlador.usuario.getValoraciones().put(f.idFilm, n);
                controlador.controladorFirebase.firebaseDatabasesetdatos();
            }catch (Exception e){

            }
        }

    }

    //*---------------------LOGICA VACIAR ESTRELLAS----------------------------------------//
    public void vaciarestrellas() {
        for (ImageView estrella : estrellas) {
            Glide.with(mainActivity).load(R.drawable.baseline_star_vacia).into(estrella);

        }
    }


    //---------------------VARIOS DIALOG QUE SE USAN-----------------------------------------------//
    //---------------(para abrir el trailer y para la galeria de Wallpapers)-------------//

    public void putoDialogtrailer(String url) {
        Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.layoutreproduccionvideo);

        WebView webView = dialog.findViewById(R.id.webviewtrailer);

        // URL del video que deseas reproducir
        String videoUrl = url;

        // Configura el MediaController para controlar la reproducción del video
        webView.loadUrl(videoUrl);


        dialog.show();
    }

    public void dialogGaleria(ArrayList<String> lista) {
        Toast.makeText(mainActivity, "Manten el Wallpaper que quieras descargar", Toast.LENGTH_SHORT).show();

        n = 0;
        Dialog dialog = new Dialog(mainActivity);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.galerialayout);
        LinearLayout contenedor = dialog.findViewById(R.id.contenedoralomejor);

        for (String imageUrl : lista) {
            ImageView imageView = new ImageView(mainActivity);

            // Establece los parámetros de diseño para el ImageView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
            layoutParams.setMargins(0, 10, 0, 10);
            imageView.setLayoutParams(layoutParams);

            // Carga la imagen utilizando Glide y establece las opciones deseadas
            RequestOptions options = new RequestOptions().override(800, 800); // Tamaño personalizado
            Glide.with(mainActivity)
                    .load("https://image.tmdb.org/t/p/original" + imageUrl)
                    .apply(options)
                    .into(imageView);
            imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    controlador.controladorImagenes.descargarWallaper("https://image.tmdb.org/t/p/original" + imageUrl);
                    return true;
                }
            });
            // Agrega el ImageView al contenedor de imágenes
            contenedor.addView(imageView);


        }
        dialog.show();
    }

    //-------------------------------CARGAR INFOPELI--------------------------------------//
    public void cargainfopeli(Film pelichula) {

        TextView titulo = mainActivity.findViewById(R.id.nombrepeli);
        TextView valoracion = mainActivity.findViewById(R.id.valoracionpeli);
        TextView sinopsis = mainActivity.findViewById(R.id.sinopsispeli);
        TextView añosalida = mainActivity.findViewById(R.id.fechasalidapeli);
        ImageView posterinfo = mainActivity.findViewById(R.id.FotoPosterInfo);
        Animation animation = AnimationUtils.loadAnimation(mainActivity, R.anim.cargapelis);

        LeerJsonPelisCartelera miau = controlador.LJPC;

        titulo.setText(pelichula.getNombre());
        valoracion.setText(pelichula.getValoration());
        sinopsis.setText(pelichula.getSinopsis());
        añosalida.setText(pelichula.getReleasedate());
        posterinfo.startAnimation(animation);
        Glide.with(mainActivity).load(pelichula.getImg_path()).into(posterinfo);


    }
}
