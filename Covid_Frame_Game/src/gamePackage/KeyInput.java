package gamePackage;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {      //class to make controls for player

    private Handler handler;

    public KeyInput(Handler handler){
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e){                         //method to move player if particular key pressed
        int key = e.getKeyCode();

        for(int i=0; i < handler.myObjects.size(); i++){
            GameObject tempObject = handler.myObjects.get(i);

            if(tempObject.getId()==ID.Player){

                if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) tempObject.setVelY(8);
                if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) tempObject.setVelY(-8);
                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) tempObject.setVelX(-8);
                if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) tempObject.setVelX(8);
            }

        }

        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
    }

    public void keyReleased(KeyEvent e ){           //method to stopad player if particular key released
        int key = e.getKeyCode();

        for(int i=0; i < handler.myObjects.size(); i++){
            GameObject tempObject = handler.myObjects.get(i);

            if(tempObject.getId()==ID.Player){

                if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) tempObject.setVelX(0);
            }

        }

    }
}
