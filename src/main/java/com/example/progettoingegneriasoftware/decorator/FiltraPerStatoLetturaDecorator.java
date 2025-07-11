package com.example.progettoingegneriasoftware.decorator;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.StatoLettura;
import com.example.progettoingegneriasoftware.state.Libro;

import java.util.ArrayList;

public class FiltraPerStatoLetturaDecorator extends FiltroDecorator{

    private Libreria libreria;

    public FiltraPerStatoLetturaDecorator(LibreriaIF libreria){
        this.libreria = (Libreria) libreria;
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
