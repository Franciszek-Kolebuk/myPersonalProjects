package PassTheBall.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
    private final static int port = 8888;
    private static final Game game = new Game();
    private static final Ball ball = new Ball();

    public static void main(String[] args) {
        RunSever();
    }

    private static void RunSever() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is running. Waiting for incoming connections...");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ClientHandler(socket, game, ball)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

