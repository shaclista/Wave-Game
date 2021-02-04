package it.unimol.wave_dash.window;

import it.unimol.wave_dash.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Classe che crea un frame e imposta le caratteristiche base della finestra
 */
public class Window extends Canvas {

    public Window(int width, int height, String title, Game game) {
        JFrame frame = new JFrame((title));

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game);
        frame.setVisible(true);
        game.start();

    }


}

