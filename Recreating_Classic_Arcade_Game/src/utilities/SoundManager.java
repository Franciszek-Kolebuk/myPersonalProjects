package utilities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class SoundManager {
    static int nOfBullets = 0;
    static boolean thrusting = false;

    final static String path = "sounds/";

    public final static Clip shot = getClip("shot");
    public final static Clip boom = getClip("boom");
    public final static Clip getPoint = getClip("getPoint");
    public final static Clip live = getClip("live");
    public final static Clip warp = getClip("warp");
    public final static Clip saucersBullet = getClip("saucersBullet");
    public final static Clip death = getClip("death");
    public final static Clip engine = getClip("engine");

    public final static Clip[] clips = {shot, boom, getPoint, live, warp, saucersBullet, death, engine};

    public final static Clip[] bullets = new Clip[10];

    static{
        for (int i = 0; i < bullets.length; i++){
            bullets[i] = getClip("shot");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            shot();
            Thread.sleep(300);
        }
        for (Clip clip : clips){
            play(clip);
            Thread.sleep(1000);
        }

    }
    public static void play(Clip clip){
        clip.setFramePosition(0);
        clip.start();
    }

    public static Clip getClip(String filename) {
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            AudioInputStream storedSound = AudioSystem.getAudioInputStream(new File(path + filename+".wav"));
            clip.open(storedSound);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return clip;
    }

    public static void shot(){
        Clip clip = bullets[nOfBullets];
        clip.setFramePosition(0);
        clip.start();
        nOfBullets = (nOfBullets+1) % bullets.length;
    }

    public static void engineStart(){
        if (!thrusting){
            engine.loop(-1);
            thrusting = true;
        }
    }

    public static void engineStop(){
        engine.loop(0);
        thrusting = false;
    }

    public static void getLive(){play(live);}
    public static void getPoint(){play(getPoint);}
    public static void death(){play(death);}
    public static void warp() {play(warp);
    }
}
