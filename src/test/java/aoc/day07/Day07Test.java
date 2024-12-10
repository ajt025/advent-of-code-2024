package aoc.day07;


import aoc.problem.day07.Day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test {

    @Test
    public void testPart1(){
        // Given
        String input = "190: 10 19\n" + //
                        "3267: 81 40 27\n" + //
                        "83: 17 5\n" + //
                        "156: 15 6\n" + //
                        "7290: 6 8 6 15\n" + //
                        "161011: 16 10 13\n" + //
                        "192: 17 8 14\n" + //
                        "21037: 9 7 18 13\n" + //
                        "292: 11 6 16 20";

        // When
        String result = new Day07().part1(input);

        // Then
        assertEquals("3749", result);
    }

    @Test
    public void testPart2(){
        // Given
        String input =  "190: 10 19\n" + //
                        "3267: 81 40 27\n" + //
                        "83: 17 5\n" + //
                        "156: 15 6\n" + //
                        "7290: 6 8 6 15\n" + //
                        "161011: 16 10 13\n" + //
                        "192: 17 8 14\n" + //
                        "21037: 9 7 18 13\n" + //
                        "292: 11 6 16 20";

        // When
        String result = new Day07().part2(input);

        // Then
        assertEquals("11387", result);
    }
}
