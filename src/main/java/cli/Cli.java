package cli;

import java.io.IOException;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import config.Config;
import service.Service;

/**
 * Command line interface.
 */
public class Cli implements Runnable {
    /**
     * Configs are injected from Main.
     */
    private Config config;

    /**
     * @param config injected, reads from the config.properties.
     */
    public Cli(Config config) {
        this.config = config;
    }
    
    /**
     * File name parameter.
     */
    @Parameters(
        index = "0",
        description = "The file to compress."
    )
    private String filepath;

    /**
     * Option to choose if verification is run on encoding.
     */
    @Option(
        names = {"-v", "--verify"}, 
        description = "decompresses the file and verifies that the decompression matches original content"
        )
    private boolean checkCompression = false;

    /**
     * Option to decompress a file.
     */
    @Option(
        names = {"-d", "--decompress"},
        description = "decompress the given file using chosen method"
    )
    private boolean decompress = false;

    /**
     * Compression method.
     */
    @Option(
        names = {"-m", "--method"}, 
        description = "compression method: 'l' for LZSS or 'b' for BWT + RLE", 
        required = true
        )
    private String method;

    /**
     * Option to choose if BWT transformed string is shown.
     */
    @Option(
        names = {"--showbwt"}, 
        description = "displays the BWT transformation of the input"
        )
    private boolean showBwt = false;

    /**
     * Dispalys help message.
     */
    @Option(
        names = {"-h", "--help"},
        usageHelp = true,
        description = "displays a help message"
    )
    private boolean helpRequested = false;

    /**
     * Runs the application service.
     */
    @Override
    public void run() {
        boolean canRun = method.equals("l") | method.equals("b");
        if (!canRun) {
            System.out.println("Invalid compression method argument.");
            return;
        } 

        try {
            Service service = new Service(config, decompress, method, showBwt, checkCompression, filepath);    
            service.run();
        } catch (IOException e) {
            
        }
        
    }

}
