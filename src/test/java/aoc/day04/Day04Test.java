package aoc.day04;


import aoc.problem.day04.Day04;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {

    @Test
    public void testPart1(){
        // Given
        String input =  "MMMSXXMASM\n" + //
                        "MSAMXMSMSA\n" + //
                        "AMXSXMAAMM\n" + //
                        "MSAMASMSMX\n" + //
                        "XMASAMXAMM\n" + //
                        "XXAMMXXAMA\n" + //
                        "SMSMSASXSS\n" + //
                        "SAXAMASAAA\n" + //
                        "MAMMMXMMMM\n" + //
                        "MXMXAXMASX";

        // When
        String result = new Day04().part1(input);

        // Then
        assertEquals("18", result);
    }

    @Test
    public void testPart2(){
        // Given
        String input =  ".M.S......\n" + //
                        "..A..MSMS.\n" + //
                        ".M.S.MAA..\n" + //
                        "..A.ASMSM.\n" + //
                        ".M.S.M....\n" + //
                        "..........\n" + //
                        "S.S.S.S.S.\n" + //
                        ".A.A.A.A..\n" + //
                        "M.M.M.M.M.\n" + //
                        "..........";

        // When
        String result = new Day04().part2(input);

        // Then
        assertEquals("9", result);
    }
}
