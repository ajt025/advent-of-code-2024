package aoc.day03;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import aoc.Day;
import aoc.Utils;

public class Day03 implements Day {

    public static final Pattern INSTRUCTION = Pattern.compile("mul\\(\\d+,\\d+\\)");
    public static final Pattern DIGIT = Pattern.compile("\\d+");

    @Override
    public String part1(String input) {
        Matcher instructionFinder = INSTRUCTION.matcher(input);
        int sum = instructionFinder.results()
                    // flatten
                    .map(match -> match.group())
                    // from each instruction, multiply the values
                    .map(match -> {
                        Matcher digitFinder = DIGIT.matcher(match);
                        return digitFinder.results().mapToInt(i -> Integer.parseInt(i.group())).reduce(1, (a, b) -> a * b);
                    })
                    // add products to the running sum
                    .collect(Collectors.summingInt(Integer::intValue));

        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        return "";
    }

}
