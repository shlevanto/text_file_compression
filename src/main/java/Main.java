import java.util.Arrays;

public class Main {

    public static void main(String[] args){
        
        String filepath = null;
        String outputPath = null;
        
        try {
            filepath = args[0];
        }
        catch (Exception e) {

        }
        try {
            outputPath = args[1];
        }
        catch (Exception e) {

        }

        
        if (filepath == null) {
            System.out.println("Invalid filepath.");
        }
        
        if (outputPath == null) {
            outputPath = filepath + "_output";
        }

        else {
            System.out.println("Compressing file " + filepath + " ");
            
            FileIO io = new FileIO();
            RLE rle = new RLE(io);
            BWT bwt = new BWT();

            String content = new String();

            try {
                content = io.readFile(filepath);
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }

            String encoded = bwt.encode(content);
            String decoded = bwt.decode(encoded);
            System.out.println("Optimized BWT encoding works: " + decoded.equals(content));

            
                                   

        }
    }
}