package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01 implements Day {

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);
        
        // create list of a and list of b for each pair/line "a b"
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String line : lines) {
            String[] pair = line.split(" +");
            list1.add(Integer.parseInt(pair[0]));
            list2.add(Integer.parseInt(pair[1]));
        }

        // sort a, sort b ascending to ensure we're getting the smallest value pairs
        Collections.sort(list1);
        Collections.sort(list2);

        int runningSum = 0;
        // iterate a, b and calculate running sum of |a - b|
        for (int i = 0; i < list1.size(); i++) {
            runningSum += Math.abs(list2.get(i) - list1.get(i));
        }

        return String.valueOf(runningSum);
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);
        
        // (a,b) => create list of a, and a frequency counter list for b
        List<Integer> list1 = new ArrayList<>();
        Map<Integer, Integer> list2Frequency = new HashMap<>();

        for (String line : lines) {
            String[] pair = line.split(" +");
            list1.add(Integer.parseInt(pair[0]));

            // count frequency of b value
            int parsedValue = Integer.parseInt(pair[1]);
            int parsedValueFrequency = list2Frequency.getOrDefault(parsedValue, 0) + 1;
            list2Frequency.put(parsedValue, parsedValueFrequency);
        }

        int runningSum = 0;
        // iterate a, b and calculate running sum of a * (frequency of a appearing in b)
        for (Integer each : list1) {
            runningSum += (each * list2Frequency.getOrDefault(each, 0));
        }

        return String.valueOf(runningSum);
    }

}
