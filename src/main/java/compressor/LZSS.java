package compressor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    private int[] match(byte[] chars, byte[] buffer, byte c, int startingIndex, int i) {
        int index = indexOfElement(buffer, c, startingIndex, i);
        //System.out.println("Find match for index: " + i);
        //System.out.println("Looking from " + slide + " to " + i);
        //System.out.println("Searching for: " + (char) c ); 
        if (index < 0) {
            //System.out.println("Match not found.");
            return null;
        }

        int offset = i - index;
        int length = 1;
        
        // keep checking next characters until no match
        int j = 1;
        while (true) {
            if(i + j >= chars.length || index + j >= i) {
                return null;
            }
            //System.out.println("Does next match?");
            byte nextChar = chars[i + j];
            byte nextBuffer = buffer[index + j];

            if (nextChar == nextBuffer) {
                //System.out.println("Increasing length.");
                length++;
                j++;
                continue;
            } else if (length < this.tokenSize) {
                // This is causing some problems...
                //System.out.println("Search again from " + index + 1);
                //match(chars, buffer, c, index + 1, i); 
                return null;
            } else {
                //System.out.println("Making token");
                int[] token = {offset, length};
                return token;
            }
            //j++;
        }
        
    }

    public byte[] encode(String s) {
        byte[] chars = null;
        try{
            chars = s.getBytes("UTF-8");
        } catch (Exception e) {

        }

        // System.out.println(Arrays.toString(chars));
        byte[] buffer = new byte[chars.length];
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int slide = 0;

        for (int i = 0; i < chars.length; i++) {
            byte c = chars[i];
            // if i is more than buffersize, slide is increased
            // so the search for the matching character starts att slide
            // an buffer size is kept constant
            
            if (i > this.bufferSize) {
                slide++;
            }
            
            int[] token = match(chars, buffer, c, slide, i);

            if (token == null) {
                buffer[i] = c;
                    try{
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
                try{
                    bos.write(byteToken);
                } catch (Exception e) {

                }
                
                // write token to buffer
                for (int k = 0; k < length; k++) {
                    buffer[i + k] = chars[i + k];
                }
                
                // jump over tokenized part
                i += length - 1;
                    
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