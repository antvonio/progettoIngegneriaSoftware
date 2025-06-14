package com.example.progettoingegneriasoftware.iterator;

import com.example.progettoingegneriasoftware.builder.Libro;

public interface LibreriaIteratorIF {

    Libro first();

    Libro last();

    Libro next();

    boolean isDone();

    Libro currentItem();
}
