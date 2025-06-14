package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Libreria {
    private List<Libro> libri = new ArrayList<>(); //di default i libri sono ordinati in ordine alfabetico

    private State stato;

    public Libreria(){

    }

    public Libreria(ArrayList<Libro> libri){
        this.libri = libri;
    }

    public Libreria(ArrayList<Libro> libri, State stato){
        this.libri = libri;
        this.stato = stato;
    }

    public ArrayList<Libro> getLibri() {
        return (ArrayList<Libro>) libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public State getStato(){
        return stato;
    }

    public void setStato(State stato){
        this.stato = stato;
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
            if(libro.getTitolo().equalsIgnoreCase(titolo) || libro.getTitolo().matches("(?i).*"+titolo+".*"))
                libriTitolo.add(libro);
        }
        if(libriTitolo.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriTitolo;
    }

    public List<Libro> cercaLibriByAutore(String autore){
        List<Libro> libriAutore = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getAutore().equalsIgnoreCase(autore) || libro.getAutore().matches("(?i).*"+autore+".*"))
                libriAutore.add(libro);
        }
        if(libriAutore.isEmpty())
            System.out.println("Nessun Libro trovato");
        return libriAutore;
    }

    public List<Libro> cercaLibriByGenere(String genere){
        List<Libro> libriGenere = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getGenere().equalsIgnoreCase(genere) || libro.getGenere().matches("(?i).*"+genere+".*"))
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

    public void ordina(){
        stato.ordina(this);
    }

    public void salva(){ //salva la collezione di libri sul file system in formato JSON o CSV

    }

    public void carica(){ //carica la collezione di libri dal file system

    }

    public void annullaOrdinamento(){ //rimuove tutti i filtri e riporta l'ordinamento a quello di default, cioè all'ordinamento per titolo
        //ordinaByTitolo();
    }

    public void annullaFiltri(){ //annulla tutti i filtri e riporta la builder all'ordinamento in cui si trovava

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libreria libreria = (Libreria) o;
        return Objects.equals(getLibri(), libreria.getLibri());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLibri());
    }

    @Override
    public String toString() {
        return "Libreria{" +
                "libri=" + libri +
                '}';
    }
}
