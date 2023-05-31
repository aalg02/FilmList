package com.example.filmlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.example.filmlist.CONTROLADOR.Controlador;
import com.example.filmlist.VISTA.GestionVistas.gestorvistas;
import com.google.firebase.FirebaseApp;
//import com.example.filmlist.JsonRead.Film;
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.cloud.firestore.Firestore;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.cloud.FirestoreClient;
//import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    protected Controlador miControlador;
    // public  FirebaseApp firebase;
    gestorvistas gestor;
  //  FirebaseFirestore firestore;
    SegundaActivity segundaActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        miControlador = Controlador.getInstance();
        miControlador.setActivity(this);
        miControlador.controlViewpage();
        gestor=new gestorvistas();
        gestor.setActivity(this);
        gestor.listeners();
        gestor.listenersperfil();
        gestor.framelayoutinicio(0);
        miControlador.setVistamanager(gestor);
        miControlador.checkSavedCredentialsAndSignIn();





        FirebaseApp.initializeApp(this);



        // gestor=gestorvistas.getInstance();

        // miControlador.cargarRV(this);


    }
    public void onStart() {

        super.onStart();
        gestor.Listenergeneros();
    };
    public void onResume() {

        super.onResume();
        gestor.Listenergeneros();
    }
    @Override
    public void onBackPressed() {
        //miControlador.guardadatos();
        gestor.framelayoutinicio(0);


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Controlador.getInstance().guardadatos();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();

            // hacer algo con la imagen seleccionada, por ejemplo, mostrarla en un ImageView
            Controlador.getInstance().storageFirebase(imageUri);
            gestor.ponerfoto(imageUri.toString());
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            // Verificar si los permisos fueron concedidos
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Los permisos fueron concedidos, puedes realizar las operaciones necesarias

            } else {
                // Los permisos fueron denegados, muestra un mensaje de error o realiza otra acción
                Toast.makeText(this, "Los permisos fueron denegados.", Toast.LENGTH_SHORT).show();
            }
        }
    }








}