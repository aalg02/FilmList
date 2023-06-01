package com.example.filmlist.CONTROLADOR.Controladores;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
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
import com.example.filmlist.MODELO.usuarios.usuario;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;
import com.example.filmlist.VISTA.FragmentManager.MyPagerAdapter;
import com.example.filmlist.VISTA.GestionVistas.gestorVistasGeneral;
import com.example.filmlist.VISTA.RV_Actores.RVunion_A;
import com.example.filmlist.VISTA.RV_Inicial.RVunion;
import com.example.filmlist.VISTA.RV_listaVistas.RVunion_LV;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

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
    public ControladorPeticiones controladorPeticiones;
    public ControladorPeliculas controladorPeliculas;
    public ControladorActores controladorActores;
    public ControladorListas controladorListas;
    public ControladorImagenes controladorImagenes;
    


    //-----------------LANZA LA PETICION------------------//


    public Controlador(MainActivity miActivity) {
        this.miActivity = miActivity;
        controladorFirebase=new ControladorFirebase(miActivity,this);
        controladorPeticiones=new ControladorPeticiones(miActivity,this);
        controladorPeliculas = new ControladorPeliculas(miActivity,this);
        controladorActores=new ControladorActores(miActivity,this);
        controladorListas = new ControladorListas(miActivity,this);
        controladorImagenes = new ControladorImagenes(miActivity,this);
        usuario = new usuario();

    }

   

    public void setActivity(MainActivity act) {
        miActivity = act;
        gestorVistasGeneral =new gestorVistasGeneral(miActivity,this);
    }


    //---------DISTINTAS BUSQUEDAS QUE SE VAN A USAR-------------//

     public void LeerOpcionyRefrescar(int n,String respuesta){
         if(n==1) {
             controladorPeliculas.LeerPeliculasCartelera(respuesta);
             RefrscaInicial();
         }if(n==2){
             controladorPeliculas.LeerPeliculasBusqueda(respuesta);
             RefrscaInicial();
         }if(n==3){
             controladorPeliculas.LeerPeliculaspopulares(respuesta);
             RefrscaInicial();

         }if(n==4){
             controladorPeliculas.LeerPeliculasestrenos(respuesta);
             RefrscaInicial();
         }if(n==5){
             controladorPeliculas.LeerPeliculasToprated(respuesta);
             RefrscaInicial();
         }if(n==6){
             controladorPeliculas.LeerPeliculasRecomendaciones(respuesta);
         }if(n==7){
             LeerPeliVistas(respuesta);
         }if(n==8){
             LeerPeliFav(respuesta);
         }if(n==9){
             LeerPeliPendiente(respuesta);
         }if(n==10) {
             LeerPelivaloradas(respuesta);
         }if(n==11){
             controladorPeliculas.LeerPeligenero(respuesta);
         }if(n==12){
             LeerActoresPeli(respuesta);
         }if(n==13){
             LeerActor(respuesta);
         }if(n==14){
             controladorPeliculas.LeerPeliculasActor(respuesta);
         }if(n==15){
             controladorPeliculas.LeerPeliculasActorFav(respuesta);
         } if(n==16){
             LeerFotosGaleriaPeli (respuesta);
         }

     }

   //-----------------------------------------------------------------------------------//

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
        LISTASACTORES.setListaActoresPeli(LJCP.getListaActore());
        new RVunion_A(miActivity,this, LISTASACTORES.getListaActoresPeli(), stringManager.ACTORES);

    }

    public void LeerActor(String json) {
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonActor LJA = new LeerJsonActor(pelicula, stringManager.ACTORESFAV);
        LISTASACTORES.getListaActorFav().add(LJA.getActor());


    }


    public void LeerFotosGaleriaPeli(String json) {

        JsonElement pelicula = JsonParser.parseString(json);
        leerJsongaleriafotos LJGF = new leerJsongaleriafotos(pelicula);
        gestorVistasGeneral.gestorinfopeli.dialogGaleria(LJGF.getMaiu());
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

   //---------------------------CUANDO CLICAS PELI---------------------------------------//


    public void cuandoClicasPeli(View recycler, Film f){
        ScrollView scrollview = miActivity.findViewById(R.id.scrollview);
        recomendacion(f);
        controladorPeticiones.rellenarRVActores(f.idFilm);
        scrollview.smoothScrollTo(0, 0);
        gestorVistasGeneral.gestorinfopeli.cargainfopeli(f);
        gestorVistasGeneral.gestorinfopeli.framelayoutPelis(1);
        gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActor(0);
        gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActorFav(0);
        gestorVistasGeneral.gestorinfopeli.floatingMenu(f);
        gestorVistasGeneral.gestorinfopeli.listenerEstrellas(f);
        ocultateclado();
    }


    //-------------------------CUANDO MANTIENES PELI-----------------------------------------//


    public void cuandoMantienesPeli(String opcion, int n, Film f){
        gestorVistasGeneral.dialogEliminar(opcion, n);
        Toast.makeText(miActivity, f.getNombre(), Toast.LENGTH_SHORT).show();
    }

    //--------------------------CUANDO CLICAS ACTOR--------------------------------------------//


    public void cuandoClicasActor(View recycler, Actor actor, String opcion){
        ScrollView scrollview = miActivity.findViewById(R.id.scrollInfoActor);

        if (opcion.equals("ACTORES")) {

            controladorPeticiones.OtrasPelisActor(actor.getId());
            gestorVistasGeneral.gestorLayoutInfoActor.listenerActorinfo(actor);
            gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActor(1);
            gestorVistasGeneral.gestorLayoutInfoActor.cargaInfoActor(actor);
        }
        if (opcion.equals("ACTORESFAV")) {

            controladorPeticiones.OtrasPelisActorFav(actor.getId());
            gestorVistasGeneral.gestorLayoutInfoActor.listenerActorinfo(actor);
            gestorVistasGeneral.gestorLayoutInfoActor.framelayoutActorFav(1);
            gestorVistasGeneral.gestorLayoutInfoActor.cargaInfoActorFav(actor);
        }
    }



    //--------------------------CUANDO MANTIENES ACTOR-----------------------------------------//

    public void cuandoMantienesActor(View recycler, Actor actor, int n){
        gestorVistasGeneral.dialogEliminar(stringManager.ACTORESFAV, n);
        Toast.makeText(miActivity, actor.getNombre(), Toast.LENGTH_SHORT).show();
    }

    //-----------------GESTION REFRESCAR LOS RECICLERVIEW------------------------------//

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
        controladorPeticiones.peticionaWeb(miActivity, url, 6);


    }
    //------------------------RECUPERAR DATOS USUARIO----------------------------//

    public void recuperarpelis() {

        LISTAS.getListaFvistas().clear();
        LISTAS.getListaFfavoritas().clear();
        LISTAS.getListaFpendientes().clear();
        LISTAS.getListaFvaloradas().clear();
        LISTASACTORES.getListaActorFav().clear();

        if (usuario.getListavistas() != null) {
            for (String id : usuario.getListavistas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                controladorPeticiones.peticionaWeb(miActivity, url, 7);

            }
        }
        if (usuario.getListafavoritas() != null) {
            for (String id : usuario.getListafavoritas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                controladorPeticiones.peticionaWeb(miActivity, url, 8);
            }
        }

        if (usuario.getListapendientes() != null) {
            for (String id : usuario.getListapendientes()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                controladorPeticiones.peticionaWeb(miActivity, url, 9);
            }
        }
        if (usuario.getListavaloradas() != null) {
            for (String id : usuario.getListavaloradas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey + stringManager.español;
                controladorPeticiones.peticionaWeb(miActivity, url, 10);
            }
        }
        if (usuario.getListaActores() != null) {
            for (String id : usuario.getListaActores()) {
                String url = stringManager.urlactor + id + stringManager.apiKey + stringManager.español;
                controladorPeticiones.peticionaWeb(miActivity, url, 13);
            }
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


    //------------------------RELLENA VALORACION-------------------------------//
    public int rellenaValoraciones(int n) {

        return (usuario.getValoraciones().get(usuario.getListavaloradas().get(n)) + 1);
    }


}
















