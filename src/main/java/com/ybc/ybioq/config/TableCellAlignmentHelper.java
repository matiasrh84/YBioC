package com.ybc.ybioq.config;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class TableCellAlignmentHelper {

    private final DefaultTableCellRenderer alinearCentro;
    private final DefaultTableCellRenderer alinearDerecha;
    private final DefaultTableCellRenderer alinearIzquierda;

    public TableCellAlignmentHelper() {
        alinearCentro = new DefaultTableCellRenderer();
        alinearCentro.setHorizontalAlignment(SwingConstants.CENTER);
        alinearDerecha = new DefaultTableCellRenderer();
        alinearDerecha.setHorizontalAlignment(SwingConstants.RIGHT);
        alinearIzquierda = new DefaultTableCellRenderer();
        alinearIzquierda.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void alinearColumnas(TableColumnModel columnModel, int[] columnasCentro, int[] columnasDerecha, int[] columnasIzquierda) {
        for (int col : columnasCentro) {
            columnModel.getColumn(col).setCellRenderer(alinearCentro);
        }
        for (int col : columnasDerecha) {
            columnModel.getColumn(col).setCellRenderer(alinearDerecha);
        }
        for (int col : columnasIzquierda) {
            columnModel.getColumn(col).setCellRenderer(alinearIzquierda);
        }
    }
}
