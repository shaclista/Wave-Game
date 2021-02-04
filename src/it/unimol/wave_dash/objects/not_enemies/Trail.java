package it.unimol.wave_dash.objects.not_enemies;

import it.unimol.wave_dash.objects.GameHandler;
import it.unimol.wave_dash.objects.GameObject;
import it.unimol.wave_dash.objects.ID;

import java.awt.*;

/**
 * Classe che crea la scia degli oggetti del game
 */
public class Trail extends GameObject {
    private float alpha = 1;
    private GameHandler handler;
    private float life;
    int width, lenghth;
    private Color color;


    public Trail(int x, int y, int width, int length, ID id, GameHandler handler, float life, Color color) {
        super(x, y, id);
        this.handler = handler;
        this.life = life;
        this.width = width;
        this.lenghth = length;
        this.color = color;
    }

    /**
     * Metodo che gestisce la vita o durata della scia degli oggetti
     */
    @Override
    public void move() {
       if (alpha > life) {
            alpha -= life - 0.005f;
        } else
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(becomeTransparent(alpha));
        g.setColor(color);
        g.fillRect(x, y, width, lenghth);
        g2d.setComposite(becomeTransparent(alpha));
    }

    /**
     * Metodo che gestisce la trasparenza degli oggetti
     * @param alpha é un valore usato per modificare l'opacitá di ogni pixel dell'oggetto considerato
     * @return ritorna l'istanza di trasparenza ottenuta dalle regola precedentemente specificate con SRC_OVER e il valore alpha
     */
    public AlphaComposite becomeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Rectangle getOutline() {
        return null;
    }
}
