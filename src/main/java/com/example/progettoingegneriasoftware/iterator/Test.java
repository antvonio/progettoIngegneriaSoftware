package com.example.progettoingegneriasoftware.iterator;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args){
        Libreria libreria = new Libreria();
        List<Libro> libri = new ArrayList<>();

        Libro libro = new Libro.Builder("Libro1","Paperino",000000000000).build();

        Libro libro2 = new Libro.Builder("Libro2","Pluto",001111111111).genere("Avventura").valutazione(3).statoLettura(StatoLettura.DALEGGERE).build();

        Libro libro3 = new Libro.Builder("Libro3", libro2.getAutore(), libro2.getISBN()).genere(libro2.getGenere()).valutazione(libro2.getValutazione()).statoLettura(StatoLettura.INLETTURA).build();

        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);

        libreria.setLibri((ArrayList<Libro>) libri);

        LibreriaIteratorIF it = libreria.createIterator();
        it.next();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        System.out.println("Primo elemento: "+it.first().getTitolo());
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        it.next();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        it.first();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        System.out.println("Ultimo elemento: "+it.last().getTitolo()+" (il puntatore è dopo l'ultimo elemento)");
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());

    }
}
