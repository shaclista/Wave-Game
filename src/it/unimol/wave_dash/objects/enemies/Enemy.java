package it.unimol.wave_dash.objects.enemies;

import it.unimol.wave_dash.*;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Trail;

import java.awt.*;

/**
 * Classe del nemico di base del gioco
 */
public class Enemy extends GameObject {
    private GameHandler handler;

    public Enemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        vely = 5;
        velx = 5;

        if (velx == 0) velx = 1;
        if (vely == 0) vely = 1;
    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 16, 16);
    }


    public void move() {
        x += velx;
        y += vely;


        if (y <= 0 || y >= Game.HEIGHT - 32) vely *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velx *= -1;
        handler.addObject(new Trail(x, y, 16, 16, ID.Pathway, handler, 0.05f, Color.red));

    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 16, 16);

    }
}


