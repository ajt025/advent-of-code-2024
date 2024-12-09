package aoc.day06;


import aoc.problem.day06.Day06;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {

    @Test
    public void testPart1(){
        // Given
        String input =  "....#.....\n" +
                        ".........#\n" +
                        "..........\n" +
                        "..#.......\n" +
                        ".......#..\n" +
                        "..........\n" +
                        ".#..^.....\n" +
                        "........#.\n" +
                        "#.........\n" +
                        "......#...\n";

        // When
        String result = new Day06().part1(input);

        // Then
        assertEquals("41", result);
    }

    @Test
    public void testPart2(){
        // Given
        String input = "";

        // When
        String result = new Day06().part2(input);

        // Then
        assertEquals("not implemented", result);
    }
}
