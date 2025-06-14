package com.example.progettoingegneriasoftware.observer;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import javax.swing.*;
import java.awt.*;
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
        libri.add(new Libro.Builder("Libro4","Paperino",000000000000).build());

        libreria.setLibri((ArrayList<Libro>) libri);
        libreria.setObserver(new LibreriaGraphic(libreria));

        libreria.notifica();
    }
}
