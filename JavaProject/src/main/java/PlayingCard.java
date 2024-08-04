public class PlayingCard {
    public enum Suit {
        SPADES("♠"), HEARTS("♥"), DIAMONDS("♦"), CLUBS("♣");

        private final String symbol;

        Suit(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public enum Value {
        TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
        SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
        JACK("J"), QUEEN("Q"), KING("K"), ACE("A"),
        HIDDEN("Hidden");

        private final String symbol;

        Value(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    private Suit suit;
    private Value value;

    public PlayingCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString() + " of " + suit.toString();
    }
}
