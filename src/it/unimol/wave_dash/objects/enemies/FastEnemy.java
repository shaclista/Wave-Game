package it.unimol.wave_dash.objects.enemies;

import it.unimol.wave_dash.*;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Trail;

import java.awt.*;

public class FastEnemy extends GameObject {
    private GameHandler handler;

    public FastEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        vely = 2;
        velx = 8;
    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 16, 16);
    }


    public void move() {
        x += velx;
        y += vely;


        if (y <= 0 || y >= Game.HEIGHT - 32) vely *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velx *= -1;
        handler.addObject(new Trail(x, y, 16, 16, ID.Pathway, handler, 0.05f, Color.cyan));

    }


    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(x, y, 16, 16);

    }
}


