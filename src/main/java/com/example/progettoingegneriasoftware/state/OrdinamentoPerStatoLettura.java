package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.builder.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdinamentoPerStatoLettura implements State{

    @Override
    public void ordina(Libreria libreria){
        ordinaPerStatoLettura(libreria);
    }

    private void ordinaPerStatoLettura(Libreria libreria) {
        ArrayList<Libro> libri = libreria.getLibri();
        Collections.sort(libri, Comparator.comparing(Libro::getStatoLettura));
    }
}
