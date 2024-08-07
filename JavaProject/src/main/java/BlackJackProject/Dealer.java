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
    private List<PlayingCard> hand;

    /**
     * Constructs a Dealer with an empty hand.
     */
    public Dealer() {
        hand = new ArrayList<>();
    }

    /**
     * Adds a card to the dealer's hand.
     * @param card the card to add
     */
    public void addCard(PlayingCard card) {
        hand.add(card);
    }

    /**
     * Returns the dealer's hand.
     *
     * @return the dealer's hand as a list of playing cards
     */
    public List<PlayingCard> getHand() {
        return hand;
    }

    /**
     * Resets the dealer's hand.
     */
    public void resetHand() {
        hand.clear();
    }

    /**
     * Calculates the value of the dealer's hand.
     *
     * @return the hand value
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
     * Checks if the dealer is bust.
     *
     * @return true if the hand value is over 21, false otherwise
     */
    public boolean isBust() {
        return calculateHandValue() > 21;
    }

    /**
     * Checks if the dealer should hit.
     *
     * @return true if the hand value is less than 17, false otherwise
     */
    public boolean shouldHit() {
        return calculateHandValue() < 17;
    }
}
