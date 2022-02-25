package compressor;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.io.ByteArrayOutputStream;

import tool.Pair;
import config.Config;

public class BWTRLE {
    
    private BWT bwt;
    private RLE rle;
    private int chunkSize;
    private Config properties;

    public BWTRLE(Config properties) {
        this.bwt = new BWT();
        this.rle = new RLE();
        this.chunkSize = properties.getBwtChunkSize();
    }

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

        // BWT encode all chunks
        ArrayList<byte[]> encodedChunks = new ArrayList<>();

        for (String chunk : chunks) {
            String bwtEncoded = this.bwt.encode(chunk);
            byte[] doubleEncoded = this.rle.encode(bwtEncoded);
            encodedChunks.add(doubleEncoded);
        }

        return toBytes(encodedChunks);
    }

    public String decode (byte[] encodedChunks) {
        // partition to chunks according to chunkSize
        // rle decode each chunk
        // bwt decode each chunk
        
        // And make a string
        StringBuilder sbChunksDecoded = new StringBuilder();

        return "";
    }

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
