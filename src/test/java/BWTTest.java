import org.junit.Test;
import static org.junit.Assert.*;

import compressor.BWT;

public class BWTTest {
    private BWT bwt;

    public BWTTest() {
        this.bwt = new BWT();
    }

    @Test 
    public void encodedIsNotOriginalInputTest() {
        String s = "Is this the real life?";
        String encoded = bwt.transform(s);

        assertFalse(s.equals(encoded));
    }

    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        String s = "Is this just fantasy?";
        String encoded = bwt.transform(s);
        String decoded = bwt.restore(encoded);
        
        assertEquals(decoded, s);
    }

    @Test 
    public void simpleEncodingCheck() {
        String s = "banana";
        String encoded = bwt.transform(s);
        char[] encodedByHand = {97,110,110,98,0,97,97};
        String encodedShouldBe = new String(encodedByHand);
        assertEquals(encoded, encodedShouldBe);
    }

}
