package com.example.filmlist.usuarios;

import java.util.ArrayList;

public class usuario {

    String gmail;
    String contraseña;
    String fotoperfil;
    ArrayList<String> listavistas;
    ArrayList<String> listafavoritas;
    ArrayList<String> listapendientes;

    public usuario(String gmail, String contraseña, ArrayList<String> listavistas, ArrayList<String> listafavoritas, ArrayList<String> listapendientes,String fotoperfil) {
        this.gmail = gmail;
        this.contraseña = contraseña;
        this.listavistas = listavistas;
        this.listafavoritas = listafavoritas;
        this.listapendientes = listapendientes;
        this.fotoperfil=fotoperfil;
    }

    public usuario(){

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
