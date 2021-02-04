package it.unimol.wave_dash.objects.not_enemies;

import it.unimol.wave_dash.*;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;

import java.awt.*;
import java.util.Random;

/**
 * Classe dei missili id background del gioco
 */
public class BackgroundParticles extends GameObject {
    private GameHandler handler;
    Random random = new Random();
    private Color color = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));

    public BackgroundParticles(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        velx = (random.nextInt(6 - -6) + -6);
        vely = (random.nextInt(6 - -6) + -6);

        if (velx == 0) velx = 1;
        if (vely == 0) vely = 1;
    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 13, 13);
    }


    public void move() {
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT - 32) vely *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velx *= -1;
        handler.addObject(new Trail(x, y, 13, 13, ID.Pathway, handler, 0.04f, color));

    }


    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, 13, 13);

    }
}


