package it.unimol.wave_dash.objects.enemies;

import it.unimol.wave_dash.Game;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Trail;

import java.awt.*;

/**
 * Classe del nemico intelligente del gioco
 */
public class SmartEnemy extends GameObject {

    private GameHandler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        for (int i = 0; i < handler.objects.size(); i++) {
            if (handler.objects.get(i).getID() == ID.Player)
                player = handler.objects.get(i);
        }


    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 16, 16);
    }


    /**
     * Metodo che gestisce il movimento del nemico calcolando la distanza tra l'oggetto e il player e incrementa la propria velocitá seguendo
     * le coordinate del player.
     */
    public void move() {
        x += velx;
        y += vely;

        float diffX = x - player.getX();
        float diffY = y - player.getY();


        float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));

        velx = (int) ((-1 / (distance)) * diffX * 3);
        vely = (int) ((-1 / (distance)) * diffY * 3);


        if (y <= 0 || y >= Game.HEIGHT - 32) vely *= -1;
        if (x <= 0 || x >= Game.WIDTH - 32) velx *= -1;
        handler.addObject(new Trail(x, y, 16, 16, ID.Pathway, handler, 0.05f, Color.MAGENTA));

    }


    public void render(Graphics g) {
        g.setColor(Color.MAGENTA);
        g.fillRect(x, y, 16, 16);

    }
}
