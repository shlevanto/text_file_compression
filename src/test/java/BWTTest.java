import org.junit.Test;
import static org.junit.Assert.*;

public class BWTTest {
    private BWT bwt;

    public BWTTest() {
        this.bwt = new BWT();
    }

    @Test
    public void simpleEncodeDecodeTest() {
        String s = "Is this the real life?";
        String encoded = bwt.encode(s);
        String decoded = bwt.decode(encoded);
        
        assertEquals(decoded, s);
    }

}
