package asteroids_start;

import utilities.Vector2D;

import java.awt.*;

import static asteroids_start.Constants.DT;

public class SaucerBullet extends GameObject {
    public static final double INITIAL_SPEED = 200;
    private double lifetime;
    public static final int RADIUS = 2;
    public static final int BULLET_LIFE = 4;

    public SaucerBullet(Vector2D pos, Vector2D vel) {
        super(pos, vel, 0);
        this.lifetime = BULLET_LIFE;
    }

    @Override
    public void update() {
        super.update();
        lifetime -= DT;
        if (lifetime <= 0) dead = true;
    }

    @Override
    public void draw(Graphics2D g)
    {g.setColor(Color.green);
        g.fillOval((int) position.x-RADIUS, (int) position.y-RADIUS, 2*RADIUS, 2*RADIUS);


    }
}
