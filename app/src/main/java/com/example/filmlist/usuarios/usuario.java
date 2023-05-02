package com.example.filmlist.usuarios;

import java.util.ArrayList;

public class usuario {

    String gmail;
    String contraseña;
    ArrayList<String> listavistas;
    ArrayList<String> listafavoritas;
    ArrayList<String> listapendientes;

    public usuario(String gmail, String contraseña, ArrayList<String> listavistas, ArrayList<String> listafavoritas, ArrayList<String> listapendientes) {
        this.gmail = gmail;
        this.contraseña = contraseña;
        this.listavistas = listavistas;
        this.listafavoritas = listafavoritas;
        this.listapendientes = listapendientes;
    }

    public usuario(){

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
