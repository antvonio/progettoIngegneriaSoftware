package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.StatoLettura;

public class InLettura implements State{

    @Override
    public void setStato(Libro libro){
        libro.setStatoLettura(StatoLettura.INLETTURA);
    }
}
