import java.lang.Character;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.System;

public class Main {
    public static String expandBanner(String code) {
        int length = code.length();
        int[] countStack = new int[length];
        StringBuilder[] stringStack = new StringBuilder[length];
        int countTop = -1;
        int stringTop = -1;

        StringBuilder currentString = new StringBuilder();
        int currentCount = 0;

        for (int index = 0; index < length; index++) {
            char character = code.charAt(index);

            if (Character.isDigit(character)) {
                currentCount = currentCount * 10 + (character - '0');
            } else if (character == '[') {
                countTop++;
                countStack[countTop] = currentCount;
                stringTop++;
                stringStack[stringTop] = currentString;

                currentString = new StringBuilder();
                currentCount = 0;
            } else if (character == ']') {
                int repeatCount = countStack[countTop];
                countTop--;

                StringBuilder previousString = stringStack[stringTop];
                stringTop--;

                for (int iteration = 0; iteration < repeatCount; iteration++) {
                    previousString.append(currentString);
                }
                currentString = previousString;
            } else {
                currentString.append(character);
            }
        }

        return currentString.toString();
    }

    public static void main(String[] args) {
        String testCaseOne = "3[ab]2[c]";
        String resultOne = expandBanner(testCaseOne);
        System.out.println(resultOne);

        String testCaseTwo = "2[x3[y]]z";
        String resultTwo = expandBanner(testCaseTwo);
        System.out.println(resultTwo);
    }
}
// Time Complexity: O(m) where m is the length of the fully expanded string
// Space Complexity: O(m + n) where n is input code length and m is output string length
