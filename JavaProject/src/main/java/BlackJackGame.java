import java.util.ArrayList;
import java.util.List;

public class BlackJackGame {
    private Player player;
    private Dealer dealer;
    private CardDeck deck;
    private int currentBet;
    private boolean isDealerHandRevealed;

    public BlackJackGame() {
        player = new Player(100);
        dealer = new Dealer();
        deck = new CardDeck();
        deck.shuffle(); // Shuffle the deck initially
        isDealerHandRevealed = false;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void placeBet(int bet) {
        currentBet = bet;
        player.placeBet(bet);
        isDealerHandRevealed = false; // Reset dealer hand reveal status
    }

    public void playerHit() {
        player.addCard(deck.drawCard());
    }

    public void dealerHit() {
        dealer.addCard(deck.drawCard());
    }

    public void playerStay() {
        isDealerHandRevealed = true; // Reveal dealer's hand
        while (dealer.shouldHit()) {
            dealer.addCard(deck.drawCard());
        }
    }

    public void endGame() {
        player.resetHand();
        dealer.resetHand();
        deck.shuffle();
        isDealerHandRevealed = false;
    }

    public List<PlayingCard> getPlayerHand() {
        return player.getHand();
    }

    public List<PlayingCard> getDealerHand() {
        return dealer.getHand();
    }

    public List<PlayingCard> getDealerInitialHand() {
        List<PlayingCard> initialHand = new ArrayList<>();
        for (int i = 0; i < dealer.getHand().size(); i++) {
            if (i == 1 && !isDealerHandRevealed) {
                initialHand.add(new PlayingCard(dealer.getHand().get(i).getSuit(), PlayingCard.Value.HIDDEN));
            } else {
                initialHand.add(dealer.getHand().get(i));
            }
        }
        return initialHand;
    }

    public String getGameResult() {
        int playerValue = player.calculateHandValue();
        int dealerValue = dealer.calculateHandValue();

        if (player.isBust()) {
            return "Player busts! Dealer wins.";
        } else if (dealer.isBust()) {
            player.receiveWinnings(currentBet * 2);
            return "Dealer busts! Player wins!";
        } else if (playerValue > dealerValue) {
            player.receiveWinnings(currentBet * 2);
            return "Player wins!";
        } else if (playerValue == dealerValue) {
            player.receiveWinnings(currentBet);
            return "Push! It's a tie.";
        } else {
            return "Dealer wins.";
        }
    }

    public static String formatHand(List<PlayingCard> hand) {
        StringBuilder formattedHand = new StringBuilder();
        for (PlayingCard card : hand) {
            formattedHand.append(card.toString()).append(", ");
        }
        if (formattedHand.length() > 0) {
            formattedHand.setLength(formattedHand.length() - 2);
        }
        return formattedHand.toString();
    }

    public int getPlayerHandValue() {
        return player.calculateHandValue();
    }

    public int getDealerHandValue() {
        return dealer.calculateHandValue();
    }
}
