package aoc.shared;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Utils {

    public static final String DIGIT_REGEX = "\\d+";
    public static final Pattern DIGIT = Pattern.compile(DIGIT_REGEX);

    // Line Methods
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static List<Integer> parseLineToInts(String input) {
        return DIGIT.matcher(input).results().map((match) -> Integer.parseInt(match.group())).collect(Collectors.toList());
    }

    public static List<Long> parseLineToLongs(String input) {
        return DIGIT.matcher(input).results().map((match) -> Long.parseLong(match.group())).collect(Collectors.toList());
    }

    // Board Methods
    public static char[][] buildBoard(String input) {
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

    public static char[][] buildBoard(int m, int n, char fill) {
        char[][] board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = fill;
            }
        }
        return board;
    }

    public static boolean isInBounds(char[][] board, int x, int y) {
        return x >= 0 && y >= 0 && x < board.length && y < board[x].length;
    }

    public static int findCountOnBoard(char[][] board, char target) {
        int count = 0;
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == target) {
                    ++count;
                }
            }
        }
        return count;
    }

    public static void printBoard(char[][] board) {
        for (char[] line : board) {
            System.out.println(line);
        }
    }

}
