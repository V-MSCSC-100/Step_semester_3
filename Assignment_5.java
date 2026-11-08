import java.lang.System;

public class Main {
    public static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        int cols = plan[0].length;
        int[][] distances = new int[rows][cols];

        int totalCells = rows * cols;
        int[] queueRow = new int[totalCells];
        int[] queueCol = new int[totalCells];
        int head = 0;
        int tail = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    distances[r][c] = 0;
                    queueRow[tail] = r;
                    queueCol[tail] = c;
                    tail++;
                } else {
                    distances[r][c] = -1;
                }
            }
        }

        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};

        while (head < tail) {
            int currentRow = queueRow[head];
            int currentCol = queueCol[head];
            head++;

            for (int i = 0; i < 4; i++) {
                int nextRow = currentRow + deltaRow[i];
                int nextCol = currentCol + deltaCol[i];

                if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                    if (plan[nextRow][nextCol] == '.' && distances[nextRow][nextCol] == -1) {
                        distances[nextRow][nextCol] = distances[currentRow][currentCol] + 1;
                        queueRow[tail] = nextRow;
                        queueCol[tail] = nextCol;
                        tail++;
                    }
                }
            }
        }

        return distances;
    }

    public static void printGrid(int[][] grid) {
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                System.out.print(grid[r][c]);
                if (c < grid[r].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] samplePlan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(samplePlan);
        printGrid(result);
    }
}
// Time Complexity: O(R * C) where R is rows and C is columns
// Space Complexity: O(R * C) for queue arrays and distance matrix
