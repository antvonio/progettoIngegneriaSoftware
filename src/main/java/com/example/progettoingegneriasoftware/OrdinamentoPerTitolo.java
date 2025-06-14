package com.example.progettoingegneriasoftware;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerTitolo implements State {

    @Override
    public void ordina(Libreria libreria){
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getTitolo)); //case-sensitive
    }
}
