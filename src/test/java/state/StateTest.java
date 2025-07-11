package state;

import com.example.progettoingegneriasoftware.Libreria;
import com.example.progettoingegneriasoftware.StatoLettura;
import com.example.progettoingegneriasoftware.state.DaLeggere;
import com.example.progettoingegneriasoftware.state.InLettura;
import com.example.progettoingegneriasoftware.state.Letto;
import com.example.progettoingegneriasoftware.state.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class StateTest {

    private Libro libro;

    @BeforeEach
    public void setUp(){
        libro = new Libro();
    }

    @Test
    @DisplayName("Test State")
    public void test(){
        Libro libro = new Libro("Libro1","Paperino",000000000000,"f",1);

        System.out.println(libro);

        libro.setStato(new DaLeggere());
        libro.impostaStatoLettura();
        System.out.println(libro);

        libro.setStato(new Letto());
        libro.impostaStatoLettura();
        System.out.println(libro);

        libro.setStato(new InLettura());
        libro.impostaStatoLettura();
        System.out.println(libro);
    }
}
