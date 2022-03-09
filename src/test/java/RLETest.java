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
        String s = "aaaabba";
        byte[] encoded = rle.encode(s);
        byte[] target = {1, 4, 97, 98, 98, 97};

        assertArrayEquals(encoded, target);
    }

    @Test
    public void noRunsNoEncoding() {
        RLE rle = new RLE();
        String s = "qwertyuiop";
        byte[] encoded = rle.encode(s);
        
        byte[] original = null;
        try{
            original = s.getBytes("UTF-8");
        } catch (Exception e) {

        }

        assertArrayEquals(original, encoded);
    }

    @Test
    public void justRunReturnsThreeBytes() {
        RLE rle = new RLE();
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        byte[] encoded = rle.encode(s);

        assertTrue(encoded.length == 3);
    }

    @Test
    public void longRunWorks() {
        RLE rle = new RLE();
        String s = "b";

        for (int i = 0; i < 130; i++) {
            s += "a";
        }

        byte[] encoded = rle.encode(s);
        String decoded = rle.decode(encoded);
        
        assertEquals(decoded, s);
    }

}