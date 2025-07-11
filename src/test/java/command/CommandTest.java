package command;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.StatoLettura;
import com.example.progettoingegneriasoftware.command.LibreriaGraphic;
import com.example.progettoingegneriasoftware.state.Libro;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CommandTest {

    private Libreria libreria;
    private List<Libro> libri;

    @BeforeEach
    public void setUp(){
        libreria= new Libreria();
        libri = new ArrayList<>();
    }

    @Test
    @DisplayName("Test Command")
    public void test(){
        Libro libro = new Libro("Libro1","Paperino",000000000000,"Fantasy",3);

        Libro libro2 = new Libro("Libro2","Pluto",001111111111,"Avventura",3);

        Libro libro3 = new Libro("Libro3", libro2.getAutore(), libro2.getISBN(),libro2.getGenere(),libro2.getValutazione());

        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);
        libri.add(new Libro("Libro4","Paperino",000000000000,"Horror",2));

        libreria.setLibri((ArrayList<Libro>) libri);
        libreria.setCommand(new LibreriaGraphic(libreria));

        libreria.notifica();
    }
}
