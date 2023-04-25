package com.example.filmlist.FragmentManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    int layout;
    public MyFragment(int layout){
      this.layout=layout;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View view = inflater.inflate(layout, container, false);

        // Realiza cualquier otra operación que desees en la vista

        return view;
    }
}
