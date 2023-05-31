package com.example.filmlist.VISTA.FragmentManager;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.filmlist.CONTROLADOR.Controlador;
import com.example.filmlist.MainActivity;
import com.example.filmlist.R;
import com.example.filmlist.StringManager;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 4; // Número total de vistas
    private static final String[] TITLES = {"Vista 1", "Vista 2", "Vista 3","Vista 4"}; // Títulos de las vistas
    MainActivity main;
    String titulo;
    StringManager SManager =new StringManager();
     public Fragment Fragment1=new MyFragment(R.layout.inicio_layout);
    Fragment Fragment2=new MyFragment(R.layout.busqueda_layout);
    public Fragment Fragment3=new MyFragment(R.layout.perfil);


    String url1=SManager.apiUrl+SManager.now_playing+ SManager.apiKey+ SManager.español;
    String url3=SManager.apiUrl+SManager.popular+ SManager.apiKey+ SManager.español;
    String url4=SManager.apiUrl+SManager.upcoming+ SManager.apiKey+ SManager.español;

    String url5=SManager.apiUrl+SManager.top_rated+ SManager.apiKey+ SManager.español;


    String url2="";
    Controlador controlador;


    public MyPagerAdapter(FragmentManager fragmentManager, MainActivity main,Controlador controlador) {
        super(fragmentManager);
        this.main=main;
        this.controlador=controlador;
    }

    @SuppressLint("ResourceType")
    @Override
    public Fragment getItem(int position) {
        // Crea una nueva instancia del fragmento correspondiente
        switch (position) {
            case 0:
                controlador.LISTASINICIAL.getListaFCartelera().clear();
                controlador.LISTASINICIAL.getListaFpopulares().clear();
                controlador.LISTASINICIAL.getListaFestrenos().clear();
                controlador.LISTASINICIAL.getListaFtoprated().clear();
                controlador.getPrevision(main,url3,3);
                controlador.getPrevision(main,url4,4);
                controlador.getPrevision(main,url1,1);
                controlador.getPrevision(main,url5,5);

                return Fragment1=new MyFragment(R.layout.inicio_layout);
            case 1:

                return new MyFragment(R.layout.listas_layout);
            case 2:
                controlador.getPrevision(main,url2,2);
                return Fragment2= new MyFragment(R.layout.busqueda_layout);

            case 3:
                    return Fragment3=new MyFragment(R.layout.perfil);
            default:
                return null;
        }
    }

    public void busca(String titulo){
        url2= SManager.busqueda+ titulo+ SManager.español;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Retorna el título de la vista correspondiente
        return TITLES[position];
    }
}