package aoc.shared;

import java.util.regex.Pattern;

// Shared RegEx helpers
public class Patterns {
    public static final String DIGIT_REGEX = "\\d+";
    public static final Pattern DIGIT = Pattern.compile(DIGIT_REGEX);
}