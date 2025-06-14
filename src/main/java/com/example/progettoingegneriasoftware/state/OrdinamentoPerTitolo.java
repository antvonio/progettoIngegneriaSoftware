package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.builder.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerTitolo implements State{

    @Override
    public void ordina(Libreria libreria){
        ordinaPerTitolo(libreria);
    }

    private void ordinaPerTitolo(Libreria libreria){
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getTitolo));
    }
}
