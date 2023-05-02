package com.example.filmlist;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.filmlist.FragmentManager.MyFragment;
import com.example.filmlist.FragmentManager.MyPagerAdapter;
import com.example.filmlist.GestionVistas.gestorvistas;
import com.example.filmlist.JsonRead.Film;
import com.example.filmlist.JsonRead.LeerJsonGenerosPelis;
import com.example.filmlist.JsonRead.LeerJsonPelisCartelera;
import com.example.filmlist.JsonRead.ListaPelis;
import com.example.filmlist.JsonRead.ListasPropias;
import com.example.filmlist.PeticionWeb.peticion2;


import com.example.filmlist.RV_Inicial.RVunion;


import com.example.filmlist.RV_listaVistas.RVAdapter_LV;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
                LISTASINICIAL.getListaFBusqueda().clear();
                new RVunion(miActivity, LISTASINICIAL.getListaFBusqueda(),stringManager.BUSQUEDA);

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


//    public void guardadatos(){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//      guardardatos guardar=new guardardatos();
//      EditText nombret=miActivity.findViewById(R.id.first_name2);
//      EditText pasword=miActivity.findViewById(R.id.pasword);
//      usuario.setGmail(nombret.getText().toString());
//      usuario.setContraseña(pasword.getText().toString());
//      guardar.guardalistasusuarios();
//
//
//
//        Map<String,Object>map=new HashMap<>();
//        map.put("gmail","manuel");
//        map.put("contraseña","1234");
//            db.collection("users").document("user1").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void unused) {
//                    Toast.makeText(miActivity, "EXITO AL AÑADIR", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//    }
//
//    public void recuperardatos(){
//
//        TextView contraseñat=miActivity.findViewById(R.id.contraseña);
//        TextView nombre=miActivity.findViewById(R.id.last_name2);
//
//        firestore = FirebaseFirestore.getInstance();
//        firestore.collection("users")
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot querySnapshot) {
//                        if (!querySnapshot.isEmpty()) {
//                            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//                            usuario usuario1 = documentSnapshot.toObject(usuario.class);
//
//                            contraseñat.setText(usuario1.getContraseña());
//                            nombre.setText(usuario1.getGmail());
//                            // Aquí puedes acceder a las propiedades del usuario
//                        } else {
//                            // El usuario no fue encontrado
//                        }
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        // Manejar el error
//                        Toast.makeText(miActivity, "ERROR", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//    }
//

    public void firebaseDatabasesetdatos(){

        guardar.guardalistasusuarios();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        mDatabase.child("usuario1").setValue(usuario);
    }

    public void firebaseDatabasegetdatos(){
        TextView contraseñat=miActivity.findViewById(R.id.contraseña);
        TextView nombre=miActivity.findViewById(R.id.last_name2);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference("usuario1");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Aquí es donde se manejan los datos recuperados
                // Utiliza el método getValue() para obtener los datos
                 usuario1 = dataSnapshot.getValue(usuario.class);
                contraseñat.setText(usuario1.getContraseña());
                nombre.setText(usuario1.getGmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores
            }
        });
    }


    //------------------------RECUPERAR DATOS----------------------------//









}
