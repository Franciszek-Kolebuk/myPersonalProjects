package asteroids_start;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter implements Controller {
    Action action;

    private long timeLast = System.currentTimeMillis();

    public Keys(){
        action = new Action();
    }

    @Override
    public Action action() {
        return action;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                action.flyForward = 2;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                action.flyForward = -3;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                action.turn = -1;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                action.turn = 1;
                break;
            case KeyEvent.VK_SPACE:
                long timeNow = System.currentTimeMillis();
                if (timeNow - timeLast > 200) {
                    action.shoot = true;
                    timeLast = timeNow;
                }
                else {
                    if(timeNow - timeLast > 500){
                        action.shoot = false;
                    }
                }
                break;
            case KeyEvent.VK_SHIFT:
                action.warp = true;
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                action.flyForward = 0;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                action.turn = 0;
                break;
            case KeyEvent.VK_SPACE:
                action.shoot = false;
                break;
            case KeyEvent.VK_SHIFT:
                action.warp = false;
                break;
        }
    }
}

