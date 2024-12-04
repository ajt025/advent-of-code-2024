package aoc.day04;

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

    public char[][] buildBoard(String input) {
        // build the board
        List<String> lines = Utils.splitLines(input);
        int m = lines.size();
        int n = lines.get(0).length();

        char[][] board = new char[m][n];

        for (int i = 0; i < lines.size(); ++i) {
            String currLine = lines.get(i);
            for (int j = 0; j < currLine.length(); ++j) {
                board[i][j] = currLine.charAt(j);
            }
        }

        return board;
    }
    
    @Override
    public String part1(String input) {
        char[][] board = buildBoard(input);

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
        return "";
    }

}
