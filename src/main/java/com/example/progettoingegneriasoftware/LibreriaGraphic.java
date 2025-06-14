package com.example.progettoingegneriasoftware;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LibreriaGraphic extends JPanel implements Observer {
    private Libreria libreria;
    private JTable jTable;

    public LibreriaGraphic(Libreria libreria){
        super(new GridLayout(3,2));
        this.libreria = libreria;


        String[] columnNames = {"Titolo","Autore","ISBN","genere","Valutazione","StatoLettura","Modifica","Elimina"};

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

        DefaultTableModel myModel = new DefaultTableModel(data,columnNames){

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                if(column==6 || column==7)
                    return true;
                return false;
            }
        };
        jTable = new JTable(myModel);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(data[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,data,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,data,columnNames);

        jTable.setPreferredScrollableViewportSize(new Dimension(1000,400));
        jTable.setFillsViewportHeight(true);

        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane);


        JPanel content = new JPanel();

        JTextField searchField = new JTextField(8);
        searchField.setSize(200,100);
        searchField.setText("Cerca titolo");
        JButton searchButton = new JButton("Cerca");
        searchButton.addActionListener(evt -> search(searchField,columnNames,libreria));
        content.add(searchField);
        content.add(searchButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(evt ->reset(libreria,data,columnNames));
        content.add(resetButton);

        JButton addButton = new JButton("Aggiungi Libro");
        addButton.addActionListener(evt->add(libreria,data,columnNames));
        content.add(addButton);


        JPanel orderContent = new JPanel();

        JButton orderByTitoloButton = new JButton("Ordina per titolo");
        orderByTitoloButton.addActionListener(evt ->orderByTitolo(columnNames,libreria));
        orderContent.add(orderByTitoloButton);

        JButton orderByAutoreButton = new JButton("Ordina per autore");
        orderByAutoreButton.addActionListener(evt ->orderByAutore(columnNames,libreria));
        orderContent.add(orderByAutoreButton);

        JButton orderByGenereButton = new JButton("Ordina per genere");
        orderByGenereButton.addActionListener(evt ->orderByGenere(columnNames,libreria));
        orderContent.add(orderByGenereButton);

        JButton orderByValutazioneButton = new JButton("Ordina per valutazione");
        orderByValutazioneButton.addActionListener(evt ->orderByValutazione(columnNames,libreria));
        orderContent.add(orderByValutazioneButton);

        JButton orderByStatoLetturaButton = new JButton("Ordina per stato lettura");
        orderByStatoLetturaButton.addActionListener(evt ->orderByStatoLettura(columnNames,libreria));
        orderContent.add(orderByStatoLetturaButton);


        JPanel filterContent = new JPanel(new FlowLayout());

        JButton filterByGenereButton = new JButton("Filtra per genere");
        filterByGenereButton.addActionListener(evt ->filtraByGenere(columnNames,libreria));
        filterContent.add(filterByGenereButton);

        JButton filterByStatoLetturaButton = new JButton("Filtra per stato lettura");
        filterByStatoLetturaButton.addActionListener(evt ->filtraByStatoLettura(columnNames,libreria));
        filterContent.add(filterByStatoLetturaButton);

        JPanel saveLoadContent = new JPanel();

        JButton saveButton = new JButton("Salva su desktop");
        saveButton.addActionListener(evt -> salvaSuDesktop(libreria));
        saveLoadContent.add(saveButton);

        JButton loadButton = new JButton("Carica ");
        loadButton.addActionListener(evt -> caricaDaDesktop(libreria,data,columnNames));
        saveLoadContent.add(loadButton);

        add(content);
        add(orderContent);
        add(filterContent);
        add(saveLoadContent);
    }

    @Override
    public void update(Libreria libreria){
        JFrame frame = new JFrame("Libreria");
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

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
        data = new Object[100][100];
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);
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
        String nuovoTitolo = JOptionPane.showInputDialog("imposta titolo ");
        String nuovoAutore = JOptionPane.showInputDialog("imposta autore ");
        String nuovoGenere = JOptionPane.showInputDialog("imposta genere");
        String nuovaValutazione = JOptionPane.showInputDialog("imposta valutazione (1-5)");
        StatoLettura[] statiLettura = {StatoLettura.DALEGGERE, StatoLettura.INLETTURA, StatoLettura.LETTO};
        StatoLettura selected = (StatoLettura) JOptionPane.showInputDialog(null,"imposta stato lettura", "selection",JOptionPane.DEFAULT_OPTION,null,statiLettura,"0");
        if(nuovoTitolo!=null && !nuovoTitolo.isEmpty()){
            if(nuovoAutore!=null && !nuovoAutore.isEmpty()){
                nuovoLibro = new Libro.Builder(nuovoTitolo,nuovoAutore,new Random().nextInt(1000000000,1234567899)).build();
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
                        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
                        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
                        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);
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

    private void orderByTitolo(String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.getLibri();
        libreria.setStato(new OrdinamentoPerTitolo());
        libreria.ordina();
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void orderByAutore(String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.getLibri();
        libreria.setStato(new OrdinamentoPerAutore());
        libreria.ordina();
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void orderByGenere(String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.getLibri();
        libreria.setStato(new OrdinamentoPerGenere());
        libreria.ordina();
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void orderByValutazione(String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.getLibri();
        libreria.setStato(new OrdinamentoPerValutazione());
        libreria.ordina();
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void orderByStatoLettura(String[] columnNames, Libreria libreria){
        List<Libro> libri = libreria.getLibri();
        libreria.setStato(new OrdinamentoPerStatoLettura());
        libreria.ordina();
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void filtraByGenere(String[] columnNames, Libreria libreria){
        String[] generi = {"Avventura", "Fantasy", "Horror"};
        //String selected = (String) JOptionPane.showInputDialog(null,"Scegli il genere", "selection",JOptionPane.DEFAULT_OPTION,null,generi,"0");
        String selected = JOptionPane.showInputDialog("Scegli un genere");
        List<Libro> libri = libreria.cercaLibriByGenere(selected);
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void filtraByStatoLettura(String[] columnNames, Libreria libreria){
        StatoLettura[] statiLettura = {StatoLettura.DALEGGERE, StatoLettura.INLETTURA, StatoLettura.LETTO};
        StatoLettura selected = (StatoLettura) JOptionPane.showInputDialog(null,"imposta stato lettura", "selection",JOptionPane.DEFAULT_OPTION,null,statiLettura,"0");
        List<Libro> libri = libreria.cercaLibriByStatoLettura(selected);
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
        jTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        jTable.getColumnModel().getColumn(1).setPreferredWidth(60);
        jTable.getColumnModel().getColumn(4).setPreferredWidth(60);

        for(Libro libro : libri){
            if(newData[libri.indexOf(libro)][0]!=null){
                jTable.setValueAt(columnNames[6],libri.indexOf(libro),6);
                jTable.setValueAt(columnNames[7],libri.indexOf(libro),7);
            }
        }
        ButtonColumn editButton = new ButtonColumn(jTable,0,6,libreria,newData,columnNames);
        ButtonColumn deleteButton = new ButtonColumn(jTable,0,7,libreria,newData,columnNames);
    }

    private void salvaSuDesktop(Libreria libreria){
        libreria.salva();
        JOptionPane.showMessageDialog(null,"La tua libreria è stata salvata sul desktop!");
    }

    private void caricaDaDesktop(Libreria libreria,Object[][] data, String[] columnNames){
        Libreria newLibreria = libreria.carica();
        if(!newLibreria.getLibri().isEmpty()){
            reset(newLibreria,data,columnNames);
            JOptionPane.showMessageDialog(null,"La tua libreria è stata caricata!");
        }
        else{
            JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile trovare il file");
        }
    }
}
