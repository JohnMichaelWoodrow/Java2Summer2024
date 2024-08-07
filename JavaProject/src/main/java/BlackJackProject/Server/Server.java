package BlackJackProject.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BlackJack Project
 * Server class to accept client connections and start a new thread for each connection.
 * @author john-michael woodrow
 */

/**
 * Constructs a new server for the Blackjack game
 */
public class Server {
    private static final int PORT = 12345;
    private static boolean running = true;

    /**
     * Main method to start the server and accept client connections.
     *
     * @param args Port arguments.
     */
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");
            do {
                Socket clientSocket = serverSocket.accept();
                new Thread(new DealerHandler(clientSocket)).start();
            } while (running);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
