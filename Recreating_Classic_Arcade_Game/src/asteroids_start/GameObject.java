package asteroids_start;

import utilities.Vector2D;
import java.awt.*;

public abstract class GameObject {
    public Vector2D position;
    public Vector2D velocity;
    public double radius;
    public boolean dead;

    public GameObject(Vector2D position, Vector2D velocity, double radius){
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.dead = false;
    }

    public GameObject(double x, double y, double vx, double vy, double radius) {
        dead = false;
        this.position = new Vector2D(x, y);
        this.velocity = new Vector2D(vx, vy);
        this.radius = radius;
    }

    public void hit() {
        dead = true;
    }

    public boolean overlap(GameObject other) {
        return position.dist(other.position) <= radius + other.radius;
    }

    public void collisionHandling(GameObject other) {
        /*
        if (this.getClass() != other.getClass() && this.overlap(other) && other.getClass() != FlyingSaucer.class && this.getClass() != FlyingSaucer.class) {
            this.hit();
            other.hit();
        }
        else if(this.getClass()==FlyingSaucer.class && this.overlap(other) && (other.getClass()==Ship.class || other.getClass()==Bullet.class)){
            this.hit();
            other.hit();
        }
        else if(other.getClass()==Ship.class && this.overlap(other) && (this.getClass()==SaucerBullet.class )){
            this.hit();
            other.hit();
        }
               */
        if(this.getClass()==Ship.class && this.overlap(other) && (other.getClass()==Asteroid.class || other.getClass()==FlyingSaucer.class || other.getClass()==SaucerBullet.class )){
            this.hit();
            other.hit();
        }
        else if(this.getClass()==Bullet.class && this.overlap(other) && (other.getClass()==Asteroid.class || other.getClass()==FlyingSaucer.class)){
            this.hit();
            other.hit();
        }
    }

    public void update(){
        position.addScaled(velocity, Constants.DT);
        position.wrap(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
    }

    public abstract void draw(Graphics2D g);

}
