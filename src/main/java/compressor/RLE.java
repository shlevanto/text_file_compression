package compressor;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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

        Pair<byte[], int[]> result = new Pair<byte[], int[]>(finalChars, finalCounts);
        
        return toBytes(result); 
    }

    /**
     * Decodes an encoded byte[] to String.
     * @param encoded Pair<char[], int[]> that is encoded by RLE.encode() method.
     * @return decoded content as string.
     */
    public String decode(byte[] encoded) {
        ArrayList<Byte> decoded = new ArrayList<>();
        
        for (int i = 0; i < encoded.length; i++) {
            if (encoded[i] == 1) {
                int count = encoded[i + 1];
                byte c = encoded[i + 2];
                for (int j = 0; j < count; j++) {
                    decoded.add(c);
                }
                i += 2; // jump over the 3 bytes that hold the run 
            } else {
                decoded.add(encoded[i]);
            }
        }

        byte[] result = new byte[decoded.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = decoded.get(i);
        }

        return new String(result, StandardCharsets.UTF_8);
    }
    
    /**
     * Converts the encoding to a single byte array.
     * @param pair the pair of bytes and counts to convert
     * @return a single byte array
     */
    private byte[] toBytes(Pair<byte[], int[]> pair) {
        byte[] chars = pair.getFirst();
        int[] counts = pair.getSecond();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        for (int i = 0; i < chars.length; i++) {
            
            //if there is a run longer than 3,
            //put a marker + counts + byte that is repeated

            if (counts[i] > 3) {
                try {
                    bos.write(1);
                    bos.write(counts[i]);
                    bos.write(chars[i]);
                } catch (Exception e) {
                    //TODO: handle exception
                }
                continue;
            }
            
            // otherwise just write the character(s)
            for (int j = 0; j < counts[i]; j++) {
                try {
                    bos.write(chars[i]);
                } catch (Exception e) {

                }
            }
        }
        
        try {
            bos.close();    
        } catch (Exception e) {

        }
        
        return bos.toByteArray();
    }

}