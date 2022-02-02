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
            rle.encode(filepath, outputPath);

                       
            String rleDecoded = rle.decode(outputPath);
            String original = "";

            try {
                original = io.readFile(filepath);
            } catch(Exception e) {
                System.out.println(e);
            }

            System.out.println("RLE decoded matches original: " + rleDecoded.equals(original));

            BWT bwt = new BWT(io);
            
            bwt.slowEncode(filepath, "BWT_" + outputPath);
            String bwtDecoded = bwt.slowDecode("BWT_" + outputPath);

            System.out.println("BWT decoded matches original: " + bwtDecoded.equals(original)); 

            System.out.println("And now combined BWT + RLE");

            rle.encode("BWT_" + outputPath, "COMBO_" + outputPath);
            String comboDecoded_a = rle.decode("COMBO_" + outputPath);
            try{
                io.writeFile(comboDecoded_a, "TEMP");
            } catch (Exception e) {

            }
            String comboDecoded = bwt.slowDecode("TEMP");

            System.out.println("COMBO decoded matches original: " + comboDecoded.equals(original)); 

            System.out.println(io.compressionRatio(filepath, "COMBO_" + outputPath));

            System.out.println(bwt.encode("banana"));
            System.out.println(bwt.slowDecode("short.txt"));
            

        }
    }
}