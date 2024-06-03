package question1;

import java.util.HashMap;
import java.util.Map;

public class TelephoneNumberWord {
    private static final Map<Character, char[]> digitToLettersMap = new HashMap<>();
    static {
        digitToLettersMap.put('2', new char[]{'A', 'B', 'C'});
        digitToLettersMap.put('3', new char[]{'D', 'E', 'F'});
        digitToLettersMap.put('4', new char[]{'G', 'H', 'I'});
        digitToLettersMap.put('5', new char[]{'J', 'K', 'L'});
        digitToLettersMap.put('6', new char[]{'M', 'N', 'O'});
        digitToLettersMap.put('7', new char[]{'P', 'R', 'S'});
        digitToLettersMap.put('8', new char[]{'T', 'U', 'V'});
        digitToLettersMap.put('9', new char[]{'W', 'X', 'Y'});
    }

    public static char[] getLettersForDigit(char digit) {
        return digitToLettersMap.getOrDefault(digit, new char[]{});
    }
}

