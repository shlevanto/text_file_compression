import org.junit.Test;
import static org.junit.Assert.*;


public class BWTTest {
    private BWT bwt;

    public BWTTest() {
        this.bwt = new BWT();
    }

    @Test 
    public void encodedIsNotOriginalInputTest() {
        String s = "Is this the real life?";
        String encoded = bwt.encode(s);

        assertFalse(s.equals(encoded));
    }

    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        String s = "Is this just fantasy?";
        String encoded = bwt.encode(s);
        String decoded = bwt.decode(encoded);
        
        assertEquals(decoded, s);
    }

    @Test 
    public void simpleEncodingCheck() {
        String s = "banana";
        String encoded = bwt.encode(s);
        char[] encodedByHand = {97,110,110,98,0,97,97};
        String encodedShouldBe = new String(encodedByHand);
        assertEquals(encoded, encodedShouldBe);
    }

}
