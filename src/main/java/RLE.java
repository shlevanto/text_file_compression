import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * ?
 */
public class RLE {
    /**
     * FileIO opbject.
     */
    private FileIO io;

    /**
     * Sixe of header for file interactions. Set to 4 by default.
     */
    private int headerSize;
    
    /**
     * Constructor.
     * @param ioParam used for writing and reading files.
     */
    public RLE(FileIO ioParam) {
        this.io = ioParam;
        this.headerSize = 4;

    }

    /**
     * Encodes given file with RLE and writes to given location.
     * 
     * @param s String to encode
     * @return a pair with first object holding charaters 
     * and second object holding run lengths.
     */
    public Pair<char[], int[]> encode(String s) {
        
        int size = s.length();
        char[] source = s.toCharArray();
        
        int[] counts = new int[size];
        char[] chars = new char[size];

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
        char[] finalChars = new char[charIndex + 1];

        for (int i = 0; i < finalCounts.length; i++) {
            finalCounts[i] = counts[i];
            finalChars[i] = chars[i];
        }

        return new Pair<char[], int[]>(finalChars, finalCounts);
    }

    /**
     * Decodes a pair of char[] and int[] to String.
     * @param encoded Pair<char[], int[]> that is encoded by RLE.encode() method.
     * @return decoded content as string.
     */
    public String decode(Pair<char[], int[]> encoded) {
        char[] chars = encoded.getFirst();
        int[] counts = encoded.getSecond();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                result.append(chars[i]);
            }
        }

        return result.toString();
    }

    /**
     * Creates a list of byte arrays for the FileIO class.
     * @param pair the encoded data characters and their counts.
     * @return a list of byte arrays for writing.
     */
    public ArrayList<byte[]> toByteArrayList(Pair<char[], int[]> pair) {
        String content = new String(pair.getFirst());
        int[] counts = pair.getSecond();
        
        byte[] contentBytes = null;
        byte[] countsBytes = new byte[counts.length];

        try {
            contentBytes = content.getBytes("UTF-8");
        } catch (Exception e) {

        }
        for (int i = 0; i < countsBytes.length; i++) {
            countsBytes[i] = (byte) counts[i];
        }
        
        // Construct a 4 byte header that tells us 
        // where the counts stop and the characters begin. 
        // 32 bits should be enough I hope.

        byte[] header = BigInteger.valueOf(countsBytes.length).toByteArray();

        if (header.length < this.headerSize) {
            byte[] temp = new byte[this.headerSize];

            int i = header.length - 1;
            int j = temp.length - 1;

            while (i >= 0) {
                temp[j] = header[i];
                i--;
                j--;
            }

            header = temp;
        }

        ArrayList<byte[]> list = new ArrayList<>();
        list.add(header);
        list.add(countsBytes);
        list.add(contentBytes);

        return list;
        
    }

    /**
     * 
     * @param content read from an RLE encoded file.
     * @return characters and their counts for decode -method.
     */
    public Pair<char[], int[]> fromByteArray(byte[] content) {
        // the header is 4 bytes
        byte[] header = new byte[this.headerSize];

        for (int i = 0; i < header.length; i++) {
            header[i] = content[i];
        }

        BigInteger big = new BigInteger(header);
        int n = big.intValue();


        // so counts start at i = 4 and chars at i = 4 + header
        int[] counts = new int[n];
        
        int countsIndex = 0;
        for (int i = this.headerSize; i < this.headerSize + n; i++) {
            counts[countsIndex] = (int) content[i];
            countsIndex++;
        }

        
        int charsLength = content.length - n - this.headerSize;
        
       

        byte[] bytesToString = new byte[charsLength];
        int charsIndex = 0;
        
        for (int i = n + this.headerSize; i < content.length; i++) {
            bytesToString[charsIndex] = content[i];
            charsIndex++;
        }
        
        
        char[] chars = (new String(bytesToString, StandardCharsets.UTF_8)).toCharArray();
        
        Pair<char[], int[]> result = new Pair<>(chars, counts);
        
        return result;
    } 

}