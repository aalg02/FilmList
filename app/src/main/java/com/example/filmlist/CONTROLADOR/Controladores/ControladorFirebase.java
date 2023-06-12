package com.example.filmlist.CONTROLADOR.Controladores;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.filmlist.MODELO.usuarios.guardardatos;
import com.example.filmlist.MODELO.usuarios.usuario;
import com.example.filmlist.MainActivity;
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

public class ControladorFirebase {

    MainActivity miActivity;
    Controlador controlador;
    public guardardatos guardar;
    String gmailN="";
    Boolean punto;

    usuario usuario1;

    public ControladorFirebase(MainActivity mainActivity, Controlador controlador){
        this.miActivity=mainActivity;
        this.controlador = controlador;
        guardar = new guardardatos(controlador);


    }

//---------------------------GUARDAR EL USUARIO ACTUAL(completo)----------------------------//
    public void firebaseDatabasesetdatos() {

        guardar.guardalistasusuarios();
        int arrobaPosicion = controlador.usuario.getGmail().indexOf('@');
        String nombreUsuario = controlador.usuario.getGmail().substring(0, arrobaPosicion);
        if (nombreUsuario.contains(".")) {
            nombreUsuario = nombreUsuario.replace(".", "");
        }

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference(nombreUsuario);
        mDatabase.setValue(null);
        mDatabase.setValue(controlador.usuario);

    }

    public void registroDatabase(String gmail, String contraseña) {
        guardar.guardarusuario(gmail, contraseña);
        int arrobaPosicion = gmail.indexOf('@');

        String nombreUsuario = gmail.substring(0, arrobaPosicion);
        if (nombreUsuario.contains(".")) {
            nombreUsuario = nombreUsuario.replace(".", "");
        }
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        mDatabase.child(nombreUsuario).setValue(controlador.usuario);
        controlador.showNotification(miActivity,"REGISTRADO CON EXITO","Enhorabuena, "+gmail+" te has registrado con exito");
    }


    //----------------------COGER TODOS LOS DATOS DE USUARIO--------------------------------//

    public void firebaseDatabasegetdatos(String gmail, String contraseña) {


        int arrobaPosicion = gmail.indexOf('@');
        String nombreUsuario = gmail.substring(0, arrobaPosicion);
        if (nombreUsuario.contains(".")) {
            nombreUsuario = nombreUsuario.replace(".", "");
        }
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://filmlist-ed9e7-default-rtdb.europe-west1.firebasedatabase.app").getReference(nombreUsuario);
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario1 = dataSnapshot.getValue(usuario.class);
                if (usuario1 == null) {
                    Toast.makeText(miActivity, "Este usuario no existe tio", Toast.LENGTH_SHORT).show();
                } else {
                    controlador.usuario.setGmail(usuario1.getGmail());
                    controlador.usuario.setContraseña(usuario1.getContraseña());
                    controlador.usuario.setFotoperfil(usuario1.getFotoperfil());
                    controlador.usuario.setValoraciones(usuario1.getValoraciones());
                    controlador.usuario.setListavistas(usuario1.getListavistas());
                    controlador.usuario.setListafavoritas(usuario1.getListafavoritas());
                    controlador.usuario.setListapendientes(usuario1.getListapendientes());
                    controlador.usuario.setListavaloradas(usuario1.getListavaloradas());
                    controlador.usuario.setListaActores(usuario1.getListaActores());
                    controlador.recuperarpelis();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Manejar errores
            }
        });
    }


    // ------------------------REGISTRAR A UN USUARIO EN FIREBASE AUTHENTICATION-----------------------//
    public void authenticationRegistro(String gmail, String contraseña) {
        punto=false;

        if (gmail == null || contraseña == null) {
            Toast.makeText(miActivity, "faltan campos", Toast.LENGTH_LONG).show();



        }
        else {

            if(gmail.contains(".")){
                gmailN=gmail.replace(".","");
                punto=true;
            }


            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            mAuth.createUserWithEmailAndPassword(gmail, contraseña).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    if(punto){
                        registroDatabase(gmailN, contraseña);
                    }else{
                    registroDatabase(gmail, contraseña);}
                    controlador.gestorVistasGeneral.framelayoutLogin(0);
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
                    } catch (DatabaseException e) {
                        Toast.makeText(miActivity, "Has puesto un caracter que no esta permitido '.', '#', '$', '[', or ']'", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(miActivity, "Error al crear la cuenta: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }


    //------------------------LOGEAR A  UN USUARIO EN FIREBASE AUTHENTICATION--------------------------------//
    public void authenticationLogin(String gmail, String contraseña) {
        punto=false;
        if(gmail.contains(".")){
            gmailN=gmail.replace(".","");
            punto=true;
        }
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(gmail, contraseña).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if(punto){
                    firebaseDatabasegetdatos(gmailN, contraseña);

                }else {
                    firebaseDatabasegetdatos(gmail, contraseña);
                }

                controlador.gestorVistasGeneral.framelayoutLogin(0);
                controlador.showNotification(miActivity,"BIENVENIDO DE VUELTA","Bienvenido de vuelta a FilmList "+gmail+" !!!");

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

//----------------------------------CERRAR SESION FIREBASE AUTHENTICATION----------------------//
    public void authenticationlogout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
    }


    //----------------------------GUARDAR LA IMAGEN EN FIREBASE STORAGE----------------------//

    public void storageFirebase(Uri uri) {
        eliminarstorage();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageReference carpetaRef = storageRef.child(controlador.usuario.getGmail());
        String nombreArchivo = uri.getLastPathSegment();
        StorageReference imagenRef = carpetaRef.child(nombreArchivo);
        StorageMetadata metadata = new StorageMetadata.Builder().setContentType("image/jpeg").build();
        UploadTask uploadTask = imagenRef.putFile(uri, metadata);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl();
                downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        String imageUrl = uri.toString();
                        controlador.usuario.setFotoperfil(imageUrl);
                        firebaseDatabasesetdatos();
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Ocurrió un error al subir el archivo
            }
        });
    }
//---------------------------------ELIMINAR IMAGEN DE FIREBASE STORAGE---------------------------------//
    public void eliminarstorage() {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = null;
        try {
            storageRef = storage.getReferenceFromUrl(controlador.usuario.getFotoperfil());
        } catch (IllegalArgumentException a) {

        }


// Elimina el archivo
        if (storageRef == null) {

        } else {
            storageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Ha ocurrido un error al eliminar la imagen
                }
            });
        }
    }


    //--------------mantener sesion iniciada---------------//


    public void checkSavedCredentialsAndSignIn() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            firebaseDatabasegetdatos(currentUser.getEmail(), "");
            controlador.gestorVistasGeneral.framelayoutLogin(0);
        } else {
            controlador.gestorVistasGeneral.framelayoutLogin(1);
        }
    }




}
