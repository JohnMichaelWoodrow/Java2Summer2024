package BlackJackProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BlackJack Project
 * CardDeck class to manage the deck of playing cards.
 * @author john-michael woodrow
 */

/**
 * Constructs a new CardDeck with a full set of playing cards
 */
public class CardDeck {
    private List<PlayingCard> cards;
    private List<PlayingCard> discardedPile;

    /**
     * Constructs a CardDeck with a full set of playing cards.
     */
    public CardDeck() {
        cards = new ArrayList<>();
        discardedPile = new ArrayList<>();
        initializeDeck();
    }

    /**
     * Initializes the deck with all cards in a standard deck.
     */
    private void initializeDeck() {
        for (PlayingCard.Suit suit : PlayingCard.Suit.values()) {
            for (PlayingCard.Value value : PlayingCard.Value.values()) {
                if (value != PlayingCard.Value.HIDDEN) {
                    cards.add(new PlayingCard(suit, value));
                }
            }
        }
    }

    /**
     * Shuffles the deck, including the discarded pile.
     */
    public void shuffle() {
        cards.addAll(discardedPile);
        discardedPile.clear();
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the top of the deck.
     *
     * @return the drawn playing card
     */
    public PlayingCard drawCard() {
        if (cards.isEmpty()) {
            shuffle();
        }
        PlayingCard card = cards.remove(0);
        discardedPile.add(card);
        return card;
    }
}
