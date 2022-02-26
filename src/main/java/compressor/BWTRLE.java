package compressor;

import java.util.Arrays;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;

import config.Config;

/**
 * Combines BWT + RLE and handles the file in chunks.
 * chunk size can be set in config.properties.
 */
public class BWTRLE {
    /**
     * A BWT transformation.
     */
    private BWT bwt;
    /**
     * A RLE encoder.
     */
    private RLE rle;
    /**
     * Size of the chunks.
     */
    private int chunkSize;
    /**
     * properties contains chunksize.
     */
    private Config properties;

    /**
     * @param properties injected from class calling this class.
     */
    public BWTRLE(Config properties) {
        this.bwt = new BWT();
        this.rle = new RLE();
        this.chunkSize = properties.getBwtChunkSize();
    }

    /**
     * @param s the string to encode
     * @return encoded input as a byte array
     */
    public byte[] encode(String s) {
        
        int start = 0;
        int noOfChunks = s.length() / this.chunkSize;
        
        ArrayList<String> chunks = new ArrayList<>();

        // add full-sized chunks to list
        for (int i = 0; i < noOfChunks; i++) {
            int end = start + this.chunkSize;
            String part = s.substring(start, end);
            chunks.add(part);
            start += this.chunkSize;
        }
        
        // and remainder
        chunks.add(s.substring(start));

        // first BWT and then RLE all chunks
        ArrayList<byte[]> encodedChunks = new ArrayList<>();

        for (String chunk : chunks) {
            String bwtEncoded = this.bwt.transform(chunk);
            byte[] doubleEncoded = this.rle.encode(bwtEncoded);
            encodedChunks.add(doubleEncoded);
        }

        return toBytes(encodedChunks);
    }

    /**
     * Decodes a byte array to String.
     * @param encodedChunks a byte array of double encoded chunks
     * @return the double decoded string
     */
    public String decode(byte[] encodedChunks) {
        ArrayList<byte[]> chunks = new ArrayList();

        int start = 0;
        int noOfChunks = encodedChunks.length / this.chunkSize;
        System.out.println("Number of complete chunks: " + noOfChunks);
        
        // add full-sized chunks
        for (int i = 0; i < noOfChunks; i++) {
            int end = start + this.chunkSize;
            byte[] part = Arrays.copyOfRange(encodedChunks, start, end);
            chunks.add(part);    
            System.out.println("This shouldn't run.");
            start += this.chunkSize;
        }
        
        // and remainder
        chunks.add(Arrays.copyOfRange(encodedChunks, start, encodedChunks.length));    
        
        System.out.println(chunks.size());

        // Decode tostring
        StringBuilder sbChunksDecoded = new StringBuilder();
        
        for (byte[] chunk : chunks) {
            System.out.println(chunk.length);
            // rle decode each chunk
            String rleDecoded = rle.decode(chunk);
            // bwt decode each chunk
            String bwtDecoded = bwt.restore(rleDecoded);
            // append to string
            sbChunksDecoded.append(bwtDecoded);
        } 

        return sbChunksDecoded.toString();
    }

    /**
     * Helper method. Combines the encoded chunks to one byte array.
     * @param encodedChunks encoded chunks in a list
     * @return all chunks combined to one array
     */
    private byte[] toBytes(ArrayList<byte[]> encodedChunks) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        for (byte[] chunk : encodedChunks) {
            try {
                bos.write(chunk);
            } catch (Exception e) {

            }   
        }
        try {
            bos.close();    
        } catch (Exception e) {

        }   
        return bos.toByteArray();
    }
}
