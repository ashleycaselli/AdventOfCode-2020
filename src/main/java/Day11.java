import kotlin.Pair;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Day11 implements Day {

    private File inputFile;
    private final List<String> lines;
    private final Map<Integer, List<List<Character>>> solutionSteps = new HashMap<>();
    private Pair<Integer, Integer> solutions;

    public Day11() throws IOException {
        this.loadData();
        this.lines = Files.lines(Path.of(inputFile.getPath()))
                .map(String::new)
                .collect(Collectors.toList());
    }

    public Pair<Integer, Integer> getSolutions() {
        return solutions;
    }

    @Override
    public void solve() {
        int solution1 = solvePart1();
        int solution2 = solvePart2();
        this.solutions = new Pair<>(solution1, solution2);
        Utils.printSolution(this.getClass().getName(), solution1, solution2);
    }

    private int solvePart1() {
        List<List<Character>> seatsGrid = new ArrayList<>();

        lines.forEach(line -> {
            List<Character> seatsRow = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            seatsGrid.add(seatsRow);
        });
        solutionSteps.put(0, seatsGrid);

        int step = 1;
        while (doStep(solutionSteps.get(step - 1), step)) {
            System.out.println("STEP: " + step);
            step++;
        }

        // . -> floor
        // L -> empty
        // # -> occupied

        // if L and no adjacent = # --> #
        // if # and > 3 adjacent = # -> L
        // if . , nothing

        return (int) solutionSteps.get(step)
                .stream().flatMap(Collection::stream).collect(Collectors.toList())
                .stream().filter(el -> el == '#').count();
    }

    private boolean doStep(List<List<Character>> seatsGrid, final int stepNumber) {
        List<List<Character>> newSeatsGrid = new ArrayList<>();
        for (int i = 0; i < seatsGrid.size(); i++) {
            List<Character> newRow = new ArrayList<>();
            for (int j = 0; j < seatsGrid.get(i).size(); j++) {
                if (seatsGrid.get(i).get(j) == 'L' && !checkAdjacents(seatsGrid, i, j).contains('#')) {
                    newRow.add(j, '#');
                    //seatsGrid.get(i).set(j, '#');
                } else if (seatsGrid.get(i).get(j) == '#' && Collections.frequency(checkAdjacents(seatsGrid, i, j), '#') > 3) {
                    newRow.add(j, 'L');
                    //seatsGrid.get(i).set(j, 'L');
                } else {
                    newRow.add(j, seatsGrid.get(i).get(j));
                }
            }
            newSeatsGrid.add(i, newRow);
        }
        //System.out.println(stepNumber);
        solutionSteps.put(stepNumber, newSeatsGrid);
        return !solutionSteps.get(stepNumber).equals(solutionSteps.get(stepNumber - 1));
    }

    public List<List<Character>> getSolutionsByStep(final int stepNumber) {
        return this.solutionSteps.get(stepNumber);
    }

    private List<Character> checkAdjacents(final List<List<Character>> grid, final int i, final int j) {
        List<Character> listAdjacents = new ArrayList<>();

        int top = (i - 1) < 0 ? i : i - 1;
        int bottom = (i + 1) > grid.size() ? i : i + 1;
        int left = (j - 1) < 0 ? j : j - 1;
        int right = (j + 1) > grid.get(0).size() ? j : j + 1;

        if ((i - 1) >= 0 && (j - 1) >= 0) {
            listAdjacents.add((grid.get(i - 1).get(j - 1))); // top left
        }

        if ((i - 1) >= 0) {
            listAdjacents.add((grid.get(i - 1).get(j))); // top center
        }

        if ((j - 1) >= 0) {
            listAdjacents.add((grid.get(i).get(j - 1))); // left
        }

        if ((j + 1) < grid.get(0).size()) {
            listAdjacents.add((grid.get(i).get(j + 1))); // right
        }
        if ((i + 1) < grid.size()) {
            listAdjacents.add((grid.get(i + 1).get(j))); // bottom center
        }

        if ((j + 1) < grid.get(0).size() && (i + 1) < grid.size()) {
            listAdjacents.add((grid.get(i + 1).get(j + 1))); // bottom right
        }

        if ((j - 1) >= 0 && (i + 1) < grid.size()) {
            listAdjacents.add((grid.get(i + 1).get(j - 1))); // bottom left
        }

        if ((i - 1) >= 0 && (j + 1) < grid.get(0).size()) {
            listAdjacents.add((grid.get(i - 1).get(j + 1))); // top right
        }
        return listAdjacents;
    }

    private int solvePart2() {
        // TODO
        return 0;
    }

    @Override
    public void loadData() {
        this.inputFile = new File(Day11.class.getResource("input_11").getFile());
    }

}
