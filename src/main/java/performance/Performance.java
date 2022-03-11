package performance;

import compressor.LZSS;
import compressor.BWTRLE;
import config.Config;

public class Performance {
    /**
     * Config injected from service.
     */
    private Config config;
    /**
     * LZSS compressor.
     */
    private LZSS lzss;
    /**
     * BWTRLE compressor.
     */
    private BWTRLE bwtrle;
    
    /**
     * @param config for compressors
     */
    public Performance(Config config) {
        this.config = config;
        this.lzss = new LZSS(config);
        this.bwtrle = new BWTRLE(config);
    }

    /**
     * Runs the performance tests in one go.
     * @param content the file to encode read into a string
     */
    public void run(String content) {
        int[] sizes = new int[16];

        for (int i = 0; i < sizes.length; i++) {
            int power = i + 6;
            sizes[i] = 1 << power; 
        }



        for (int size : sizes) {
            System.out.println("LZSS: ");
            runLzss(content, size);
            System.out.println("*****");
            System.out.println("BWTRLE");
            runBwtrle(content, size);
            System.out.println("*****");
        }
    }

    /**
     * Runs the performance test for LZSS.
     * @param content the content used for tes
     * @param size sample size
     */
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
        System.out.println("Compression time: " + String.format("%,.2f", (double) timeMs) + "ms");
        System.out.println("Compression ratio: " + String.format("%,.2f", (encoded.length * 1.0 / bytes.length)));

        start = System.nanoTime();
        String decoded = this.lzss.decode(encoded);
        stop = System.nanoTime();

        timeMs = (stop - start) / 1000000;

        System.out.println("Decompression time: " + String.format("%,.2f", (double) timeMs) + "ms");

    }

    /**
     * Runs the performance test for BWTRLE.
     * @param content the content used for tes
     * @param size sample size
     */
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
        System.out.println("Compression time: " + String.format("%,.2f", (double) timeMs) + "ms");
        System.out.println("Compression: " + String.format("%,.2f", (encoded.length * 1.0 / bytes.length)));

        start = System.nanoTime();
        String decoded = this.bwtrle.decode(encoded);
        stop = System.nanoTime();

        timeMs = (stop - start) / 1000000;

        System.out.println("Decompression time: " + String.format("%,.4f", (double) timeMs) + "ms");

        
    }

    /**
     * Takes a sample from the content to encode.
     * @param s the string to sample
     * @param size sample size
     * @return the sample
     */
    private String sample(String s, int size) {
        return s.substring(0, size);
    }

}
