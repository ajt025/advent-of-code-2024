package aoc.problem.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import aoc.Day;
import aoc.shared.Utils;

public class Day08 implements Day {
    private static final char BLANK = '.';
    private static final char ANTINODE = '#';

    @Override
    public String part1(String input) {
        char[][] board = Utils.buildBoard(input);
        char[][] antinodeBoard = Utils.buildBoard(board.length, board[0].length, BLANK);
        
        // get all locations of each antenna Map<AntennaType, Locations>
        Map<Character, List<List<Integer>>> antennas = parseAntennaPositions(board);

        // per antennatype, calc vector distance between every element
        for (Character antennaType : antennas.keySet()) {
            List<List<Integer>> positions = antennas.get(antennaType);

            // each location, add/sub vector distance
            for (int i = 0; i < positions.size(); i++) {
                for (int j = i + 1; j < positions.size(); j++) {
                    List<Integer> distance = calculateVector(positions.get(i), positions.get(j));
                    generateAntinodes(board, antinodeBoard, antennaType, List.of(
                        positions.get(i), positions.get(j)
                    ), distance);
                }
            }
        }

        // Utils.printBoard(antinodeBoard);

        // count all antinodes and return
        return String.valueOf(Utils.findCountOnBoard(antinodeBoard, ANTINODE));
    }

    private Map<Character, List<List<Integer>>> parseAntennaPositions(char[][] board) {
        Map<Character, List<List<Integer>>> antennas = new HashMap<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                char currCell = board[i][j];

                if (currCell != BLANK) {
                    if (antennas.containsKey(currCell)) {
                        List<List<Integer>> positions = antennas.get(currCell);
                        positions.add(List.of(i, j));
                    } else {
                        List<List<Integer>> positions = new ArrayList<>();
                        positions.add(List.of(i, j));
                        antennas.put(currCell, positions);
                    }
                }
            }
        }
        return antennas;
    }
    
    private void generateAntinodes(char[][] antennaBoard, char[][] antinodeBoard, char antennaType,
                                    List<List<Integer>> antennaPositions, List<Integer> distance) {
        // each location, add/sub manhattan distance
        for (List<Integer> position : antennaPositions) {
            for (List<Integer> pole : generatePolars(distance)) {
                List<Integer> potentialAntinode = calculateSum(position, pole);
                int x = potentialAntinode.get(0);
                int y = potentialAntinode.get(1);
                
                // inbounds check
                if (x >= 0 && y >= 0 && x < antinodeBoard.length && y < antennaBoard[x].length) {
                    // if not equal to a current antenna, set pos on antinode board to antinode
                    if (antennaBoard[x][y] != antennaType) {
                        // System.out.println("antinode at " + x + "," + y + " for antennae " + antennaPositions);
                        antinodeBoard[x][y] = ANTINODE;
                    }
                }
            }

        }   
    }

    // TODO generalize this to arbitrary list size
    private List<Integer> calculateSum(List<Integer> a, List<Integer> b) {
        return List.of(
            a.get(0) + b.get(0),
            a.get(1) + b.get(1)
        );
    }

    // TODO generalize this to arbitrary list size
    private List<Integer> calculateVector(List<Integer> a, List<Integer> b) {
        return List.of(
            a.get(0) - b.get(0),
            a.get(1) - b.get(1)
        );
    }

    // Returns a list of two elements; one with the original element and the polar opposite of that element
    private List<List<Integer>> generatePolars(List<Integer> distance) {
        List<Integer> polar = distance.stream().map((element) -> element * -1).collect(Collectors.toList());
        return List.of(distance, polar);
    }

    @Override
    public String part2(String input) {
        // TODO Auto-generated method stub
        return null;
    }
}