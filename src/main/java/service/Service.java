package service;

import java.io.IOException;

import config.Config;
import io.FileIO;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import compressor.RLE;

// for debugging
import java.util.Arrays;


public class Service {
    /**
     * Config injected from Main.
     */
    private Config config;
    /**
     * Handles file operations.
     */
    private FileIO io;
    /**
     * Compression method, comes from Cli.
     */
    private String method;
    /**
     * Option: do we want to check that the compression was valid?
     * Checks if decompressed matches original.
     */
    private boolean checkCompression;
    /**
     * Content to be encoded.
     */
    private String content;
    /**
     * Stores the encoded content.
     */
    private byte[] encoded;
    /**
     * Stores the decoded content.
     */
    private String decoded;
    /**
     * Filepath that content is read from.
     */
    private String filepath;
    /**
     * Filepath that encoded text is saved to.
     */
    private String outputPath;

    public Service(Config config, String method, boolean checkCompression, String filepath) throws IOException {
        this.config = config;
        this.io = new FileIO();
        this.method = method;
        this.checkCompression = checkCompression;
        this.content = new String();
        this.encoded = null;
        this.decoded = new String();
        this.filepath = filepath;
    }

    /**
     * Starts the service. Reads the file and runs the encoding 
     * given from cli options.
     */
    public void run() {
        // read file
        readFile();

        if (this.method.equals("l")) {
            runLzss();
        }
        
        if (this.method.equals("b")) {
            runBwtRle();
        }

        System.exit(0);
    }

    /**
     * Runs the LZSS encoding.
     */
    public void runLzss() {
        LZSS lzss = new LZSS(this.config);
        String outputPath = this.filepath + "_lzss";
        
        System.out.println("LZSS encoding, with sliding window size " 
            + this.config.getLzssBufferSize() 
            + " and token size " 
            + this.config.getLzssTokenSize());
        
        this.encoded = lzss.encode(this.content);
        this.decoded = lzss.decode(this.encoded);

        writeFile(outputPath);
        
        System.out.println(this.io.compressionRatio(this.filepath, outputPath));

        if (this.checkCompression) {
            check(outputPath);
        }
    
    }

    /**
     * Runs the BWT + RLE encoding.
     */
    public void runBwtRle() {
        BWT bwt = new BWT();
        RLE rle = new RLE();
        
        BWTRLE bwtrle = new BWTRLE(this.config);
        String outputPath = this.filepath + "_bwtrle";
        
        System.out.println("BWT + RLE compression, with chunk size " + this.config.getBwtChunkSize());

        this.encoded = bwtrle.encode(this.content);
        this.decoded = bwtrle.decode(this.encoded);
        
        writeFile(outputPath);

        System.out.println(io.compressionRatio(this.filepath, outputPath));
        
        if (this.checkCompression) {
            check(outputPath);
        }
    }


    private void check(String outputPath) {
        
        System.out.println("Decoded string matches original string: " + this.content.equals(this.decoded));
    }


    private void readFile() {
        try {
            this.content += io.readFile(this.filepath);
        } catch (Exception e) {
            System.out.println("Can not read file " + filepath);
        }
    }

    private void writeFile(String outputPath) {
        try {
            io.writeByteArray(this.encoded, outputPath);
        } catch (Exception e) {
            System.out.println("Can not write file " + outputPath);
        }
    }
 
}
