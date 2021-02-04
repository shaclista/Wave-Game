package it.unimol.wave_dash.objects;

import java.awt.*;
import java.util.LinkedList;

/**
 * Classe che gestisce gli oggetti del gioco
 */
public class GameHandler {
    public LinkedList<GameObject> objects = new LinkedList<GameObject>();

    /**
     * Metodo che aggiorna gli oggetti presenti in gioco
     */
    public void updateObject() {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i); //Reset temp to get current object
            temp.move();
        }

    }

    /**
     * Metodo che aggiorna gli oggetti visibili nel gioco
     */
    public void render(Graphics g) {
        for (int i = 0; i < objects.size(); i++) {
            GameObject temp = objects.get(i); //Reset temp to get current object
            temp.render(g);

        }
    }

    public void addObject(GameObject object) {
        this.objects.add(object);
    }

    public void clearEnemies() {
        for (int i = 0; i < objects.size(); i++) {
            if (objects.get(i).getID() != ID.Player) {
                removeObject(objects.get(i));
                i--; //Decrementare la i in quanto se rimuovi un elemento della lista perdi il successivo il quale scala di posizione
            }
        }
    }

    public void clearAllObjects() {
        for (int i = 0; i < objects.size(); i++) {
            removeObject(objects.get(i));
            i--;

        }
    }

    public void removeObject(GameObject object) {
        this.objects.remove(object);
    }

}
