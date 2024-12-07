package aoc.problem.day05;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import aoc.Day;
import aoc.shared.Patterns;
import aoc.shared.Utils;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);

        List<String> rulesRaw = lines.subList(0, lines.indexOf(""));
        List<String> updates = lines.subList(lines.indexOf("") + 1, lines.size());

        // build rules mapping int -> list<int>
        Map<Integer, Set<Integer>> rules = buildRulesMapping(rulesRaw);
        // System.out.println(rules);

        int runningSum = 0;
        // TODO iterate updates; create set and put previous elements in there
        for (String update : updates) {
            List<Integer> pageNumbers = Patterns.DIGIT.matcher(update).results()
                                            .map(MatchResult::group)
                                            .map(Integer::parseInt)
                                            .collect(Collectors.toList());
            // BR: add middle element to running sum if matches our criteria
            if (isPageSequenceValid(rules, pageNumbers)) {
                // System.out.println("match: " + update);
                runningSum += pageNumbers.get(pageNumbers.size() / 2);
            }
            // System.out.println("---");
        }
            
        return String.valueOf(runningSum);
    }

    private static Map<Integer, Set<Integer>> buildRulesMapping(List<String> rules) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (String rule : rules) {
            List<Integer> pair = Patterns.DIGIT.matcher(rule).results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

            int source = pair.get(0);
            if (map.containsKey(source)) {
                map.get(source).add(pair.get(1));
            } else {
                Set<Integer> targets = new HashSet<>();
                targets.add(pair.get(1));
                map.put(source, targets);
            }
        }

        return map;
    }

    private static boolean isPageSequenceValid(Map<Integer, Set<Integer>> rules, List<Integer> pageSequence) {
        Set<Integer> pagesSoFar = new HashSet<>();
        
        for (int i = 0; i < pageSequence.size(); ++i) {
            // get list from rules map and check if any items in list have appeared in set
            int currPage = pageSequence.get(i);
            pagesSoFar.add(currPage);

            // no preconditions, move onto the next page
            if (!rules.containsKey(currPage)) {
                continue;
            }

            Set<Integer> invalidNumbers = rules.get(currPage);
            // System.out.println("invalid nums for " + currPage + ": " + invalidNumbers);

            // valid if no items in list have appeared
            if (!Sets.intersection(pagesSoFar, invalidNumbers).isEmpty()) {
                return false;
            }

        }

        return true;
    }

    @Override
    public String part2(String input) {

        return String.valueOf("");
    }
}
