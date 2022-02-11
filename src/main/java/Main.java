/**
 * The application doesn't have UI yet, so it is run from the Class at the moment.
 */
public class Main {

    public static void main(String[] args) {
        
        String filepath = null;
        String outputPath = null;
        
        try {
            filepath = args[0];
        } catch (Exception e) {

        }
        try {
            outputPath = args[1];
        } catch (Exception e) {

        }

        
        if (filepath == null) {
            System.out.println("Invalid filepath.");
        }  else {

            if (outputPath == null) {
                outputPath = filepath + "_encoded";
            }

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

            // First we show that the encoding and decoding 
            // works when we only handle strings
            String BWTencoded = bwt.encode(content);
            Pair<char[], int[]> RLEEncoded = rle.encode(BWTencoded);

            String RLEDecoded = rle.decode(RLEEncoded);
            String BWTDecoded = bwt.decode(RLEDecoded);
            System.out.println("Double encoded string matches original: " + BWTDecoded.equals(content));
            

            // Second we show that the encoding and decoding works when we handle files
            ArrayList<byte[]> fna = rle.toByteArrayList(RLEEncoded);
            try {
                io.writeByteArray(fna, outputPath);
            } catch (Exception e) {
                
            }

            byte[] doubleEncoded = null;
            try {
                doubleEncoded = io.readByteArray(outputPath);
            } catch (Exception e) {
                System.out.println(e);
            }

            Pair<char[], int[]> doubleEncodedA = rle.fromByteArray(doubleEncoded);

            String RLEDecodedA = rle.decode(doubleEncodedA);
            String BWTDecodedA = bwt.decode(RLEDecodedA);
            System.out.println("Double encoded from file matches original: " + BWTDecodedA.equals(content));
            
            
            // And finally we look at the compression rate
            String compressionResults = io.compressionRatio(filepath, outputPath);
            System.out.println(compressionResults);

            Pair<char[], int[]> foo = rle.encode("aaabba");
            Pair<char[], int[]> bar = rle.encode("aaabba");

            System.out.println(foo.equals(bar));

        }
    }
}