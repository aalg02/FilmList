package com.example.filmlist.CONTROLADOR;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.CONTROLADOR.ManejoImagenes.DownloadImageTask;
import com.example.filmlist.CONTROLADOR.PeticionWeb.peticion2;
import com.example.filmlist.CONTROLADOR.PeticionWeb.youtube;
import com.example.filmlist.MODELO.LeerJsons.LeerInfoCastPeli;
import com.example.filmlist.MODELO.LeerJsons.LeerJsonActor;
import com.example.filmlist.MODELO.LeerJsons.LeerJsonGenerosPelis;
import com.example.filmlist.MODELO.LeerJsons.LeerJsonPeli;
import com.example.filmlist.MODELO.LeerJsons.LeerJsonPelisCartelera;
import com.example.filmlist.MODELO.LeerJsons.leerJsongaleriafotos;
import com.example.filmlist.MODELO.Listas.ListaPelis;
import com.example.filmlist.MODELO.Listas.ListasActores;
import com.example.filmlist.MODELO.Listas.ListasPropias;
import com.example.filmlist.MODELO.objetos.Actor;
import com.example.filmlist.MODELO.objetos.Film;
import com.example.filmlist.MODELO.usuarios.guardardatos;
import com.example.filmlist.MODELO.usuarios.usuario;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;
import com.example.filmlist.VISTA.FragmentManager.MyPagerAdapter;
import com.example.filmlist.VISTA.GestionVistas.gestorVistasGeneral;
import com.example.filmlist.VISTA.RV_Actores.RVunion_A;
import com.example.filmlist.VISTA.RV_Inicial.RVunion;
import com.example.filmlist.VISTA.RV_listaVistas.RVunion_LV;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Iterator;

public class Controlador {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static Controlador miControlador;
    public LeerJsonPelisCartelera LJPC;
    public LeerJsonPelisCartelera LPB;
    public LeerJsonPelisCartelera LPP;
    public LeerJsonPelisCartelera LPE;
    public LeerJsonPelisCartelera LPTP;


    public ViewPager viewPager;
    public MyPagerAdapter adapter;
    public gestorVistasGeneral gestorVistasGeneral;

    public ListaPelis LISTASINICIAL = new ListaPelis();
    public ListasPropias LISTAS = new ListasPropias();
    public usuario usuario1;
    public usuario usuario;
    public ListasActores LISTASACTORES = new ListasActores();
    public ArrayList<String> fotospeli;
    LeerJsonGenerosPelis LJGP;
    StringManager stringManager = new StringManager();

    Actor actoraso;
    youtube youtube;
    private RecyclerView mRecyclerView;

    //----------------------------------------
    public MainActivity miActivity;
    public ControladorFirebase controladorFirebase;
    peticion2 peticionapi ;


    //-----------------LANZA LA PETICION------------------//


    public Controlador(MainActivity miActivity) {
        this.miActivity = miActivity;
        controladorFirebase=new ControladorFirebase(miActivity,this);
        peticionapi = new peticion2(this);
        usuario = new usuario();

    }

