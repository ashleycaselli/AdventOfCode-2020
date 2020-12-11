import kotlin.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay10 {

    @Test
    public void testJolts() throws IOException {
        Day10 day10 = new Day10();
        day10.solve();
        Pair<Integer, Integer> solution1 = day10.getSolutionOnePair();
        Assert.assertEquals(solution1.component1().intValue(), 22);
        Assert.assertEquals(solution1.component2().intValue(), 10);
    }

}
