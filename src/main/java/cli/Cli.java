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
        description = "verifies that the decompression matches original content"
        )
    private boolean checkCompression = false;

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
        names = {"--verbose"}, 
        description = "prints the BWT transformed string"
        )
    private boolean verbose;

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
            Service service = new Service(config, method, verbose, checkCompression, filepath);    
            service.run();
        } catch (IOException e) {
            
        }
        
    }

}
