//Question 1
import java.util.*;

public class Main {
    static int[] pairSumSorted(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target)
                return new int[]{nums[left], nums[right]};
            else if (sum < target)
                left++;
            else
                right--;
        }

        return new int[0];
    }

    public static void main(String[] args) {
        int[] result = pairSumSorted(
            new int[]{-4, -1, 0, 3, 5, 9}, 4
        );

        System.out.println(result.length == 0
            ? "Not Found"
            : Arrays.toString(result));
    }
}
//Complexity: Time \(O(n)\), auxiliary space \(O(1)\).

//Question 2
public class Main {
    static void warehouseSummary(int[][] grid) {
        int total = 0, max = -1, maxRow = 0, maxCol = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];

                if (grid[i][j] > max) {
                    max = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Total = " + total);
        System.out.println("Max Coordinate = (" + maxRow + ", " + maxCol + ")");
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };

        warehouseSummary(grid);
    }
}
//Complexity: Time \(O(mn)\), auxiliary space \(O(1)\).

//Question 3
public class Main {
    static String findBook(String[][] catalog, String targetIsbn) {
        int left = 0, right = catalog.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = catalog[mid][0].compareTo(targetIsbn);

            if (comparison == 0)
                return catalog[mid][1];
            else if (comparison < 0)
                left = mid + 1;
            else
                right = mid - 1;
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        String[][] catalog = {
            {"0001112223", "Introduction to Algebra"},
            {"0002223334", "Beginning Python"},
            {"0003334445", "Classic Mythology"},
            {"0004445556", "Data and Society"},
            {"0005556667", "European History"}
        };

        System.out.println(findBook(catalog, "0003334445"));
    }
}

// Time: O(log n)
// Auxiliary space: O(1)

//Question 4
//SAME AS QUESTION 3

//Question 5
public class Main {
    static int maxSumSubarray(int[] sales, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++)
            sum += sales[i];

        int maxSum = sum;

        for (int i = k; i < sales.length; i++) {
            sum += sales[i] - sales[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(
            maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3)
        );
    }
}

// Time: O(n)
// Auxiliary space: O(1)
