import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class BWT {
    private FileIO io;
        
    public BWT (FileIO io) {
        this.io = io;
    }

    public String encode(String s) {
        SuffixArray sa = new SuffixArray(s);
        int[] suffixArray = sa.get();
        char[] c = ("$" + s).toCharArray();
        System.out.println(Arrays.toString(c));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < c.length; i++ ) {    
            sb.append(c[suffixArray[i]]);
        }

        String result = sb.toString();
        //result = result.replace("$", "|");

        // write to file here
        
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
    
    private String rotate(String s) {
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

    public String decodeLFMap(String s) {
        char[] f = s.toCharArray();
        Arrays.sort(f);
        HashMap<Character, Integer> fCounts = new HashMap<>();
        HashMap<String, Integer> fMap = new HashMap<>();

        for (int i = 0; i < f.length; i++) {
            char key = f[i];
            int count = 1;
            if (fCounts.containsKey(key)) {
                count = fCounts.get(key);
                count++;
            } 
            fCounts.put(key, count);

            String indexed = key + String.valueOf(count);
            fMap.put(indexed, i);
        }
        
        char[] l = s.toCharArray();
        int[] occurance = new int[l.length];
        HashMap<Character, Integer> lCounts = new HashMap<>();

        for (int i = 0; i < l.length; i++) {
            char key = l[i];
            int count = 1;
            if (lCounts.containsKey(key)) {
                count = lCounts.get(key);
                count++;
            } 
            lCounts.put(key, count);
            occurance[i] = count;
        }

        char[] decoded = new char[l.length];
        int decoderIndex = decoded.length - 1; 
        StringBuilder sb = new StringBuilder();

        sb.append(l[0]);
        decoded[decoderIndex] = l[0];
        decoderIndex--;
        
        int counter = 0;

        while (true) {
            String key = l[counter] + String.valueOf(occurance[counter]);
            
            if (key.equals("$1")) {
                break;
            }
            
            counter = fMap.get(key);
            sb.append(l[counter]);
            decoded[decoderIndex] = l[counter];
            decoderIndex--;
                        
        }
        
        System.out.println(Arrays.toString(decoded));
        System.out.println(Arrays.toString(l));
        System.out.println(Arrays.toString(occurance));
        System.out.println(fMap);
        String result = String.valueOf(decoded);

        return result.substring(1);

    }


}
