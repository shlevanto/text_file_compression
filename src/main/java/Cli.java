import java.util.Arrays;
import java.io.IOException;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import config.Config;
import tool.Pair;
import IO.FileIO;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import compressor.RLE;


public class Cli implements Runnable{
    private Config config;
    private FileIO io;

    public Cli(Config config) {
        this.config = config;
        this.io = new FileIO();
    }
    
    @Parameters(
        index = "0",
        description = "The file to compress."
    )
    private String filepath;

    @Option(names = {"-v", "--verify"}, description = "verifies that the decompression matches original content")
    private boolean checkCompression = false;

    @Option(names = {"-m", "--method"}, description = "compression method: 'l' for LZSS or 'b' for BWT + RLE", required = true)
    private String method;

    @Option(
        names = {"-h", "--help"},
        usageHelp = true,
        description = "displays a help message"
    )
    private boolean helpRequested = false;

    @Override
    public void run() {
        String outputPath = filepath + "_encoded";

        boolean canRun = method.equals("l") | method.equals("b");
        if (!canRun) {
            System.out.println("Invalid compression method argument.");
            return;
        }
        
        String content = new String();
        
        try {
            content += io.readFile(filepath);
        } catch (Exception e) {
            System.out.println("Can not read file " + filepath);
        }


        if (method.equals("l")) {
            // run lzss
            LZSS lzss = new LZSS(this.config, this.io);

            System.out.println("LZSS encoding, with sliding window: ");
            
            byte[] lzssEncoded = lzss.encode(content);
            String lzssDecoded = lzss.decodeBytes(lzssEncoded);
            
            byte[] lzssFromFile = null;
            
            // And finally we look at the compression rate
            System.out.println(io.compressionRatio(filepath, "_lzss_" + outputPath));
        

            if (checkCompression) {
                
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
            
            }
        }

        if (method.equals("b")) {
            // run bwt + rle
            if (checkCompression) {
                // run check
            }
        }
    }

}
