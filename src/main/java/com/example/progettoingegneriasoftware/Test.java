package com.example.progettoingegneriasoftware;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args){
        Libro libro = new Libro.Builder("Libro1","Pippo",new Random().nextInt(1000000000,1234567899)).build();
        libro.setGenere("Fantasy");
        libro.setValutazione(2);
        libro.setStatoLettura(StatoLettura.INLETTURA);

        Libro libro2 = new Libro.Builder("Libro2","Paperino",new Random().nextInt(1000000000,1234567899)).genere("Avventura").valutazione(3).statoLettura(StatoLettura.DALEGGERE).build();

        Libro libro3 = new Libro.Builder("Libro3", "Topolino", new Random().nextInt(1000000000,1234567899)).genere("Horror").valutazione(5).statoLettura(StatoLettura.INLETTURA).build();

        List<Libro> libri = new ArrayList<>();
        libri.add(libro2);
        libri.add(libro);
        libri.add(libro3);
        Libreria libreria = new Libreria((ArrayList<Libro>) libri);

        /*System.out.println(libreria.getLibri());
        FiltroDecorator decorator = new FiltraPerGenereDecorator(libreria);
        libreria = new Libreria((ArrayList<Libro>) libri, decorator);
        libreria.filtra(libreria,"f");
        System.out.println(libreria.getLibri());

        FiltroDecorator decorator2 = new FiltraPerStatoLetturaDecorator(libreria);
        libreria = new Libreria((ArrayList<Libro>) libri, decorator2);
        libreria.filtra(libreria,StatoLettura.DALEGGERE);
        System.out.println(libreria.getLibri()); */
        /*System.out.println(libreria);

        Libro trova = libreria.cercaLibro(libro3);
        System.out.println(trova);

        System.out.println(libreria.cercaLibriByTitolo("libro"));
        System.out.println(libreria.cercaLibriByAutore("Plut"));
        System.out.println(libreria.cercaLibriByGenere("f"));
        System.out.println(libreria.cercaLibriByValutazione(3));
        System.out.println(libreria.cercaLibriByStatoLettura(StatoLettura.LETTO));*/

        /*LibreriaIteratorIF it = libreria.createIterator();
        it.next();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        System.out.println("Primo elemento: "+it.first().getTitolo());
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        it.next();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        it.first();
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());
        System.out.println("Ultimo elemento: "+it.last().getTitolo()+" (il puntatore è dopo l'ultimo elemento)");
        System.out.println("Elemento corrente: "+it.currentItem().getTitolo());*/

        /*System.out.println(libri);
        libreria.setStato(new OrdinamentoPerTitolo());
        libreria.ordina();
        System.out.println("ordinamento per titolo: "+libri);

        libreria.setStato(new OrdinamentoPerAutore());
        libreria.ordina();
        System.out.println("ordinamento per autore: "+libri);

        libreria.setStato(new OrdinamentoPerGenere());
        libreria.ordina();
        System.out.println("ordinamento per genere: "+libri);

        libreria.setStato(new OrdinamentoPerValutazione());
        libreria.ordina();
        System.out.println("ordinamento per valutazione: "+libri);

        libreria.setStato(new OrdinamentoPerStatoLettura());
        libreria.ordina();
        System.out.println("ordinamento per stato lettura: "+libri);*/

        //libreria.salva();
        //libreria.carica();

        libreria.setLibri((ArrayList<Libro>) libri);
        libreria.setObserver(new LibreriaGraphic(libreria));
        libreria.notifica();
    }
}
