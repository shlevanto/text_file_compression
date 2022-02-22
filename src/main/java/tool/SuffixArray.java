package tool;

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
     * The suffix array of indexes.
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
        HashMap<String, Integer> originalIndex = new HashMap<>(); 

        // First we store the original index of each suffix
        // into a HashMap.
        for (int i = 0; i < this.suffixes.length; i++) {
            this.suffixes[i] = s;
            originalIndex.put(s, i);
            s = s.substring(1);
        }
        
        // Then we sort the array of suffixes.
        Arrays.sort(this.suffixes);
        
        int[] newSuffixArray = new int[this.input.length()];

        // And finally we go through the sorted suffixes
        // saving the original index of each suffix to the actual
        // suffix array data structure.
        for (int i = 0; i < newSuffixArray.length; i++) {
            newSuffixArray[i] = originalIndex.get(this.suffixes[i]);
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
