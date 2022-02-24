package compressor;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

import tool.Token;
import config.Config;

public class LZSS {
    private Config properties;
    private int bufferSize;
    private int tokenSize;

    public LZSS(Config properties) {
        this.bufferSize = properties.getLzssBufferSize();
        this.tokenSize = properties.getLzssTokenSize();
        
    }

    private int indexOfElement(byte[] buffer, byte element, int startingIndex, int stop) {
        for (int i = startingIndex; i < stop; i++) {
            if (buffer[i] == element) {
                return i;
            }
        }
        
        return -1;
    }

    public byte[] encode(String s) {
        byte[] chars = null;
        try{
            chars = s.getBytes("UTF-8");
        } catch (Exception e) {

        }

        byte[] buffer = new byte[chars.length];
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int slide = 0;

        for (int i = 0; i < chars.length; i++) {
            byte c = chars[i];
            
            // if i is more than buffersize, slide is incresed
            // so the search for the matching character starts att slide
            // an buffer size is kept constant
            
            if (i > this.bufferSize) {
                slide++;
            }
            
            // the index of c in the buffer
            int encountered = indexOfElement(buffer, c, slide, i);
            
            // If so, check the next character and prepare a token to be outputted
            if (encountered > 0) {
                // calculate offset
                int index = encountered;
                int offset = i - index;
                // initialize length
                int length = 1;

                // keep checking next characters until no match
                int j = 1;
                while (true) {
                    if (i + j >= chars.length || index + j >= buffer.length - 1) {
                        break;
                    } else {
                        byte nextChar = chars[i + j];
                        byte nextBuffer = buffer[index + j];
                        if (nextChar == nextBuffer) {
                            length++;
                        } else {
                            // here we need to check that length is more than token size
                            // if it is not, start looking for new match from encountered + 1
                            break;
                        }
                        j++;
                    }
                }
                
                // Add all characters from token to buffer
                if (length > this.tokenSize) {
                    // construct a byte token
                    byte[] byteToken = Token.toBytes(offset, length);
                    // append token to bytestream
                    try{
                        bos.write(byteToken);
                    } catch (Exception e) {

                    }
                    for (int k = 0; k < length; k++) {
                        buffer[i + k] = chars[i+k];
                    }
                    i += length -1;
                    
                } else {
                    buffer[i] = c;
                    try{
                        bos.write(c);
                    } catch (Exception e) {

                    }
                }
            } else {
                // If not, add the character to the search buffer and continue
                buffer[i] = c;
                // add to bytestream
                try {
                        bos.write(c);
                } catch (Exception e) {
                        
                }
            }
        }
        
        System.out.println("Original: " + chars.length);
        System.out.println("Encoded: " + bos.size());
        System.out.println("Compression ratio: " + (1.0 * bos.size() / chars.length));
        return bos.toByteArray();
    }

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
                
                i += this.tokenSize; // jump to end of token
            }
        }
        
        byte[] result = new byte[decoded.size()];

        for (int i = 0; i < result.length; i++) {
            result[i] = decoded.get(i);
        }

        return new String(result, StandardCharsets.UTF_8);
    }
} 