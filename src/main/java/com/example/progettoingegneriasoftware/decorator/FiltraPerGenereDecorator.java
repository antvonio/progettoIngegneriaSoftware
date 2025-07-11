package com.example.progettoingegneriasoftware.decorator;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.state.Libro;

import java.util.ArrayList;

public class FiltraPerGenereDecorator extends FiltroDecorator{

    private Libreria libreria;

    public FiltraPerGenereDecorator(LibreriaIF libreria){
        this.libreria = (Libreria) libreria;
    }

    @Override
    public void filtra(LibreriaIF libreria, Object param){
        filtraPerGenere((Libreria) libreria, (String) param);
    }

    private void filtraPerGenere(Libreria libreria, String genere){
        ArrayList<Libro> libriGenere = (ArrayList<Libro>) libreria.cercaLibriByGenere(genere);
        libreria.setLibri(libriGenere);
    }
}
