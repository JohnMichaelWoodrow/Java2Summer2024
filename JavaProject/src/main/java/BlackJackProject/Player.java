package BlackJackProject;

/**
 * BlackJack Project
 * Player class to manage the player's hand and money.
 * @author john-michael woodrow
 */

/**
 * Constructs a new Player with an initial amount of money
 */
public class Player {
    private Hand hand;
    private int money;

    /**
     * Constructs a Player with the specified initial money and empty hand.
     * @param initialMoney The initial amount of money.
     */
    public Player(int initialMoney) {
        hand = new Hand();
        money = initialMoney;
    }

    /**
     * Adds a card to the player's hand.
     * @param card The card to add.
     */
    public void addCard(PlayingCard card) {
        hand.addCard(card);
    }

    /**
     * Returns the player's hand.
     * @return The player's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Returns the player's money.
     * @return The amount of money the player has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Places a bet by subtracting the amount from the player's money.
     * @param amount The amount to bet.
     */
    public void placeBet(int amount) {
        money -= amount;
    }

    /**
     * Receives winnings by adding the amount to the player's money.
     * @param amount The amount to add.
     */
    public void receiveWinnings(int amount) {
        money += amount;
    }

    /**
     * Resets the player's hand.
     */
    public void resetHand() {
        hand.resetHand();
    }

    /**
     * Calculates the value of the player's hand.
     * @return The hand value.
     */
    public int calculateHandValue() {
        return hand.calculateHandValue();
    }

    /**
     * Checks if the player is bust.
     * @return true if the hand value is over 21, false otherwise.
     */
    public boolean isBust() {
        return hand.isBust();
    }

    /**
     * Returns a formatted string of the player's hand.
     * @return a string representing the player's hand.
     */
    public String getFormattedHand() {
        return "Player Hand: " + hand.formatHand();
    }

    /**
     * Returns a formatted string of the player's money.
     * @return a string representing the player's money.
     */
    public String getFormattedMoney() {
        return "Player's money: $" + money;
    }
}
