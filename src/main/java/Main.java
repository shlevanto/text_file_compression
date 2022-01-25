import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.io.FileOutputStream;
import java.io.FileWriter;


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
            //System.out.println("instructions");
            
            // read file to string
            Path fileName = Path.of("txt");
            String content = "";
            try {
                content = Files.readString(fileName);
            } catch (Exception e) {

            }
            
            
            RLE rle = new RLE(content);
            Pair result = rle.encode();

            char[] chars = result.getChars();
            int[] counts = result.getCounts();

        
            //System.out.println(chars);
            //System.out.println(Arrays.toString(counts));

            // write the compressed string and counts to file
           
            try {
                FileWriter writer = new FileWriter("txtoutput.txt");
                writer.write(chars);
                writer.write(Arrays.toString(counts));
                writer.close();
            } catch (Exception e) {

            }

            try {
                long originalBytes = Files.size(Path.of("txt"));
                long compressedBytes = Files.size(Path.of("txtoutput.txt"));
                System.out.println("original size: " + originalBytes);
                System.out.println("compressed size: " + compressedBytes);
            } catch (Exception e) {

            }
            
            

            System.out.println(chars);
            System.out.println(Arrays.toString(counts));



    
    }

        else if (filepath.equals("demo")) {
            System.out.print("Running demo ");
        }

        else {
            System.out.print("Compressing file " + filepath + " ");
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