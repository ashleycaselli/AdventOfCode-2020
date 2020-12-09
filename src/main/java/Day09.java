import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day09 implements Day {

    private final List<BigInteger> lines;
    private static final int PREAMBLE_LENGTH = 25;
    private File inputFile;

    public Day09() throws IOException {
        this.loadData();
        this.lines = Files.lines(Path.of(inputFile.getPath()))
                .map(BigInteger::new)
                .collect(Collectors.toList());
    }

    @Override
    public void solve() {
        BigInteger solution1 = this.solvePart1();
        BigInteger solution2 = this.solvePart2(solution1);
        Utils.printSolution(this.getClass().getName(), solution1, solution2);
    }

    @Override
    public void loadData() {
        this.inputFile = new File(Day09.class.getResource("input_09").getFile());
    }

    private BigInteger solvePart1() {
        int i = 0;
        int topIndex = PREAMBLE_LENGTH;
        boolean found = false;
        while (topIndex < lines.size() && !found) {
            Set<BigInteger> els = IntStream.range(i, topIndex)
                    .mapToObj(lines::get)
                    .collect(Collectors.toSet());

            BigInteger nextNumber = lines.get(topIndex);
            //Logger.getAnonymousLogger().info("26th number: " + nextNumber);

            Optional<BigInteger> res = els.stream().filter(el -> {
                BigInteger diff = nextNumber.subtract(el).abs();
                //Logger.getAnonymousLogger().info("YES: " + nextNumber + " = " + el + " + " + diff);
                return els.contains(diff);
            }).findFirst();

            if (res.isEmpty()) {
                //Logger.getAnonymousLogger().info("NO, index = " + i + " and topIndex = " + topIndex);
                found = true;
            } else {
                topIndex += 1;
                i = topIndex - PREAMBLE_LENGTH;
            }
        }
        return lines.get(topIndex);
    }

    private BigInteger solvePart2(final BigInteger solution) {
        int i = 0, j;
        boolean found = false;
        List<BigInteger> subList = new ArrayList<>();

        for (; i < lines.size() && !found; i++) {
            BigInteger partialSum = BigInteger.valueOf(0);

            for (j = i; j < lines.size() && !found; j++) {
                partialSum = partialSum.add(lines.get(j));

                if (partialSum.equals(solution)) {
                    subList = IntStream.range(i, j + 1)
                            .mapToObj(lines::get)
                            .collect(Collectors.toList());
                    found = true;
                }
            }
        }
        return Collections.max(subList).add(Collections.min(subList));
    }
}
