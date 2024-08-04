/**
 * Blackjack Project
 * Represents a deck of cards with methods to initalize, shuffle, draw card, draw hand and print the deck
 * @author john-michael woodrow
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Constructs a new card deck with implementation of cards list and discarded pile list
 */
public class CardDeck {
    private List<PlayingCard> cards;
    private List<PlayingCard> discardedPile;

    /**
     * Constructs a new CardDeck, initializes the deck with a standard set of cards
     */
    public CardDeck() {
        cards = new ArrayList<>();
        discardedPile = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with a standard set of playing cards
     */
    private void initializeDeck() {
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            for (PlayingCard.Value value : PlayingCard.Value.values()) {
                cards.add(new PlayingCard(suit, value));
            }
        }
    }

    /**
     * Shuffles the deck of cards, reintegrates any discarded cards back into the deck before shuffling
     */
    public void shuffle() {
        // Add all discarded cards back to the deck
        cards.addAll(discardedPile);
        discardedPile.clear();
        Collections.shuffle(cards);
    }

    /**
     * Draws a single card from the deck, if the deck is empty, it will shuffle in the discarded cards before drawing
     * @return The drawn PlayingCard, or null if no cards are left after shuffling
     */
    public PlayingCard drawCard() {
        if (cards.isEmpty()) {
            // Shuffle in discarded cards if the deck is empty
            shuffle();
        } else if (!cards.isEmpty()) {
            PlayingCard card = cards.remove(0);
            discardedPile.add(card);
            return card;
        }
        return null;
    }

    /**
     * Draws a hand of cards from the deck, the number of cards drawn is specified by the parameter
     * @param numberOfCards The number of cards to draw
     * @return hand A list of Cards representing the drawn hand
     */
    public List<PlayingCard> drawHand(int numberOfCards) {
        List<PlayingCard> hand = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            PlayingCard card = drawCard();
            if (card != null) {
                hand.add(card);
            } else {
                break;
            }
        }
        return hand;
    }

    /**
     * Prints the cards currently in the deck by suit and value in ascending order
     */
    public void printDeck() {
        if (cards.isEmpty()) {
            System.out.println("The deck is empty.");
            return;
        }
        System.out.println("Cards in Deck:");
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            System.out.println(suit + ":");
            for (PlayingCard.Value value : PlayingCard.Value.values()) {
                for (PlayingCard card : cards) {
                    if (card.getSuit() == suit && card.getValue() == value) {
                        System.out.println(card);
                    }
                }
            }
        }
    }
}
