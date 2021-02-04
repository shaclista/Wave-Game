package it.unimol.wave_dash.objects;
//Classe astratta che rappresenta tutti gli oggetti che fanno parte del gioco ( player, nemici ,ecc...)

import it.unimol.wave_dash.objects.ID;

import java.awt.*;

/**
 * Classe astratta che contiene le caratteristiche di tutti gli oggetti del gioco
 */
public abstract class GameObject {

    public int x, y;
    public ID id;
    public float velx, vely;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void move();

    public abstract void render(Graphics g);

    /**
     * Metodo che ritorna il contorno di un rettangolo e server per gestire le collisioni tra oggetti del gioco in quanto sono tutti rettangoli
     * @return
     */
    public abstract Rectangle getOutline();


    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(int velX) {
        this.velx = velX;
    }

    public void setVelY(int velY) {
        this.vely = velY;
    }

}
