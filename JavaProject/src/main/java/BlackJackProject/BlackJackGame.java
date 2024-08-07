package BlackJackProject;

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
     * @return the player object
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the dealer in the game.
     * @return the dealer object
     */
    public Dealer getDealer() {
        return dealer;
    }

    /**
     * Places a bet and starts a new round.
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
     * Returns the game result as a string.
     *
     * @return a string representing the game result.
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
}
