package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.builder.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerGenere implements State{

    @Override
    public void ordina(Libreria libreria){
        ordinaPerGenere(libreria);
    }

    private void ordinaPerGenere(Libreria libreria) {
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getGenere));
    }
}
