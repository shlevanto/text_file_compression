package tool;

import java.util.Arrays;
import java.math.BigInteger;

/**
 * Class for constructing and deconstructing the LZSS tokens.
 * No constructors, only static methods.
 */
public class Token {
    
    /**
     * 
     * @param offset The number indicating how far back in the search buffer the matching string is found.
     * @param length Length of the matching string.
     * @return A 6 byte array containing the token. 
     * The first byte is a marker that the token starts, 
     * 3 bytes for offset and 2 for length.
     */
    public static byte[] toBytes(int offset, int length) {
        byte[] tokenBytes = new byte[5];
   
        // offset is packed in 2 bytes
        tokenBytes[1] = (byte) ((offset >> 8) & 0xff);
        tokenBytes[2] = (byte) (offset & 0xff);

        // length is packed in 2 bytes
        tokenBytes[3] = (byte) ((length >> 8) & 0xff);
        tokenBytes[4] = (byte) (length & 0xff);

        return tokenBytes;
    }

    /**
     * 
     * @param tokenBytes The token in a byte array format.
     * @return An array [offset, length].
     */
    public static int[] fromBytes(byte[] tokenBytes) {
        byte[] offsetBytes = Arrays.copyOfRange(tokenBytes, 1, 3);
        byte[] lengthBytes = Arrays.copyOfRange(tokenBytes, 3, 5);

        int offset = new BigInteger(offsetBytes).intValue();
        int length = new BigInteger(lengthBytes).intValue();

        return new int[]{offset, length};
    }
}
