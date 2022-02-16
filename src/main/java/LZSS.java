import java.util.HashMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LZSS {

    public LZSS() {

    }
    
    public void encode(String s) {
        byte[] bytes = s.getBytes();

        //HashMap<Integer, Byte> buffer = new HashMap<>();
        ArrayList<Byte> buffer = new ArrayList<>();

        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            // Check if it’s seen the character before
            boolean encountered = buffer.contains(b);

            // If so, check the next character and prepare a token to be outputted
            if (encountered) {
                // calculate offset
                int index = buffer.indexOf(b);
                int offset = i - index;
                // initialize length
                int length = 1;

                // keep checking next characters until no match
                int j = 1;
                while (true) {
                    if (i + j >= bytes.length) {
                        break;
                    } else {
                        byte nextByte = bytes[i + j];
                        byte nextBuffer = buffer.get(index + j);
                        if (nextByte == nextBuffer) {
                            length++;
                        } else {
                            break;
                        }
                        j++;
                    }
                }
                
                i += length - 1;
                System.out.println(offset + "," + length);
            
            } else {
                System.out.println((char) b);
            }
            // If the token is longer than the text it’s representing, don’t output a token
            // Add the text to the search buffer and continue
            // If not, add the character to the search buffer and continue
            buffer.add(b);
        }

    }
}
