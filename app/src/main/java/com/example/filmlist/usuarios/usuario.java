package com.example.filmlist.usuarios;

import com.example.filmlist.JsonRead.ListasPropias;

public class usuario {

    String gmail;
    String contraseña;
    ListasPropias Mislistas;

    public usuario(String gmail, String contraseña, ListasPropias mislistas) {
        this.gmail = gmail;
        this.contraseña = contraseña;
        Mislistas = mislistas;
    }

    public String getGmail() {
        return gmail;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ListasPropias getMislistas() {
        return Mislistas;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setMislistas(ListasPropias mislistas) {
        Mislistas = mislistas;
    }
}
