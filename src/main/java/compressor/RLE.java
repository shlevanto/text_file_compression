package compressor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;

import tool.Pair;

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
    public byte[] encode(String s) {
        byte[] source = null;

        try {
            source = s.getBytes("UTF-8");
        } catch (Exception e) {

        }
        int size = source.length;

        int[] counts = new int[size];
        byte[] chars = new byte[size];

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
        byte[] finalChars = new byte[charIndex + 1];

        for (int i = 0; i < finalCounts.length; i++) {
            finalCounts[i] = counts[i];
            finalChars[i] = chars[i];
        }

        Pair <byte[], int[]> result = new Pair<byte[], int[]>(finalChars, finalCounts);
        
        return toBytes(result); 
    }

    /**
     * Decodes a pair of char[] and int[] to String.
     * @param encoded Pair<char[], int[]> that is encoded by RLE.encode() method.
     * @return decoded content as string.
     */
    public String decode(byte[] encoded) {
        ArrayList<Byte> decoded = new ArrayList<>();
        
        for (int i = 0; i < encoded.length; i += 2) {
            int count = encoded[i];
            byte c = encoded[i+1];
            
            for (int j = 0; j < count; j++) {
                decoded.add(c);
            }
        }

        byte[] result = new byte[decoded.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = decoded.get(i);
        }

        return new String(result, StandardCharsets.UTF_8);
    }
    
    public byte[] toBytes(Pair<byte[], int[]> pair) {
        byte[] chars = pair.getFirst();
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