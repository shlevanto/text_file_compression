import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;

public class RLETest {
    private RLE rle;
    private FileIO io;

    public RLETest() {
        this.io = new FileIO();
        this.rle = new RLE(this.io);
    }

    @Test 
    public void encodingReturnsPair() {
        String s = "Is this the real life?";
        Pair<char[], int[]> encoded = this.rle.encode(s);

        assertThat(encoded, instanceOf(Pair.class));
    }

    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        String s = "Is this just fantasy?";
        Pair<char[], int[]> encoded = this.rle.encode(s);
        String decoded = this.rle.decode(encoded);
        
        assertEquals(decoded, s);
    }

    @Test 
    public void simpleEncodingCheck() {
        String s = "aaabba";
        Pair<char[], int[]> encoded = this.rle.encode(s);
        char[] charTarget = {'a', 'b', 'a'};
        int[] countTarget = {3, 2, 1};
        
        assertTrue(Arrays.equals(encoded.getFirst(), charTarget));
        assertTrue(Arrays.equals(encoded.getSecond(), countTarget));

    }
}