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
        
        if (filepath == null) {
            // System.out.println("Invalid filepath.");
            // LZSS
            FileIO io = new FileIO();
            LZSS lzss = new LZSS();
            String s = new String();
            s = "is this the real life? is this just fantasy?";
            /*
            try {
                s = io.readFile("poem.txt");
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }*/
            Pair<ArrayList<Character>, ArrayDeque<Pair>> njum = lzss.encode(s);
            String njam = lzss.decode(njum);

            System.out.println(njam);
            

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

            // BWT+RLE in chunks
            String tolstoy = new String();
            try {
                tolstoy = io.readFile("tolstoy.txt");
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }

            // split into chunks
            int chunkSize = 4096;
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

            // BWT encode all chunks
            ArrayList<String> encodedChunks = new ArrayList<>();

            for (String chunk : chunks) {
                encodedChunks.add(bwt.encode(chunk));
            }

            // RLE encode all chunks
            ArrayList<Pair<char[], int[]>> doubleChunks = new ArrayList<>();

            for (String chunk : encodedChunks) {
                doubleChunks.add(rle.encode(chunk));
            }

            // write to file
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(doubleChunks);
                oos.close();
                byte[] data = bos.toByteArray();
                ArrayList<byte[]> dataList = new ArrayList<>();
                dataList.add(data);
                io.writeByteArray(dataList, "tolstoy.txt_encoded");
            } catch (Exception e) {
                System.out.println("Exception!");
            }

            // RLE decode all chunks
            ArrayList<String> decodedDoubleChunks = new ArrayList<>();

            for (Pair<char[], int[]> pair : doubleChunks) {
                decodedDoubleChunks.add(rle.decode(pair));
            }

            StringBuilder sb = new StringBuilder();
            
            for (String chunk : decodedDoubleChunks) {
                sb.append(bwt.decode(chunk));
            }

            String chunksDecoded = sb.toString();
            System.out.println(chunksDecoded.equals(tolstoy));


        



        }
    }
}