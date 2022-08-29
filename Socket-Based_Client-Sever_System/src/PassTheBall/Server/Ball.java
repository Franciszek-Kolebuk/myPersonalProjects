package PassTheBall.Server;

public class Ball {
    private int currentPlayer;


    public Ball() {
        currentPlayer = 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void passToPlayer(int playerID) {
        currentPlayer = playerID;
    }
}
