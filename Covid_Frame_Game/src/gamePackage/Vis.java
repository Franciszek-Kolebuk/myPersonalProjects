package gamePackage;

import java.awt.*;

public class Vis {          //class made to visualize game score, number of the enemies and comments

    private int score = 0;
    private int enemiesNum = 0;

    public void time(){         //increases score every milisecond
        score = score + 1;
    }

    public void rendering(Graphics g){         //renders score, number of enemies, and main task of the game
        g.setColor(Color.BLACK);
        Font scoreFont = new Font("Arial", 4, 25);
        g.setFont(scoreFont);
        g.setFont(Font.getFont("TimesNewRoman"));
        g.drawString("Score: "+ score, 20, 30);
        g.drawString("Number of enemies: "+ enemiesNum, 20, 60);
        g.setColor(Color.BLUE);
        g.drawString("Red rectangles are people. They might have Covid19. Avoid them!", 200, 30);
    }

    public int getScore(){         //methods to get access and work with score, and number of enemies
        return score;
    }

    public int getEnemiesNum(){
        return enemiesNum;
    }

    public void setEnemiesNum(int enemiesNum){
        this.enemiesNum = enemiesNum;
    }


}

