package com.ybc.ybioq.fx.util;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public final class AutocompleteTextField {

    private static final int MAX_RESULTADOS = 12;

    private AutocompleteTextField() {}

    public static <T> void configurar(TextField field,
                                      List<T> items,
                                      Function<T, String> toLabel,
                                      Consumer<T> onSelect) {

        ContextMenu popup = new ContextMenu();
        popup.setAutoHide(true);

        boolean[] seleccionando = {false};
        int[]     cursorIdx     = {-1};

        field.textProperty().addListener((obs, anterior, texto) -> {
            if (seleccionando[0]) return;
            cursorIdx[0] = -1;

            if (texto == null || texto.isBlank()) { popup.hide(); return; }

            String q      = texto.trim();
            String filtro = q.toLowerCase();

            // No mostrar popup si el texto ya es coincidencia exacta (fue cargado por código)
            boolean esExacta = items.stream()
                    .anyMatch(i -> { String l = toLabel.apply(i); return l != null && l.equalsIgnoreCase(q); });
            if (esExacta) { popup.hide(); return; }

            List<T> matches = items.stream()
                    .filter(i -> { String l = toLabel.apply(i); return l != null && l.toLowerCase().contains(filtro); })
                    .sorted((a, b) -> {
                        int ra = rankMatch(toLabel.apply(a), filtro);
                        int rb = rankMatch(toLabel.apply(b), filtro);
                        return ra != rb ? Integer.compare(ra, rb)
                                       : toLabel.apply(a).compareToIgnoreCase(toLabel.apply(b));
                    })
                    .limit(MAX_RESULTADOS)
                    .toList();

            if (matches.isEmpty()) { popup.hide(); return; }

            popup.getItems().clear();
            for (T item : matches) {
                String label = toLabel.apply(item);
                MenuItem mi  = new MenuItem(label);
                mi.setUserData(new Object[]{item, label});
                mi.setOnAction(e -> confirmar(field, popup, seleccionando, label, item, onSelect));
                popup.getItems().add(mi);
            }

            if (!popup.isShowing()) popup.show(field, Side.BOTTOM, 0, 0);
        });

        field.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (!popup.isShowing()) return;
            int size = popup.getItems().size();
            if (size == 0) return;

            switch (e.getCode()) {
                case DOWN -> {
                    moverCursor(popup, cursorIdx, Math.min(cursorIdx[0] + 1, size - 1));
                    e.consume();
                }
                case UP -> {
                    if (cursorIdx[0] > 0) moverCursor(popup, cursorIdx, cursorIdx[0] - 1);
                    e.consume();
                }
                case ENTER -> {
                    if (cursorIdx[0] >= 0) {
                        // Hay ítem resaltado (por flechas o por Enter anterior) → confirmar
                        confirmarPorIndice(popup, cursorIdx[0], field, seleccionando, onSelect);
                    } else if (size == 1) {
                        // Un solo resultado → confirmar directo
                        confirmarPorIndice(popup, 0, field, seleccionando, onSelect);
                    } else {
                        // Múltiples sin cursor: primer Enter resalta el primero,
                        // el siguiente Enter lo confirmará
                        moverCursor(popup, cursorIdx, 0);
                    }
                    e.consume();
                }
                case ESCAPE -> { popup.hide(); cursorIdx[0] = -1; e.consume(); }
                default -> {}
            }
        });

        field.focusedProperty().addListener((obs, o, focused) -> {
            if (!focused) { popup.hide(); cursorIdx[0] = -1; }
        });
    }

    @SuppressWarnings("unchecked")
    private static <T> void confirmarPorIndice(ContextMenu popup, int idx, TextField field,
                                               boolean[] seleccionando, Consumer<T> onSelect) {
        Object[] ud    = (Object[]) popup.getItems().get(idx).getUserData();
        T        item  = (T) ud[0];
        String   label = (String) ud[1];
        confirmar(field, popup, seleccionando, label, item, onSelect);
    }

    private static void moverCursor(ContextMenu popup, int[] cursorIdx, int newIdx) {
        for (int i = 0; i < popup.getItems().size(); i++) {
            String clean = (String) ((Object[]) popup.getItems().get(i).getUserData())[1];
            popup.getItems().get(i).setText(i == newIdx ? "► " + clean : clean);
        }
        cursorIdx[0] = newIdx;
    }

    private static <T> void confirmar(TextField field, ContextMenu popup, boolean[] seleccionando,
                                      String label, T item, Consumer<T> onSelect) {
        seleccionando[0] = true;
        field.setText(label);
        field.positionCaret(label.length());
        seleccionando[0] = false;
        popup.hide();
        onSelect.accept(item);
    }

    /**
     * Rango de prioridad (menor = más relevante).
     * Analiza el código (segmento antes del " - ") para ítems con formato "CODIGO - NOMBRE".
     */
    private static int rankMatch(String label, String filtro) {
        if (label == null) return 99;
        String lower    = label.toLowerCase();
        String segmento = lower.contains(" - ") ? lower.substring(0, lower.indexOf(" - ")).trim() : lower;

        if (segmento.equals(filtro))      return 0;   // código exacto
        if (segmento.startsWith(filtro))  return 1;   // código empieza con lo buscado
        if (segmento.endsWith(filtro))    return 2;   // código termina con lo buscado
        if (segmento.contains(filtro))    return 3;   // código contiene lo buscado
        return 99;                                     // coincidencia solo en nombre
    }
}
