import java.util.HashMap;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class LZSS {

    public LZSS() {

    }
    
    public Pair<ArrayList<Character>, ArrayDeque<Pair>> encode(String s) {
        char[] chars = s.toCharArray();
        System.out.println("String length: " + chars.length);
        ArrayList<Character> buffer = new ArrayList<>();
        ArrayList<Character> encoded = new ArrayList<>();
        ArrayDeque<Pair<Integer, Integer>> tokens = new ArrayDeque<>();

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
                encoded.add((char) 0);
                tokens.add(new Pair(offset, length));
            
            } else {
                // If not, add the character to the search buffer and continue
                encoded.add(c);
                buffer.add(c);
            }
            
            // TODO If the token is longer than the text it’s representing, don’t output a token
            
            
        }
        System.out.println("***");
        return new Pair(encoded, tokens);       
    }

    public String decode(Pair<ArrayList<Character>, ArrayDeque<Pair>> input) {
        ArrayList<Character> chars = input.getFirst();
        ArrayDeque<Pair> tokens = input.getSecond();
        ArrayList<Character> decoded = new ArrayList();
        int decodedIndex = decoded.size();

        for (int i = 0; i < chars.size(); i++) {
            char c = chars.get(i);
            if (c == 0) {
                Pair<Integer, Integer> token = tokens.pop();
                int offset = token.getFirst();
                int length = token.getSecond();

                // deconstruct token
                int j = 0;
                while (j < length) {
                    char toAdd = decoded.get(decodedIndex - offset + j);
                    decoded.add(toAdd);
                    j++;
                }
                decodedIndex += length;
                
                
            } else {
                decoded.add(c);
                decodedIndex++;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : decoded) {
            sb.append(c);
        }
        return sb.toString();
    }
}
