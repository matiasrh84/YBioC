package com.ybc.ybioq.config;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * @author PC1
 */
public class Escape {
    public static void funcionescape(final JDialog windowDialog) {
        ActionListener escAction = e -> windowDialog.dispose();
        windowDialog.getRootPane().registerKeyboardAction(escAction,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
