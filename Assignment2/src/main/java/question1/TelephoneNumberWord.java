package question1;

public class TelephoneNumberWord {
    public enum Digit {
        TWO('2', new char[]{'A', 'B', 'C'}),
        THREE('3', new char[]{'D', 'E', 'F'}),
        FOUR('4', new char[]{'G', 'H', 'I'}),
        FIVE('5', new char[]{'J', 'K', 'L'}),
        SIX('6', new char[]{'M', 'N', 'O'}),
        SEVEN('7', new char[]{'P', 'R', 'S'}),
        EIGHT('8', new char[]{'T', 'U', 'V'}),
        NINE('9', new char[]{'W', 'X', 'Y'});

        private final char digit;
        private final char[] letters;

        Digit(char digit, char[] letters) {
            this.digit = digit;
            this.letters = letters;
        }

        public char[] getLetters() {
            return letters;
        }

        public char getDigit() {
            return digit;
        }

        public static char[] getLettersForDigit(char digit) {
            for (Digit d : values()) {
                if (d.getDigit() == digit) {
                    return d.getLetters();
                }
            }
            return new char[0];
        }
    }
}
