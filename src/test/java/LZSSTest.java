import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;

import compressor.LZSS;
import config.Config;


public class LZSSTest {


    @Test 
    public void encodingHappensTest() {
        LZSS lzss = new LZSS(new Config());
        String s = "Is this the real life?";
        byte[] encoded = lzss.encode(s);

        assertTrue(encoded.length > 0);
    }

    @Test
    public void tokenizedIsShorterThanOriginal() {
        LZSS lzss = new LZSS(new Config());
        String s = "Samsung is the phone for a Samsung man that has the phone";
        byte[] encoded = lzss.encode(s);
        String decoded = lzss.decode(encoded);

        assertTrue(decoded.equals(s));
        assertTrue(s.length() > encoded.length);
    }
    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        LZSS lzss = new LZSS(new Config());
        String s = "Is this just fantasy? Caught in a land slide. No escape from reality.";
        byte[]  encoded = lzss.encode(s);
        String decoded = lzss.decode(encoded);
        
        assertEquals(decoded, s);
    }
}