import java.util.ArrayList;
import java.util.List;

/**
 * Blackjack Project
 * Represents a player in the Blackjack game with a hand of cards and money for betting.
 * Manages actions such as placing bets, adding cards to the hand, and calculating hand value.
 *
 * @author john-michael woodrow
 */
public class Player {
    private List<PlayingCard> hand;
    private int money;

    /**
     * Constructs a new player with an initial amount of money.
     *
     * @param initialMoney The starting money for the player.
     */
    public Player(int initialMoney) {
        hand = new ArrayList<>();
        money = initialMoney;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The card to add.
     */
    public void addCard(PlayingCard card) {
        hand.add(card);
    }

    /**
     * Returns the player's current hand.
     *
     * @return The player's hand.
     */
    public List<PlayingCard> getHand() {
        return hand;
    }

    /**
     * Returns the player's current money.
     *
     * @return The player's money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Places a bet by subtracting the amount from the player's money.
     *
     * @param amount The amount to bet.
     */
    public void placeBet(int amount) {
        money -= amount;
    }

    /**
     * Receives winnings by adding the amount to the player's money.
     *
     * @param amount The amount won.
     */
    public void receiveWinnings(int amount) {
        money += amount;
    }

    /**
     * Resets the player's hand by clearing the list of cards.
     */
    public void resetHand() {
        hand.clear();
    }

    /**
     * Calculates the total value of the player's hand.
     *
     * @return The total hand value.
     */
    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;
        for (PlayingCard card : hand) {
            PlayingCard.Value cardValue = card.getValue();
            if (cardValue == PlayingCard.Value.TWO) {
                value += 2;}
            else if (cardValue == PlayingCard.Value.THREE){
                value += 3;}
            else if (cardValue == PlayingCard.Value.FOUR) {
                value += 4;}
            else if (cardValue == PlayingCard.Value.FIVE) {
                value += 5;}
            else if (cardValue == PlayingCard.Value.SIX) {
                value += 6;}
            else if (cardValue == PlayingCard.Value.SEVEN) {
                value += 7;}
            else if (cardValue == PlayingCard.Value.EIGHT) {
                value += 8;}
            else if (cardValue == PlayingCard.Value.NINE) {
                value += 9;}
            else if (cardValue == PlayingCard.Value.TEN || cardValue == PlayingCard.Value.JACK || cardValue == PlayingCard.Value.QUEEN || cardValue == PlayingCard.Value.KING) {
                value += 10;}
            else if (cardValue == PlayingCard.Value.ACE) {
                aceCount++;}
        }
        for (int i = 0; i < aceCount; i++) {
            if (value + 11 <= 21) {
                value += 11;
            } else {
                value += 1;
            }
        }
        return value;
    }

    /**
     * Checks if the player's hand value exceeds 21.
     *
     * @return True if the player is bust, false otherwise.
     */
    public boolean isBust() {
        return calculateHandValue() > 21;
    }
}
