package com.example.progettoingegneriasoftware.observer;

import com.example.progettoingegneriasoftware.builder.Libro;
import com.example.progettoingegneriasoftware.builder.StatoLettura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumn extends AbstractCellEditor
        implements TableCellRenderer, TableCellEditor, ActionListener {

    JTable table;
    JButton renderButton;
    JButton editButton;
    String text;

    Libreria libreria;

    Object[][] data;
    String[] columnNames;

    public ButtonColumn(JTable table, int row, int column, Libreria libreria, Object[][] data, String[] columnNames)
    {
        super();
        this.table = table;
        this.libreria = libreria;
        this.data = data;
        this.columnNames = columnNames;
        renderButton = new JButton();

        editButton = new JButton();
        editButton.setFocusPainted( false );
        editButton.addActionListener( this );

        TableColumnModel columnModel = table.getColumnModel();
        if(table.getValueAt(row,column)!=null){
            columnModel.getColumn(column).setCellRenderer( this );
            columnModel.getColumn(column).setCellEditor( this );
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
        if(e.getActionCommand().equals("Modifica")){
            //System.out.println( e.getActionCommand() + " : " + table.getSelectedRow()); //quando si clicca sul pulsante
            if(table.getValueAt(table.getSelectedRow(),0)!=null){
                Libro daModificare = libreria.getLibri().get(table.getSelectedRow());
                String nuovoTitolo = JOptionPane.showInputDialog("modifica titolo");
                if(nuovoTitolo!=null && !nuovoTitolo.isEmpty()){
                    daModificare.setTitolo(nuovoTitolo);
                    table.setValueAt(nuovoTitolo,libreria.getLibri().indexOf(daModificare),0);
                }
                String nuovoAutore = JOptionPane.showInputDialog("modifica autore");
                if(nuovoAutore!=null && !nuovoAutore.isEmpty()){
                    daModificare.setAutore(nuovoAutore);
                    table.setValueAt(nuovoAutore,libreria.getLibri().indexOf(daModificare),1);
                }
                String nuovoGenere = JOptionPane.showInputDialog("modifica genere");
                if(nuovoGenere!=null && !nuovoGenere.isEmpty()){
                    daModificare.setGenere(nuovoGenere);
                    table.setValueAt(nuovoGenere,libreria.getLibri().indexOf(daModificare),3);
                }
                String nuovaValutazione = JOptionPane.showInputDialog("modifica valutazione (1-5)");
                if(nuovaValutazione!=null && !nuovaValutazione.isEmpty()){
                    if(Integer.parseInt(nuovaValutazione)>=1 && Integer.parseInt(nuovaValutazione)<=5){
                        daModificare.setValutazione(Integer.parseInt(nuovaValutazione));
                        table.setValueAt(nuovaValutazione,libreria.getLibri().indexOf(daModificare),4);
                    }
                }
                StatoLettura[] statiLettura = {StatoLettura.DALEGGERE,StatoLettura.INLETTURA,StatoLettura.LETTO};
                StatoLettura selected = (StatoLettura) JOptionPane.showInputDialog(null,"modifica stato lettura", "selection",JOptionPane.DEFAULT_OPTION,null,statiLettura,"0");
                if(selected!=null){
                    daModificare.setStatoLettura(selected);
                    table.setValueAt(selected,libreria.getLibri().indexOf(daModificare),5);
                }
                System.out.println(daModificare);
            }
        }
        else if(e.getActionCommand().equals("Elimina")){
            int i = JOptionPane.showConfirmDialog(null,"Vuoi eliminare il libro "+table.getValueAt(table.getSelectedRow(),0)+"?","Conferma eliminazione",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(i==JOptionPane.YES_OPTION){
                DefaultTableModel myModel = (DefaultTableModel) table.getModel();
                myModel.removeRow(table.getSelectedRow());

                libreria.rimuoviLibro(libreria.getLibri().get(table.getSelectedRow()));
            }
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        text = (value == null) ? "" : value.toString();
        editButton.setText( text );
        return editButton;
    }

    @Override
    public Object getCellEditorValue() {
        return text;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (hasFocus)
        {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }
        else if (isSelected)
        {
            renderButton.setForeground(table.getSelectionForeground());
            renderButton.setBackground(table.getSelectionBackground());
        }
        else
        {
            renderButton.setForeground(table.getForeground());
            renderButton.setBackground(UIManager.getColor("Button.background"));
        }

        renderButton.setText( (value == null) ? "" : value.toString() );
        return renderButton;
    }
}
