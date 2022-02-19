import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;

public class LZSS {

    public LZSS() {

    }
    
    public String encode(String s) {
        char[] chars = s.toCharArray();
        ArrayList<Character> buffer = new ArrayList<>();
    
        StringBuilder sb = new StringBuilder();
        

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // Check if it’s seen the character before
            boolean encountered = buffer.contains(c);
            
            // If so, check the next character and prepare a token to be outputted
            if (encountered) {
                // calculate offset
                int index = buffer.indexOf(c);
                int offset = i - index;
                // initialize length
                int length = 1;

                // keep checking next characters until no match
                int j = 1;
                while (true) {
                    if (i + j >= chars.length || index + j >= buffer.size() - 1) {
                        break;
                    } else {
                        char nextChar = chars[i + j];
                        char nextBuffer = buffer.get(index + j);
                        if (nextChar == nextBuffer) {
                            length++;
                        } else {
                            break;
                        }
                        j++;
                    }
                }
                // construct a byte[] token
                String marker = String.valueOf((char) 0);
                String token = marker + offset + "," + length + marker;
                // Add all characters from token to buffer
                if (token.length() < length) {
                    sb.append(token);
                    for (int k = 0; k < length; k++) {
                        buffer.add(chars[i+k]);
                    }
                    i += length -1;
                    
                } else {
                    buffer.add(c);
                    sb.append(c);
                }
            } else {
                // If not, add the character to the search buffer and continue
                buffer.add(c);
                sb.append(c);
            }
        }
        
        System.out.println("Encoded length: " + sb.length());
        System.out.println("String length: " + s.length());
        float compression = ((float) sb.length() / s.length());
        System.out.println("Compression rate: " + compression);      

        return sb.toString();
    }

    
    public String decode(String input) {
        char[] chars = input.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != 0) {
                sb.append(c);
                continue;
            } else {
                // deconstruct token
                StringBuilder token = new StringBuilder();
                int tokenIndex = i+1;


                while (true) {
                    if (chars[tokenIndex] == 0 || tokenIndex >= chars.length - 1) {
                        break;
                    }
                    token.append(chars[tokenIndex]);
                    tokenIndex++;
                }

                String tokenString = token.toString();
                
                String[] tokenParts = tokenString.split(",");
                int offset = Integer.valueOf(tokenParts[0]);
                int length = Integer.valueOf(tokenParts[1]);

                byte[] tokenBytes = tokenToBytes(offset, length);
                int[] tokenFromBytes = bytesToToken(tokenBytes);

                System.out.println("stringtoken: " + offset + "," + length);
                System.out.println("bytetoken: " + Arrays.toString(tokenFromBytes));
          
                String replacement = sb.substring(sb.length() - offset, sb.length() - offset + length);
                sb.append(replacement);
                
                
                i += tokenString.length() + 1;
                
            }

        }
        return sb.toString();
    }

    private byte[] tokenToBytes(int offset, int length) {
        byte[] tokenBytes = new byte[6];

        // tokenBytes[0] = 0 --> marks the beginning of the token
        
        // offset is packed in 3 bytes
        tokenBytes[1] = (byte) ((offset >> 16) & 0xff);
        tokenBytes[2] = (byte) ((offset >> 8) & 0xff);
        tokenBytes[3] = (byte) (offset & 0xff);

        // length is packed in 2 bytes
        tokenBytes[4] = (byte) ((length >> 8) & 0xff);
        tokenBytes[5] = (byte) (length & 0xff);

        return tokenBytes;
    }

    private int[] bytesToToken(byte[] tokenBytes) {
        byte[] offsetBytes = Arrays.copyOfRange(tokenBytes, 1, 4);
        byte[] lengthBytes = Arrays.copyOfRange(tokenBytes, 4, 6);

        int offset = new BigInteger(offsetBytes).intValue();
        int length = new BigInteger(lengthBytes).intValue();

        return new int[]{offset, length};
    }
}
