package com.example.progettoingegneriasoftware;

public interface BuilderIF {

    BuilderIF genere(String val);

    BuilderIF valutazione(int val);

    BuilderIF statoLettura(StatoLettura statoLettura);
}
