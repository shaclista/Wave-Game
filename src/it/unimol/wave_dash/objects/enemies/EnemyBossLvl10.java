package it.unimol.wave_dash.objects.enemies;

import it.unimol.wave_dash.*;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;

import java.awt.*;
import java.util.Random;

/**
 * Classe del Boss del livello 10 del gioco
 */
public class EnemyBossLvl10 extends GameObject {
    private GameHandler handler;
    Random random = new Random();
    private int stopTime = 60;
    private int timer = 50;

    public EnemyBossLvl10(int x, int y, ID id, GameHandler handler) {
        super(x, y, id);
        this.handler = handler;

        vely = 3;
        velx = 0;
    }

    public Rectangle getOutline() {
        return new Rectangle(x, y, 96, 96);
    }


    /**
     * Metodo che gestisce il movimento del boss e spawna i proiettili
     */
    public void move() {
        x += velx;
        y += vely;

        if (stopTime == 0) {
            vely = 0;
        } else
            stopTime--;

        if (stopTime <= 0) timer--;
        if (timer <= 0) {

            if (velx == 0) velx = 3;
            if (velx > 0)
                velx += 0.005f;

            else if (velx < 0)
                velx -= 0.005f;

            if (velx > 7)
                velx = 7;
            else if (velx < -7)
                velx = -7;

            int spawn = random.nextInt(10);
            if (spawn == 0) handler.addObject(new BossRockets(x + 48, y + 48, ID.BossRockets, handler));
        }
        if (x <= 0 || x >= Game.WIDTH - 100) velx *= -1;


    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(x, y, 96, 96);

    }
}


