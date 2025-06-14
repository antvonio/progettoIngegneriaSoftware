package com.example.progettoingegneriasoftware;

public abstract class FiltroDecorator implements LibreriaIF {

    private LibreriaIF libreria;

    @Override
    public void filtra(LibreriaIF libreria, Object param){
        this.libreria.filtra(libreria,param);
    }
}
