package com.example.progettoingegneriasoftware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerAutore implements State {

    @Override
    public void ordina(Libreria libreria){
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getAutore));
    }
}
