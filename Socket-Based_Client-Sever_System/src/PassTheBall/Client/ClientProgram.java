package PassTheBall.Client;

import PassTheBall.Server.Player;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientProgram {
    final static int port = 8888;
    private static Scanner reader;
    private static PrintWriter writer = null;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", port);
        reader = new Scanner(socket.getInputStream());
        writer = new PrintWriter(socket.getOutputStream(), true);
        int lastIDPlayerBallHolder = 0;

        try {
            Client client = new Client(reader.nextLine());
            Player player = client.getPlayer();
            System.out.println("Logged in successfully. Your are player " + player.getPlayerID());

            while (true) {
                writer.println("WHO HAS THE BALL");
                String line = reader.nextLine();
                if (client.getCurrentIDBallHolder(writer, line) == player.getPlayerID()) {
                    System.out.println("YOU HAVE THE BALL");
                    System.out.println("PLAYERS CURRENTLY PLAYING: " + client.getPlayers().stream().map(value -> value + ",").collect(Collectors.joining()));
                    System.out.println("WHOM WOULD YOU LIKE TO PASS THE BALL TO? ENTER ID");

                    try {
                        Scanner scan = new Scanner(System.in);
                        int choosePlayer = Integer.parseInt(scan.nextLine());
                        client.passToPlayer(choosePlayer, writer);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Not a valid ID");
                    }
                } else {
                    int currentIDBallHolder = client.getCurrentIDBallHolder(writer, line);
                    if (!(currentIDBallHolder == lastIDPlayerBallHolder)) {
                        System.out.println("PLAYER " + currentIDBallHolder + " HAS THE BALL. WAIT UNTIL YOU WILL GET IT");
                        lastIDPlayerBallHolder = currentIDBallHolder;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
