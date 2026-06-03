package com.ybc.ybioq.fx.util;

import javafx.animation.TranslateTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Toggle switch (palanca on/off) puro JavaFX sin dependencias externas.
 * Uso en FXML: {@code <ToggleSwitchControl fx:id="miSwitch"/>}
 * API compatible con CheckBox/ToggleButton: isSelected(), setSelected(), selectedProperty().
 */
public class ToggleSwitchControl extends StackPane {

    private static final double TRACK_W  = 46;
    private static final double TRACK_H  = 22;
    private static final double THUMB_R  = 9;
    private static final double THUMB_ON  =  12;
    private static final double THUMB_OFF = -12;

    private static final Color COLOR_OFF = Color.web("#b8c8d4");
    private static final Color COLOR_ON  = Color.web("#0077a8");

    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final Rectangle track  = new Rectangle(TRACK_W, TRACK_H);
    private final Circle    thumb  = new Circle(THUMB_R);

    public ToggleSwitchControl() {
        track.setArcWidth(TRACK_H);
        track.setArcHeight(TRACK_H);
        track.setFill(COLOR_OFF);

        thumb.setFill(Color.WHITE);
        thumb.setEffect(new DropShadow(4, 0, 1, Color.color(0, 0, 0, 0.30)));
        thumb.setTranslateX(THUMB_OFF);

        selected.addListener((obs, oldVal, newVal) -> animarCambio(newVal));

        setOnMouseClicked(e -> setSelected(!isSelected()));
        setCursor(Cursor.HAND);
        setMaxWidth(TRACK_W);
        getChildren().addAll(track, thumb);
    }

    private void animarCambio(boolean on) {
        track.setFill(on ? COLOR_ON : COLOR_OFF);
        TranslateTransition tt = new TranslateTransition(Duration.millis(160), thumb);
        tt.setToX(on ? THUMB_ON : THUMB_OFF);
        tt.play();
    }

    // ── API pública ─────────────────────────────────────────────

    public BooleanProperty selectedProperty() { return selected; }

    public boolean isSelected() { return selected.get(); }

    public void setSelected(boolean value) { selected.set(value); }
}
