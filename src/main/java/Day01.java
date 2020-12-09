import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Day01 implements Day {

    private File inputFile;
    private final List<Integer> lines;
    private final int TARGET_VALUE = 2020;

    public Day01() throws IOException {
        this.loadData();
        this.lines = Files.lines(Path.of(inputFile.getPath()))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public void solve() {
        int solution1 = solvePart1();
        int solution2 = solvePart2();
        Utils.printSolution(this.getClass().getName(), solution1, solution2);
    }

    private int solvePart1() {
        AtomicInteger finalDiff = new AtomicInteger();
        Optional<Integer> res = lines.stream().filter(el -> {
            int diff = Math.abs(TARGET_VALUE - el);
            finalDiff.set(diff);
            return lines.contains(diff);
        }).findAny();

        return res.get() * finalDiff.intValue();
    }

    private AbstractMap.SimpleEntry<Integer, Integer> findEntry(final int sum) {
        Set<Integer> numbers = new HashSet<>(this.lines);
        AbstractMap.SimpleEntry<Integer, Integer> entry = new AbstractMap.SimpleEntry<>(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for (Integer number : numbers) {
            int diff = sum - number;
            if (numbers.contains(diff)) {
                entry = new AbstractMap.SimpleEntry<>(number, diff);
            }
        }
        return entry;
    }

    private int solvePart2() {
        Set<Integer> inputNumbers = new HashSet<>(this.lines);
        for (Integer i : inputNumbers) {
            AbstractMap.SimpleEntry<Integer, Integer> entry = findEntry(TARGET_VALUE - i);
            if (entry.getKey() != Integer.MIN_VALUE) {
                //System.out.println(i + " " + entry.getKey() + " " + entry.getValue());
                return i * entry.getKey() * entry.getValue();
            }
        }
        return Integer.MIN_VALUE;
    }

    @Override
    public void loadData() {
        this.inputFile = new File(Day09.class.getResource("input_01").getFile());
    }

}
