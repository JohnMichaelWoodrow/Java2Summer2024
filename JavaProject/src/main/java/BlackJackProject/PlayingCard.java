package BlackJackProject;

/**
 * BlackJack Project
 * Represents a playing card with a suit and value, used to create playing cards that make up a deck.
 * Author: john-michael woodrow
 */

/**
 * Constructs a new playing card with implementation of enums.
 */
public class PlayingCard {
    /**
     * Enum representing the four suits of a standard card deck, each suit is represented by a symbol.
     */
    public enum Suit {
        SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

        private final String symbol;

        /**
         * Constructs a Suit instance with a given symbol.
         *
         * @param symbol Symbol representing the suit.
         */
        Suit(String symbol) {
            this.symbol = symbol;
        }

        /**
         * Returns the symbol of the suit as a string.
         *
         * @return String representing the symbol.
         */
        @Override
        public String toString() {
            return symbol;
        }
    }

    /**
     * Enum representing the values of cards in a standard deck, each value is represented by a symbol.
     */
    public enum Value {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
        SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("J"), QUEEN("Q"), KING("K"), ACE("A"),
        HIDDEN("Hidden");

        private final String symbol;

        /**
         * Constructs a Value instance with a given symbol.
         *
         * @param symbol Symbol representing the value.
         */
        Value(String symbol) {
            this.symbol = symbol;
        }

        /**
         * Returns the symbol of the value as a string.
         *
         * @return String representing the symbol of the value.
         */
        @Override
        public String toString() {
            return symbol;
        }
    }

    private Suit suit;
    private Value value;

    /**
     * Constructs a PlayingCard with the specified suit and value.
     *
     * @param suit Suit of the card.
     * @param value Value of the card.
     */
    public PlayingCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    /**
     * Returns the suit of the card.
     *
     * @return Suit.
     */
    public Suit getSuit() {
        return suit;
    }

    /**
     * Returns the value of the card.
     *
     * @return Value.
     */
    public Value getValue() {
        return value;
    }

    /**
     * Returns a string representation of the playing card.
     *
     * @return String representation of the playing card.
     */
    @Override
    public String toString() {
        return value.toString() + " of " + suit.toString();
    }
}
