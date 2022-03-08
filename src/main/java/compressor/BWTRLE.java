package compressor;

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
        StringBuilder sb = new StringBuilder();

        // add full-sized chunks to list
        for (int i = 0; i < noOfChunks; i++) {
            int end = start + this.chunkSize;
            String part = s.substring(start, end);
            sb.append(this.bwt.transform(part));
            chunks.add(part);
            start += this.chunkSize;
        }
        
        // and remainder
        int isRemainder = 0;
        if (s.length() % this.chunkSize > 0) {
            isRemainder++;
        }

        chunks.add(s.substring(start));
        sb.append(this.bwt.transform(s.substring(start)));

        String chunked = sb.toString();
        byte[] encoded = this.rle.encode(chunked);
        return encoded;
    }        

    /**
     * Decodes a byte array to String.
     * @param encodedChunks a byte array of double encoded chunks
     * @return the double decoded string
     */
    public String decode(byte[] encodedChunks) {
        ArrayList<byte[]> chunks = new ArrayList();


        // decode RLE
        String r = rle.decode(encodedChunks);
        StringBuilder f = new StringBuilder();
        
        // partition String to chunks
        // and BWT restore each chunk
        
        int noOfEncodedChunks = r.length() / (this.chunkSize + 1); // because BWT adds one extra byte
        int start = 0;

        for (int i = 0; i < noOfEncodedChunks; i++) {
            int end = start + this.chunkSize + 1;
            String part = r.substring(start, end);
            f.append(this.bwt.restore(part));    
            start += this.chunkSize + 1;
        }
        
        // and remainder
        f.append(this.bwt.restore(r.substring(start)));


        //return sbChunksDecoded.toString();
        return f.toString();
    }
}
