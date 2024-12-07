package aoc.problem.day03;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import aoc.Day;
import aoc.shared.Patterns;

public class Day03 implements Day {

    public static final String INSTRUCTION_REGEX = "mul\\(\\d+,\\d+\\)";
    public static final String ENABLE_REGEX = "do\\(\\)";
    public static final String DISABLE_REGEX = "don't\\(\\)";

    public static final Pattern INSTRUCTION = Pattern.compile(INSTRUCTION_REGEX);

    @Override
    public String part1(String input) {
        Matcher instructionFinder = INSTRUCTION.matcher(input);
        int sum = instructionFinder.results()
                    // flatten
                    .map(match -> match.group())
                    // from each instruction, multiply the values
                    .map(match -> {
                        Matcher digitFinder = Patterns.DIGIT.matcher(match);
                        return digitFinder.results().mapToInt(i -> Integer.parseInt(i.group())).reduce(1, (a, b) -> a * b);
                    })
                    // add products to the running sum
                    .collect(Collectors.summingInt(Integer::intValue));

        return String.valueOf(sum);
    }

    // Had to convert back from streams to for loop since summing dependent on state felt more complicated 
    @Override
    public String part2(String input) {
        Pattern instructionAndToggle = Pattern.compile(
            String.format("(%s)|(%s)|(%s)", INSTRUCTION_REGEX, ENABLE_REGEX, DISABLE_REGEX)
        );
        Matcher matcher = instructionAndToggle.matcher(input);

        boolean isEnabled = true;
        int runningSum = 0;
        for (MatchResult each : matcher.results().toList()) {
            String match = each.group();

            if (match.matches(ENABLE_REGEX)) {
                isEnabled = true;
            } else if (match.matches(DISABLE_REGEX)) {
                isEnabled = false;
            } else {
                // Do not add to running sum if calculations have been toggled off
                if (!isEnabled) {
                    continue;
                }

                Matcher factors = Patterns.DIGIT.matcher(match);
                runningSum += factors.results()
                                        .mapToInt(i -> Integer.parseInt(i.group()))
                                        .reduce(1, (a, b) -> a * b);
            }
        }

        return String.valueOf(runningSum);
    }

}
