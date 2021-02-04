package it.unimol.wave_dash.utils;

import it.unimol.wave_dash.Game;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.*;
import it.unimol.wave_dash.objects.enemies.Enemy;
import it.unimol.wave_dash.objects.enemies.EnemyBossLvl10;
import it.unimol.wave_dash.objects.enemies.FastEnemy;
import it.unimol.wave_dash.objects.enemies.SmartEnemy;
import it.unimol.wave_dash.window.HUD;

import java.util.Random;

/**
 * Classe che gestisce lo spawn dei nemici nei vari livelli del gioco
 */
public class Spawn {
    private GameHandler handler;
    private HUD hud;
    private int scoreCount = 0;
    private Random r = new Random();
    private int neededPoints = 100, winningPoints = 500;


    public Spawn(GameHandler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }

    public void setNeededPoints(int points) {
        this.neededPoints = points;
    }

    public int getScoreCount() {
        return scoreCount;
    }

    public int getWinningPoints() {
        return winningPoints;
    }


    /**
     * Metodo che aggiorna lo spawn dei nemici dipendentemente dal livello del gioco
     */
    public void updateSpawn() {

        scoreCount++;

        if ((hud.getLevel() >= 1 && hud.getLevel() < 10) && scoreCount >= neededPoints) {


            scoreCount = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 1)
                handler.addObject((new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler)));

            if (hud.getLevel() == 2 || hud.getLevel() == 3)
                handler.addObject((new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler)));

            if (hud.getLevel() == 4 || hud.getLevel() == 6)
                handler.addObject((new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler)));

            if (hud.getLevel() == 7)
                handler.addObject((new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy, handler)));

            if (hud.getLevel() == 8)
                handler.addObject((new Enemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.Enemy, handler)));

            if (hud.getLevel() == 10) {

                handler.clearEnemies();
                handler.addObject((new EnemyBossLvl10(((Game.WIDTH / 2) - 48), -130, ID.Lvl10EnemyBoss, handler)));


            }
        }


    }
}

