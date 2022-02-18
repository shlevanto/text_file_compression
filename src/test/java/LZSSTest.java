import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.Arrays;
import java.util.ArrayList;

public class LZSSTest {
    private LZSS rle;
    private FileIO io;

    public LZSSTest() {
        io = new FileIO();
        lzss = new LZSS(io);
    }

    @Test 
    public void encodingReturnsPair() {
        String s = "Is this the real life?";
        Pair<ArrayList<Character>, ArrayDeque<Pair>> encoded = lzss.encode(s);

        assertThat(encoded, instanceOf(Pair.class));
    }
/*
    @Test
    public void encodeDecodeMatchesOriginalInputTest() {
        String s = "Is this just fantasy?";
        Pair<char[], int[]> encoded = rle.encode(s);
        String decoded = rle.decode(encoded);
        
        assertEquals(decoded, s);
    }

    @Test 
    public void simpleEncodingTest() {
        String s = "aaabba";
        Pair<char[], int[]> encoded = rle.encode(s);
        char[] charTarget = {'a', 'b', 'a'};
        int[] countTarget = {3, 2, 1};
        
        assertTrue(Arrays.equals(encoded.getFirst(), charTarget));
        assertTrue(Arrays.equals(encoded.getSecond(), countTarget));

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


*/
        
    }
}