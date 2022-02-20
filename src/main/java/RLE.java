import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

/**
 * ?
 */
public class RLE {


    /**
     * Encodes given file with RLE and writes to given location.
     * 
     * @param s String to encode
     * @return a pair with first object holding charaters 
     * and second object holding run lengths.
     */
    public Pair<char[], int[]> encode(String s) {
        
        int size = s.length();
        char[] source = s.toCharArray();
        
        int[] counts = new int[size];
        char[] chars = new char[size];

        int count = 1;
        int charIndex = 0;
        
        for (int i = 0; i < size; i++) {  
            if (i == size - 1) {
                counts[charIndex] = count;
                chars[charIndex] = source[i];
                break;
            }
            
            if (source[i] == source[i + 1]) {
                chars[charIndex] = source[i];
                count++;
                continue;
            }
            
            chars[charIndex] = source[i];
            counts[charIndex] = count;

            count = 1;
            charIndex++;
        }

        int[] finalCounts = new int[charIndex + 1];
        char[] finalChars = new char[charIndex + 1];

        for (int i = 0; i < finalCounts.length; i++) {
            finalCounts[i] = counts[i];
            finalChars[i] = chars[i];
        }

        return new Pair<char[], int[]>(finalChars, finalCounts);
    }

    /**
     * Decodes a pair of char[] and int[] to String.
     * @param encoded Pair<char[], int[]> that is encoded by RLE.encode() method.
     * @return decoded content as string.
     */
    public String decode(Pair<char[], int[]> encoded) {
        char[] chars = encoded.getFirst();
        int[] counts = encoded.getSecond();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }
    
    public byte[] toBytes(Pair<char[], int[]> pair) {
        char[] chars = pair.getFirst();
        int[] counts = pair.getSecond();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        for (int i = 0; i < chars.length; i++) {
            try {
                bos.write(counts[i]);
                bos.write(chars[i]);
            } catch (Exception e) {

            }   
        }
        
        try {
            bos.close();    
        } catch (Exception e) {

        }
        

        return bos.toByteArray();
        
    }

    public Pair<char[], int[]> fromBytes(byte[] input) {
        char[] chars = new char[input.length / 2];
        int[] counts = new int[input.length / 2];

        for (int i = 0; i < input.length; i += 2) {
            counts[i/2] = input[i];
            chars[i/2] = (char) input[i+1];
        }

        return new Pair(chars, counts);
    }

}