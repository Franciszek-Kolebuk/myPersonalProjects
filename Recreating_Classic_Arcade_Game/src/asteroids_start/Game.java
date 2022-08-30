package asteroids_start;

import utilities.JEasyFrame;
import utilities.SoundManager;

import java.awt.geom.FlatteningPathIterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class  Game {
    public static final int INITIAL_ASTEROIDS = 5;
    public static final int INITIAL_LIVES = 3;
    private static int lives;
    public List<GameObject> objects;
    public Ship ship;
    public View view;
    int score;
    int activeAsteroids;
    int level;
    long startTime;

    public Game() {
        objects = new LinkedList<>();
        for (int i = 0; i < INITIAL_ASTEROIDS; i++) {
            objects.add(new Asteroid(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                    2 * Asteroid.VMAX * (Math.random() - 0.5), 2 * Asteroid.VMAX * (Math.random() - 0.5)));

        }
        objects.add(new FlyingSaucer(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                2 * FlyingSaucer.VMAX * (Math.random() - 0.5), 2 * FlyingSaucer.VMAX * (Math.random() - 0.5)));
        Keys keys = new Keys();
        ship = new Ship(keys);
        objects.add(ship);
        view = new View(objects, this);
        score = 0;
        lives = INITIAL_LIVES;
        level = 1;
        activeAsteroids = INITIAL_ASTEROIDS;
        JEasyFrame jf = new JEasyFrame(view, "Asteroid Game");
        jf.setResizable(false);
        jf.addKeyListener(keys);
    }

    public static void main(String[] args) {
        gameLoop();
        }

    public static void gameLoop() {
        Game game = new Game();
        final long DT_MS = Math.round(1000 / Constants.FPS);
        while (true){
            long time0 = System.currentTimeMillis();
            game.update();
            game.view.repaint();
            long timeToSleep = DT_MS + time0 - System.currentTimeMillis();
            if (!(timeToSleep < 0)){
                try{
                    Thread.sleep(timeToSleep);
                } catch (Exception e){
                }
            }
        }
    }

    public int getScore() {
        return score;
    }

    public static int getLives() {
        return lives;
    }

    public static void decreaseLives(){
        lives--;
    }

    public int getLevel() {
        return level;
    }

    public boolean reset(boolean newLevel) {
        objects.clear();
        if (newLevel){
            level++;
            lives++;
        }
        for (int i = 0; i < INITIAL_ASTEROIDS + (level - 1) * 5; i++) {
            objects.add(new Asteroid(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                    2 * Asteroid.VMAX * (Math.random() - 0.5), 2 * Asteroid.VMAX * (Math.random() - 0.5)));
        }

        objects.add(new FlyingSaucer(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                2 * FlyingSaucer.VMAX * (Math.random() - 0.5), 2 * FlyingSaucer.VMAX * (Math.random() - 0.5)));

        activeAsteroids = INITIAL_ASTEROIDS + (level - 1) * 5;
        ship.reset();
        objects.add(ship);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        startTime = System.currentTimeMillis();
        return true;
    }

    public void update() {
        for (GameObject object : objects) {
            for (GameObject other : objects) {
                if (object != other)
                    object.collisionHandling(other);
            }
        }
        List<GameObject> alive = new ArrayList<GameObject>();
        for (GameObject object : objects) {
            if (!object.dead) {
                object.update();
                alive.add(object);
            }
            else {
                updateScore(object);
            }
            if(object instanceof FlyingSaucer){
                FlyingSaucer flyingSaucer = (FlyingSaucer) object;
                if(flyingSaucer.bullet!=null){
                    alive.add(flyingSaucer.bullet);
                    flyingSaucer.bullet=null;}

            }
        }
        if (ship.bullet != null) {
            alive.add(ship.bullet);
            ship.bullet = null;
        }
        if(lives<=0){
            System.exit(0);
        }

        if(score%1000==0 && score>0){
            lives++;
            score+=100;
        }
        synchronized (Game.class) {
            if(activeAsteroids==0){
                reset(true);
            } else {
                objects.clear();
                objects.addAll(alive);
            }
        }
}
    public void updateScore(GameObject object){
        if (object.getClass() == Asteroid.class) {
            score += 100;
            activeAsteroids -= 1;

        }
    }
}
