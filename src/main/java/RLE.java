import java.util.Arrays;

public class RLE {
    private String s;
    private int size;

    public RLE(String s){
        this.s = s;
        this.size = s.length();
    }

    public void encode() {
        if (this.s.equals("")) {
            System.out.println("Can not encode empty string.");
            return;
        }
        
        char[] source = this.s.toCharArray();
        System.out.println(source);

        int[] counts = new int[this.size];
        char[] chars = new char[this.size];

        int count = 1;
        int charIndex = 0;
        
        for (int i = 0; i < this.size; i++) {  
            if (i == this.size - 1) {
                // increase the count of counts[i]
                counts[charIndex] = count;
                chars[charIndex] = source[i];
                break;
            }
            
            if (source[i] == source[i+1]) {
                chars[charIndex] = source[i];
                count++;
                continue;
            }
            
            chars[charIndex] = source[i];
            counts[charIndex] = count;

            count = 1;
            charIndex++;
        }

        int[] finalCount = new int[charIndex + 1];

        for (int i = 0; i < finalCount.length; i++) {
            finalCount[i] = counts[i];
        }

        System.out.println(chars);
        System.out.println(Arrays.toString(finalCount));

        for (int i = 0; i <= charIndex; i++) {
            System.out.println(chars[i] + "/" + Integer.toString(finalCount[i]));
        }
    }

    // decode

    // save encoded to file

}