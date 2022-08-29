package gamePackage;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Covid_Frame_Game extends Canvas implements Runnable{

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private boolean running = false;
    private Thread thread;
    private Handler handler;
    private Vis vis;
    private Menu menu;
    private EnemySpawner enemySpawner;
    private GameOver gameOver;

    public enum GameState{          //enumeration where I keep needed game states
        Menu,
        Play,
        GameOver
    };

    public GameState gameState = GameState.Play;       //current state of the game

    public Covid_Frame_Game(){

        handler = new Handler();
        menu = new Menu(this, handler);
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        new Frame(WIDTH, HEIGHT, "1901839", this );     //creates frame and initializes needed classes
        vis = new Vis();
        enemySpawner = new EnemySpawner(handler, vis);
        gameOver = new GameOver();


        if(gameState == GameState.Play) {                           //creates a player when game starts
            handler.addObject(new Player(WIDTH / 2 - 50, HEIGHT / 2 - 50, ID.Player, handler));
        }
    }

    public synchronized void start(){           //starts the game
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop(){            //stops the game
        try{
            thread.join();
            running = false;
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run(){                      //game loop to make game running
        this.requestFocus();
        long past = System.nanoTime();
        double numberOfTicks = 60.0;
        double nanosecs =(1000000000/numberOfTicks);
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(running){
            long now = System.nanoTime();
            delta += (now - past)/nanosecs;
            past = now;
            while(delta >= 1){
                time();
                delta--;
            }
            if(running)
                rendering();

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
            }
        }
        stop();

    }

    private void time() {                           //method to start timer for specific classes
        handler.time();
        if(gameState == GameState.Play){
            vis.time();
            enemySpawner.time();
        }
    }

    private void rendering() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }


        Graphics g = bufferStrategy.getDrawGraphics();      //sets color and size of our frame
        g.setColor(Color.lightGray);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.rendering(g);

        if(gameState == GameState.Play){            // renders graphics for specific states
            vis.rendering(g);
        }
        else if (gameState == GameState.Menu){
            menu.rendering(g);
            g.drawString("Social Distancing the Game", Covid_Frame_Game.WIDTH/6-20, Covid_Frame_Game.HEIGHT/12);

            g.drawString("Start", Covid_Frame_Game.WIDTH/6-70, (Covid_Frame_Game.HEIGHT/3)*2);
            g.drawString("High Scores", Covid_Frame_Game.WIDTH/2-150, (Covid_Frame_Game.HEIGHT/3)*2);
            g.drawString("Exit", (Covid_Frame_Game.WIDTH/6)*5-60, (Covid_Frame_Game.HEIGHT/3)*2);
        }
        else if (gameState == GameState.GameOver){
            gameOver.render(g);
            g.setColor(Color.RED);
            g.drawString("Risk of getting infected!",WIDTH/3-130, HEIGHT/12);
            g.drawString("You lose",WIDTH/2-120, HEIGHT/5);
            g.setColor(Color.BLACK);
            g.drawString("Your score: " + vis.getScore(),WIDTH/3-10, HEIGHT/3);
            g.drawString("High Scores",WIDTH/3, (HEIGHT/6)*3);

            g.drawString("Back",(WIDTH/8)*7-30, (HEIGHT/6)*5+70);


        }

        g.dispose();
        bufferStrategy.show();
    }


    public static void main(String[] args) {
        new Covid_Frame_Game();
    }

}
