//Problem 1
import java.util.*;


public class SeatDuplicationChecker {


    static void checkDuplicateSeats(int[] seatNumbers) {


        boolean found = false;


        for (int i = 0; i < seatNumbers.length; i++) {
            for (int j = i + 1; j < seatNumbers.length; j++) {


                if (seatNumbers[i] == seatNumbers[j]) {


                    boolean alreadyPrinted = false;


                    for (int k = 0; k < i; k++) {
                        if (seatNumbers[k] == seatNumbers[i]) {
                            alreadyPrinted = true;
                            break;
                        }
                    }


                    if (!alreadyPrinted) {
                        System.out.println(
                            "Duplicate Seat Number Found: " + seatNumbers[i]);
                        found = true;
                    }
                }
            }
        }


        if (!found)
            System.out.println("No Duplicate Seats Found");
    }


    public static void main(String[] args) {


        int[] seats = {101, 102, 103, 102, 105};


        checkDuplicateSeats(seats);
    }
}
//Problem 2
import java.util.*;


public class TypingAccuracy {


    static void checkTypingAccuracy(String original, String typed) {


        int matched = 0;
        int firstMismatch = -1;


        for (int i = 0; i < original.length(); i++) {


            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }


        double accuracy = matched * 100.0 / original.length();


        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%",
                matched, original.length(), accuracy);


        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.println(" | First Mismatch at position " +
                    (firstMismatch + 1) + " ('" +
                    original.charAt(firstMismatch) + "' vs '" +
                    typed.charAt(firstMismatch) + "')");
        }
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter original passage: ");
        String original = sc.nextLine();


        System.out.print("Enter typed text: ");
        String typed = sc.nextLine();


        if (original.length() != typed.length()) {
            System.out.println("Both strings must have equal length.");
            return;
        }


        checkTypingAccuracy(original, typed);
    }
}
//Problem 3
import java.util.*;


public class TrafficStreak {


    static void findLongestStreak(String signalLog) {


        char longestColor = signalLog.charAt(0);
        int longest = 1;


        int current = 1;


        for (int i = 1; i < signalLog.length(); i++) {


            if (signalLog.charAt(i) == signalLog.charAt(i - 1)) {
                current++;
            } else {
                current = 1;
            }


            if (current > longest) {
                longest = current;
                longestColor = signalLog.charAt(i);
            }
        }


        System.out.println("Longest Streak: '" +
                longestColor + "' repeated " + longest + " times");
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter signal log: ");
        String signalLog = sc.nextLine();


        findLongestStreak(signalLog);
    }
}
//Problem 4
import java.util.*;


public class InventoryBalancer {


    static void analyzeInventory(int[] sectionA, int[] sectionB) {


        int totalA = 0;
        int totalB = 0;


        for (int i = 0; i < sectionA.length; i++)
            totalA += sectionA[i];


        for (int i = 0; i < sectionB.length; i++)
            totalB += sectionB[i];


        int highest = sectionA[0];
        String section = "Section A";
        int index = 0;


        for (int i = 0; i < sectionA.length; i++) {
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                index = i;
            }
        }


        for (int i = 0; i < sectionB.length; i++) {
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                index = i;
            }
        }


        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);


        if (totalA == totalB)
            System.out.println("Status: Balanced");
        else
            System.out.println("Status: Not Balanced");


        System.out.println("Highest Quantity: " + highest +
                " (" + section + ", Item " + (index + 1) + ")");
    }


    public static void main(String[] args) {


        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};


        analyzeInventory(sectionA, sectionB);
    }
}
//Problem 5
import java.util.*;


public class WordLengthProfiler {


    static void classifyWordLengths(String review) {


        String[] words = review.split("\\s+");


        int shortWords = 0;
        int mediumWords = 0;
        int longWords = 0;


        for (String word : words) {


            int length = word.length();


            if (length >= 1 && length <= 4)
                shortWords++;
            else if (length <= 8)
                mediumWords++;
            else
                longWords++;
        }


        System.out.println("Short: " + shortWords);
        System.out.println("Medium: " + mediumWords);
        System.out.println("Long: " + longWords);
    }


    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);


        System.out.print("Enter movie review: ");
        String review = sc.nextLine();


        classifyWordLengths(review);
    }
}
