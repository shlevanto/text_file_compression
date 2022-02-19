import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class LZSSTest {
    private LZSS lzss;
    private FileIO io;

    public LZSSTest() {
        io = new FileIO();
        lzss = new LZSS();
    }

    @Test 
    public void encodingWorks() {
        String s = "Is this the real life?";
        byte[] encoded = lzss.encode(s);

        assertTrue(encoded.length > 0);
    }
/*
    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        String s = "Is this just fantasy? Caught in a land slide. No escape from reality.";
        byte[]  encoded = lzss.encode(s);
        String decoded = lzss.decode(encoded);
        
        assertEquals(decoded, s);
    }

    @Test
    public void toByteArrayTest() {
        String s = "aaabba";
        Pair<char[], int[]> encoded = rle.encode(s);

        ArrayList<byte[]> list = rle.toByteArrayList(encoded);

        assertThat(list, instanceOf(ArrayList.class));
        assertThat(list.get(0), instanceOf(byte[].class));
        
    }

    @Test
    public void fromByteArrayTest() {
        byte[] bytes = {0, 0, 0, 2, 2, 1, 97, 98};

        Pair<char[], int[]> fromBytes = rle.fromByteArray(bytes);
        char[] chars = fromBytes.getFirst();
        int[] counts = fromBytes.getSecond();

        assertTrue(Arrays.equals(chars, new char[]{97, 98}));
        assertTrue(Arrays.equals(counts, new int[]{2, 1}));
        
    }
    */
}