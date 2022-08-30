package asteroids_start;

import utilities.SoundManager;
import utilities.Vector2D;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static asteroids_start.Constants.*;

public class Ship extends GameObject {
    public static final double STEER_RATE = 2 * Math.PI;

    public static final double MAX_ACC = 100;

    public static final double DRAG = 0.01;

    public static final double DRAWING_SCALE = 1.5;

    public static final Color COLOR = Color.lightGray;

    public Vector2D dir;

    public boolean thrusting = false;

    public Bullet bullet;

    public int XP[] = { -9, -5, -5, -1, -1, 0, 1, 1, 5, 5, 9, 9, 3, 3, 0, -3, -3, -9}, YP[] = { 8, 5, 10, 10, 6, 6, 6, 10, 10, 5, 8, 4, -1, -5, -8, -5, -1, 4};
    public int XP2[] = {-2, 2, 2, 0, -2}, YP2[] = {0, 0, -5, -6, -5};
    public int XPTHRUST[] = { -5, -1, -2, -4 }, YPTHRUST[] = { 13, 13, 10, 10 };
    public int XPTHRUST2[] = { 1, 5, 4, 2 }, YPTHRUST2[] = { 13, 13, 10, 10 };

    private Controller ctrl;

    public Ship(Controller ctrl) {
        super(new Vector2D(FRAME_WIDTH / 2, FRAME_HEIGHT / 2), new Vector2D(0, -1), 0);
        this.ctrl = ctrl;
        dir = new Vector2D(0,-1);
        reset();
    }

    public void reset() {
        position.set(Constants.CENTER);
        velocity.set(new Vector2D(0,0));
        dir.set(Constants.UPWARDS);
    }

    public void warp() {
        position.set(new Vector2D(Constants.FRAME_WIDTH * Math.random(), Constants.FRAME_HEIGHT * Math.random()));
    }


    private void mkBullet() {
        bullet = new Bullet(new Vector2D(position), new Vector2D(velocity));
        bullet.position.addScaled(dir, (radius + bullet.radius) * 1.1+15);
        bullet.velocity.addScaled(dir, Bullet.INITIAL_SPEED);
    }

    @Override
    public void update() {
        Action action = ctrl.action();
        if (action.shoot) {
            mkBullet();
            action.shoot = false;
            SoundManager.shot();
        }
        if (action.warp) {
            warp();
            SoundManager.warp();
            action.warp = false;
        }
        thrusting = action.flyForward != 0;
        if(action.flyForward>0){
            SoundManager.engineStart();
        } else {SoundManager.engineStop();}

        dir.rotate(action.turn * STEER_RATE * DT);
        velocity = new Vector2D(dir).mult(velocity.mag());
        velocity.addScaled(dir, MAX_ACC * DT * action.flyForward);
        velocity.addScaled(velocity, -DRAG);

        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform at = g.getTransform();
        g.translate(position.x, position.y);
        double rot = dir.angle() + Math.PI / 2;
        g.rotate(rot);
        g.scale(DRAWING_SCALE, DRAWING_SCALE);
        g.setColor(COLOR);
        g.drawPolygon(XP, YP, XP.length);
        g.setColor(Color.BLUE);
        g.fillPolygon(XP2, YP2, XP2.length);
        if (thrusting) {
            g.setColor(Color.orange);
            g.fillPolygon(XPTHRUST, YPTHRUST, XPTHRUST.length);
            g.fillPolygon(XPTHRUST2, YPTHRUST2, XPTHRUST2.length);
        }
        g.setTransform(at);
    }

    @Override
    public void hit() {
        Game.decreaseLives();
        SoundManager.death();
        this.reset();
    }
}
