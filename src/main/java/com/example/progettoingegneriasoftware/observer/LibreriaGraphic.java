package com.example.progettoingegneriasoftware.observer;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class LibreriaGraphic extends JPanel implements Observer{

    private Libreria libreria;
    private JTable jTable;

    public LibreriaGraphic(Libreria libreria){
        super(new GridLayout(1,0));
        this.libreria = libreria;


        String[] columnNames = {"Titolo","Autore","ISBN","genere","Valutazione","StatoLettura","Modifica","Elimina"};

        /*JButton editButton = new JButton("modifica");
        JButton deleteButton = new JButton("elimina");

        JPanel content = new JPanel();
        content.add(editButton);
        content.add(deleteButton);*/

        Object[][] data = new Object[100][100];
        ArrayList<Libro> libri = libreria.getLibri();
        for(Libro libro : libri){
           data[libri.indexOf(libro)][0] = libro.getTitolo();
            data[libri.indexOf(libro)][1] = libro.getAutore();
            data[libri.indexOf(libro)][2] = libro.getISBN();
            data[libri.indexOf(libro)][3] = libro.getGenere();
            data[libri.indexOf(libro)][4] = libro.getValutazione();
            data[libri.indexOf(libro)][5] = libro.getStatoLettura();
        }

        DefaultTableModel myModel = new DefaultTableModel(data,columnNames);
        jTable = new JTable(myModel);

        for(Libro libro : libri){
            if(data[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,data,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,data,columnNames);

        jTable.setPreferredScrollableViewportSize(new Dimension(500,70));
        jTable.setFillsViewportHeight(true);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);

        JPanel content = new JPanel();

        JTextField searchField = new JTextField(8);
        searchField.setSize(200,100);
        searchField.setText("Cerca libro");
        JButton searchButton = new JButton("Cerca");
        searchButton.addActionListener(evt -> search(searchField,columnNames,libreria));
        content.add(searchField);
        content.add(searchButton);

        JButton addButton = new JButton("Aggiungi Libro");
        addButton.addActionListener(evt->add(libreria,data,columnNames));
        content.add(addButton);


        JButton resetButton = new JButton("reset");
        resetButton.addActionListener(evt ->reset(libreria,data,columnNames));
        content.add(resetButton);

        JButton orderByTitoloButton = new JButton("Ordina per titolo");
        orderByTitoloButton.addActionListener(evt ->orderByTitolo());
        JButton orderByAutoreButton = new JButton("Ordina per autore");
        JButton orderByGenereButton = new JButton("Ordina per genere");
        JButton orderByValutazioneButton = new JButton("Ordina per valutazione");
        JButton orderByStatoLetturaButton = new JButton("Ordina per stato lettura");

        add(content);
    }

    @Override
    public void update(Libreria libreria){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LibreriaGraphic newContentPane = new LibreriaGraphic(libreria);
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        frame.pack();
        frame.setVisible(true);
    }

    private void search(JTextField textField, String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.cercaLibriByTitolo(textField.getText());
        Object[][] newData = new Object[100][100];
        for(Libro libro : libri){
            newData[libri.indexOf(libro)][0] = libro.getTitolo();
            newData[libri.indexOf(libro)][1] = libro.getAutore();
            newData[libri.indexOf(libro)][2] = libro.getISBN();
            newData[libri.indexOf(libro)][3] = libro.getGenere();
            newData[libri.indexOf(libro)][4] = libro.getValutazione();
            newData[libri.indexOf(libro)][5] = libro.getStatoLettura();
        }
        DefaultTableModel myModel = new DefaultTableModel(newData,columnNames);
        jTable.setModel(myModel);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void reset(Libreria libreria, Object[][] data, String[] columnNames){ //riporta la tabella alla visualizzazione originale
        ArrayList<Libro> libri = libreria.getLibri();
        for(Libro libro : libri){
            data[libri.indexOf(libro)][0] = libro.getTitolo();
            data[libri.indexOf(libro)][1] = libro.getAutore();
            data[libri.indexOf(libro)][2] = libro.getISBN();
            data[libri.indexOf(libro)][3] = libro.getGenere();
            data[libri.indexOf(libro)][4] = libro.getValutazione();
            data[libri.indexOf(libro)][5] = libro.getStatoLettura();
        }
        DefaultTableModel myModel = new DefaultTableModel(data,columnNames);
        jTable.setModel(myModel);
        for(Libro libro : libri){
            if(data[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,data,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,data,columnNames);
    }

    private void add(Libreria libreria, Object[][] data, String[] columnNames){
        Libro nuovoLibro = null;
        String nuovoTitolo = JOptionPane.showInputDialog("imposta titolo [parametro necessario]");
        String nuovoAutore = JOptionPane.showInputDialog("imposta autore [parametro necessario]");
        String nuovoGenere = JOptionPane.showInputDialog("imposta genere");
        String nuovaValutazione = JOptionPane.showInputDialog("imposta valutazione (1-5)");
        StatoLettura[] statiLettura = {StatoLettura.DALEGGERE,StatoLettura.INLETTURA,StatoLettura.LETTO};
        StatoLettura selected = (StatoLettura) JOptionPane.showInputDialog(null,"imposta stato lettura", "selection",JOptionPane.DEFAULT_OPTION,null,statiLettura,"0");
        if(nuovoTitolo!=null && !nuovoTitolo.isEmpty()){
            if(nuovoAutore!=null && !nuovoAutore.isEmpty()){
                nuovoLibro = new Libro.Builder(nuovoTitolo,nuovoAutore,new Random().nextInt()).build();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Errore! il libro non è stato creato!");
            return;
        }
        if(nuovoGenere!=null && !nuovoGenere.isEmpty()){
            if(nuovaValutazione!=null && !nuovaValutazione.isEmpty()) {
                if (Integer.parseInt(nuovaValutazione) >= 1 && Integer.parseInt(nuovaValutazione) <= 5) {
                    if(selected!=null){
                        nuovoLibro.setGenere(nuovoGenere);
                        nuovoLibro.setValutazione(Integer.parseInt(nuovaValutazione));
                        nuovoLibro.setStatoLettura(selected);
                        jTable.setValueAt(nuovoLibro.getTitolo(),libreria.getLibri().size(),0);
                        jTable.setValueAt(nuovoLibro.getAutore(),libreria.getLibri().size(),1);
                        jTable.setValueAt(nuovoLibro.getISBN(),libreria.getLibri().size(),2);
                        jTable.setValueAt(nuovoLibro.getGenere(),libreria.getLibri().size(),3);
                        jTable.setValueAt(nuovoLibro.getValutazione(),libreria.getLibri().size(),4);
                        jTable.setValueAt(nuovoLibro.getStatoLettura(),libreria.getLibri().size(),5);
                        libreria.aggiungiLibro(nuovoLibro);

                        jTable.setValueAt(nuovoTitolo,libreria.getLibri().size(),6);
                        jTable.setValueAt(nuovoTitolo,libreria.getLibri().size(),7);

                        DefaultTableModel myModel = new DefaultTableModel(data,columnNames);
                        jTable.setModel(myModel);
                        ArrayList<Libro> libri = libreria.getLibri();
                        for(Libro libro : libri){
                            if(data[libri.indexOf(libro)][0]!=null){
                                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
                            }
                        }
                        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,data,columnNames);
                        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,data,columnNames);
                    }
                }
            }
        }
        reset(libreria,data,columnNames);
    }

    private void orderByTitolo(){

    }

    private void orderByAutore(){

    }

    private void orderByGenere(){

    }

    private void ordinaByValutazione(){

    }

    private void ordinaByStatoLettura(){

    }
}
