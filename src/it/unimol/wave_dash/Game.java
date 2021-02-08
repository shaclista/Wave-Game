package it.unimol.wave_dash;

//import it.unimol.wave_dash.utils.AudioPlayer;
import it.unimol.wave_dash.input.Menu;
import it.unimol.wave_dash.input.MovementPlayer;
import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.not_enemies.BackgroundParticles;
import it.unimol.wave_dash.objects.ID;
import it.unimol.wave_dash.objects.not_enemies.Player;
import it.unimol.wave_dash.window.HUD;
import it.unimol.wave_dash.utils.Spawn;
import it.unimol.wave_dash.window.Window;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * Classe Gioco che estende Canvas, una componente che rappresenta un'area rettangolare su cui é possibile disegnare e ascoltare gli eventi di input
 * @author Simone Di Nucci
 */
public class Game extends Canvas {

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 12;

    private Thread thread;
    private boolean running = false;

    private GameHandler handler;
    Random random;
    private HUD hud;
    private Spawn spawn;
    private Menu menu;
    public boolean paused = false;
    private int lastScore;
    private int lastLevel;

    public int getLastScore(){ return lastScore; }


    /**
     * Enumerazione di stati di gioco utili per settare il GameState per il menù
     */
    //Indicizziamo gli elementi del menu e gioco
    public enum STATE {
        Menu,
        Game,
        Help,
        GameOver,
        GameWon,
    }

    public STATE gameState = STATE.Menu;

    /**
     *Costruttore della classe Game e istanza di gioco in cui vengono inizializzate tutte le classi
     *incluso l'AudioPlay e i listener
     */
    public Game() {
        handler = new GameHandler();
        menu = new it.unimol.wave_dash.input.Menu(this, handler,hud);
        this.addKeyListener(new MovementPlayer(handler, hud, this));
        this.addMouseListener(new Menu(this, handler,hud));
        //AudioPlayer.load();
        new Window(WIDTH, HEIGHT, "Wave!", this);
        //AudioPlayer.getMusic("music").loop();
        hud = new HUD();


        spawn = new Spawn(handler, hud);
        random = new Random();

        if (gameState == STATE.Game) {

            handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
        } else if (gameState == STATE.Menu) {
            for (int i = 0; i < 10; i++) {
                handler.addObject((new BackgroundParticles(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BackgroundParticles, handler)));

            }
        }
    }

    /**
     *Metodo start che crea il nuovo thread e lo fa partire
     *
     */
    public void start() {
        thread = new Thread(task);
        thread.start();
        running = true;
    }

    /**
     *Metodo stop che ferma l'esecuzione del thread e in caso di exception traccia dove si é verificata
     * @throw exception e
     */
    private void stop() {
        try {
            thread.join();
            running = false;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Classe anonima con espressione Lambda che implementa il Thread che permette l'esecuzione del gioco e descrive il Game loop infinito del gioco
     */
    Runnable task = () -> {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountofTicks = 60.0;
        double ns = 1000000000 / amountofTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                delta--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    };

    /**
     * Metodo che aggiorna costantemente il gioco attraverso tutte le loro componenti (handler,spawn,hud) modificando il Game State
     */
    private void update() {
        if (!paused) {

            handler.updateObject();
            if (gameState == STATE.Game) {

                hud.updateHUD();
                spawn.updateSpawn();

                if (hud.getLevel() == 10) {

                    if (spawn.getScoreCount() >= spawn.getWinningPoints()) {
                        lastLevel = hud.getLevel();

                        HUD.HEALTH = 100;
                        hud.setScore(0);
                        hud.setLevel(1);
                        spawn.setNeededPoints(100);
                        handler.clearAllObjects();
                        gameState = STATE.GameWon;

                        if (gameState == STATE.GameWon) {

                            for (int i = 0; i < 10; i++) {
                                handler.addObject((new BackgroundParticles(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BackgroundParticles, handler)));
                            }
                        }

                    }

                    for (int i = 0; i < handler.objects.size(); i++) {
                        if (handler.objects.get(i).getID() == ID.Player) {
                            if (handler.objects.get(i).y < HEIGHT - 360)
                                handler.objects.get(i).y = HEIGHT - 360;

                        }
                    }
                }
                if (hud.HEALTH <= 0) {
                    lastScore = hud.getScore();
                    HUD.HEALTH = 100;
                    hud.setScore(0);
                    hud.setLevel(1);
                    spawn.setNeededPoints(100);


                    handler.clearAllObjects();
                    gameState = STATE.GameOver;
                    if (gameState == STATE.GameOver) {

                        for (int i = 0; i < 10; i++) {
                            handler.addObject((new BackgroundParticles(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BackgroundParticles, handler)));
                        }
                    }
                }

            } else if (gameState == STATE.Menu || gameState == STATE.GameOver || gameState == STATE.GameWon) {
                    menu.updateMenu();
            }
        }

    }

    /**
     * Metodo che renderizza la componente Window in base allo stato del giocoe con una grafica 2d creando una strategia a triplo buffer
     * passando per il Back buffer,middle buffer e front buffer per poi mostrarsi sul display.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        if (paused) {
            g.setColor(Color.white);
            g.drawString("Game Paused", WIDTH - 90, 20);
        }
        handler.render(g);

        if (gameState == STATE.Game) {
            hud.render(g);

        } else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.GameWon) {
            menu.render(g);

        }
        g.dispose();                //Assegna al getbufferstrategy =  null
        bs.show();
    }


}
