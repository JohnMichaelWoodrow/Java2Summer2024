package BlackJackProject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * BlackJack Project
 * Hand class to manage a collection of playing cards.
 * @author john-michael woodrow
 */

/**
 * Constructs a new Hand with an empty list of cards
 */
public class Hand {
    private List<PlayingCard> cards;

    /**
     * Constructs a Hand with an empty list of cards.
     */
    public Hand() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the hand.
     * @param card The card to add.
     */
    public void addCard(PlayingCard card) {
        cards.add(card);
    }

    /**
     * Clears the hand.
     */
    public void resetHand() {
        cards.clear();
    }

    /**
     * Returns the list of cards in the hand.
     * @return A list of playing cards.
     */
    public List<PlayingCard> getCards() {
        return new ArrayList<>(cards);
    }

    /**
     * Calculates the value of the hand.
     * @return The hand value.
     */
    public int calculateHandValue() {
        int value = 0;
        int aceCount = 0;
        for (PlayingCard card : cards) {
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
     * Checks if the hand is bust.
     * @return true if the hand value is over 21, false otherwise.
     */
    public boolean isBust() {
        return calculateHandValue() > 21;
    }

    /**
     * Formats the hand of playing cards into a string.
     * @return A formatted string of the hand.
     */
    public String formatHand() {
        return cards.stream().map(PlayingCard::toString).collect(Collectors.joining(", "));
    }
}
