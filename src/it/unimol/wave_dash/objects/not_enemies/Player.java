package it.unimol.wave_dash.objects.not_enemies;

import it.unimol.wave_dash.*;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.window.HUD;

import java.awt.*;


public class Player extends GameObject {
    GameHandler handler;

    public Player(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;


    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 32, 32);
    }


    public void move() {
        x += velx;
        y += vely;

        handler.addObject(new Trail(x, y, 32, 32, ID.Pathway, handler, 0.08f, Color.white));

        if (x > Game.WIDTH - 32)
            x = Game.WIDTH - 32;
        if (x < 0)
            x = 0;
        if (y > Game.HEIGHT - 54)
            y = Game.HEIGHT - 54;
        if (y < 0)
            y = 0;


        collision();


    }

    /**
     * Metodo che gestisce la collisione tra rettangoli
     */
    public void collision() {
        for (int i = 0; i < handler.objects.size(); i++) {

            if (handler.objects.get(i).getID() == ID.Enemy || handler.objects.get(i).getID() == ID.FastEnemy ||
                    handler.objects.get(i).getID() == ID.SmartEnemy || handler.objects.get(i).getID() == ID.BossRockets) {
                if (getOutline().intersects(handler.objects.get(i).getOutline()))
                    HUD.HEALTH -= 4;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 32, 32);

    }


}
