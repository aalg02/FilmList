package com.example.filmlist.usuarios;

import java.util.ArrayList;
import java.util.HashMap;

public class usuario {

    String gmail;
    String contraseña;
    String fotoperfil;
    ArrayList<String> listavistas;
    ArrayList<String> listafavoritas;
    ArrayList<String> listapendientes;
    ArrayList<String> listavaloradas;

    HashMap<String,Integer>valoraciones;


    public usuario(String gmail, String contraseña, ArrayList<String> listavistas, ArrayList<String> listafavoritas, ArrayList<String> listapendientes,ArrayList<String >listavaloradas,String fotoperfil,HashMap<String,Integer>valoraciones) {
        this.gmail = gmail;
        this.contraseña = contraseña;
        this.listavistas = listavistas;
        this.listafavoritas = listafavoritas;
        this.listapendientes = listapendientes;
        this.listavaloradas=listavaloradas;
        this.fotoperfil=fotoperfil;
        this.valoraciones=valoraciones;
    }

    public usuario(){
      valoraciones=new HashMap<>();
    }

    public HashMap<String, Integer> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(HashMap<String, Integer> valoraciones) {
        this.valoraciones = valoraciones;
    }

    public ArrayList<String> getListavaloradas() {
        return listavaloradas;
    }

    public void setListavaloradas(ArrayList<String> listavaloradas) {
        this.listavaloradas = listavaloradas;
    }

    public String getFotoperfil() {
        return fotoperfil;
    }

    public void setFotoperfil(String fotoperfil) {
        this.fotoperfil = fotoperfil;
    }

    public ArrayList<String> getListavistas() {
        return listavistas;
    }

    public void setListavistas(ArrayList<String> listavistas) {
        this.listavistas = listavistas;
    }

    public ArrayList<String> getListafavoritas() {
        return listafavoritas;
    }

    public void setListafavoritas(ArrayList<String> listafavoritas) {
        this.listafavoritas = listafavoritas;
    }

    public ArrayList<String> getListapendientes() {
        return listapendientes;
    }

    public void setListapendientes(ArrayList<String> listapendientes) {
        this.listapendientes = listapendientes;
    }

    public String getGmail() {
        return gmail;
    }

    public String getContraseña() {
        return contraseña;
    }



    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


}
