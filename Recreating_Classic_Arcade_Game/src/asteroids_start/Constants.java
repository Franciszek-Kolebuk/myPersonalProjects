package asteroids_start;

import utilities.Vector2D;

import java.awt.*;

public class Constants {
    public static final int FPS = 60; // in frames per second
    public static final int FRAME_HEIGHT = 700; // in pixels
    public static final int FRAME_WIDTH = 900;
    public static final Dimension FRAME_SIZE = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
    public static final int SCORE_PANEL_HEIGHT = 30;

    public static final Vector2D CENTER = new Vector2D(Constants.FRAME_WIDTH / 2, Constants.FRAME_HEIGHT / 2);
    public static final Vector2D UPWARDS = new Vector2D(0, -1);

    public static final int DELAY = 20; // in milliseconds
    public static final double DT = DELAY/1000.0;  // delay time in seconds
}
