import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import com.google.gson.Gson;

public class Server {
    private static final int PORT = 12345;
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Blackjack Server is running...");
            boolean running = true;
            do {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            } while (running);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;
        private BlackJackGame game;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
            this.game = new BlackJackGame();
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                boolean activeSession = true;

                do {
                    String request = in.readLine();
                    if (request == null) break;

                    Message message = gson.fromJson(request, Message.class);
                    handleClientMessage(message);

                    if ("QUIT".equals(message.getType())) {
                        activeSession = false;
                    }
                } while (activeSession);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleClientMessage(Message message) {
            String type = message.getType();
            String response;

            if (type.equalsIgnoreCase("BET")) {
                int betAmount = ((Double) message.getData()).intValue();
                game.placeBet(betAmount);
                game.playerHit();
                game.playerHit();
                game.dealerHit();
                game.dealerHit();
                out.println(gson.toJson(new Message("BET_PLACED", null)));
                out.println(gson.toJson(new Message("DEALER_HAND", game.getDealerInitialHand())));
                out.println(gson.toJson(new Message("PLAYER_HAND", game.getPlayerHand())));
                out.println("Player's hand value: " + game.getPlayerHandValue());
            } else if (type.equalsIgnoreCase("HIT")) {
                game.playerHit();
                out.println(gson.toJson(new Message("PLAYER_HAND", game.getPlayerHand())));
                out.println("Player's hand value: " + game.getPlayerHandValue());
                if (game.getPlayer().isBust()) {
                    response = gson.toJson(new Message("GAME_RESULT", game.getGameResult()));
                    out.println(response);
                    out.println(gson.toJson(new Message("DEALER_HAND", game.getDealerHand()))); // Reveal dealer's hand
                    out.println("Dealer's hand value: " + game.getDealerHandValue());
                    game.endGame();
                }
            } else if (type.equalsIgnoreCase("STAY")) {
                game.playerStay();
                out.println(gson.toJson(new Message("DEALER_HAND", game.getDealerHand())));
                out.println("Dealer's hand value: " + game.getDealerHandValue());
                response = gson.toJson(new Message("GAME_RESULT", game.getGameResult()));
                out.println(response);
                game.endGame();
            } else if (type.equalsIgnoreCase("QUIT")) {
                out.println(gson.toJson(new Message("QUIT", null)));
            } else {
                out.println(gson.toJson(new Message("UNKNOWN_COMMAND", null)));
            }
        }
    }
}











