package com.example.progettoingegneriasoftware.builder;

import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args){
        Libro libro = new Libro.Builder("Libro1","Paperino",000000000000).build();
        //System.out.println(libro.toString());
        libro.setGenere("Fantasy");
        libro.setValutazione(2);
        libro.setStatoLettura(StatoLettura.INLETTURA);
        //System.out.println(libro.toString());

        Libro libro2 = new Libro.Builder("Libro2","Pluto",001111111111).genere("Avventura").valutazione(3).statoLettura(StatoLettura.DALEGGERE).build();
        //System.out.println(libro2.toString());
        libro2.inizioLettura();
        //System.out.println(libro2.toString());

        Libro libro3 = new Libro.Builder("Libro3", libro2.getAutore(), libro2.getISBN()).genere(libro2.getGenere()).valutazione(libro2.getValutazione()).statoLettura(StatoLettura.INLETTURA).build();
        //System.out.println(libro3.toString());
        libro3.fineLettura();
        //System.out.println(libro3.toString());

        List<Libro> libri = new ArrayList<>();
        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);
        Libreria libreria = new Libreria((ArrayList<Libro>) libri);
        //libreria.rimuoviLibro(libro3);
        //System.out.println(libreria);

        //Libro trova = libreria.cercaLibro(libro3);
        //System.out.println(trova);

        //System.out.println(libreria.cercaLibriByTitolo("libro"));
        //System.out.println(libreria.cercaLibriByAutore("Plut"));
        //System.out.println(libreria.cercaLibriByGenere("f"));
        //System.out.println(libreria.cercaLibriByValutazione(3));
        System.out.println(libreria.cercaLibriByStatoLettura(StatoLettura.LETTO));
    }
}