    public void getPrevision(MainActivity fromActivity, String url, int n) {

        try {
            this.miActivity = fromActivity;
            peticionapi.requestData(url, n);
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void setActivity(MainActivity act) {
        miActivity = act;
        gestorVistasGeneral =new gestorVistasGeneral(miActivity,this);
    }


    //---------DISTINTAS BUSQUEDAS QUE SE VAN A USAR-------------//


    public void LeerPeliculasCartelera(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 1);
        LISTASINICIAL.setListaFCartelera(LJPC.getListaPeli());
    }

    public void LeerPeliculasBusqueda(String json) {

        LJPC = new LeerJsonPelisCartelera(json, 2);
        LISTASINICIAL.setListaFGenero(LJPC.getListaPeli());
    }

    public void LeerPeliculaspopulares(String json) {

        LPP = new LeerJsonPelisCartelera(json, 3);
        LISTASINICIAL.setListaFpopulares(LPP.getListaPeli());

    }

    public void LeerPeliculasestrenos(String json) {

        LPE = new LeerJsonPelisCartelera(json, 4);
        LISTASINICIAL.setListaFtoprated(LPE.getListaPeli());
    }

    public void LeerPeliculasToprated(String json) {

        LPTP = new LeerJsonPelisCartelera(json, 5);
        LISTASINICIAL.setListaFestrenos(LPTP.getListaPeli());

    }

    public void LeerPeliculasRecomendaciones(String json) {

        LPTP = new LeerJsonPelisCartelera(json, 6);
        LISTASINICIAL.setListaFrecomendaciones(LPTP.getListaPeli());
        new RVunion(miActivity, this,LISTASINICIAL.getListaFrecomendaciones(), stringManager.RECOMENDACIONES);

    }

    public void LeerPeligenero(String json) {

        LPTP = new LeerJsonPelisCartelera(json, 7);
        LISTASINICIAL.setListaFGenero(LPTP.getListaPeli());
        new RVunion(miActivity,this, LISTASINICIAL.getListaFGenero(), stringManager.GENERO);

    }

    public void LeerPeliVistas(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL = new LeerJsonPeli(pelicula);
        LISTAS.getListaFvistas().add(PL.getPeli());
    }

    public void LeerPeliFav(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL = new LeerJsonPeli(pelicula);
        LISTAS.getListaFfavoritas().add(PL.getPeli());
    }

    public void LeerPeliPendiente(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL = new LeerJsonPeli(pelicula);
        LISTAS.getListaFpendientes().add(PL.getPeli());
    }

    public void LeerPelivaloradas(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL = new LeerJsonPeli(pelicula);
        LISTAS.getListaFvaloradas().add(PL.getPeli());

    }

    public void LeerActoresPeli(String json) {
        LeerInfoCastPeli LJCP = new LeerInfoCastPeli(json);
        new RVunion_A(miActivity,this, LISTASACTORES.getListaActoresPeli(), stringManager.ACTORES);
        LISTASACTORES.setListaActoresPeli(LJCP.getListaActore());

    }

    public void LeerActor(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonActor LJA = new LeerJsonActor(pelicula, stringManager.ACTORESFAV);
        LISTASACTORES.getListaActorFav().add(LJA.getActor());


    }

    public void LeerPeliculasActor(String json) {

        LPTP = new LeerJsonPelisCartelera(json, 8);
        LISTASINICIAL.setListaFActores(LPTP.getListaPeli());
        new RVunion(miActivity,this, LISTASINICIAL.getListaFActores(), stringManager.PELISACTORES);

    }

    public void LeerPeliculasActorFav(String json) {

        LPTP = new LeerJsonPelisCartelera(json, 8);
        LISTASINICIAL.setListaFActores(LPTP.getListaPeli());
        new RVunion(miActivity,this, LISTASINICIAL.getListaFActores(), stringManager.ACTORESFAV);

    }

    public void LeerFotosGaleriaPeli(String json) {

        JsonElement pelicula = JsonParser.parseString(json);
        leerJsongaleriafotos LJGF = new leerJsongaleriafotos(pelicula);
        gestorVistasGeneral.gestorinfopeli.dialogGaleria(LJGF.getMaiu());
    }


    public void LeerGenerosPeliculas(String json) {
        JsonElement generosO = JsonParser.parseString(json);
        LJGP = new LeerJsonGenerosPelis(generosO);
        //new RVunion(miActivity, LJPC);
    }


    //---------------------------Si NO tenemos conexion a internet------------------------//
    public void NoConexion() {

        gestorVistasGeneral.NoConexion();
    }


    //-------------------------------CONTROL DEL VIEWPAGE-------------------------------------//

    public void controlViewpage() {

        viewPager = miActivity.findViewById(R.id.viewpage);
        adapter = new MyPagerAdapter(miActivity.getSupportFragmentManager(), miActivity,this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        adapter.notifyDataSetChanged();


    }


    //------------------------------CONTROL DE BUSQUEDAS-------------------------------------//


    public void busqueda() {

        EditText EditT = miActivity.findViewById(R.id.search_bar);
        String text = EditT.getText().toString();
        Glide.with(miActivity).load(0).into((ImageView) miActivity.findViewById(R.id.imagenbusqueda));
        adapter.busca(text);
        adapter.getItem(2);
        LISTASINICIAL.getListaFBusqueda().clear();
        LISTASINICIAL.getListaFGenero().clear();
        new RVunion(miActivity,this, LISTASINICIAL.getListaFBusqueda(), miActivity.getString(R.string.BUSQUEDA));

    }

    //----------------------------CARGA---------------------//

    public void carga() {
        final long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while (elapsedTime < 200) {

            Glide.with(miActivity).asGif().load("loading.gif").into((ImageView) miActivity.findViewById(R.id.error_img));
            //-------------------------------------//
            elapsedTime = System.currentTimeMillis() - startTime;
        }


    }


    //----------------------------CLICK EN LA PELICULA---------------------//


    public void CliclckPelisola(View recycler, Film f) {
        ScrollView scrollview = miActivity.findViewById(R.id.scrollview);

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recomendacion(f);
                rellenarRVActores(f.idFilm);
                scrollview.smoothScrollTo(0, 0);
                gestorVistasGeneral.gestorinfopeli.cargainfopeli(f);
                gestorVistasGeneral.gestorinfopeli.framelayoutPelis(1);
                gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActor(0);
                gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActorFav(0);
                gestorVistasGeneral.gestorinfopeli.floatingMenu(f);
                gestorVistasGeneral.gestorinfopeli.listenerEstrellas(f);

            }
        });
    }


    public void MantenerPelicula(View recycler, String opcion, int n, Film f) {
        recycler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                gestorVistasGeneral.dialogEliminar(opcion, n);
                Toast.makeText(miActivity, f.getNombre(), Toast.LENGTH_SHORT).show();


                return true; // Retorna true para indicar que se ha manejado el evento correctamente
            }
        });
    }


    public void ClickActor(View recycler, Actor actor, String opcion) {
        ScrollView scrollview = miActivity.findViewById(R.id.scrollInfoActor);

        recycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (opcion.equals("ACTORES")) {

                    OtrasPelisActor(actor.getId());
                    gestorVistasGeneral.gestorLayoutInfoActor.listenerActorinfo(actor);
                    gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActor(1);
                    gestorVistasGeneral.gestorLayoutInfoActor.cargaInfoActor(actor);
                }
                if (opcion.equals("ACTORESFAV")) {

                    OtrasPelisActorFav(actor.getId());
                    gestorVistasGeneral.gestorLayoutInfoActor.listenerActorinfo(actor);
                    gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActorFav(1);
                    gestorVistasGeneral.gestorLayoutInfoActor.cargaInfoActorFav(actor);
                }
            }
        });
    }

    public void MantenerActor(View recycler, Actor actor, int n) {
        recycler.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                gestorVistasGeneral.dialogEliminar(stringManager.ACTORESFAV, n);
                Toast.makeText(miActivity, actor.getNombre(), Toast.LENGTH_SHORT).show();
                return true; // Retorna true para indicar que se ha manejado el evento correctamente
            }
        });
    }


    //-----------------GESTION DE LA PAGINA DE LISTAS------------------------------//

    public void RefrscaInicial() {
        HorizontalScrollView scrollgeneros = miActivity.findViewById(R.id.scrollgeneros);
        scrollgeneros.smoothScrollTo(0, 0);

        new RVunion(miActivity, this,LISTASINICIAL.getListaFCartelera(), stringManager.INICIAL);
        new RVunion(miActivity,this, LISTASINICIAL.getListaFBusqueda(), stringManager.BUSQUEDA);
        new RVunion(miActivity,this, LISTASINICIAL.getListaFpopulares(), stringManager.POPULARES);
        new RVunion(miActivity, this,LISTASINICIAL.getListaFestrenos(), stringManager.ESTRENOS);
        new RVunion(miActivity, this,LISTASINICIAL.getListaFtoprated(), stringManager.TOPRATED);
        RefrescaGenero();
    }

    public void RefrescaVistas() {

        new RVunion_LV(miActivity,this, LISTAS.getListaFvistas(), 1, stringManager.VISTAS);
        new RVunion_LV(miActivity,this, LISTAS.getListaFfavoritas(), 2, stringManager.FAVORITAS);
        new RVunion_LV(miActivity,this, LISTAS.getListaFpendientes(), 3, stringManager.PENDIENTES);

    }


    public void RefrescaValoraciones() {
        new RVunion_LV(miActivity,this, LISTAS.getListaFvaloradas(), 4, stringManager.VALORACIONES);
    }

    public void RefrescaGenero() {
        new RVunion(miActivity, this,LISTASINICIAL.getListaFGenero(), stringManager.GENERO);

    }

    public void RefrescaActoresFav() {
        new RVunion_A(miActivity,this, LISTASACTORES.getListaActorFav(), stringManager.ACTORESFAV);

    }


    //-----------------------------RECOMENDACION PELICULAS-----------------------------------//

    public void recomendacion(Film f) {

        String url = stringManager.apiUrl + f.getId() + stringManager.recommendations + stringManager.apiKey;
        LISTASINICIAL.getListaFrecomendaciones().clear();
        getPrevision(miActivity, url, 6);


    }

    //---------------------------------Guardar datos y movidas------------------------//



    public void login() {


    }


    //------------------------RECUPERAR DATOS----------------------------//

    public void recuperarpelis() {

        LISTAS.getListaFvistas().clear();
        LISTAS.getListaFfavoritas().clear();
        LISTAS.getListaFpendientes().clear();
        LISTAS.getListaFvaloradas().clear();
        LISTASACTORES.getListaActorFav().clear();

        if (usuario.getListavistas() != null) {
            for (String id : usuario.getListavistas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                getPrevision(miActivity, url, 7);

            }
        }
        if (usuario.getListafavoritas() != null) {
            for (String id : usuario.getListafavoritas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                getPrevision(miActivity, url, 8);
            }
        }

        if (usuario.getListapendientes() != null) {
            for (String id : usuario.getListapendientes()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                getPrevision(miActivity, url, 9);
            }
        }
        if (usuario.getListavaloradas() != null) {
            for (String id : usuario.getListavaloradas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                getPrevision(miActivity, url, 10);
            }
        }
        if (usuario.getListaActores() != null) {
            for (String id : usuario.getListaActores()) {
                String url = stringManager.urlactor + id + stringManager.apiKey + stringManager.español;
                getPrevision(miActivity, url, 13);
            }
        }

    }

    //------------AUTHENTICATION----------------------//



    //--------------------------------------------------//

    public void logueado() {


    }


    //-------------controles varios-------------//


    public void controlaPeliListaVistas(Film f) {
        boolean miaubool = true;
        Iterator<Film> iterator = LISTAS.getListaFvistas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A VISTAS", Toast.LENGTH_SHORT).show();
            LISTAS.getListaFvistas().add(f);
        }
    }

    public void controlaPeliListaFavoritas(Film f) {
        boolean miaubool = true;
        Iterator<Film> iterator = LISTAS.getListaFfavoritas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A FAVORITAS", Toast.LENGTH_SHORT).show();
            LISTAS.getListaFfavoritas().add(f);
        }
    }

    public void controlaPeliListaPendientes(Film f) {

        boolean miaubool = true;
        Iterator<Film> iterator = LISTAS.getListaFpendientes().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDA A PENDIENTES", Toast.LENGTH_SHORT).show();
            LISTAS.getListaFpendientes().add(f);

        }
    }


    public void controlPelisVaolradas(Film f) {

        boolean miaubool = true;
        Iterator<Film> iterator = LISTAS.getListaFvaloradas().iterator();
        while (iterator.hasNext()) {
            Film filmi = iterator.next();
            if (filmi == null || filmi.idFilm.equals(f.idFilm)) {
                Toast.makeText(miActivity, "Esta pelicula ya la valoraste anteriormente", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            LISTAS.getListaFvaloradas().add(f);
        }
    }

    public void controlActoresFav(Actor actor) {
        boolean miaubool = true;
        Iterator<Actor> iterator = LISTASACTORES.getListaActorFav().iterator();
        while (iterator.hasNext()) {
            Actor Actori = iterator.next();
            if (Actori == null || Actori.getId().equals(actor.getId())) {
                Toast.makeText(miActivity, "Este actor ya esta en la lista", Toast.LENGTH_SHORT).show();
                miaubool = false;
                break;
            }
        }
        if (miaubool) {
            Toast.makeText(miActivity, "AÑADIDO A FAVORITOS", Toast.LENGTH_SHORT).show();
            busquedaActor(actor.getId());
        }
    }


    //--------------OCULTAR TECLADO---------//
    public void ocultateclado() {
        InputMethodManager imm = (InputMethodManager) miActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View vista = miActivity.getCurrentFocus();
        if (vista != null) {
            imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);
        }
    }


    public void galeria() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        miActivity.startActivityForResult(intent, 123);
    }



    //------------------------eliminar peli-------------//

    public void eliminarpeli(String opcion, int n) {
        if (opcion == stringManager.VISTAS) {
            LISTAS.getListaFvistas().remove(n);
            controladorFirebase.firebaseDatabasesetdatos();
        }
        if (opcion == stringManager.FAVORITAS) {
            LISTAS.getListaFfavoritas().remove(n);
            controladorFirebase.firebaseDatabasesetdatos();
        }
        if (opcion == stringManager.PENDIENTES) {
            LISTAS.getListaFpendientes().remove(n);
            controladorFirebase.firebaseDatabasesetdatos();

        }
        if (opcion == stringManager.VALORACIONES) {
            usuario.getValoraciones().remove(LISTAS.getListaFvaloradas().get(n).getId());
            LISTAS.getListaFvaloradas().remove(n);
            controladorFirebase.firebaseDatabasesetdatos();

        }

    }


    public void eliminaractor(int n) {
        usuario.getListaActores().remove(LISTASACTORES.ListaActorFav.get(n).getId());
        LISTASACTORES.getListaActorFav().remove(n);
        controladorFirebase.firebaseDatabasesetdatos();
    }

    public int rellenaValoraciones(int n) {

        return (usuario.getValoraciones().get(usuario.getListavaloradas().get(n)) + 1);
    }

    public void rellenarRVGeneros(int n) {
        LISTASINICIAL.getListaFGenero().clear();
        peticionapi.requestData(stringManager.urlgeneros + stringManager.IDGEN[n], 11);

    }

    public void rellenarRVActores(String idPeli) {
        LISTASACTORES.getListaActoresPeli().clear();
        peticionapi.requestData(stringManager.apiUrl + idPeli + stringManager.urlcast + stringManager.español, 12);
    }


    public void busquedaActor(String idActor) {
        getPrevision(miActivity, stringManager.urlactor + idActor + stringManager.apiKey + stringManager.español, 13);
    }

    public void OtrasPelisActor(String idActor) {

        LISTASINICIAL.getListaFActores().clear();
        getPrevision(miActivity, stringManager.urlactor + idActor + stringManager.urlPliActores + stringManager.español, 14);
    }

    public void OtrasPelisActorFav(String idActor) {

        LISTASINICIAL.getListaFActores().clear();
        getPrevision(miActivity, stringManager.urlactor + idActor + stringManager.urlPliActores + stringManager.español, 15);
    }


    public void BusquedaTrailer(String titulo) {
        youtube = new youtube(this);
        youtube.busqueda(titulo);
    }

    public void cargarGaleriapeli(Film f) {
        f.getFotospeli().clear();
        getPrevision(miActivity, "https://api.themoviedb.org/3/movie/" + f.getId() + "/images?api_key=18f552217e447f369638f70fa4f06a20", 16);
        //gestorvistas.dialogGaleria(f);
    }

    public void descargarWallaper(String url) {
        DownloadImageTask downloadTask = new DownloadImageTask(this);
        downloadTask.execute(url, "miau");
    }


}
















