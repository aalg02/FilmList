package com.example.filmlist;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.GestionVistas.gestorvistas;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonGenerosPelis;
import com.example.filmlist.JsonRead.LeerJsonPeli;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.JsonRead.ListaPelis;
import com.example.filmlist.JsonRead.ListasPropias;
import com.example.filmlist.PeticionWeb.peticion2;


import com.example.filmlist.RV_Inicial.RVunion;


import com.example.filmlist.RV_listaVistas.RVunion_LV;
import com.example.filmlist.usuarios.guardardatos;
import com.example.filmlist.usuarios.usuario;
//import com.google.android.gms.common.api.GoogleApi;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.auth.GoogleAuthCredential;
//import com.google.firebase.cloud.FirestoreClient;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    guardardatos guardar=new guardardatos();
    public ViewPager viewPager;
    public MyPagerAdapter adapter;

    private RecyclerView mRecyclerView;
    public gestorvistas gestorvistas;

    public SegundaActivity miau=new SegundaActivity();
    public ListaPelis LISTASINICIAL=new ListaPelis();
    public ListasPropias LISTAS=new ListasPropias();
    public usuario usuario1;
    public usuario usuario=new usuario();
    String Gmail;
//    FirebaseApp Firebase;
//    FirebaseFirestore firestore;
//    Firestore db;







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
//   // public void setFirebaseapp(FirebaseApp miau) {
//         this.Firebase= miau;
//    }





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
    public void LeerPeliVistas(String json){
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL=new LeerJsonPeli(pelicula);
        LISTAS.getListaFvistas().add(PL.getPeli());
    }
    public void LeerPeliFav(String json){
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL=new LeerJsonPeli(pelicula);
        LISTAS.getListaFfavoritas().add(PL.getPeli());
    }
    public void LeerPeliPendiente(String json){
        JsonElement pelicula = JsonParser.parseString(json);
        LeerJsonPeli PL=new LeerJsonPeli(pelicula);
        LISTAS.getListaFpendientes().add(PL.getPeli());
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

        EditText EditT = miActivity.findViewById(R.id.search_bar);
        String text = EditT.getText().toString();
        Glide.with( miActivity).load(0).into((ImageView)miActivity.findViewById(R.id.imagenbusqueda));
        adapter.busca(text);
        adapter.getItem(2);
        LISTASINICIAL.getListaFBusqueda().clear();
        new RVunion(miActivity, LISTASINICIAL.getListaFBusqueda(),stringManager.BUSQUEDA);

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
    //---------------------------------Guardar datos y movidas------------------------//



    public void firebaseDatabasesetdatos(){

        guardar.guardalistasusuarios();
        int arrobaPosicion = usuario.getGmail().indexOf('@');
        String nombreUsuario = usuario.getGmail().substring(0, arrobaPosicion);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference(nombreUsuario);
        mDatabase.setValue(null);
        mDatabase.setValue(usuario);


    }

    public void registroDatabase(String gmail,String contraseña){
        guardar.guardarusuario(gmail,contraseña);
        int arrobaPosicion = gmail.indexOf('@');
        String nombreUsuario = gmail.substring(0, arrobaPosicion);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        mDatabase.child(nombreUsuario).setValue(usuario);
    }

    public void firebaseDatabasegetdatos(String gmail,String contraseña){


        int arrobaPosicion = gmail.indexOf('@');
        String nombreUsuario = gmail.substring(0, arrobaPosicion);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference(nombreUsuario);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario1 = dataSnapshot.getValue(usuario.class);
                if(usuario1==null){
                    Toast.makeText(miActivity, "Este usuario no existe tio", Toast.LENGTH_SHORT).show();
                }else {
                    usuario.setGmail(usuario1.getGmail());
                    usuario.setContraseña(usuario1.getContraseña());

                    recuperarpelis();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores
            }
        });
    }

    public void login(){


    }


    //------------------------RECUPERAR DATOS----------------------------//

    public void recuperarpelis(){
        usuario.setListavistas(usuario1.getListavistas());
        usuario.setListafavoritas(usuario1.getListafavoritas());
        usuario.setListapendientes(usuario1.getListapendientes());
        LISTAS.getListaFvistas().clear();
        LISTAS.getListaFfavoritas().clear();
        LISTAS.getListaFpendientes().clear();

        if(usuario.getListavistas()!=null) {
            for (String id : usuario.getListavistas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey;
                getPrevision(miActivity, url, 7);

            }
        }
        if(usuario.getListafavoritas()!=null) {
            for (String id : usuario.getListafavoritas()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey;
                getPrevision(miActivity, url, 8);
            }
        }

        if(usuario.getListapendientes()!=null) {
            for (String id : usuario.getListapendientes()) {
                String url = stringManager.apiUrl + id + stringManager.apiKey;
                getPrevision(miActivity, url, 9);
            }
        }

    }

    //------------AUTHENTICATION----------------------//


    public void authenticationRegistro(String gmail,String contraseña){


        if(gmail==null||contraseña==null){
            Toast.makeText(miActivity, "faltan campos", Toast.LENGTH_LONG).show();

        }else {

            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(gmail, contraseña)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Registro exitoso
                            registroDatabase(gmail, contraseña);
                            gestorvistas.framelayoutLogin(0);
                        } else {
                            // Registro fallido
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthWeakPasswordException e) {
                                String errorMessage = "La contraseña es demasiado corta (mínimo " + "6" + " caracteres)";
                                Toast.makeText(miActivity, errorMessage, Toast.LENGTH_LONG).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(miActivity, "Credenciales de autenticación inválidas", Toast.LENGTH_LONG).show();
                            } catch (FirebaseAuthUserCollisionException e) {
                                Toast.makeText(miActivity, "El usuario ya existe", Toast.LENGTH_LONG).show();
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(miActivity, "Error de red", Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(miActivity, "Error al crear la cuenta: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    public void authenticationLogin(String gmail,String contraseña) {


            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.signInWithEmailAndPassword(gmail, contraseña)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            firebaseDatabasegetdatos(gmail, contraseña);
                            gestorvistas.framelayoutLogin(0);
                        } else {
                            try {
                                throw task.getException();

                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(miActivity, "la contraseña no coincide con la del correo ", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidUserException e) {

                                Toast.makeText(miActivity, "El correo no esta registrado", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(miActivity, "Error al crear la cuenta: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

    }


    public void authenticationlogout(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }



    //--------------------------------------------------//

    public void logueado(){



    }













}
