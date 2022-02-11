import java.util.Arrays;
import java.util.HashMap;

/**
 * This class implements a simple suffix array.
 */
public class SuffixArray {
    /**
     * The input string from BWT.
     */
    private String input;
    /**
     * An array of the suffix strings.
     */
    private String[] suffixes;
    /**
     * The suffix array indexes.
     */
    private int[] suffixArray;

    public SuffixArray(String inputBWT) {
        char last = 0;
        this.input = inputBWT + last;
        this.suffixes = new String[this.input.length()];
        this.suffixArray = create();
    }

    private int[] create() {
        String s = this.input;
        HashMap<String, Integer> a = new HashMap<>(); 

        for (int i = 0; i < this.suffixes.length; i++) {
            this.suffixes[i] = s;
            a.put(s, i);
            s = s.substring(1);
        }
    
        Arrays.sort(this.suffixes);
        
        int[] newSuffixArray = new int[this.input.length()];

        for (int i = 0; i < newSuffixArray.length; i++) {
            newSuffixArray[i] = a.get(this.suffixes[i]);
        }
        
    
        return newSuffixArray;
    }

    /**
     * Returns the sorted suffix array.
     * @return indexes of the sorted suffix array.
     */
    public int[] getSuffixArray() {
        return this.suffixArray;
    }

}
