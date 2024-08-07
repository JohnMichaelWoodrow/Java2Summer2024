package BlackJackProject;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackJack Project
 * Player class to manage the player's hand and money.
 * Author: john-michael woodrow
 */

/**
 * Constructs a new Player with an initial amount of money
 */
public class Player {
    private List<PlayingCard> hand;
    private int money;

    /**
     * Constructs a Player with the specified initial money.
     *
     * @param initialMoney The initial amount of money.
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
     * Returns the player's hand.
     *
     * @return The player's hand as a list of playing cards.
     */
    public List<PlayingCard> getHand() {
        return new ArrayList<>(hand);
    }

    /**
     * Returns the player's money.
     *
     * @return The amount of money the player has.
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
     * @param amount The amount to add.
     */
    public void receiveWinnings(int amount) {
        money += amount;
    }

    /**
     * Resets the player's hand.
     */
    public void resetHand() {
        hand.clear();
    }

    /**
     * Calculates the value of the player's hand.
     *
     * @return The hand value.
     */
    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;
        for (PlayingCard card : hand) {
            PlayingCard.Value cardValue = card.getValue();
            if (cardValue == PlayingCard.Value.TWO) value += 2;
            else if (cardValue == PlayingCard.Value.THREE) value += 3;
            else if (cardValue == PlayingCard.Value.FOUR) value += 4;
            else if (cardValue == PlayingCard.Value.FIVE) value += 5;
            else if (cardValue == PlayingCard.Value.SIX) value += 6;
            else if (cardValue == PlayingCard.Value.SEVEN) value += 7;
            else if (cardValue == PlayingCard.Value.EIGHT) value += 8;
            else if (cardValue == PlayingCard.Value.NINE) value += 9;
            else if (cardValue == PlayingCard.Value.TEN || cardValue == PlayingCard.Value.JACK || cardValue == PlayingCard.Value.QUEEN || cardValue == PlayingCard.Value.KING) value += 10;
            else if (cardValue == PlayingCard.Value.ACE) aceCount++;
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
     * Checks if the player is bust.
     *
     * @return true if the hand value is over 21, false otherwise.
     */
    public boolean isBust() {
        return calculateHandValue() > 21;
    }
}
