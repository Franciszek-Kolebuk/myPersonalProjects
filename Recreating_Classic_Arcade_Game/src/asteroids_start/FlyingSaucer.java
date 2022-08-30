package asteroids_start;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.Color;
import java.awt.Graphics2D;

import static asteroids_start.Constants.DT;

public class FlyingSaucer extends GameObject {
    public static final double VMAX = 200;
    public final static int RADIUS = 20;
    public SaucerBullet bullet;
    public Vector2D dir = new Vector2D(0,0);
    private Controller ctrl;

    public FlyingSaucer(

        double x, double y, double vx, double vy
    ) {
        super(x,y,vx,vy,RADIUS);
       // this.position = new Vector2D(x,y);
        //this.velocity = new Vector2D(vx, vy);
    }

    @Override
    public void draw(Graphics2D g) {
        int x = (int) position.x;
        int y = (int) position.y;
        g.setColor(new Color(185, 0, 61));
        g.drawOval(x - RADIUS, y - RADIUS, 2 * RADIUS, RADIUS);
        g.setColor(new Color(62, 168, 185));
        g.fillOval(x - RADIUS/2, y - RADIUS, RADIUS, RADIUS/2);
    }

    private void createBullet() {
        bullet = new SaucerBullet(new Vector2D(position), new Vector2D(velocity));
        bullet.position.addScaled(dir, (radius + bullet.radius) * 1.1+15);
        bullet.velocity.addScaled(new Vector2D(2 * VMAX * (Math.random() - 0.5), 2 * VMAX * (Math.random() - 0.5)), 1);
    }

    int counter=0;
    @Override
    public void update() {
        counter++;
        if(counter==100){
            createBullet();
            SoundManager.shot();
            counter=0;
        }

        super.update();
    }

}
