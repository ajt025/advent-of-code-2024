package aoc.problem.day06;

import aoc.Day;
import aoc.shared.Utils;

public class Day06 implements Day {

    public static final char START = '^';
    public static final char OBSTACLE = '#';
    public static final char VISITED = 'X';

    public static int[][] DIRECTIONS = {
            {-1, 0},    // top
            {0, 1},     // right
            {1, 0},     // bottom
            {0, -1},    // left
    };

    @Override
    public String part1(String input) {
        char[][] board = Utils.buildBoard(input);

        // find starting point '^' (helper)
        int[] startPosition = findStartingPosition(board);

        // for each direction, call recursive function and add counts
        walk(board, startPosition[0], startPosition[1], DIRECTIONS[0]);

        int totalSpacesCovered = countVisited(board);

        return String.valueOf(totalSpacesCovered);
    }

    private void walk(char[][] board, int i, int j, int[] direction) {
        // update curr position to visited and increment count
        board[i][j] = VISITED;

        // check if there's an obstacle, and we need to turn
        int[] nextPosition = new int[] {i + direction[0], j + direction[1]};
        // Guard: over board edge
        if (nextPosition[0] < 0 || nextPosition[1] < 0 ||
            nextPosition[0] >= board.length || nextPosition[1] >= board[i].length) {
            return;
        }

        int[] nextDirection = direction;
        if (board[nextPosition[0]][nextPosition[1]] == OBSTACLE) {
            nextDirection = rotateGuard(direction);
        }

        walk(board, i + nextDirection[0], j + nextDirection[1], nextDirection);
    }

    private static int[] findStartingPosition(char[][] board) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == START) {
                    return new int[] {i, j};
                }
            }
        }

        throw new RuntimeException("Input is missing a starting position");
    }

    // Directions array is ordered in a clockwise fashion; simply grabs the next direction
    private static int[] rotateGuard(int[] currentDirection) {
        for (int i = 0; i < DIRECTIONS.length; ++i) {
            if (DIRECTIONS[i] == currentDirection) {
                return DIRECTIONS[(i + 1) % DIRECTIONS.length];
            }
        }

        throw new RuntimeException("Current direction is not a valid direction for problem");
    }

    private static int countVisited(char[][] board) {
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == VISITED) {
                    ++count;
                }
            }
        }
        return count;
    }

    @Override
    public String part2(String input) {
        return null;
    }
}