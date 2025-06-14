package com.example.progettoingegneriasoftware.builder;

import java.util.Objects;

public class Libro {
    private String titolo; //parametro richiesto
    private String autore; //parametro richiesto
    private final int ISBN; //parametro richiesto, univoco per ogni libro
    private String genere;
    private int valutazione; //da 1 a 5
    private StatoLettura statoLettura;

    /*public Libro(String titolo){

        this(titolo,"");
    }*/

    /*public Libro(String titolo, String autore){
        this.titolo = titolo;
        this.autore = autore;
    }*/

    public static class Builder implements BuilderIF {
        private String titolo; //parametro richiesto
        private String autore; //parametro richiesto
        private final int ISBN; //parametro richiesto
        private String genere = "";
        private int valutazione = 0; //da 1 a 5
        private StatoLettura statoLettura = StatoLettura.DALEGGERE; //inizialmente ogni libro si trova nello stato "DALEGGERE"

        public Builder(String titolo, String autore, int ISBN){ //costruttore con i parametri richiesti
            this.titolo = titolo;
            this.autore = autore;
            this.ISBN = ISBN;
        }

        @Override
        public Builder genere(String val){
            genere = val;
            return this;
        }

        @Override
        public Builder valutazione(int val){
            valutazione = val;
            return this;
        }

        @Override
        public Builder statoLettura(StatoLettura stato){
            statoLettura = stato;
            return this;
        }

        public Libro build(){
            return new Libro(this);
        }
    }

    private Libro(Builder builder){
        titolo = builder.titolo;
        autore = builder.autore;
        ISBN = builder.ISBN;
        genere = builder.genere;
        valutazione = builder.valutazione;
        statoLettura = builder.statoLettura;
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

    public void setGenere(String genere){
        this.genere = genere;
    }

    public void setValutazione(int valutazione){
        this.valutazione = valutazione;
    }

    public void setStatoLettura(StatoLettura statoLettura){
        this.statoLettura = statoLettura;
    }

    public void inizioLettura(){
        statoLettura = StatoLettura.INLETTURA;
    }

    public void fineLettura(){
        statoLettura = StatoLettura.LETTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return getISBN() == libro.getISBN() && getValutazione() == libro.getValutazione() && Objects.equals(getTitolo(), libro.getTitolo()) && Objects.equals(getAutore(), libro.getAutore()) && Objects.equals(getGenere(), libro.getGenere()) && getStatoLettura() == libro.getStatoLettura();
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
