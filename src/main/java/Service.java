import java.util.Arrays;
import java.io.IOException;

import config.Config;
import tool.Pair;
import IO.FileIO;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import compressor.RLE;


public class Service {
    private Config config;
    private FileIO io;
    private String method;
    private boolean checkCompression;
    private String content;
    private byte[] encoded;
    private String decoded;
    private String filepath;
    private String outputPath;

    public Service(Config config, String method, boolean checkCompression, String filepath) {
        this.config = config;
        this.io = new FileIO();
        this.method = method;
        this.checkCompression = checkCompression;
        this.content = new String();
        this.encoded = null;
        this.decoded = new String();
        this.filepath = filepath;
        this.outputPath = filepath + "_encoded";
    }

    public void run() {
        // read file
        readFile();

        if (this.method.equals("l")) {
            runLzss(filepath, outputPath);
        }
        
        if (this.method.equals("b")) {
            // run bwtrle
        }

        if (checkCompression) {
            // run checkCompression
        }
    }

    public void runLzss(String filepath, String outputPath) {
        LZSS lzss = new LZSS(this.config, this.io);

        System.out.println("LZSS encoding, with sliding window: ");
        
        this.encoded = lzss.encode(content);
        this.decoded = lzss.decodeBytes(this.encoded);
        
        byte[] lzssFromFile = null;
        
        System.out.println(io.compressionRatio(filepath, "_lzss_" + outputPath));
    
    }

    private void check() {

    }


    private void readFile() {
        try {
            this.content += io.readFile(this.filepath);
        } catch (Exception e) {
            System.out.println("Can not read file " + filepath);
        }
    }
    
}

/*
public class Main {

    public static void main(String[] args) {
        
        Config config = new Config();
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
            System.out.println("Invalid filepath.");
            
            
        }  else {

            if (outputPath == null) {
                outputPath = filepath + "_encoded";
            }
            System.out.println("Compressing file " + filepath + " ");
            String compressionResults = new String();

            System.out.println("*****");

            FileIO io = new FileIO();
            RLE rle = new RLE();
            BWT bwt = new BWT();
            LZSS lzss = new LZSS();

            String content = new String();

            try {
                content = io.readFile(filepath);
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }

            
            System.out.println("BWT + RLE encoding without chunks: ");
            // First we show that the encoding and decoding 
            // works when we only handle strings
            String BWTencoded = bwt.encode(content);
            Pair<char[], int[]> RLEEncoded = rle.encode(BWTencoded);

            String RLEDecoded = rle.decode(RLEEncoded);
            String BWTDecoded = bwt.decode(RLEDecoded);
            System.out.println("Double encoded string matches original: " + BWTDecoded.equals(content));
            

            // Second we show that the encoding and decoding works when we handle files
            byte[] fna = rle.toBytes(RLEEncoded);
            
            try {
                io.writeByteArray(fna, outputPath + "_bwtrle");
            } catch (Exception e) {
                
            }

            byte[] doubleEncoded = null;
            try {
                doubleEncoded = io.readByteArray(outputPath + "_bwtrle");
            } catch (Exception e) {
                System.out.println(e);
            }


            Pair<char[], int[]> doubleEncodedA = rle.fromBytes(doubleEncoded);

            String RLEDecodedA = rle.decode(doubleEncodedA);
            String BWTDecodedA = bwt.decode(RLEDecodedA);
            System.out.println("Double encoded from file matches original: " + BWTDecodedA.equals(content));
            
            try {
                io.writeFile(BWTDecodedA, filepath + "_bwtrle_restored");    
            } catch (Exception e) {

            }

            System.out.println("Decoded written to file matches original file: " + io.compareFiles(filepath, filepath + "_bwtrle_restored"));
            
            // And finally we look at the compression rate
            compressionResults = io.compressionRatio(filepath, outputPath + "_bwtrle");
            System.out.println(compressionResults);
        
            System.out.println("*****");
            
            System.out.println("LZSS encoding, no sliding window: ");
            String s = new String();
            
            byte[] lzssEncoded = lzss.encode(content);
            String lzssDecoded = lzss.decodeBytes(lzssEncoded);
            
            byte[] lzssFromFile = null;
            
            try{ 
                io.writeByteArray(lzssEncoded, outputPath + "_lzss");
                lzssFromFile = io.readByteArray(outputPath + "_lzss");
            } catch (IOException e) {
                
            }

            String lzzsDecodedFromFile = lzss.decodeBytes(lzssFromFile);

            System.out.println("Decoded from file matches original: " + content.equals(lzzsDecodedFromFile));
            
            try {
                io.writeFile(lzzsDecodedFromFile, filepath + "_lzss_restored");    
            } catch (Exception e) {
                
            }
            

            System.out.println("Decoded written to file matches original file: " + io.compareFiles(filepath, filepath + "_lzss_restored"));
            
            // And finally we look at the compression rate
            compressionResults = io.compressionRatio(filepath, "_lzss_" + outputPath);
            System.out.println(compressionResults);


            System.out.println("*****");
            
            
            // BWT+RLE in chunks
            String flaa = new String();
            //long genome
            flaa = "poem.txt";
            // short example
            //flaa = "one.txt";
            String tolstoy = new String();
            try {
                tolstoy = io.readFile(flaa);
            } catch (Exception e) {
                System.out.println("Can not read file " + filepath);
            }

            BWTRLE bwtRle = new BWTRLE(config);
            ArrayList<Pair<char[], int[]>> bwtRleEncoded = bwtRle.encode(tolstoy);

            int size = 0;
            for (Pair<char[], int[]> pair : bwtRleEncoded) {
                size += rle.toBytes(pair).length;
            }

            String doubleEncodedDecoded = bwtRle.decode(bwtRleEncoded);
            System.out.println("Chunk encoding - decoding works with strings: " + tolstoy.equals(doubleEncodedDecoded));
            System.out.println("String encoding ratio: " + (tolstoy.length() / (float) size));
        



        }
    }
} */