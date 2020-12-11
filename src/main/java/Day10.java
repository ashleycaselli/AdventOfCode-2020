import kotlin.Pair;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day10 implements Day {

    private File inputFile;
    private List<Integer> lines;
    private Pair<Integer, Integer> solutionOnePair;

    public Day10() throws IOException {
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

    @Override
    public void loadData() {
        this.inputFile = new File(Day09.class.getResource("input_10").getFile());
    }

    private int solvePart1() {
        lines.add(Collections.max(lines) + 3);
        lines = lines.stream().sorted().collect(Collectors.toList());

        int countOneStep = 0;
        int countThreeStep = 0;
        int previousJoltage = 0;

        for (Integer number : lines) {
            if (number - previousJoltage == 1) {
                countOneStep++;
            }
            if (number - previousJoltage == 3) {
                countThreeStep++;
            }
            previousJoltage = number;
        }
        this.solutionOnePair = new Pair<>(countOneStep, countThreeStep);
        return countOneStep * countThreeStep;
    }

    private int solvePart2() {
        // TODO complete
        return Integer.MIN_VALUE;
    }

    public Pair<Integer, Integer> getSolutionOnePair() {
        return this.solutionOnePair;
    }

}
