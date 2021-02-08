package it.unimol.wave_dash.input;

import it.unimol.wave_dash.Game;
import it.unimol.wave_dash.objects.GameHandler;
//import it.unimol.wave_dash.utils.AudioPlayer;
import it.unimol.wave_dash.window.HUD;
import it.unimol.wave_dash.objects.enemies.Enemy;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private GameHandler handler;
    private Random random = new Random();
    HUD hud;


    public Menu(Game game, GameHandler handler,HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    /**
     * Metodo che é invocato quando un pulsante del mouse é stato premuto, dipendentemente dallo stato del gioco in cui si trova.
     * @param e che corrispondo al relativo evento
     */
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();


        if (game.gameState == Game.STATE.Menu) {

            //Bottone PLAY
            if (isMouseOverTarget(235, 150, mx, my, 200, 64)) {

                handler.clearEnemies();
                game.gameState = Game.STATE.Game;

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject((new Enemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.Enemy, handler)));
                //AudioPlayer.getSound("sound").play();

            }

            //Bottone QUIT
            if (isMouseOverTarget(235, 350, mx, my, 200, 64)) {

                //AudioPlayer.getSound("sound").play();

                System.exit(1);

            }

            //Bottone HELP
            if (isMouseOverTarget(235, 250, mx, my, 200, 64)) {
                game.gameState = Game.STATE.Help;
                //AudioPlayer.getSound("sound").play();

            }


        }

        //Bottone BACK in Help
        if (game.gameState == Game.STATE.Help) {
            if (isMouseOverTarget(40, 30, mx, my, 150, 40)) {
                game.gameState = Game.STATE.Menu;
                //AudioPlayer.getSound("sound").play();

                return;


            }
        }

        //Bottone BACK e TRY AGAIN a GameOver
        if (game.gameState == Game.STATE.GameOver) {

            if (isMouseOverTarget(50, 230, mx, my, 80, 35)) {
                game.gameState = Game.STATE.Menu;
                //AudioPlayer.getSound("sound").play();
                return;
            } else if (isMouseOverTarget(465, 230, mx, my, 150, 35)) {

                handler.clearAllObjects();
                game.gameState = Game.STATE.Game;


                if (game.gameState == Game.STATE.Game) {

                    handler.addObject(new Enemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.Enemy, handler));

                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));

                   // AudioPlayer.getSound("sound").play();


                }
            }
        }

        //Bottone BACK e TRY AGAIN quando si vince
        if (game.gameState == Game.STATE.GameWon) {

            if (isMouseOverTarget(50, 230, mx, my, 80, 35)) {
                game.gameState = Game.STATE.Menu;
                //AudioPlayer.getSound("sound").play();
                return;
            } else if (isMouseOverTarget(465, 230, mx, my, 150, 35)) {

                handler.clearAllObjects();
                game.gameState = Game.STATE.Game;

                if (game.gameState == Game.STATE.Game) {

                    handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));


                    handler.addObject(new Enemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.Enemy, handler));


                   // AudioPlayer.getSound("sound").play();


                }
            }
        }

    }

    /**
     * Metodo per controllare se il mouse si trova nel perimetro delle box del menu e dei tasti in generale
     * @param x coordinata x del rettangolo
     * @param y coordinata y del rettangolo
     * @param mx coordinata x del mouse al momento del click
     * @param my coordinata y del mouse al momento del click
     * @param width largezza del rettangolo
     * @param height altezza del rettangolo
     *
     */
    //Funzione per controllare se il mouse si trova sulla box interessata
    //Controlla se le cordinate mx e my si trovano nella x e y della box
    private boolean isMouseOverTarget(int x, int y, int mx, int my, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height)
                return true;
            else return false;
        } else return false;


    }

    /**
     * Interfaccia  dei vari stati del game
     * @param g
     */
    public void render(Graphics g) {

        //Grafica MENU/GAMEOVER/GAMEWON
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.orange);
            g.drawString("WaveDash", 220, 70);

            g.setFont(fnt2);
            g.drawRect(235, 150, 200, 64);
            g.drawString("Play", 305, 190);
            g.setColor(Color.orange);
            g.drawRect(235, 250, 200, 64);
            g.drawString("Help", 305, 290);
            g.setColor(Color.orange);
            g.drawRect(235, 350, 200, 64);
            g.drawString("Quit", 305, 390);

        } else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 3, 15);

            g.setFont(fnt);
            g.setColor(Color.orange);
            g.drawString("Help", 240, 70);
            g.setFont(fnt2);
            g.drawRect(40, 30, 150, 40);
            g.drawString("Back", 75, 60);
            g.setFont(fnt3);
            g.drawRect(50, 120, 400, 100);
            g.drawString("To move the player you can use W-A-S-D keys or UP", 50, 150);
            g.drawString("DOWN-RIGHT-LEFT and the objective is to dodge the", 50, 170);
            g.drawString("enemies and defeat the boss.", 50, 190);
            g.drawString("To pause or unpause the game press P", 50, 210);

        } else if (game.gameState == Game.STATE.GameOver) {
            Font fnt2 = new Font("Courier New", 2, 55);
            Font fnt3 = new Font("Courier New", 2, 20);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 185, 150);
            g.setFont(fnt3);

            g.setColor(Color.white);
            g.drawString("Your score was of:" + game.getLastScore(), 200, 180);

            g.drawRect(50, 230, 80, 35);
            g.drawRect(465, 230, 150, 35);
            g.drawString("Back", 60, 250);
            g.drawString("Try Again", 480, 250);
        } else if (game.gameState == Game.STATE.GameWon) {
            Font fnt2 = new Font("Rockwell", 3, 30);
            Font fnt3 = new Font("Courier New", 2, 20);

            g.setFont(fnt2);
            g.setColor(Color.GREEN);
            g.drawString("YOU WIN!", 240, 150);
            g.drawString("Congratulation!", 200, 185);

            g.setColor(Color.GREEN);
            g.setFont(fnt3);
            g.drawRect(50, 230, 80, 35);
            g.drawRect(465, 230, 150, 35);
            g.drawString("Back", 60, 250);
            g.drawString("Play Again", 480, 250);
        }
    }


    public void updateMenu() {
    }
}
