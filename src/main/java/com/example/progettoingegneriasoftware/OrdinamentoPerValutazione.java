package com.example.progettoingegneriasoftware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerValutazione implements State {

    @Override
    public void ordina(Libreria libreria){
        ArrayList<Libro> libri = libreria.getLibri();
        //Collections.sort(libri, Comparator.comparing(Libro::getValutazione)); //ordine crescente
        Collections.sort(libri, Comparator.comparing(Libro::getValutazione).reversed()); //ordine decrescente
    }
}
