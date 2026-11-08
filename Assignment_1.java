import java.util.Arrays;

public class Solution {
    public int[] priceStreak(int[] price) {
        int length = price.length;
        int[] streaks = new int[length];
        int[] stack = new int[length];
        int top = -1;

        for (int currentIndex = 0; currentIndex < length; currentIndex++) {
            while (top >= 0 && price[stack[top]] <= price[currentIndex]) {
                top--;
            }

            if (top < 0) {
                streaks[currentIndex] = currentIndex + 1;
            } else {
                streaks[currentIndex] = currentIndex - stack[top];
            }

            top++;
            stack[top] = currentIndex;
        }

        return streaks;
    }
}
// Time Complexity: O(n)
// Space Complexity: O(n)

public class Main {
    public static int[] priceStreak(int[] price) {
        int length = price.length;
        int[] streaks = new int[length];
        int[] stack = new int[length];
        int top = -1;

        for (int currentIndex = 0; currentIndex < length; currentIndex++) {
            while (top >= 0 && price[stack[top]] <= price[currentIndex]) {
                top--;
            }

            if (top < 0) {
                streaks[currentIndex] = currentIndex + 1;
            } else {
                streaks[currentIndex] = currentIndex - stack[top];
            }

            top++;
            stack[top] = currentIndex;
        }

        return streaks;
    }

    public static void main(String[] args) {
        int[] firstInput = {100, 80, 60, 70, 60, 75, 85};
        int[] firstOutput = priceStreak(firstInput);
        System.out.println(Arrays.toString(firstOutput));

        int[] secondInput = {10, 20, 30};
        int[] secondOutput = priceStreak(secondInput);
        System.out.println(Arrays.toString(secondOutput));
    }
}
// Time Complexity: O(n)
// Space Complexity: O(n)
