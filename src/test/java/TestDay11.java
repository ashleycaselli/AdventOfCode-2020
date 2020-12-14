import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestDay11 {
    private final Map<Integer, List<List<Character>>> gridSolutionsMap = new HashMap<>();

    public TestDay11() throws IOException {
        for (int i = 1; i <= 5; i++) {
            String fileName = "input_11_0" + i;
            List<List<Character>> tmpGrid = new ArrayList<>();
            Files.lines(Path.of(new File(TestDay11.class.getResource(fileName).getFile()).getPath()))
                    .map(String::new)
                    .collect(Collectors.toList())
                    .forEach(line -> {
                        List<Character> seatsRow = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                        tmpGrid.add(seatsRow);
                    });
            gridSolutionsMap.put(i, tmpGrid);
        }
    }


    @Test
    public void testSteps() throws IOException {
        Day11 day11 = new Day11();
        day11.solve();
        for (int i = 1; i <= 5; i++) {
            Assert.assertEquals(day11.getSolutionsByStep(i), gridSolutionsMap.get(i));
        }
        Assert.assertEquals(day11.getSolutions().getFirst().intValue(), 37);
        Assert.assertEquals(day11.getSolutions().getFirst().intValue(), 26);
    }

}
