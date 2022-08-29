package gamePackage;

import java.awt.*;
public class Player extends GameObject {

    Handler handler;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle borders(){
        return new Rectangle(x, y, 50, 50);
    }       //sets borders to player


    private void playerCollision(){                                 //checks if player touched an enemy and closes the game if yes
        for(int i = 0; i < handler.myObjects.size(); i++){
            GameObject tempObject = handler.myObjects.get(i);

            if(tempObject.getId() == ID.Enemy){
                if(borders().intersects(tempObject.borders())){
                    System.exit(1);

                }
            }
        }
    }

    public void time() {        //method to constantly move player, and check collision
        x = x + velX;
        y = y + velY;
        playerCollision();
    }


    public void rendering(Graphics g) {         //makes and shows player
        if(id == ID.Player){
            g.setColor(Color.blue);
            g.fillRect(x, y, 50,50);
        }

    }

}
