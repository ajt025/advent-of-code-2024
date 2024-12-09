package aoc.problem.day04;

import java.util.List;

import aoc.Day;
import aoc.shared.Utils;

public class Day04 implements Day {

    int[][] DIRECTIONS = {
        {-1, -1},   // top left
        {0, -1},    // top
        {1, -1},    // top right
        {-1, 0},    // left
        {1, 0},     // right
        {-1, 1},    // bottom left
        {0, 1},     // bottom
        {1, 1},     // bottom right
    };

    int[][] DIAGONAL_DIRECTIONS = {
        {-1, -1},   // top left
        {1, 1},     // bottom right
        {1, -1},    // top right
        {-1, 1},    // bottom left
    };
    
    @Override
    public String part1(String input) {
        char[][] board = Utils.buildBoard(input);

        int runningSum = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                for (int[] direction : DIRECTIONS) {
                    runningSum += recurseSearch(board, i, j, "XMAS", 0, direction);
                }
            }
        }

        return String.valueOf(runningSum);
    }

    public int recurseSearch(char[][] board, int i, int j, String word, int letterIndexToMatch, int[] direction) {
        // GUARD: Board edge check
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return 0;
        }

        // GUARD: no letter match
        if (board[i][j] != word.charAt(letterIndexToMatch)) {
            return 0;
        }
        
        // full match! count this path
        if (letterIndexToMatch == word.length() - 1) {
            return 1;
        }
        
        // since this is potential match and we haven't hit the end of the word,
        // continue recursion on this cell
        int nextI = i + direction[0];
        int nextJ = j + direction[1];
        return recurseSearch(board, nextI, nextJ, word, letterIndexToMatch + 1, direction);
    }

    @Override
    public String part2(String input) {
        char[][] board = Utils.buildBoard(input);

        int runningSum = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                // 'A' acts as the origin for each X-MAS cross
                if (board[i][j] == 'A') {
                    runningSum += findXmas(board, i, j);
                }
            }
        }

        return String.valueOf(runningSum);
    }

    public int findXmas(char[][] board, int i, int j) {
        int mCount = 0;
        int sCount = 0;

        for (int[] direction : DIAGONAL_DIRECTIONS) {
            int nextI = i + direction[0];
            int nextJ = j + direction[1];
            // current cell is impossible for X-MAS to exist due to boundary
            if (nextI < 0 || nextJ < 0 || nextI >= board.length || nextJ >= board[i].length) {
                break;
            }
            
            // count number of letters to see if forming X-MAS is even possible
            char currLetter = board[nextI][nextJ];
            if (currLetter == 'M') ++mCount;
            if (currLetter == 'S') ++sCount;
        }

        // check letter counts and one diagonal bc X-MAS must be formed with 2 M's and 2 S's
        // the diagonal check is to ensure a diagonal isn't spelling MAM/SAS. if one diagonal is valid, the other must be as well
        if (mCount == 2 && sCount == 2 && isDiagonalDifferent(board, i, j)) {
            return 1;
        }

        return 0;
    }

    // Check top left and bottom right cells and return if they're different
    private boolean isDiagonalDifferent(char[][] board, int i, int j) {
        int[] topLeft = {i + DIAGONAL_DIRECTIONS[0][0], j + DIAGONAL_DIRECTIONS[0][1]};
        int[] bottomRight = {i + DIAGONAL_DIRECTIONS[1][0], j + DIAGONAL_DIRECTIONS[1][1]};

        return board[topLeft[0]][topLeft[1]] != board[bottomRight[0]][bottomRight[1]];
    }

}
