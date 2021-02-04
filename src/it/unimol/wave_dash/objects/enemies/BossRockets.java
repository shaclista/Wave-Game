package it.unimol.wave_dash.objects.enemies;

import it.unimol.wave_dash.Game;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Trail;

import java.awt.*;
import java.util.Random;

/**
 * Classe dei missili del Boss del livello 10 del gioco
 */
public class BossRockets extends GameObject {
    private GameHandler handler;
    Random random = new Random();

    public BossRockets(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        velx = (random.nextInt(5 - -5) + -5);
        vely = 6;
    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 12, 12);
    }


    public void move() {
        x += velx;
        y += vely;

        if (y <= 0 || y >= Game.HEIGHT - 32) handler.removeObject(this);
        if (x <= 0 || x >= Game.WIDTH - 32) handler.removeObject(this);
        handler.addObject(new Trail(x, y, 12, 12, ID.Pathway, handler, 0.05f, Color.red));

    }


    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 12, 12);

    }
}


