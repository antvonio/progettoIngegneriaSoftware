package strategy;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.StatoLettura;
import com.example.progettoingegneriasoftware.decorator.FiltraPerGenereDecorator;
import com.example.progettoingegneriasoftware.decorator.FiltraPerStatoLetturaDecorator;
import com.example.progettoingegneriasoftware.decorator.FiltroDecorator;
import com.example.progettoingegneriasoftware.state.Libro;
import com.example.progettoingegneriasoftware.strategy.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StrategyTest {

    private Libreria libreria;
    private List<Libro> libri;

    @BeforeEach
    public void setUp(){
        libreria= new Libreria();
        libri = new ArrayList<>();
    }

    @Test
    @DisplayName("Test Strategy")
    public void test(){
        Libro libro = new Libro("Libro1","Paperino",000000000000,"f",1);

        Libro libro2 = new Libro("Libro2","Bluto",001111111111,"Avventura",5);

        Libro libro3 = new Libro("Libro3", libro2.getAutore(), libro2.getISBN(),"b",3);

        libri.add(libro);
        libri.add(libro2);
        libri.add(libro3);

        libreria = new Libreria((ArrayList<Libro>) libri,new OrdinamentoPerTitolo());
        System.out.println(libri);
        libreria.ordina();
        System.out.println("Ordinamento per titolo: "+libri);

        libreria.setStato(new OrdinamentoPerAutore());
        libreria.ordina();
        System.out.println("Ordinamento per autore: "+libri);

        libreria.setStato(new OrdinamentoPerGenere());
        libreria.ordina();
        System.out.println("Ordinamento per genere: "+libri);

        libreria.setStato(new OrdinamentoPerValutazione());
        libreria.ordina();
        System.out.println("Ordinamento per valutazione: "+libri);

        libreria.setStato(new OrdinamentoPerStatoLettura());
        libreria.ordina();
        System.out.println("Ordinamento per statoLettura: "+libri);
    }
}
