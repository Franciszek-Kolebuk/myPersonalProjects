package PassTheBall.Server;

import java.util.*;

public class Game {
    private final List<Integer> players = new ArrayList<>();

    public int joinPlayer() {
        int playerID = players.stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        players.add(playerID);
        return playerID;
    }

    public List<Integer> getPlayers() {
        return players;
    }

    public void deletePlayer(Integer playerID) {
        players.remove(playerID);
    }
}
