package aoc.day01;


import aoc.problem.day01.Day01;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    public void testPart1(){
        // Given
        String input =  "3   4\n" + //
                        "4   3\n" + //
                        "2   5\n" + //
                        "1   3\n" + //
                        "3   9\n" + //
                        "3   3";

        // When
        String result = new Day01().part1(input);

        // Then
        assertEquals("11", result);
    }

    @Test
    public void testPart2(){
        // Given
        String input =  "3   4\n" +
                        "4   3\n" +
                        "2   5\n" +
                        "1   3\n" +
                        "3   9\n" +
                        "3   3";

        // When
        String result = new Day01().part2(input);

        // Then
        assertEquals("31", result);
    }
}
