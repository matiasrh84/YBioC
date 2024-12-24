package com.ybc.ybioq.config;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RowsRenderer extends DefaultTableCellRenderer {

    private int columna;
    public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

    public RowsRenderer(int Colpatron) {
        this.columna = Colpatron;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
        JLabel celda = (JLabel) super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        celda.setHorizontalAlignment(SwingConstants.CENTER);

        setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);

        if (!table.getValueAt(row, columna).equals("-")) {

            this.setBackground(Color.decode("#98FB98"));
            this.setForeground(Color.BLACK);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

        if (selected) {
            this.setOpaque(true);
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);

        }

        return this;

    }

}
