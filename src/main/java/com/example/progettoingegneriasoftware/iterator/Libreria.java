package com.example.progettoingegneriasoftware.iterator;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import java.util.ArrayList;
import java.util.List;

public class Libreria extends LibreriaAbstract{
    private List<Libro> libri = new ArrayList<>(); //di default i libri sono ordinati in ordine alfabetico

    public Libreria(){

    }

    public Libreria(ArrayList<Libro> libri){
        this.libri = libri;
    }

    public ArrayList<Libro> getLibri() {
        return (ArrayList<Libro>) libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public Libro cercaLibro(Libro libro){
        if(libri.contains(libro)){
            return libro;
        }
        return null;
    }

    public Libro aggiungiLibro(Libro libro){
        libri.add(libro);
        return libro;
    }

    public Libro rimuoviLibro(Libro libro){
        libri.remove(libro);
        return libro;
    }

    public List<Libro> cercaLibriByTitolo(String titolo){ //cerca tutti i libri con il titolo "titolo"
        List<Libro> libriTitolo = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getTitolo().equals(titolo))
                libriTitolo.add(libro);
        }
        if(libriTitolo.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriTitolo;
    }

    public List<Libro> cercaLibriByAutore(String autore){
        List<Libro> libriAutore = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getAutore().equals(autore))
                libriAutore.add(libro);
        }
        if(libriAutore.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriAutore;
    }

    public List<Libro> cercaLibriByGenere(String genere){
        List<Libro> libriGenere = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getGenere().equals(genere))
                libriGenere.add(libro);
        }
        if(libriGenere.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriGenere;
    }

    public List<Libro> cercaLibriByValutazione(int valutazione){
        if(valutazione<1 || valutazione>5){
            return null;
        }
        List<Libro> libriValutazione = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getValutazione()==valutazione)
                libriValutazione.add(libro);
        }
        if(libriValutazione.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriValutazione;
    }

    public List<Libro> cercaLibriByStatoLettura(StatoLettura statoLettura){
        List<Libro> libriStato = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getStatoLettura().equals(statoLettura))
                libriStato.add(libro);
        }
        if(libriStato.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriStato;
    }

    public void salva(){ //salva la collezione di libri sul file system in formato JSON o CSV

    }

    public void carica(){ //carica la collezione di libri dal file system

    }


    @Override
    public LibreriaIteratorIF createIterator() {
        return new LibreriaIterator(this);
    }
}
