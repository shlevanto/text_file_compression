package service;

import java.io.IOException;

import config.Config;
import io.FileIO;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import performance.Performance;

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
     * Decompress given file.
     */
    private boolean decompress;
    /**
     * Compression method, comes from Cli.
     */
    private String method;
    /**
     * Option: show BWT transformed string with $ indicating the end of string.
     */
    private boolean showBwt;
    /**
     * Option: do we want to check that the compression was valid?
     * Checks if decompressed matches original.
     */
    private boolean checkCompression;
    /**
     * Option: run performance test
     */
    private boolean performance;
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

    public Service(
        Config config,
        boolean decompress, 
        String method, 
        boolean showBwt, 
        boolean checkCompression, 
        String filepath,
        boolean performance
        ) throws IOException {
            this.config = config;
            this.io = new FileIO();
            this.decompress = decompress;
            this.method = method;
            this.showBwt = showBwt;
            this.checkCompression = checkCompression;
            this.content = new String();
            this.encoded = null;
            this.decoded = new String();
            this.filepath = filepath;
            this.performance = performance;
    }

    /**
     * Starts the service. Reads the file and runs the encoding 
     * given from cli options.
     */
    public void run() {
        // read file
        readFile();

        if (this.performance) {
            runPerformance();
            System.exit(0);
        }
        if (this.decompress) {
            runDecompress();
            System.exit(0);
        }

        if (this.method.equals("l")) {
            runLzss();
        }
        
        if (this.method.equals("b")) {
            runBwtRle();
        }

        if (this.showBwt) {
            showBwtBWT();
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

        writeFile(this.encoded, outputPath);
        
        System.out.println(this.io.compressionRatio(this.filepath, outputPath));

        if (this.checkCompression) {
            this.decoded = lzss.decode(this.encoded);
            check(outputPath);
        }
    }

    /**
     * Runs the BWT + RLE encoding.
     */
    public void runBwtRle() {
        BWTRLE bwtrle = new BWTRLE(this.config);
        String outputPath = this.filepath + "_bwtrle";
        
        System.out.println("BWT + RLE compression, with chunk size " + this.config.getBwtChunkSize());

        this.encoded = bwtrle.encode(this.content);
        
        writeFile(this.encoded, outputPath);

        System.out.println(io.compressionRatio(this.filepath, outputPath));
        
        if (this.checkCompression) {
            this.decoded = bwtrle.decode(this.encoded);
            check(outputPath);
        }
    }

    private void check(String outputPath) {
        System.out.println("*** Verification ***");
        
        boolean verified = this.content.equals(this.decoded);
        System.out.println("Decoded string matches original string: " + verified);
        
        outputPath += "_decompressed";
        
        try {
            this.io.writeFile(this.decoded, outputPath);
        } catch (Exception e) {
            System.out.println("Decompression failed.");
        }

        boolean filesVerified = this.io.compareFiles(this.filepath, outputPath);
        System.out.println("Decoded file matches original original file: " + filesVerified);
    }

    private void readFile() {
        try {
            this.content += io.readFile(this.filepath);
        } catch (Exception e) {
            System.out.println("Can not read file " + filepath);
        }
    }

    private void writeFile(byte[] content, String outputPath) {
        try {
            io.writeByteArray(content, outputPath);
        } catch (Exception e) {
            System.out.println("Can not write file " + outputPath);
        }
    }

    private void showBwtBWT() {
        BWT bwt = new BWT();
        String transformed = bwt.transform(this.content);
        char eos = 0;
        System.out.println("***");
        System.out.println("Original input: " + this.content);
        System.out.println("Input transformed with BWT: " + transformed.replace(eos, '|'));
        System.out.println("***");
    }

    private void runDecompress() {
        System.out.println("Decompressing " + this.filepath);
        System.out.println("***");
        
        String outputPath = this.filepath + "_decompressed";
        
        try {
            this.encoded = io.readByteArray(this.filepath);
        } catch (Exception e) {

        }

        if (this.method.equals("l")) {
            LZSS lzss = new LZSS(this.config);
            this.decoded = lzss.decode(this.encoded);
        }

        if (this.method.equals("b")) {
            BWTRLE bwtrle = new BWTRLE(this.config);
            this.decoded = bwtrle.decode(this.encoded);
        }
 
        try {
            this.io.writeFile(this.decoded, outputPath);
        } catch (Exception e) {
            System.out.println("Decompression failed.");
        }

        System.out.println("Decompression succesful.");
    }

    /**
     * Runs performance tests.
     */
    private void runPerformance() {
        System.out.println("Running performance test.");
        Performance p = new Performance(this.config);
        p.run(this.content);
    }
}
