package it.unimol.wave_dash.utils;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

/**
 * Metodo che gestisce l'input della musica e del suono del gioco
 */
public class AudioPlayer {


    private static Map<String,Music> musicMap = new HashMap<String, Music>();
    private static Map<String,Sound> soundMap = new HashMap<String, Sound>();


    public static void load(){

        try {
            System.out.println("Musica");
            musicMap.put("music",new Music("./res/455109-slaking-97-free-music-bac.ogg"));
            soundMap.put("sound",new Sound("./res/Mouse-Click-02-c-FesliyanStudios.com.ogg"));


        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key){
        return musicMap.get(key);
    }
    public static Sound getSound(String key){
        return soundMap.get(key);
    }



}
