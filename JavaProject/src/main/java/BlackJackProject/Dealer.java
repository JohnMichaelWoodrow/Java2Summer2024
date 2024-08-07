package BlackJackProject;

import java.util.ArrayList;
import java.util.List;

/**
 * BlackJack Project
 * Dealer class to manage the dealer's hand and actions.
 * @author john-michael woodrow
 */

/**
 * Constructs a new Dealer with an empty hand
 */
public class Dealer {
    private Hand hand;

    /**
     * Constructs a Dealer with an empty hand.
     */
    public Dealer() {
        hand = new Hand();
    }

    /**
     * Adds a card to the dealer's hand.
     * @param card The card to add.
     */
    public void addCard(PlayingCard card) {
        hand.addCard(card);
    }

    /**
     * Returns the dealer's hand.
     * @return The dealer's hand.
     */
    public Hand getHand() {
        return hand;
    }

    /**
     * Resets the dealer's hand.
     */
    public void resetHand() {
        hand.resetHand();
    }

    /**
     * Calculates the value of the dealer's hand.
     * @return The hand value.
     */
    public int calculateHandValue() {
        return hand.calculateHandValue();
    }

    /**
     * Checks if the dealer is bust.
     * @return true if the hand value is over 21, false otherwise.
     */
    public boolean isBust() {
        return hand.isBust();
    }

    /**
     * Checks if the dealer should hit.
     * @return true if the hand value is less than 17, false otherwise.
     */
    public boolean shouldHit() {
        return hand.calculateHandValue() < 17;
    }

    /**
     * Returns a formatted string of the dealer's hand.
     * @return a string representing the dealer's hand.
     */
    public String getFormattedHand() {
        return "Dealer Hand: " + hand.formatHand();
    }

    /**
     * Returns a formatted string of the dealer's initial hand with one card hidden.
     * @param isHandRevealed Indicates if the hand should be fully revealed.
     * @return A formatted string of the dealer's initial hand.
     */
    public String getFormattedInitialHand(boolean isHandRevealed) {
        List<String> initialHand = new ArrayList<>();
        for (int i = 0; i < hand.getCards().size(); i++) {
            if (i == 1 && !isHandRevealed) {
                initialHand.add("HIDDEN");
            } else {
                initialHand.add(hand.getCards().get(i).toString());
            }
        }
        return "Dealer Hand: " + String.join(", ", initialHand);
    }
}
