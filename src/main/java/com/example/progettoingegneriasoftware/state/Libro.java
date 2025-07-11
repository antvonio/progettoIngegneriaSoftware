package com.example.progettoingegneriasoftware.state;

import com.example.progettoingegneriasoftware.StatoLettura;

import java.util.Objects;

public class Libro {

    private String titolo;
    private String autore;
    private int ISBN;
    private String genere;
    private int valutazione; //da 1 a 5

    private StatoLettura statoLettura;

    private State stato;

    public Libro(){

    }

    public Libro(String titolo, String autore, int ISBN, String genere, int valutazione){
        this.titolo = titolo;
        this.autore = autore;
        this.ISBN = ISBN;
        this.genere = genere;
        this.valutazione = valutazione;
        this.statoLettura = StatoLettura.DALEGGERE;
    }

    public Libro(String titolo, String autore, int ISBN, String genere, int valutazione, State stato){
        this.titolo = titolo;
        this.autore = autore;
        this.ISBN = ISBN;
        this.genere = genere;
        this.valutazione = valutazione;
        this.stato = stato;
    }

    public String getTitolo(){
        return titolo;
    }

    public String getAutore() {
        return autore;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getGenere() {
        return genere;
    }

    public int getValutazione() {
        return valutazione;
    }

    public StatoLettura getStatoLettura() {
        return statoLettura;
    }

    public void setTitolo(String titolo){
        this.titolo = titolo;
    }

    public void setAutore(String autore){
        this.autore = autore;
    }

    public void setISBN(int ISBN){
        this.ISBN = ISBN;
    }

    public void setGenere(String genere){
        this.genere = genere;
    }

    public void setValutazione(int valutazione){
        this.valutazione = valutazione;
    }

    public void setStatoLettura(StatoLettura statoLettura){
        this.statoLettura = statoLettura;
    }

    public State getStato(){
        return stato;
    }

    public void setStato(State stato){
        this.stato = stato;
    }

    public void impostaStatoLettura(){
        stato.setStato(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return getISBN() == libro.getISBN() && getValutazione() == libro.getValutazione() && Objects.equals(getTitolo(), libro.getTitolo()) && Objects.equals(getAutore(), libro.getAutore()) && Objects.equals(getGenere(), libro.getGenere()) && Objects.equals(getStatoLettura(), libro.getStatoLettura());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitolo(), getAutore(), getISBN(), getGenere(), getValutazione(), getStatoLettura());
    }

    @Override
    public String toString() {
        return "titolo='" + titolo + '\'' +
                ", autore='" + autore + '\'' +
                ", ISBN=" + ISBN +
                ", genere='" + genere + '\'' +
                ", valutazione=" + valutazione +
                ", statoLettura=" + statoLettura;
    }
}
