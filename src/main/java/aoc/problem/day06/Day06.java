package aoc.problem.day06;

import aoc.Day;
import aoc.shared.Utils;

public class Day06 implements Day {

    private static final int LOOP_DETECTED_COUNT = 25000;
    private static final char START = '^';
    private static final char OBSTACLE = '#';
    private static final char VISITED = 'X';

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

        // call recursive function and add counts
        walk(board, startPosition[0], startPosition[1], DIRECTIONS[0]);

        int totalSpacesCovered = Utils.findCountOnBoard(board, VISITED);

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

    @Override
    public String part2(String input) {
        char[][] board = Utils.buildBoard(input);

        // find starting point '^' (helper)
        int[] startPosition = findStartingPosition(board);

        // find all places traversed by guard
        walk(board, startPosition[0], startPosition[1], DIRECTIONS[0]);

        int loopCount = 0;
        // attempt placement of obstacle at every cell traversed by guard and brute search loop
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == VISITED) {
                    // System.out.println("Attempting obstacle at " + i + "," + j);
                    char temp = board[i][j];
                    board[i][j] = OBSTACLE;
                    if (containsLoop(board, startPosition[0], startPosition[1], DIRECTIONS[0])) {
                        // System.out.println("Loop found for obstacle at " + i + "," + j);
                        ++loopCount;
                    }
                    board[i][j] = temp;
                }
            }
        }

        return String.valueOf(loopCount);
    }

    // new approach - convert to iterative
    private boolean containsLoop(char[][] board, int i, int j, int[] direction) {
        int[] currPosition = new int[] {i, j};
        int[] currDirection = direction;
        int steps = 0;

        // check if there's an obstacle, and we need to turn
        while (currPosition[0] > 0 && currPosition[1] > 0 &&
                currPosition[0] < board.length && currPosition[1] < board[i].length) {
            // System.out.println(Arrays.toString(currPosition) + " going " + Arrays.toString(currDirection));
            
            if (steps > LOOP_DETECTED_COUNT) {
                return true;
            }

            int[] nextPosition = new int[] {currPosition[0] + currDirection[0], currPosition[1] + currDirection[1]};
            
            if (nextPosition[0] < 0 || nextPosition[1] < 0 ||
                nextPosition[0] >= board.length || nextPosition[1] >= board[i].length) {
                        return false;
            }

            if (board[nextPosition[0]][nextPosition[1]] == OBSTACLE) {
                currDirection = rotateGuard(currDirection);
            } else {
                currPosition = nextPosition;
            }
            ++steps;
        }
        
        return false;
    }
}