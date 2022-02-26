import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.ArrayList;

import compressor.RLE;
import tool.Pair;

public class RLETest {

    @Test 
    public void encodingHappens() {
        RLE rle = new RLE();        
        String s = "Is this the real life?";
        byte[] encoded = rle.encode(s);

        assertTrue(encoded.length > 1);
    }

    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        RLE rle = new RLE();
        String s = "Is this just fantasy?";
        byte[] encoded = rle.encode(s);
        String decoded = rle.decode(encoded);
        
        assertEquals(decoded, s);
    }

    @Test 
    public void simpleEncodingTest() {
        RLE rle = new RLE();
        String s = "aaabba";
        byte[] encoded = rle.encode(s);
        byte[] target = {3, 97, 2, 98, 1, 97};

        assertArrayEquals(encoded, target);
    }

}