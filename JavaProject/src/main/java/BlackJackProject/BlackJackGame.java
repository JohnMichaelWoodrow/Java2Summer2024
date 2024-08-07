package BlackJackProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * BlackJack Project
 * BlackJackGame class to manage the game logic, player and dealer interactions.
 * @author john-michael woodrow
 */

/**
 * Constructs a new BlackJackGame with initialized player, dealer, and card deck
 */
public class BlackJackGame {
    private Player player;
    private Dealer dealer;
    private CardDeck deck;
    private int currentBet;
    private boolean isDealerHandRevealed;

    /**
     * Constructs a BlackJackGame with initialized player, dealer, and card deck.
     */
    public BlackJackGame() {
        player = new Player(100);
        dealer = new Dealer();
        deck = new CardDeck();
        deck.shuffle();
        isDealerHandRevealed = false;
    }

    /**
     * Returns the player in the game.
     *
     * @return the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Places a bet and starts a new round.
     *
     * @param bet The amount to bet
     */
    public void placeBet(int bet) {
        currentBet = bet;
        player.placeBet(bet);
        isDealerHandRevealed = false;
        startRound();
    }

    /**
     * Starts a new round by dealing two cards to the player and the dealer.
     */
    private void startRound() {
        player.resetHand();
        dealer.resetHand();
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    /**
     * Adds a card to the player's hand.
     */
    public void playerHit() {
        player.addCard(deck.drawCard());
    }

    /**
     * Adds a card to the dealer's hand.
     */
    public void dealerHit() {
        dealer.addCard(deck.drawCard());
    }

    /**
     * Ends the player's turn and starts the dealer's turn.
     */
    public void playerStay() {
        isDealerHandRevealed = true;
        while (dealer.shouldHit()) {
            dealer.addCard(deck.drawCard());
        }
    }

    /**
     * Ends the round by resetting hands and shuffling the deck.
     */
    public void endRound() {
        player.resetHand();
        dealer.resetHand();
        deck.shuffle();
        isDealerHandRevealed = false;
    }

    /**
     * Returns the player's hand.
     *
     * @return the player's hand as a list of playing cards
     */
    public List<PlayingCard> getPlayerHand() {
        return player.getHand();
    }

    /**
     * Returns the dealer's hand.
     *
     * @return the dealer's hand as a list of playing cards
     */
    public List<PlayingCard> getDealerHand() {
        return dealer.getHand();
    }

    /**
     * Returns the dealer's initial hand with one card hidden.
     *
     * @return the dealer's initial hand as a list of playing cards
     */
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

    /**
     * Returns the result of the game based on the hand values.
     *
     * @return a string describing the game result
     */
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

    /**
     * Returns the value of the player's hand.
     *
     * @return the player's hand value
     */
    public int getPlayerHandValue() {
        return player.calculateHandValue();
    }

    /**
     * Returns the value of the dealer's hand.
     *
     * @return the dealer's hand value
     */
    public int getDealerHandValue() {
        return dealer.calculateHandValue();
    }

    /**
     * Returns a formatted string of the player's hand.
     *
     * @return a string representing the player's hand
     */
    public String getFormattedPlayerHand() {
        return "Player Hand: " + formatHand(player.getHand());
    }

    /**
     * Returns a formatted string of the dealer's hand.
     *
     * @return a string representing the dealer's hand
     */
    public String getFormattedDealerHand() {
        return "Dealer Hand: " + formatHand(dealer.getHand());
    }

    /**
     * Returns a formatted string of the player's money.
     *
     * @return a string representing the player's money
     */
    public String getFormattedPlayerMoney() {
        return "Player's money: $" + player.getMoney();
    }

    /**
     * Formats a hand of playing cards into a string.
     *
     * @param hand the list of playing cards to format
     *
     * @return a formatted string of the hand
     */
    private String formatHand(List<PlayingCard> hand) {
        return hand.stream().map(PlayingCard::toString).collect(Collectors.joining(", "));
    }

    /**
     * Returns a formatted string of the dealer's initial hand with one card hidden.
     *
     * @return a string representing the dealer's initial hand
     */
    public String getFormattedInitialDealerHand() {
        List<String> initialHand = new ArrayList<>();
        for (int i = 0; i < dealer.getHand().size(); i++) {
            if (i == 1 && !isDealerHandRevealed) {
                initialHand.add("HIDDEN");
            } else {
                initialHand.add(dealer.getHand().get(i).toString());
            }
        }
        return "Dealer Hand: " + String.join(", ", initialHand);
    }
}
