package compressor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import tool.Token;
import config.Config;

public class LZSS {
    /**
     * Properties contains bufferSize and tokenSize.
     */
    private Config properties;
    /**
     * Size of the sliding window buffer that matching 
     * runs of bytes are searched in.
     */
    private int bufferSize;
    /**
     * Size of the token that information is stored in.
     */
    private int tokenSize;

    public LZSS(Config properties) {
        this.bufferSize = properties.getLzssBufferSize();
        this.tokenSize = properties.getLzssTokenSize();
    }

    private int indexOfElement(byte[] buffer, byte element, int startingIndex, int stop) {
        if (startingIndex >= stop) {
            return -1;
        }
        for (int i = startingIndex; i < stop; i++) {
            if (buffer[i] == element) {
                return i;
            }
        }
        return -1;
    }

    private int[] match(byte[] chars, byte[] buffer, byte c, int startingIndex, int i, int bestLength, int[] token) {
        // find index of matching character
        int index = indexOfElement(buffer, c, startingIndex, i);
        
        if (index < 0) {
            return token;
        }

        int offset = i - index;
        int length = 1;
        
        // keep checking next characters for as long as they match
        int j = 1;
        while (true) {
            if (i + j >= chars.length || index + j >= i) {
                return token;
            }

            byte nextChar = chars[i + j];
            byte nextBuffer = buffer[index + j];

            if (nextChar == nextBuffer) {
                length++;
                j++;
                continue;
            } 
            
            if (length > bestLength) {
                bestLength = length;
                // if the match to be replaced is longer than
                // the token replacing it, make a token
                token[0] = offset;
                token[1] = length;
            }   
            
            // run a recursion to find an even better match
            return match(chars, buffer, c, index + 1, i, bestLength, token); 
                
            
        }

    }

    /**
     * Encodes UTF-8 String to byte array.
     * @param s UTF-8 string to encode
     * @return encoded byte array.
     */
    public byte[] encode(String s) {
        byte[] chars = null;
        try {
            chars = s.getBytes("UTF-8");
        } catch (Exception e) {

        }

        byte[] buffer = new byte[chars.length];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int slide = 0;

        for (int i = 0; i < chars.length; i++) {
            byte c = chars[i];
        
            // if i is more than buffersize, slide is increased
            // so the search for the matching character starts at slide
            // and buffer size is kept constant    
            if (i > this.bufferSize) {
                slide++;
            }
            
            // find a matching character sequence
            int[] token = match(chars, buffer, c, slide, i, this.tokenSize, new int[2]);

            if (token == null || token[0] == 0) {
                buffer[i] = c;
                    try {
                        bos.write(c);
                    } catch (Exception e) {

                    }
            } else {
                // Get offset and length
                int offset = token[0];
                int length = token[1];
                
                // Construct token
                byte[] byteToken = Token.toBytes(offset, length);
                
                // append token to bytestream
                try {
                    bos.write(byteToken);
                } catch (Exception e) {

                }
                
                // write tokenized characters to buffer
                for (int k = 0; k < length; k++) {
                    buffer[i + k] = chars[i + k];
                }
                
                // jump over tokenized part and continue
                i += length - 1;
                slide += length - 1;
                } 
            }
        
        return bos.toByteArray();
    }

    /**
     * Decodes a byte array with bytes and tokens back to a string.
     * @param input LZSS encoded byte array
     * @return decoded String.
     */
    public String decode(byte[] input) {
        // Here we use ArrayList instead of Stream, because
        // token deconstruction is more efficient and neat
        // using sublists.
        ArrayList<Byte> decoded = new ArrayList<>();

        for (int i = 0; i < input.length; i++) {
            byte b = input[i];
                       
            if (b != 0) {    
                decoded.add(b);
                continue;
            } else {
                // deconstruct token
                byte[] tokenBytes = Arrays.copyOfRange(input, i, i + this.tokenSize + 1);
                int[] tokenInts = Token.fromBytes(tokenBytes);
                int offset = tokenInts[0];
                int length = tokenInts[1];
                
                // get bytes indicated by token
                List<Byte> replacement = decoded.subList(decoded.size() - offset, decoded.size() - offset + length);
                
                // add bytes to decoded
                decoded.addAll(replacement);
                
                i += this.tokenSize - 1; // jump to end of token
            }
        }
        
        byte[] result = new byte[decoded.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = decoded.get(i);
        }

        return new String(result, StandardCharsets.UTF_8);
    }
    
} 