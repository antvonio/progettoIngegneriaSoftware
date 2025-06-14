package com.example.progettoingegneriasoftware;

import java.util.ArrayList;

public class FiltraPerStatoLetturaDecorator extends FiltroDecorator {

    private Libreria libreria;

    public FiltraPerStatoLetturaDecorator(){

    }

    public FiltraPerStatoLetturaDecorator(LibreriaIF libreria){
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
        filtraPerStatoLettura((Libreria) libreria, (StatoLettura) param);
    }

    private void filtraPerStatoLettura(Libreria libreria, StatoLettura statoLettura){
        ArrayList<Libro> libriStatoLettura = (ArrayList<Libro>) libreria.cercaLibriByStatoLettura(statoLettura);
        libreria.setLibri(libriStatoLettura);
    }
}
