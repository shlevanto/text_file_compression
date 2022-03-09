package performance;

import java.util.Random;
import java.nio.charset.StandardCharsets;

import compressor.*;
import config.Config;
import io.FileIO;

public class Performance {
    private Config config;
    private Random rd;
    private LZSS lzss;
    private BWTRLE bwtrle;
    
    public Performance(Config config) {
        this.config = config;
        this.rd = new Random();
        this.lzss = new LZSS(config);
        this.bwtrle = new BWTRLE(config);
    }

    public void run(String content) {
        int[] sizes = {64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144 , 524288, 1048576, 2097152};

        for (int size : sizes) {
            System.out.println("LZSS: ");
            runLzss(content, size);
            System.out.println("*****");
            System.out.println("BWTRLE");
            runBwtrle(content, size);
            System.out.println("*****");

        }
    }

    private void runLzss(String content, int size) {
        String sample = sample(content, size);
        byte[] bytes = null;

        try { 
            bytes = sample.getBytes("UTF-8");
        } catch (Exception e) {

        }

        long start = System.nanoTime();
        byte[] encoded = this.lzss.encode(sample);
        long stop = System.nanoTime();

        long timeMs = (stop - start) / 1000000;

        System.out.println("Sample: " + size + " bytes.");
        System.out.println("Compression time: " + timeMs + "ms");
        System.out.println("Compression ratio: " + String.format("%,.2f", (encoded.length * 1.0 / bytes.length)));

        start = System.nanoTime();
        String decoded = this.lzss.decode(encoded);
        stop = System.nanoTime();

        timeMs = (stop - start) / 1000000;

        System.out.println("Decompression time: " + timeMs + "ms");

    }

    private void runBwtrle(String content, int size) {
        String sample = sample(content, size);
        byte[] bytes = null;

        try { 
            bytes = sample.getBytes("UTF-8");
        } catch (Exception e) {

        }

        long start = System.nanoTime();
        byte[] encoded = this.bwtrle.encode(sample);
        long stop = System.nanoTime();

        long timeMs = (stop - start) / 1000000;

        System.out.println("Sample: " + size + " bytes.");
        System.out.println("Time: " + timeMs + "ms");
        System.out.println("Compression: " + String.format("%,.2f", (encoded.length * 1.0 / bytes.length)));

        start = System.nanoTime();
        String decoded = this.bwtrle.decode(encoded);
        stop = System.nanoTime();

        timeMs = (stop - start) / 1000000;

        System.out.println("Decompression time: " + timeMs + "ms");

        
    }

    private String sample(String s, int size) {
        return s.substring(0, size);
    }

    private String randomString(int size) {
        byte[] array = new byte[size];
        for(int i = 0; i < size; ++i) {
            array[i] = (byte) (this.rd.nextInt(126) + 2);
        }
    
        String generatedString = new String(array, StandardCharsets.UTF_8);

        return generatedString;
    }

}
