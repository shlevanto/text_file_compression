public class RLE {

    public Pair<char[], int[]> encode(String s) {
        if (s.equals("")) {
            System.out.println("Can not encode empty file.");
            return null;
        }
        
        int size = s.length();
        char[] source = s.toCharArray();
        
        int[] counts = new int[size];
        char[] chars = new char[size];

        int count = 1;
        int charIndex = 0;
        
        for (int i = 0; i < size; i++) {  
            if (i == size - 1) {
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

        int[] finalCounts = new int[charIndex + 1];
        char[] finalChars = new char[charIndex + 1];

        for (int i = 0; i < finalCounts.length; i++) {
            finalCounts[i] = counts[i];
            finalChars[i] = chars[i];
        }

        Pair<char[], int[]> result = new Pair<>(finalChars, finalCounts);

        return result;
    }

    public String decode(String s, int[] counts) {
        // java stringbuilder here
        // iterate through chars and counts and build the string
        char[] chars = s.toCharArray();
        
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }

    // save encoded to file

}