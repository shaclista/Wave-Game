package it.unimol.wave_dash.window;

import java.awt.*;

/**
 * Classe Head of display, ossia di tutte le informazioni presente sull'interfaccia durante lo state di game del gioco
 */
public class HUD {

    public static int HEALTH = 100;
    private int greenVar = 255;
    private int score = 0;
    private int level = 1;


    /**
     * Metodo per aggiornare il colore della vita man mano che il player perde vita e il punteggio assegnato
     */
    public void updateHUD() {

        if (HEALTH >= 100)
            greenVar = 100;
        else if (greenVar <= 0)
            greenVar = 0;
        else greenVar = 255;
        if (greenVar >= 255)
            greenVar = 255;
        else if (greenVar <= 0)
            greenVar = 0;
        else greenVar = 255;

        greenVar = HEALTH * 2;
        score++;

    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color((int)75, (int)greenVar, (int)0));
        g.fillRect(15, 15, HEALTH * 2, 32);

        g.drawString("Score " + score, 10, 64);
        g.drawString("Level " + level, 10, 80);

    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
