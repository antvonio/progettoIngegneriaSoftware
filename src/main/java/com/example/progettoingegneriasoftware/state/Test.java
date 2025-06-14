package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args){

        List<Libro> libri = new ArrayList<>();

        Libro libro = new Libro.Builder("Libro1","Paperino",000000000000).genere("f").valutazione(1).build();

        Libro libro2 = new Libro.Builder("Libro2","Bluto",001111111111).genere("Avventura").valutazione(5).statoLettura(StatoLettura.LETTO).build();

        Libro libro3 = new Libro.Builder("Libro3", libro2.getAutore(), libro2.getISBN()).genere("b").valutazione(3).statoLettura(StatoLettura.INLETTURA).build();

        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);

        Libreria libreria = new Libreria((ArrayList<Libro>) libri,new OrdinamentoPerTitolo());
        System.out.println(libri);
        libreria.ordina();
        System.out.println(libri);

        libreria.setStato(new OrdinamentoPerAutore());
        libreria.ordina();
        System.out.println(libri);

        libreria.setStato(new OrdinamentoPerGenere());
        libreria.ordina();
        System.out.println(libri);

        libreria.setStato(new OrdinamentoPerStatoLettura());
        libreria.ordina();
        System.out.println(libri);
    }
}
