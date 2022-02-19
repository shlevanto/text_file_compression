import java.util.ArrayList;
import java.util.ArrayDeque;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;

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

        ArrayList<Integer> poo = new ArrayList<>();
        poo.add(1);
        
        
        if (filepath == null) {
            // System.out.println("Invalid filepath.");

            // LZSS
            FileIO io = new FileIO();
            LZSS lzss = new LZSS();
            BWT bwt = new BWT();
            String s = new String();
            s = "SAMSUNG SAM PIF SAMSUNG BOB";
            
            String fule = "sam-i-am.txt";
        
            try {
                s = io.readFile(fule);
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }
            
            //String njim = bwt.encode(s);
            String njum = lzss.encode(s);
            System.out.println(njum);
            String njam = lzss.decode(njum);
            
            try {
               io.writeFile(njum, fule + "_encoded");
            } catch (Exception e) {
                
            }
            
        }  else {

            if (outputPath == null) {
                outputPath = filepath + "_encoded";
            }

            System.out.println("Compressing file " + filepath + " ");
            
            FileIO io = new FileIO();
            RLE rle = new RLE(io);
            BWT bwt = new BWT();
            LZSS lzss = new LZSS();

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

            String fule = "poem.txt";
            // BWT+RLE in chunks
            String tolstoy = new String();
            try {
                tolstoy = io.readFile(fule);
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }

            // split into chunks
            int chunkSize = 512;
            int start = 0;
            int noOfChunks = tolstoy.length() / chunkSize;
            
            ArrayList<String> chunks = new ArrayList<>();

            // add full chunks
            for (int i = 0; i < noOfChunks; i++) {
                int end = start + chunkSize;
                String k = tolstoy.substring(start, end);
                chunks.add(k);
                start += chunkSize;
            }
            
            // and remainder
            chunks.add(tolstoy.substring(start));

            // BWT encode all chunks and combine to one string
            StringBuilder chunkSb = new StringBuilder();

            for (String chunk : chunks) {
                chunkSb.append(bwt.encode(chunk));
            }

            // RLE encode this string
            Pair<char[], int[]> chunkedRle = rle.encode(chunkSb.toString());

            // Save to file
            ArrayList<byte[]> chunkToFile = rle.toByteArrayList(chunkedRle);

            try {
                io.writeByteArray(chunkToFile, fule + "_encoded");
            } catch (Exception e) {

            }
            

        



        }
    }
}