import java.util.Arrays;
import java.util.HashMap;

/** 
 * This is a naive suffix array, will optimize
*/
public class SuffixArray {
    private String input;
    private String[] suffixes;
    private int[] suffixArray;

    public SuffixArray(String input) {
        this.input = input;
        this.suffixes = new String[input.length()];
        this.suffixArray = create();
    }

    private int[] create() {
        String s = this.input;
        HashMap<String, Integer> a = new HashMap<>(); 

        for (int i = 0; i < this.suffixes.length; i++) {
            this.suffixes[i] = "|" + s;
            a.put("|" + s, i);
            s = s.substring(1);
        }

        Arrays.sort(this.suffixes);

        int[] suffixArray = new int[this.input.length()];

        System.out.println(a);

        System.out.println(suffixArray.length);
        for (int i = 0; i < suffixArray.length; i++) {
            suffixArray[i] = a.get(this.suffixes[i]);
        }
        
        System.out.println(Arrays.toString(suffixArray));
        return suffixArray;
    }

    public int[] get() {
        return this.suffixArray;
    }

}
