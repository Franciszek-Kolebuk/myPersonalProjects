package asteroids_start;

public class Action {
    public int flyForward;
    public int turn;
    public boolean shoot;
    public boolean warp;


    public Action(int flyForward, int turn, boolean shoot) {
        this.flyForward = flyForward;
        this.turn = turn;
        this.shoot = shoot;
        this.warp = warp;
    }

    public Action(){}
}
