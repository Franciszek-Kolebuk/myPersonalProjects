package gamePackage;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {
    private Random r = new Random();

    public Enemy(int x, int y, ID id) {
        super(x, y, id);



        velX = r.nextInt(6);        //sets random velocity for enemies wno greater than 6
        velY = r.nextInt(6);
    }

    public Rectangle borders(){
        return new Rectangle(x, y, 30, 30);
    }


    public void time() {                //method to close the space on which can enemies move
        x += velX;
        y += velY;

        if(y <=-150 || y >= Covid_Frame_Game.HEIGHT+150){
            velY = velY * -1;
        }
        if(x <=-150 || x >= Covid_Frame_Game.WIDTH+150){
            velX = velX * -1;
        }

    }

    public void rendering(Graphics g) {             //method to show enemies

        if(id == ID.Enemy){
            g.setColor(Color.red);
            g.fillRect(x, y, 30,30);
        }


    }
}
