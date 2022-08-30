package asteroids_start;

import utilities.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;

public class Asteroid extends GameObject {
    public static final double VMAX = 200;
    public final static int RADIUS = 20;
    private int type;
    public final static int BIG = 2;
    public final static int MEDIUM = 1;
    public final static int SMALL = 0;


    public Asteroid(double x, double y, double vx, double vy) {
        super(x,y,vx,vy,RADIUS);
        this.position = new Vector2D(x,y);
        this.velocity = new Vector2D(vx, vy);
    }
/*
    public Asteroid() {
        this(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random(),
                2 * VMAX * (Math.random() - 0.5), 2 * VMAX * (Math.random() - 0.5));
    }
*/
    @Override
    public void draw(Graphics2D g) {
        int x = (int) position.x;
        int y = (int) position.y;
        g.setColor(new Color(48, 185, 164));
        g.drawOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
