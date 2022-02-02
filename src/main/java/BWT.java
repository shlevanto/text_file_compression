import java.io.IOException;
import java.util.Arrays;

public class BWT {
    private FileIO io;
        
    public BWT (FileIO io) {
        this.io = io;
    }

    public String slowEncode(String s) {
        /*String s = "";
        
        try {
            s = this.io.readFile(inputPath);
        } catch (IOException e) {
            System.out.println(e);
            return s;
        }
        
        if (s.equals("")) {
            System.out.println("Can not encode empty file.");
            return s;
        }
*/
        String toEncode = (s + "|");

        String[] t = new String[toEncode.length()];

        t[0] = toEncode;
        for (int i = 1; i < t.length; i++) {
            toEncode = rotate(toEncode);
            t[i] = toEncode;   
        }

        Arrays.sort(t);

        StringBuilder sb = new StringBuilder();

        for (String x : t) {
            char[] c = x.toCharArray();
            sb.append(c[c.length - 1]);
        }

        return sb.toString();
    }

    public String slowDecode(String s) {
        char[] toDecode = s.toCharArray();
        String[] decoded = new String[toDecode.length];

        for (int i = 0; i < decoded.length; i++) {
            for (int j = 0; j < decoded.length; j++) {
                if (decoded[j] == null) {
                    decoded[j] = "" + toDecode[j];
                }
                
                decoded[j] = toDecode[j] + decoded[j];
            }
            Arrays.sort(decoded);
        }

        String result = "";

        for (String x : decoded) {
            char[] chars = x.toCharArray();
            if (chars[chars.length - 1] == '|') {
                result = x;
            }
        }


        return result.replace("|", "");

    }
    
    public String rotate(String s) {
        char[] a = s.toCharArray();
        char[] b = new char[a.length];

        b[0] = a[a.length - 1];

        int j = 0;

        for (int i = 1; i < a.length; i++) {
            b[i] = a[j];
            j++;
        }

        return new String(b);

    }
}
