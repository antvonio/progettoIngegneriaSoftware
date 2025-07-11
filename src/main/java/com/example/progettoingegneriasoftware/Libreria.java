package com.example.progettoingegneriasoftware;


import com.example.progettoingegneriasoftware.command.Command;
import com.example.progettoingegneriasoftware.command.LibreriaGraphic;
import com.example.progettoingegneriasoftware.decorator.FiltroDecorator;
import com.example.progettoingegneriasoftware.decorator.LibreriaIF;
import com.example.progettoingegneriasoftware.state.Libro;
import com.example.progettoingegneriasoftware.state.State;
import com.example.progettoingegneriasoftware.strategy.Strategy;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Libreria implements LibreriaIF {
    private List<Libro> libri = new ArrayList<>();

    private FiltroDecorator filtroDecorator;

    private Command command;

    private Strategy stato;

    public Libreria(){

    }

    public Libreria(ArrayList<Libro> libri){
        this.libri = libri;
    }

    public Libreria(ArrayList<Libro> libri, FiltroDecorator filtroDecorator){
        this.libri = libri;
        this.filtroDecorator = filtroDecorator;
    }

    public Libreria(ArrayList<Libro> libri, LibreriaGraphic command){
        this.libri = libri;
        this.command = command;
    }

    public Libreria(ArrayList<Libro> libri, Strategy stato){
        this.libri = libri;
        this.stato = stato;
    }

    public ArrayList<Libro> getLibri() {
        return (ArrayList<Libro>) libri;
    }

    public void setLibri(ArrayList<Libro> libri) {
        this.libri = libri;
    }

    public Command getCommand(){
        return command;
    }

    public void setCommand(Command command){
        this.command = command;
    }

    public Strategy getStato() {
        return stato;
    }

    public void setStato(Strategy stato) {
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

    public List<Libro> cercaLibriByTitolo(String titolo){ //cerca tutti i libri contenenti il titolo "titolo"
        List<Libro> libriTitolo = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getTitolo().equalsIgnoreCase(titolo) || libro.getTitolo().matches("(?i).*"+titolo+".*"))
                libriTitolo.add(libro);
        }
        return libriTitolo;
    }

    public List<Libro> cercaLibriByAutore(String autore){
        List<Libro> libriAutore = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getAutore().equalsIgnoreCase(autore) || libro.getAutore().matches("(?i).*"+autore+".*"))
                libriAutore.add(libro);
        }
        return libriAutore;
    }

    public List<Libro> cercaLibriByGenere(String genere){
        List<Libro> libriGenere = new ArrayList<>();
        for(Libro libro: libri){
            if(libro.getGenere().equalsIgnoreCase(genere) || libro.getGenere().matches("(?i).*"+genere+".*"))
                libriGenere.add(libro);
        }
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
        return libriStato;
    }

    public Libreria salva(){ //salva la collezione di libri sul file system in formato JSON o CSV
        JSONArray jsonArray = new JSONArray();
        for(Libro libro: libri){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("titolo",libro.getTitolo());
            jsonObject.put("autore",libro.getAutore());
            jsonObject.put("ISBN",libro.getISBN());
            jsonObject.put("genere",libro.getGenere());
            jsonObject.put("valutazione",libro.getValutazione());
            jsonObject.put("statoLettura",libro.getStatoLettura().toString());
            jsonArray.add(jsonObject);
        }
        try{
            String directory = System.getProperty("user.home");
            FileWriter file = new FileWriter(directory+"/Desktop/libreria.json");
            file.write("[");
            for(int i=0;i< jsonArray.size();i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                file.write(object.toString());
                if(i!=jsonArray.size()-1)
                    file.write(",");
            }
            file.write("]");
            file.close();
            return this;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public Libreria carica(){ //carica la collezione di libri dal file system
        Libreria libreria = new Libreria();
        ArrayList<Libro> libri = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try{
            String directory = System.getProperty("user.home");
            Object object = parser.parse(new FileReader(directory+"/Desktop/libreria.json"));
            JSONArray jsonArray = (JSONArray) object;
            for(int i=0;i< jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String titolo = (String) jsonObject.get("titolo");
                String autore = (String) jsonObject.get("autore");
                long ISBN = (Long) jsonObject.get("ISBN");
                String genere = (String) jsonObject.get("genere");
                long valutazione = (Long) jsonObject.get("valutazione");
                StatoLettura statoLettura = StatoLettura.valueOf((String) jsonObject.get("statoLettura"));
                Libro libro = new Libro(titolo,autore, (int) ISBN,genere, (int) valutazione);
                libro.setStatoLettura(statoLettura);
                libri.add(libro);
            }
            libreria.setLibri(libri);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return libreria;
    }

    public void notifica(){
        command.execute(this);
    }

    @Override
    public void filtra(LibreriaIF libreria, Object param){
        filtroDecorator.filtra(this,param);
    }

    public void ordina(){
        stato.ordina(this);
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
