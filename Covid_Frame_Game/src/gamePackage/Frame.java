package gamePackage;

import javax.swing.*;

public class Frame {

    public Frame(int width, int height, String title, Covid_Frame_Game game){        //simple frame which will be base of my game

        JFrame frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        game.start();

    }
}

