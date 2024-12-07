package aoc.problem.day02;

import java.util.ArrayList;
import java.util.List;

import aoc.Day;
import aoc.shared.Utils;

public class Day02 implements Day {

    private static final int MIN_LEVEL_DIFF = 1;
    private static final int MAX_LEVEL_DIFF = 3;

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
    
        int safeCount = 0;

        for (String line : lines) {
            List<Integer> levels = Utils.parseLineToInts(line, " ");

            if (isSafe(levels)) {
                ++safeCount;
            }
        }

        return String.valueOf(safeCount);
    }

    protected boolean isSafe(List<Integer> levels) {
        boolean isIncreasing = false;
        
        // compare current element to previous element, checking if difference is valid
        for (int i = 1; i < levels.size(); ++i) {
            int difference = levels.get(i) - levels.get(i - 1);
            
            // establish if levels will be increasing or decreasing
            if (i == 1) {
                isIncreasing = difference > 0;
            }

            // invalid levels if there is an inflection point
            if (isIncreasing != difference > 0) {
                return false;
            }

            // validate level difference rules defined by the problem
            int positiveDifference = Math.abs(difference);
            if (positiveDifference < MIN_LEVEL_DIFF || MAX_LEVEL_DIFF < positiveDifference) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
    
        int safeCount = 0;

        for (String line : lines) {
            List<Integer> levels = Utils.parseLineToInts(line, " ");

            // call safe check on normal input and then, if needed, n sublists with each element excluded
            if (isSafe(levels)) {
                ++safeCount;
            } else {
                for (int i = 0; i < levels.size(); ++i) {
                    List<Integer> levelsCopy = new ArrayList<>(levels);
                    levelsCopy.remove(i);
                    if (isSafe(levelsCopy)) {
                        ++safeCount;
                        break;
                    }
                }
            }
        }

        return String.valueOf(safeCount);
    }

}
