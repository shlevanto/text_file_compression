import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import picocli.CommandLine;

import config.Config;
import tool.Pair;
import IO.FileIO;
import compressor.BWT;
import compressor.BWTRLE;
import compressor.LZSS;
import compressor.RLE;

/**
 * Main reads the config.properties and starts the command line interface.
 */
public class Main {

    public static void main(String[] args) {
        
        Config config = new Config();
        CommandLine.run(new Cli(config), args);
    }
        
}