package it.unimol.wave_dash.input;

import it.unimol.wave_dash.Game;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.window.HUD;
import it.unimol.wave_dash.objects.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Classe che gestisce il movimento del player in base ai tasti premuti dalla tastiera
 */
public class MovementPlayer extends KeyAdapter {

    private GameHandler handler;
    private HUD hud;
    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyRight = false;
    private boolean keyLeft = false;
    private Game game;


    public MovementPlayer(GameHandler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;

    }

    /**
     * Metodo che é invocato quando é premuto un tasto sulla tastiera
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);

            if (object.getID() == ID.Player) {

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    object.setVelY(-5);
                    keyUp = true;
                }
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                    object.setVelX(-5);
                    keyLeft = true;
                }
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                    object.setVelX(5);
                    keyRight = true;
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    object.setVelY(5);
                    keyDown = true;
                }
            }
            if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        }
        //Gestisce la pausa nel game
        if (key == KeyEvent.VK_P) {
            if (game.paused) game.paused = false;
            else if (!game.paused) game.paused = true;
        }

    }

    /**
     * Metodo che é invocato quando é rilasciato un tasto
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for (int i = 0; i < handler.objects.size(); i++) {
            GameObject object = handler.objects.get(i);
            if (object.getID() == ID.Player) {

                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) keyUp = false;
                if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) keyLeft = false;
                if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) keyRight = false;
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) keyDown = false;


                //Horizontal movement
                if (!keyLeft && !keyRight)
                    object.setVelX(0);
                //Vertical Movement
                if (!keyUp && !keyDown)
                    object.setVelY(0);

            }

            if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        }
    }
}
