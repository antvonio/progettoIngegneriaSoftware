package com.example.progettoingegneriasoftware;

import java.util.ListIterator;

public class LibreriaIterator implements LibreriaIteratorIF {
    private Libreria libreria;

    private ListIterator<Libro> iterator;

    public LibreriaIterator(){

    }

    public LibreriaIterator(Libreria libreria){
        this.libreria = libreria;
        iterator = libreria.getLibri().listIterator();
    }

    @Override
    public Libro first() {
        while(iterator.hasPrevious())
            iterator.previous();
        return libreria.getLibri().get(0);
    }

    @Override
    public Libro last() {
        while(iterator.hasNext())
            iterator.next();
        return libreria.getLibri().get(libreria.getLibri().size()-1);
    }

    @Override
    public Libro next() {
        if(iterator.hasNext())
            return iterator.next();
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public boolean isDone() {
        return iterator.hasNext();
    }

    @Override
    public Libro currentItem() {
        if(!iterator.hasNext())
            throw new ArrayIndexOutOfBoundsException("L'elemento non esiste");
        Libro current = iterator.next();
        iterator.previous();
        return current;
    }
}
