import java.util.HashMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LZSS {

    public LZSS() {

    }
    
    public void encode(String s) {
        char[] chars = s.toCharArray();
        System.out.println("String length: " + chars.length);
        //HashMap<Integer, Byte> buffer = new HashMap<>();
        ArrayList<Character> buffer = new ArrayList<>();
        

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            // Check if it’s seen the character before
            boolean encountered = buffer.contains(c);
            
            for (char foo : buffer) {
            //    System.out.print((char) foo);  
            }
            //System.out.println("");
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
                // Add all characters from token to buffer
                for (int k = 0; k < length; k++) {
                    buffer.add(chars[i+k]);
                }
                i += length -1;
                System.out.println(offset + "," + length);
            
            } else {
                // If not, add the character to the search buffer and continue
                System.out.println(c);
                buffer.add(c);
            }
            
            // TODO If the token is longer than the text it’s representing, don’t output a token
            
            
            
        }

    }
}
