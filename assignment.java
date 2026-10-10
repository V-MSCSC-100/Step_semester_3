//Question 1
import java.util.*;

public class Main {
    static List<Long> footfallReport(int[] visitors, int[][] queries) {
        long[] prefix = new long[visitors.length + 1];

        for (int i = 0; i < visitors.length; i++)
            prefix[i + 1] = prefix[i] + visitors[i];

        List<Long> result = new ArrayList<>();

        for (int[] query : queries)
            result.add(prefix[query[1] + 1] - prefix[query[0]]);

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {
            {0, 2}, {2, 5}, {4, 6}, {3, 3}
        };

        System.out.println(footfallReport(visitors, queries));
    }
}

// Time: O(n + q)
// Auxiliary space: O(n)

//Question 2
import java.util.*;

public class Main {
    static int[] longestStreak(int[] costs, int budget) {
        int left = 0, sum = 0, maxLength = 0, startIndex = -1;

        for (int right = 0; right < costs.length; right++) {
            sum += costs[right];

            while (sum > budget && left <= right)
                sum -= costs[left++];

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                startIndex = left;
            }
        }

        return new int[]{maxLength, startIndex};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
            longestStreak(
                new int[]{4, 2, 1, 7, 3, 1, 2, 1, 5}, 8
            )
        ));
    }
}

// Time: O(n)
// Auxiliary space: O(1)

//Question 3
import java.util.*;

public class Main {
    static long countPeriods(int[] transactions, long k) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        long sum = 0, count = 0;

        for (int transaction : transactions) {
            sum += transaction;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};
        System.out.println(countPeriods(transactions, 7));
    }
}

// Time: O(n) average
// Auxiliary space: O(n)

//Question 4
public class Main {
    static int lowerBound(int[] scores, int target) {
        int left = 0, right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] < target)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    static int upperBound(int[] scores, int target) {
        int left = 0, right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (scores[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    static int countInBand(int[] scores, int low, int high) {
        return upperBound(scores, high) - lowerBound(scores, low);
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores, 42, 58));
    }
}

// Time: O(log n)
// Auxiliary space: O(1)

//Question 5
import java.util.*;

public class Main {
    static List<Integer> auditRoute(int[][] grid) {
        List<Integer> result = new ArrayList<>();

        int top = 0, bottom = grid.length - 1;
        int left = 0, right = grid[0].length - 1;

        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++)
                result.add(grid[top][j]);
            top++;

            for (int i = top; i <= bottom; i++)
                result.add(grid[i][right]);
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--)
                    result.add(grid[bottom][j]);
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--)
                    result.add(grid[i][left]);
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(auditRoute(grid));
    }
}

// Time: O(m * n)
// Auxiliary space: O(1), excluding output
