import java.util.ArrayList;
import java.util.List;

/**
 * Blackjack Project
 * Represents the dealer in the Blackjack game with a hand of cards.
 * Manages actions such as adding cards to the hand and calculating hand value.
 *
 * @author john-michael woodrow
 */
public class Dealer {
    private List<PlayingCard> hand;

    /**
     * Constructs a new dealer with an empty hand.
     */
    public Dealer() {
        hand = new ArrayList<>();
    }

    /**
     * Adds a card to the dealer's hand.
     *
     * @param card The card to add.
     */
    public void addCard(PlayingCard card) {
        hand.add(card);
    }

    /**
     * Returns the dealer's current hand.
     *
     * @return The dealer's hand.
     */
    public List<PlayingCard> getHand() {
        return hand;
    }

    /**
     * Resets the dealer's hand by clearing the list of cards.
     */
    public void resetHand() {
        hand.clear();
    }

    /**
     * Calculates the total value of the dealer's hand.
     *
     * @return The total hand value.
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
     * Checks if the dealer's hand value exceeds 21.
     *
     * @return True if the dealer is bust, false otherwise.
     */
    public boolean isBust() {
        return calculateHandValue() > 21;
    }

    /**
     * Determines if the dealer should continue drawing cards.
     *
     * @return True if the dealer should hit, false otherwise.
     */
    public boolean shouldHit() {
        return calculateHandValue() < 17;
    }
}
