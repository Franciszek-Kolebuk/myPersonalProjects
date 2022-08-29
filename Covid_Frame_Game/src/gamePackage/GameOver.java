package gamePackage;

        import java.awt.*;

public class GameOver {

    public void render(Graphics g){
        Font gameOverfont =  new Font("Arial", 4, 50);
        g.setFont(gameOverfont);

        g.setColor(Color.RED);
        g.fillRect((Covid_Frame_Game.WIDTH/6)*5, (Covid_Frame_Game.HEIGHT/8)*7-20, 150, 75);
        g.setColor(Color.BLACK);
        g.drawRect((Covid_Frame_Game.WIDTH/6)*5, (Covid_Frame_Game.HEIGHT/8)*7-20, 150, 75);

    }
}
