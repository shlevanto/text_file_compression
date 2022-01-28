public class Main {

    public static void main(String[] args){
        String filepath = null;
        String method = null;
        String direction = null;
        String outputPath = null;
        
      
        try {
            filepath = args[0];
        }
        catch (Exception e) {}

        try {
            method = args[1];
        }
        catch (Exception e) {}

        try {
            direction = args[2];
        }
        catch (Exception e) {}
        try {
            outputPath = args[3];
        }
        catch (Exception e) {
            outputPath = filepath + "_encoded";
        }
        
        if (filepath == null) {
            System.out.println("instructions");
        }

        else if (filepath.equals("demo")) {
            System.out.print("Running demo ");
        }

        else {
            System.out.println("Compressing file " + filepath + " ");
            
            FileIO io = new FileIO();
            RLE rle = new RLE(io);
            rle.encode(filepath, outputPath);

            String a = io.compressionRatio(filepath, filepath);
            System.out.println(a);
            
            // This has to be reworked
            
            String decoded = rle.decode(outputPath);
            String original = "";

            try {
                original = io.readFile(filepath);
            } catch(Exception e) {
                System.out.println(e);
            }

            System.out.println("decoded matches original: " + decoded.equals(original));


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
            
        }
}