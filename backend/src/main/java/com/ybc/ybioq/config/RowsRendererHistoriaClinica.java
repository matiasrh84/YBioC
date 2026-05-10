package com.ybc.ybioq.config;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RowsRendererHistoriaClinica extends DefaultTableCellRenderer {

    private int columna;
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    public RowsRendererHistoriaClinica(int Colpatron) {
        this.columna = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        JLabel celda = (JLabel) super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        celda.setHorizontalAlignment(SwingConstants.CENTER);

        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        if (selected) {
            this.setOpaque(true);
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);

        } else {
            if (table.getValueAt(row, columna).equals("0")) {
                this.setBackground(Color.WHITE);//#f54242
                this.setForeground(Color.BLACK);
            } else if (table.getValueAt(row, columna).equals("1")) {
                //  System.out.println("1");
                this.setBackground(Color.decode("#98FB98"));//verde
                this.setForeground(Color.BLACK);
            } else {
                ///    System.out.println("2");
                this.setBackground(Color.decode("#42a1f5"));//celeste
                this.setForeground(Color.BLACK);
            }
        }

        return this;

    }

}
