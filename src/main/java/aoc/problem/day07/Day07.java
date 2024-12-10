package aoc.problem.day07;

import java.util.List;
import java.util.stream.Collectors;

import aoc.Day;
import aoc.shared.Utils;

public class Day07 implements Day {

    @Override
    public String part1(String input) {
        List<List<Long>> equations = Utils.splitLines(input).stream()
                                         .map(Utils::parseLineToLongs)
                                         .collect(Collectors.toList());

        long runningSum = 0;
        for (List<Long> equation : equations) {
            long testValue = equation.get(0);
            if (applyOperations(testValue, 0, equation.subList(1, equation.size()))) {
                System.out.println(equation + " is valid!");
                runningSum += testValue;
            }
        }

        return String.valueOf(runningSum);
    }

    private boolean applyOperations(long target, long currValue, List<Long> operands) {
        // BASE: no more operations
        if (operands.size() == 0) {
            return currValue == target;
        }

        // OPT: operations only increase value, solution will never exceed target
        if (currValue > target) {
            return false;
        }

        long nextOperand = operands.get(0);
        List<Long> remainingOperands = operands.subList(1, operands.size());
        
        // add op
        return applyOperations(target, currValue + nextOperand, remainingOperands) ||
        // multiply op
                applyOperations(target, currValue == 0 ? nextOperand : // avoid multiplying by 0 for first recursion
                                                         currValue * nextOperand,
                                remainingOperands);
    }

    @Override
    public String part2(String input) {
        return null;
    }
    
}