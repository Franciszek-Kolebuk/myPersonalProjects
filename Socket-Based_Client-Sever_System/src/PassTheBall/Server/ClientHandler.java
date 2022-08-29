package PassTheBall.Server;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private Game game;
    private Ball ball;

    public ClientHandler(Socket socket, Game game, Ball ball) {
        this.socket = socket;
        this.game = game;
        this.ball = ball;
    }

    @Override
    public void run() {
        int playerID = game.joinPlayer();
        try (
                Scanner scanner = new Scanner(socket.getInputStream());
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {
            try {
                System.out.println("New connection; playerID: " + playerID);
                writer.println("Your ID is " + playerID);

                while (true) {
                    String line = scanner.nextLine();
                    if (ball.getCurrentPlayer() == playerID && line.contains("WHO HAS THE BALL")) {
                        writer.println("YOU HAVE THE BALL AND YOU CAN PASS TO" + game.getPlayers().stream().map(player -> " " + player).collect(Collectors.joining()));
                    } else if (line.equals("WHO HAS THE BALL")) {
                        writer.println("PLAYER " + ball.getCurrentPlayer() + " HAS THE BALL");
                    } else if (ball.getCurrentPlayer() == playerID && line.contains("I pass to player number ")) {
                        String[] substrings = line.split(" ");
                        int playerWithBall = Integer.parseInt(substrings[5]);
                        if (game.getPlayers().stream().anyMatch(value -> value == playerWithBall)) {
                            ball.passToPlayer(playerWithBall);
                            System.out.println("Player " + playerID + " pass to player " + playerWithBall);
                        } else {
                            System.out.println("YOU CANNOT PASS TO PLAYER THAT NOT EXISTS!!!");
                        }
                    }
                }

            } catch (Exception e) {
                writer.println("ERROR " + e.getMessage());
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Player " + playerID + " disconnected.");
            game.deletePlayer(playerID);
            ball.passToPlayer(game.getPlayers().stream().findAny().orElse(1));
            System.out.println("Player " + ball.getCurrentPlayer() + " has the ball");
        }
    }
}

