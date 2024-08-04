import java.io.*;
import java.net.*;
import com.google.gson.Gson;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            System.out.println("Blackjack Console Game\n");
            boolean playing = true;
            do {
                System.out.print("Enter command (BET, QUIT): ");
                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("QUIT")) {
                    out.println(gson.toJson(new Message("QUIT", null)));
                    break;
                }

                if (command.equalsIgnoreCase("BET")) {
                    System.out.print("Enter bet amount: ");
                    int bet = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    out.println(gson.toJson(new Message("BET", bet)));

                    // Handle initial responses
                    for (int i = 0; i < 4; i++) {
                        String response = in.readLine();
                        if (response != null) {
                            System.out.println(response);
                        }
                    }
                }

                boolean roundActive = true;
                do {
                    System.out.print("Enter command (HIT, STAY, QUIT): ");
                    command = scanner.nextLine();

                    if (command.equalsIgnoreCase("QUIT")) {
                        out.println(gson.toJson(new Message("QUIT", null)));
                        roundActive = false;
                        playing = false;
                    } else if (command.equalsIgnoreCase("HIT")) {
                        out.println(gson.toJson(new Message("HIT", null)));
                        for (int i = 0; i < 2; i++) {
                            String response = in.readLine();
                            if (response != null) {
                                System.out.println(response);
                            }
                        }
                        if (in.ready()) {
                            String response = in.readLine();
                            if (response.contains("GAME_RESULT")) {
                                System.out.println(response);
                                for (int i = 0; i < 2; i++) {
                                    response = in.readLine();
                                    if (response != null) {
                                        System.out.println(response);
                                    }
                                }
                                roundActive = false;
                            }
                        }
                    } else if (command.equalsIgnoreCase("STAY")) {
                        out.println(gson.toJson(new Message("STAY", null)));
                        for (int i = 0; i < 3; i++) {
                            String response = in.readLine();
                            if (response != null) {
                                System.out.println(response);
                            }
                        }
                        roundActive = false;
                    }
                } while (roundActive);
            } while (playing);

            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
















