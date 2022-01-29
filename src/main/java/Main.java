public class Main {

    public static void main(String[] args){
        String filepath = null;
              
        try {
            filepath = args[0];
        }
        catch (Exception e) {

        }
        
        if (filepath == null) {
            System.out.println("Invalid filepath.");
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

}