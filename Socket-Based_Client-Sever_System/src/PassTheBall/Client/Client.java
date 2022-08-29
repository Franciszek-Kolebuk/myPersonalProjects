package PassTheBall.Client;

import PassTheBall.Server.Player;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Client {
    private Player player;
    private List<Integer> players = new ArrayList<>();

    public Client(String line) {

        if (line.trim().contains("Your ID is ")) {
            String[] substrings = line.split(" ");
            this.player = new Player(Integer.parseInt(substrings[3]));
        }
    }


    public Player getPlayer() {
        return player;
    }

    public List<Integer> getPlayers() {
        return players;
    }

    public int getCurrentIDBallHolder(PrintWriter writer, String line) {
        int holder = 0;
        if (line.contains("PLAYER ") && line.contains(" HAS THE BALL")) {
            String[] substrings = line.split(" ");
            holder = Integer.parseInt(substrings[1]);
        } else if (line.contains("YOU HAVE THE BALL AND YOU CAN PASS TO")) {
            players.clear();
            String[] substrings = line.split(" ");
            for (int i = 9; i < substrings.length; i++) {
                players.add(Integer.parseInt(substrings[i]));
            }
            holder = player.getPlayerID();
        }
        return holder;
    }

    public void passToPlayer(int playerID, PrintWriter writer) {
        writer.println("I pass to player number " + playerID);
    }
}
