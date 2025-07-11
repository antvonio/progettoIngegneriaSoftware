package com.example.progettoingegneriasoftware;

import com.example.progettoingegneriasoftware.command.LibreriaGraphic;
import com.example.progettoingegneriasoftware.state.Libro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProgettoIngegneriaSoftwareApplication {

    public static void main(String[] args) {

        Libreria libreria = new Libreria();
        List<Libro> libri = new ArrayList<>();

        Libro libro = new Libro("Libro1","Paperino",000765432211,"Fantasy",3);

        Libro libro2 = new Libro("Libro2","Pluto",001111111111,"Avventura",3);

        Libro libro3 = new Libro("Libro3", libro2.getAutore(), libro2.getISBN()+1,libro2.getGenere(),libro2.getValutazione());

        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);
        libri.add(new Libro("Libro4","Paperino",000765432212,"Horror",2));

        libreria.setLibri((ArrayList<Libro>) libri);
        libreria.setCommand(new LibreriaGraphic(libreria));

        libreria.notifica();
    }

}
