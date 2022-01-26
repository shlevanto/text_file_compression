import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        String filepath = null;
        String method = null;
        String enhance = null;
      
        try {
            filepath = args[0];
        }
        catch (Exception e) {}

        try {
            method = args[1];
        }
        catch (Exception e) {}

        try {
            enhance = args[2];
        }
        catch (Exception e) {}
        
        if (filepath == null) {
            System.out.println("instructions");
        }

        else if (filepath.equals("demo")) {
            System.out.print("Running demo ");
        }

        else {
            System.out.println("Compressing file " + filepath + " ");
            
            // read file to string
            Path fileName = Path.of(filepath);
            String content = "";
            try {
                content = Files.readString(fileName);
            } catch (Exception e) {

            }
                       
            RLE rle = new RLE();
            Pair<char[], int[]> result = rle.encode(content);

            char[] chars = result.getFirst();
            int[] counts = result.getSecond();
            
            // write the compressed string and counts to file
           
            try {
                FileWriter writer = new FileWriter("output.rle");
                writer.write(chars); 
                
                writer.write("--\n");
                for (int i : counts) {
                    writer.write(Integer.toString(i) + ",");
                }
                writer.close();
            } catch (Exception e) {

            }

            try {
                double originalBytes = Files.size(Path.of(filepath));
                double compressedBytes = Files.size(Path.of("output.rle"));
                System.out.println("original size: " + originalBytes);
                System.out.println("compressed size: " + compressedBytes);
                double ratio = compressedBytes / originalBytes;
                System.out.println("compression ratio: " + String.format("%,.2f", ratio));
            } catch (Exception e) {

            }

            // read the output file for decoding
            // read file to string
            Path fileName2 = Path.of("output.rle");
            String content2 = "";
            try {
                content2= Files.readString(fileName2);
            } catch (Exception e) {

            }

            String[] parts = content2.split("--");

            String encodedContent = parts[0];

            // check that the encoded string read from the output file matches the string
            // that was written when encoding
            System.out.println("chars match: " + encodedContent.equals(String.valueOf(chars)));
            
            String encodedCountStrings = parts[1].trim();
            String[] encodedCountArray = encodedCountStrings.split(",");
            
            int[] encodedCounts = new int[encodedContent.length()];
            
            for (int i = 0; i < encodedCounts.length; i++) {
                encodedCounts[i] = Integer.valueOf(encodedCountArray[i]);

            }
            
            boolean countMatch = true;

            for (int i = 0; i < encodedCounts.length; i++) {
                if (counts[i] != encodedCounts[i]) {
                    countMatch = false;
                    System.out.println(countMatch);
                    break;
                }
            }
            System.out.println("counts match: " + countMatch);



            String decoded = rle.decode(encodedContent, encodedCounts);
            System.out.println(decoded.equals(content));
        }

        if (method != null) {
            if (method.equals("lzss")) {
                System.out.print("using LZSS ");
            }
        
            else if (method.equals("rle")) {
                System.out.print("using RLE ");
            }
        
            else {
                System.out.println("what? ");
            }
        } 
            
            if (enhance != null) {
                System.out.println("enhanced with BWT");
            }
        }
}