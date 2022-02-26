import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;

import tool.Pair;

public class PairTest {
    @Test
    public void pairTestArrays() {
        char[] chars = {'a', 'b', 'c'};
        int[] ints = {1,2,3};

        Pair<char[], int[]> pair = new Pair(chars, ints);

        assertArrayEquals(chars, pair.getFirst());
        assertArrayEquals(ints, pair.getSecond());

    }
}
