import org.junit.Test;
import static org.junit.Assert.*;

import compressor.BWTRLE;

import config.Config;


public class BWTRLETest {


    @Test 
    public void encodingHappensTest() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "Is this the real life?";
        byte[] encoded = bwtrle.encode(s);

        assertTrue(encoded.length > 0);
    }

    @Test
    public void encodedIsShorterThanOriginal() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "aaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbb";
        byte[] encoded = bwtrle.encode(s);
        String decoded = bwtrle.decode(encoded);

        assertTrue(decoded.equals(s));
        assertTrue(s.length() > encoded.length);
    }
    
    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        BWTRLE bwtrle = new BWTRLE(new Config());
        String s = "Is this just fantasy? Caught in a land slide. No escape from reality.";
        byte[]  encoded = bwtrle.encode(s);
        String decoded = bwtrle.decode(encoded);
        
        assertEquals(decoded, s);
    }
 
}