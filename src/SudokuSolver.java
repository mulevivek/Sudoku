import java.util.Scanner;
public class SudokuSolver {
    private static final int SIZE = 9;  // Only 9X9 Sudoku

    public static void main(String[] args) {
        int[][] board = new int[SIZE][SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle (enter 0 for empty cells): ");
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = scanner.nextInt();
            }
        }

        System.out.println("Original Board:");
        printBoard(board);

        if (solveSudoku(board)) {
            System.out.println("\nSolved Board:");
            printBoard(board);
        } else {
            System.out.println("No solution exists");
        }
    }

    // using backtracking
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                // empty cell (marked by 0)
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;

                            // Recursion
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Undo if not find solution
                            board[row][col] = 0;
                        }
                    }
                    return false;  // If no number is valid, backtrack
                }
            }
        }
        return true;
    }

    // Check if placing a number at a specific position is valid
    public static boolean isValid(int[][] board, int row, int col, int num) {
        //  row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        //  column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        //  3x3 grid
        int gridRow = row - row % 3;
        int gridCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + gridRow][j + gridCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Final Print
    public static void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("------+-------+------");
            }
            for (int col = 0; col < SIZE; col++) {
                if (col % 3 == 0 && col != 0) {
                    System.out.print("| ");
                }
                System.out.print((board[row][col] == 0 ? "." : board[row][col]) + " ");
            }
            System.out.println();
        }
    }
}
