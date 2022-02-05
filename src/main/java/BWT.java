
import java.util.Arrays;
import java.util.HashMap;

public class BWT {

    public String encode(String s) {
        char first = 0;
        SuffixArray sa = new SuffixArray(s);
        int[] suffixArray = sa.get();
        char[] c = (first + s).toCharArray();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < c.length; i++ ) {    
            sb.append(c[suffixArray[i]]);
        }

        String result = sb.toString();
        
        return result;
    }

    public String decode(String s) {
        char[] f = s.toCharArray();
        Arrays.sort(f);
        HashMap<Character, Integer> fCounts = new HashMap<>();
        HashMap<String, Integer> fMap = new HashMap<>();

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
        
        char[] l = s.toCharArray();
        int[] occurance = new int[l.length];
        HashMap<Character, Integer> lCounts = new HashMap<>();

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

        char[] decoded = new char[l.length];
        int decoderIndex = decoded.length - 1; 
        StringBuilder sb = new StringBuilder();

        sb.append(l[0]);
        decoded[decoderIndex] = l[0];
        decoderIndex--;
        
        int counter = 0;

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

        return result.substring(1);

    }
}
