import java.util.Arrays;
import java.util.HashMap;

/**
 * Implements Burrows-Wheeler Transformation for text / string input.
 * The algorithm is explained in more detail in implementation document.
 */
public class BWT {
    /**
     * Burrows-Wheeler encoding.
     * @param s String to be encoded.
     * @return Encoded string.
     */
    public String encode(String s) {
        char first = 0;
        
        // The encoding is based on a sorted suffix array
        // created and sorted in the SuffixArray class.
        SuffixArray sa = new SuffixArray(s);
        int[] suffixArray = sa.getSuffixArray();
        char[] c = (first + s).toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < c.length; i++) {    
            sb.append(c[suffixArray[i]]);
        }

        String result = sb.toString();
        
        return result;
    }
    /**
     * Burrows-wheeler decoding using LF mapping algorithm.
     * @param s Encoded string.
     * @return Decoded string.
     */
    public String decode(String s) {
        char[] f = s.toCharArray();
        // In theory BWT makes an array of all the rotations of
        // a string and then sorts the array. The encoded string is
        // the last letters of each rotation in this sorted array.
        // For decoding, we know that the first letters of this array is 
        // the characters of the original string in sorted order. 
        Arrays.sort(f);
        
        // Here we keep the counts and orders of the characters.
        HashMap<Character, Integer> fCounts = new HashMap<>();
        HashMap<String, Integer> fMap = new HashMap<>();

        // LF mapping needs to know how many of each character
        // there is and what their order is. We use HashMaps for this.
        for (int i = 0; i < f.length; i++) {
            char key = f[i];
            int count = 1;
            if (fCounts.containsKey(key)) {
                count = fCounts.get(key);
                count++;
            } 
            fCounts.put(key, count);

            String indexed = key + String.valueOf(count);
            fMap.put(indexed, i);
        }
        
        // L is the encoded string that we want to decode.
        char[] l = s.toCharArray();
        int[] occurance = new int[l.length];
        HashMap<Character, Integer> lCounts = new HashMap<>();

        // Here we count the occurance and order of each character
        // in L.
        for (int i = 0; i < l.length; i++) {
            char key = l[i];
            int count = 1;
            if (lCounts.containsKey(key)) {
                count = lCounts.get(key);
                count++;
            } 
            lCounts.put(key, count);
            occurance[i] = count;
        }

        // And then we build the decoded string.
        char[] decoded = new char[l.length];
        int decoderIndex = decoded.length - 1; 
        StringBuilder sb = new StringBuilder();

        sb.append(l[0]);
        decoded[decoderIndex] = l[0];
        decoderIndex--;
        
        int counter = 0;

        // We build the decoded string until we get to the indicator char = 0
        // that marks the end of the string.
        while (true) {
            String key = l[counter] + String.valueOf(occurance[counter]);
            
            char stopper = 0;
            if (key.equals(stopper + "1")) {
                break;
            }
            
            counter = fMap.get(key);
            sb.append(l[counter]);
            decoded[decoderIndex] = l[counter];
            decoderIndex--;
                        
        }
        
        String result = String.valueOf(decoded);

        // We leave out the stopper character 
        // when returning the decoded string.
        return result.substring(1);

    }
}
