package cli;

import java.util.Arrays;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import config.Config;
import tool.Pair;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import compressor.RLE;
import service.Service;


public class Cli implements Runnable{
    private Config config;

    public Cli(Config config) {
        this.config = config;
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
        boolean canRun = method.equals("l") | method.equals("b");
        if (!canRun) {
            System.out.println("Invalid compression method argument.");
            return;
        } 

        Service service = new Service(config, method, checkCompression, filepath);    
        service.run();
        
    }

}
