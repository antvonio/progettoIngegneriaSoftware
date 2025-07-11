package com.example.progettoingegneriasoftware.strategy;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.state.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerGenere implements Strategy {

    @Override
    public void ordina(Libreria libreria){
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getGenere));
    }
}
