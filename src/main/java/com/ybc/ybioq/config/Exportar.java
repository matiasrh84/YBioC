package com.ybc.ybioq.config;

import javax.swing.*;
import java.io.File;
import java.util.List;
//import jxl.Workbook;
//import jxl.write.Label;
//import jxl.write.WritableWorkbook;
//import jxl.write.WritableSheet;
//import jxl.write.WriteException;

public class Exportar {

    private File archivo;
    private List<JTable> tabla;
    private List<String> nom_archivo;

    public Exportar(File archivo, List<JTable> tabla, List<String> nom_archivo) throws Exception {

        this.archivo = archivo;
        this.tabla = tabla;
        this.nom_archivo = nom_archivo;
        if (nom_archivo.size() != tabla.size()) {
            throw new Exception("Error");
        }

    }

//    public boolean export() {
//        try {
//            DataOutputStream out = new DataOutputStream(new FileOutputStream(archivo));
//            WritableWorkbook w = Workbook.createWorkbook(out);
//            for (int index = 0; index < tabla.size(); index++) {
//                JTable jtable = this.tabla.get(index);
//                WritableSheet s = w.createSheet(nom_archivo.get(index), 0);
//                for (int k = 0; k < jtable.getColumnCount(); k++) {
//                    s.addCell(new Label(k, 0, jtable.getColumnName(k)));
//                }
//                for (int i = 0; i < jtable.getColumnCount(); i++) {
//                    for (int j = 0; j < jtable.getRowCount(); j++) {
//                        Object obj = jtable.getValueAt(j, i);
//                        s.addCell(new Label(i, j + 1, String.valueOf(obj)));
//                    }
//                }
//            }
//            w.write();
//            w.close();
//            out.close();
//            return true;
//        } catch (IOException | WriteException e) {
//            return false;
//        }
//
//    }
}
