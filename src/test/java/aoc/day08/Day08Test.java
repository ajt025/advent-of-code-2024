package aoc.day08;


import aoc.problem.day08.Day08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test {

    @Test
    public void testPart1(){
        // Given
        String input = "............\n" + //
                        "........0...\n" + //
                        ".....0......\n" + //
                        ".......0....\n" + //
                        "....0.......\n" + //
                        "......A.....\n" + //
                        "............\n" + //
                        "............\n" + //
                        "........A...\n" + //
                        ".........A..\n" + //
                        "............\n" + //
                        "............\n";

        // When
        String result = new Day08().part1(input);

        // Then
        assertEquals("14", result);
    }

    @Test
    public void testPart2(){
        // Given
        String input = "............\n" + //
                        "........0...\n" + //
                        ".....0......\n" + //
                        ".......0....\n" + //
                        "....0.......\n" + //
                        "......A.....\n" + //
                        "............\n" + //
                        "............\n" + //
                        "........A...\n" + //
                        ".........A..\n" + //
                        "............\n" + //
                        "............\n";

        // When
        String result = new Day08().part2(input);

        // Then
        assertEquals("34", result);
    }
}
