//Problem 1
import java.util.*;
public class RockPaperScissors {
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equals(computerMove))
            return "Draw";


        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper")))
            return "Player Wins";


        return "Computer Wins";
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();


        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] player = new String[5];
        String[] computer = new String[5];
        String[] result = new String[5];


        int wins = 0, losses = 0, draws = 0;


        for (int i = 0; i < 5; i++) {
            System.out.print("Enter Rock, Paper or Scissors: ");
            player[i] = sc.next();


            computer[i] = moves[r.nextInt(3)];
            result[i] = playRound(player[i], computer[i]);


            if (result[i].equals("Player Wins"))
                wins++;
            else if (result[i].equals("Computer Wins"))
                losses++;
            else
                draws++;
        }


        System.out.println("\nRound\tPlayer\tComputer\tResult");


        for (int i = 0; i < 5; i++)
            System.out.println((i + 1) + "\t" + player[i] + "\t" +
                    computer[i] + "\t\t" + result[i]);


        double winPercentage = wins * 100.0 / 5;


        System.out.println("\nWins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.println("Win %: " + winPercentage);
    }
}
//Problem 2
import java.util.*;


public class PalindromeChecker {


    static boolean isPalindromeIterative(String text) {
        int i = 0, j = text.length() - 1;


        while (i < j) {
            if (text.charAt(i) != text.charAt(j))
                return false;
            i++;
            j--;
        }


        return true;
    }


    static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1)
            return true;


        if (text.charAt(0) != text.charAt(text.length() - 1))
            return false;


        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }


    static boolean isPalindromeArrayReversal(String text) {
        char[] arr = text.toCharArray();


        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            char temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }


        return text.equals(new String(arr));
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter text: ");
        String text = sc.nextLine();


        System.out.println("Iterative: " +
                (isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome"));


        System.out.println("Recursive: " +
                (isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome"));


        System.out.println("Array Reversal: " +
                (isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome"));
    }
}
//Problem 3
import java.util.*;


public class BMICalculator {


    static String getBmiStatus(double bmi) {
        if (bmi < 18.5)
            return "Underweight";
        else if (bmi < 25)
            return "Normal";
        else if (bmi < 30)
            return "Overweight";
        else
            return "Obese";
    }


    static void printWellnessReport(double[] heights, double[] weights) {


        System.out.println("\nPerson\tHeight\tWeight\tBMI\tStatus");


        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);


            System.out.printf("%d\t%.2f\t%.2f\t%.2f\t%s%n",
                    i + 1,
                    heights[i],
                    weights[i],
                    bmi,
                    getBmiStatus(bmi));
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter number of people: ");
        int n = sc.nextInt();


        double[] heights = new double[n];
        double[] weights = new double[n];


        for (int i = 0; i < n; i++) {
            System.out.print("Enter height of person " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();


            System.out.print("Enter weight of person " + (i + 1) + ": ");
            weights[i] = sc.nextDouble();
        }


        printWellnessReport(heights, weights);
    }
}
//Problem 4
import java.util.*;


public class FirstNonRepeating {


    static char findFirstNonRepeatingChar(String text) {


        int[] frequency = new int[256];


        for (int i = 0; i < text.length(); i++)
            frequency[text.charAt(i)]++;


        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1)
                return text.charAt(i);
        }


        return '\0';
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter text: ");
        String text = sc.nextLine();


        char result = findFirstNonRepeatingChar(text);


        if (result == '\0')
            System.out.println("No Non-Repeating Character Found");
        else
            System.out.println("First Non-Repeating Character: '" + result + "'");
    }
}
//Problem 5
import java.util.*;


public class ReverseCustomerName {


    static String reverseCustomerName(String customerName) {
        String reversed = "";


        for (int i = customerName.length() - 1; i >= 0; i--)
            reversed += customerName.charAt(i);


        return reversed;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.print("Enter customer name: ");
        String name = sc.nextLine();


        System.out.println("Original Name: " + name);
        System.out.println("Reversed Name: " + reverseCustomerName(name));
    }
}


