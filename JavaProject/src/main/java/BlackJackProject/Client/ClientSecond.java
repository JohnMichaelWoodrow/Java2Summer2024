package BlackJackProject.Client;

import org.json.simple.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * BlackJack Project
 * Represents a second client that connects to the Blackjack server and plays the game.
 * Handles user input and server communication.
 * @author john-michael woodrow
 */

/**
 * Constructs a new second client for the Blackjack game
 */
public class ClientSecond {
    private static final String SERVERADDRESS = "localhost";
    private static final int SERVERPORT = 12345;

    /**
     * Main method to start the second client and connect to the server.
     *
     * @param args Server arguments.
     */
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVERADDRESS, SERVERPORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            boolean playing = true;
            boolean roundActive = false;

            System.out.println("-----Blackjack Console Game (Second Client)-----\n");
            do {
                if (!roundActive) {
                    System.out.print("\nEnter command (BET, QUIT): ");
                    String command = scanner.nextLine();

                    if (command.equalsIgnoreCase("QUIT")) {
                        JSONObject quitMessage = new JSONObject();
                        quitMessage.put("type", "QUIT");
                        quitMessage.put("data", null);
                        out.println(quitMessage.toJSONString());
                        break;
                    }

                    if (command.equalsIgnoreCase("BET")) {
                        System.out.print("Enter bet amount: ");
                        int bet = scanner.nextInt();
                        scanner.nextLine();
                        JSONObject betMessage = new JSONObject();
                        betMessage.put("type", "BET");
                        betMessage.put("data", bet);
                        out.println(betMessage.toJSONString());
                        readServerResponses(in);
                        roundActive = true;
                    }
                }

                do {
                    System.out.print("\nEnter command (HIT, STAY, QUIT): ");
                    String command = scanner.nextLine();
                    if (command.equalsIgnoreCase("QUIT")) {
                        JSONObject quitMessage = new JSONObject();
                        quitMessage.put("type", "QUIT");
                        quitMessage.put("data", null);
                        out.println(quitMessage.toJSONString());
                        roundActive = false;
                        playing = false;
                    } else if (command.equalsIgnoreCase("HIT")) {
                        JSONObject hitMessage = new JSONObject();
                        hitMessage.put("type", "HIT");
                        hitMessage.put("data", null);
                        out.println(hitMessage.toJSONString());
                        if (readServerResponses(in)) {
                            roundActive = false;
                        }
                    } else if (command.equalsIgnoreCase("STAY")) {
                        JSONObject stayMessage = new JSONObject();
                        stayMessage.put("type", "STAY");
                        stayMessage.put("data", null);
                        out.println(stayMessage.toJSONString());
                        readServerResponses(in);
                        roundActive = false;
                    }
                } while (roundActive);
            } while (playing);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads responses from the server and prints them.
     *
     * @param in BufferedReader to read server responses.
     * @return true if the round has ended, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    private static boolean readServerResponses(BufferedReader in) throws IOException {
        String response;
        boolean roundEnded = false;
        do {
            response = in.readLine();
            if (response != null && !response.equals("END OF MESSAGE")) {
                System.out.println(response);
                if (response.contains("busts") || response.contains("wins") || response.contains("tie")) {
                    roundEnded = true;
                }
            }
        } while (response != null && !response.equals("END OF MESSAGE"));
        return roundEnded;
    }
}
