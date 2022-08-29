package gamePackage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {

    private Covid_Frame_Game game;
    private Handler handler;

    public Menu(Covid_Frame_Game game, Handler handler){
        this.game = game;
        this.handler = handler;
    }

    public void MousePressed(MouseEvent e){
        int mouseX = e.getX();                  //ints to get where exactly mouse is currently
        int mouseY = e.getY();


        if(mouseHovering(mouseX, mouseY, Covid_Frame_Game.WIDTH/6-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300)){
            game.gameState = Covid_Frame_Game.GameState.Play;
            handler.addObject(new Player(Covid_Frame_Game.WIDTH / 2 - 50, Covid_Frame_Game.HEIGHT / 2 - 50, ID.Player, handler));
        }

    }


    private boolean mouseHovering(int mouseX, int mouseY, int x, int y, int width, int height){         //method checking if mouse cursor is over particular space
        if(mouseX > x && mouseX < x + width){
            if(mouseY > y && mouseY < y + height){
                return true;
            }else return false;
        }else return false;
    }


    public void rendering(Graphics g){                                             //creates and shes buttons for menu
        Font font =  new Font("Arial", 1, 50);
        g.setFont(font);


        g.setColor(Color.BLUE);
        g.fillRect(Covid_Frame_Game.WIDTH/6-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect(Covid_Frame_Game.WIDTH/6-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);

        g.setColor(Color.YELLOW);
        g.fillRect(Covid_Frame_Game.WIDTH/2-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect(Covid_Frame_Game.WIDTH/2-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);

        g.setColor(Color.RED);
        g.fillRect((Covid_Frame_Game.WIDTH/6)*5-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);
        g.setColor(Color.BLACK);
        g.drawRect((Covid_Frame_Game.WIDTH/6)*5-155, Covid_Frame_Game.HEIGHT/2-25, 300, 300);

    }
}
