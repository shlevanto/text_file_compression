import java.io.IOException;
import java.util.Arrays;

public class BWT {
    private FileIO io;
        
    public BWT (FileIO io) {
        this.io = io;
    }

    public String encode(String s) {
        SuffixArray sa = new SuffixArray(s);
        int[] suffixArray = sa.get();
        char[] c = ("$" + s).toCharArray();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < c.length; i++ ) {    
            sb.append(c[suffixArray[i]]);
        }

        String result = sb.toString();
        result = result.replace("$", "|");

        try {
            io.writeFile(sb.toString(), "fastBWT.txt"); 
        } catch (Exception e) {
            System.out.println("Error in writing file.");
        }
        
        return result;

    }

    public void slowEncode(String inputPath, String outputPath) {
        String s = "";
        
        try {
            s = this.io.readFile(inputPath);
        } catch (IOException e) {
            System.out.println(e);
            return;
        }
        
        if (s.equals("")) {
            System.out.println("Can not encode empty file.");
            return;
        }

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

        try {
            io.writeFile(sb.toString(), outputPath); 
        } catch (Exception e) {
            System.out.println("Error in writing file.");
        }
    }

    public String slowDecode(String inputPath) {
        String content = "";
        
        try {
            content = this.io.readFile(inputPath);
        } catch (IOException e) {
            System.out.println(e);
            return "Error reading file";
        }
        
        char[] toDecode = content.toCharArray();
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
