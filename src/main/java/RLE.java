import java.io.FileWriter;
/**
 * Run length encoding (RLE) of given file.
 */
public class RLE {
    private FileIO io;

    /**
     * 
     * @param io a FileIO object that handles interactions with files.
     */
    public RLE (FileIO io) {
        this.io = io;
    }

    /**
     * Encodes given file with RLE and writes to given location.
     * 
     * @param inputPath path of file to be encoded.
     * @param outputPath path to save the encoded file to.
     */
    public void encode(String inputPath, String outputPath) {
        String s = this.io.readFile(inputPath);
        
        if (s.equals("")) {
            System.out.println("Can not encode empty file.");
            return;
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

        writeEncoded(outputPath, finalChars, finalCounts);
    }

    /**
     * Decodes a file that was created with the decode() method.
     * 
     * @param path path of file to decode.
     * @return string with the content of the decoding. 
     * This will be changed to file write at some point.
     */
    public String decode(String path) {
        Pair<String, int[]> encoded = readEncoded(path);
        
        String s = encoded.getFirst();
        int[] counts = encoded.getSecond();

        char[] chars = s.toCharArray();
        
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }

    /**
     * Helper function for specifically reading a file created with the encode() method.
     * @param path is the path of the encoded file.
     * @return Pair with the characters and their counts in two separate arrays.
     */

    private Pair<String, int[]> readEncoded(String path) {
        String content = io.readFile(path);
        
        String[] parts = content.split("--");

        String encodedContent = parts[0];
    
        String encodedCountStrings = parts[1].trim();
        String[] encodedCountArray = encodedCountStrings.split(",");
            
        int[] encodedCounts = new int[encodedContent.length()];
            
        for (int i = 0; i < encodedCounts.length; i++) {
            encodedCounts[i] = Integer.valueOf(encodedCountArray[i]);
        }

        return new Pair<>(encodedContent, encodedCounts);
    }

    /**
     * Helper method for encode(), writes the encoded file.
     * @param outputPath the path of the output file.
     * @param chars the encoded characters array.
     * @param counts the character counts array.
     */
    private void writeEncoded(String outputPath, char[] chars, int[] counts) {
        try {
            FileWriter writer = new FileWriter(outputPath);
            writer.write(chars); 
            
            writer.write("--\n");
            for (int i : counts) {
                writer.write(Integer.toString(i) + ",");
            }
            writer.close();
        } catch (Exception e) {

        }
    }
}