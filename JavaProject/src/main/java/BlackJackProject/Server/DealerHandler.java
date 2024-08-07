package BlackJackProject.Server;

import BlackJackProject.BlackJackGame;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * BlackJack Project
 * DealerHandler class to handle the communication between server and client for a game session.
 * @author john-michael woodrow
 */

/**
 * Constructs a DealerHandler for handling client communication.
 */
public class DealerHandler implements Runnable {
    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;
    private BlackJackGame game;
    private JSONParser parser;

    /**
     * Constructs a DealerHandler for handling client communication.
     * @param socket The client socket.
     */
    public DealerHandler(Socket socket) {
        this.clientSocket = socket;
        this.game = new BlackJackGame();
        this.parser = new JSONParser();
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received from client: " + inputLine);
                JSONObject message = (JSONObject) parser.parse(inputLine);
                handleClientMessage(message);
            }
        } catch (ParseException | IOException e) {
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

    /**
     * Handles client messages and performs actions based on the message type.
     * @param message JSONObject containing the message from the client.
     */
    private void handleClientMessage(JSONObject message) {
        String type = (String) message.get("type");
        if (type.equalsIgnoreCase("BET")) {
            int betAmount = ((Long) message.get("data")).intValue();
            game.placeBet(betAmount);
            System.out.println("New round started");
            sendGameState("BET PLACED");
        } else if (type.equalsIgnoreCase("HIT")) {
            game.playerHit();
            if (game.getPlayer().isBust()) {
                sendGameResult();
            } else {
                sendGameState(null);
            }
        } else if (type.equalsIgnoreCase("STAY")) {
            game.playerStay();
            sendGameResult();
        } else if (type.equalsIgnoreCase("QUIT")) {
            out.println("QUIT");
        } else {
            out.println("UNKNOWN COMMAND");
        }
    }

    /**
     * Sends the current game state to the client.
     * @param gameResult The result of the game if available.
     */
    private void sendGameState(String gameResult) {
        if (gameResult != null) {
            out.println(gameResult);
        }
        boolean isDealerHandRevealed = false;
        out.println(game.getDealer().getFormattedInitialHand(isDealerHandRevealed));
        out.println(game.getPlayer().getFormattedHand());
        out.println("Player's hand value: " + game.getPlayer().calculateHandValue());
        out.println(game.getPlayer().getFormattedMoney());
        out.println("END OF MESSAGE");
        System.out.println("Sent game state to client");
    }

    /**
     * Sends the game result to the client.
     */
    private void sendGameResult() {
        out.println("\n\n-----GAME RESULT-----\n\n");
        out.println(game.getGameResult());
        out.println(game.getPlayer().getFormattedHand());
        out.println("Player's hand value: " + game.getPlayer().calculateHandValue());
        out.println(game.getDealer().getFormattedHand());
        out.println("Dealer's hand value: " + game.getDealer().calculateHandValue());
        out.println(game.getPlayer().getFormattedMoney());
        out.println("END OF MESSAGE");
        game.endRound();
        System.out.println("Sent game result to client");
        System.out.println("Round End");
    }
}
