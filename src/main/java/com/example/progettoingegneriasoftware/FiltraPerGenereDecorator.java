package com.example.progettoingegneriasoftware;

import java.util.ArrayList;

public class FiltraPerGenereDecorator extends FiltroDecorator {

    private Libreria libreria;

    public FiltraPerGenereDecorator(){

    }

    public FiltraPerGenereDecorator(LibreriaIF libreria){
        this.libreria = (Libreria) libreria;
    }

    public Libreria getLibreria(){
        return libreria;
    }

    public void setLibreria(Libreria libreria) {
        this.libreria = libreria;
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
