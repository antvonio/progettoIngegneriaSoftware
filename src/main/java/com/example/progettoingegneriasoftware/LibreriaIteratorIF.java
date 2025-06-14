package com.example.progettoingegneriasoftware;

public interface LibreriaIteratorIF {
    Libro first();

    Libro last();

    Libro next();

    boolean isDone();

    Libro currentItem();
}
