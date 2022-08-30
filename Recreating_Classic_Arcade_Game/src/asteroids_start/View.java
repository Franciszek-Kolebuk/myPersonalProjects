package asteroids_start;

import java.awt.*;
import javax.swing.JComponent;

public class View extends JComponent {
    private Game game;
    public static final Color BG_COLOR = Color.black;
    private Iterable<GameObject> gameObjects;
    private int iteration = 0;
    private long t0 = System.currentTimeMillis();

    public View(Iterable<GameObject> gameObjects, Game game) {
        this.gameObjects = gameObjects;
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics g0) {
        Graphics2D g = (Graphics2D) g0;
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        synchronized (Game.class) {
            for (GameObject object : gameObjects)
                object.draw(g);
        }

        //score panel
        g.setColor(Color.black);
        g.drawRect(0, getHeight() - Constants.SCORE_PANEL_HEIGHT, getWidth(), Constants.SCORE_PANEL_HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font("dialog", Font.BOLD, (2 * Constants.SCORE_PANEL_HEIGHT / 3)));
        g.drawString("Level: " + game.getLevel(), 10, getHeight() - Constants.SCORE_PANEL_HEIGHT/3);
        g.drawString("Lives: " + game.getLives(), getWidth()/3, getHeight() - Constants.SCORE_PANEL_HEIGHT/3);
        g.drawString("Score: " + game.getScore(), 2*getWidth()/3, getHeight() - Constants.SCORE_PANEL_HEIGHT/3);

        iteration++;
        if (System.currentTimeMillis() - t0 > 1000) {
            iteration = 0;
            t0 = System.currentTimeMillis();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}